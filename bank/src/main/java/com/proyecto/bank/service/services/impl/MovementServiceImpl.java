package com.proyecto.bank.service.services.impl;

import com.proyecto.bank.exceptions.*;
import com.proyecto.bank.persistence.entity.Movement;
import com.proyecto.bank.persistence.repository.MovementRespository;
import com.proyecto.bank.service.services.ClientService;
import com.proyecto.bank.service.services.AccountService;
import com.proyecto.bank.service.services.MovementService;
import com.proyecto.bank.service.dto.AccountDTO;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.dto.MovementDTO;
import com.proyecto.bank.controller.request.MovementRequest;
import com.proyecto.bank.service.mapper.MovementMapper;
import com.proyecto.bank.utils.enums.MovementTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

import static java.lang.Math.abs;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    MovementRespository movementRespository;

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    MovementMapper movementMapper;

    public MovementDTO createMovement(MovementRequest movementRequest){
        Optional<ClientDTO> clienteRecuperado = Optional.ofNullable(clientService.findClientByIdentification(movementRequest.getIdentificacionCliente()));

        Optional<AccountDTO> cuentaRecuperada = Optional.ofNullable(accountService.findByAccountNumber(movementRequest.getNumeroCuenta()));

        if(habilitadoMovimiento(cuentaRecuperada, movementRequest.getValue(), clienteRecuperado, movementRequest.getIdentificacionCliente(), movementRequest.getNumeroCuenta())) {
            Movement newMovement = new Movement(cuentaRecuperada.get().getId(), cuentaRecuperada.get().getBalance(), textoMovimiento(movementRequest.getValue()), textoTipoMovimiento(movementRequest.getValue()), movementRequest.getValue(), movementRequest.getActivity());
            Map<String, Object> accountData = new HashMap<>();
            accountData.put("balance", cuentaRecuperada.get().getBalance() + movementRequest.getValue());
            accountService.updateAccount(accountData, cuentaRecuperada.get().getId());
            return movementMapper.INSTANCE.movementToMovementDTO(movementRespository.save(newMovement));
        }
        return null;
    }

    private boolean habilitadoMovimiento(Optional<AccountDTO> cuenta, int valor, Optional<ClientDTO> cliente, String identificacionCliente, String numeroCuenta) {
        if(!cliente.isPresent()){
            throw new ClientNotFoundException(identificacionCliente);
        }
        if(!cuenta.isPresent()){
            throw new AccountNotFoundException(numeroCuenta);
        }
        if(cuenta.get().getBalance() == 0 && valor < 0){
            throw new AccountWithNoBalanceException(cuenta.get().getNumber());
        }

        return textoTipoMovimiento(valor).equals(MovementTypeEnum.Withdrawal.code())||textoTipoMovimiento(valor).equals(MovementTypeEnum.Credit.code());
    }

    private String textoTipoMovimiento(int valor) {
        return valor>0?MovementTypeEnum.Credit.code():MovementTypeEnum.Withdrawal.code();
    }

    public List<MovementDTO> listMovements(){
        List<MovementDTO> listaMovimientos = new ArrayList<>();

        for (Movement movement :
                movementRespository.findAll()) {
            listaMovimientos.add(movementMapper.movementToMovementDTO(movement));
        }
        return listaMovimientos;
    }

    public MovementDTO findMovement(Integer movementId){
        Optional<Movement> movementResult = movementRespository.findById(movementId);
        if(movementResult.isPresent()){
            return movementMapper.INSTANCE.movementToMovementDTO(movementResult.get());
        }else{
            throw new MovementNotFoundException(movementId);
        }
    }

    public MovementDTO replaceMovement(MovementRequest movementRequest, Integer movementId){
        Optional<Movement> movementResult = movementRespository.findById(movementId);
        if(movementResult.isPresent()){
            Movement movement = new Movement();
            movement.setId(movementResult.get().getId());
            movement.setValue(movementRequest.getValue());
            movement.setBalance(movementResult.get().getBalance());
            movement.setDate(movementResult.get().getDate());
            movement.setAccount(movementResult.get().getAccount());
            movement.setMovementType(movementResult.get().getMovementType());
            movement.setMovement(movementResult.get().getMovement());
            return movementMapper.INSTANCE.movementToMovementDTO(movementRespository.save(movement));

        }else{
            throw new MovementNotFoundException(movementId);
        }
    }

    public MovementDTO updateMovement(Map<String, Object> movementData, Integer movementId){
        Optional<Movement> movementResult = movementRespository.findById(movementId);
        if(movementResult.isPresent()) {
            movementData.forEach((clave, valor) -> {
                Field campo = ReflectionUtils.findField(Movement.class, clave);
                campo.setAccessible(true);
                ReflectionUtils.setField(campo, movementResult.get(), valor);
            });
            return movementMapper.INSTANCE.movementToMovementDTO(movementRespository.save(movementResult.get()));
        }else{
            throw new MovementNotFoundException(movementId);
        }
    }

    public void deleteMovement(Integer movementId) {
        if(movementRespository.findById(movementId).isPresent()) {
            movementRespository.deleteById(movementId);
        }else{
            throw new MovementNotFoundException(movementId);
        }
    }


    public Boolean superaMaximoRetiroDiario(AccountDTO cuenta, int valorNuevoMovimiento){
        int maximoRetiroDiario = 1000, retiroDelDiaPorCuenta=0;

        Optional<List<Movement>> movimientosDelDia = movementRespository.findByIdCuentaAndFecha(cuenta.getId(), new Date(), MovementTypeEnum.Withdrawal.code());

        if(movimientosDelDia.isPresent()){
            for (Movement movement : movimientosDelDia.get()) {
                    retiroDelDiaPorCuenta +=  movement.getValue();
            }
        }
        if(abs(retiroDelDiaPorCuenta+valorNuevoMovimiento)> maximoRetiroDiario){
            throw new DailyWithdrawalExceededException(cuenta.getNumber());
        }

        return Boolean.FALSE;
    }

    public String textoMovimiento(int valor){
        return valor>0?"Deposito de "+valor:"Retiro de "+abs(valor);
    }
}
