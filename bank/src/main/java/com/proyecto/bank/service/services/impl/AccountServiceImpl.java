package com.proyecto.bank.service.services.impl;

import com.proyecto.bank.exceptions.ClientNotFoundException;
import com.proyecto.bank.exceptions.AccountNotFoundException;
import com.proyecto.bank.persistence.entity.Account;
import com.proyecto.bank.persistence.entity.Movement;
import com.proyecto.bank.persistence.repository.AccountRespository;
import com.proyecto.bank.service.services.ClientService;
import com.proyecto.bank.service.services.AccountService;
import com.proyecto.bank.service.dto.AccountDTO;
import com.proyecto.bank.service.dto.AccountReportDTO;
import com.proyecto.bank.controller.request.AccountRequest;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

import static java.lang.Math.abs;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRespository accountRespository;

    @Autowired
    ClientService clientService;

    AccountMapper accountMapper;

    public AccountDTO createAccount(AccountRequest accountRequest){
        Optional<ClientDTO> clientResult = Optional.ofNullable(clientService.findClientByIdentification(accountRequest.getIdentificacionCliente()));

        if(clientResult.isPresent()) {
            Account newAccount = accountMapper.INSTANCE.accountRequestToAccount(accountRequest);
            newAccount.setClient(clientResult.get().getId());
            newAccount.setState(Boolean.TRUE);
            newAccount.setDate(new Date());
            return accountMapper.INSTANCE.accountToAccountDTO(accountRespository.save(newAccount));
        }else{
            throw new ClientNotFoundException(accountRequest.getIdentificacionCliente());
        }
    }

    public List<AccountDTO> listAccounts(){
        List<AccountDTO> accountsList = new ArrayList<>();
        for (Account account :
        accountRespository.findAll()) {
            accountsList.add(accountMapper.INSTANCE.accountToAccountDTO(account));
        }
        return accountsList;
    }

    @Override
    public AccountDTO findAccount(Integer accountId) {
        Optional<Account> accountResult = accountRespository.findById(accountId);
        if(accountResult.isPresent()){
            return accountMapper.INSTANCE.accountToAccountDTO(accountResult.get());
        }else{
            throw new ClientNotFoundException(accountId);
        }
    }

    public AccountDTO replaceAccount(AccountRequest accountRequest, Integer accountId){
        Optional<Account> accountResult = accountRespository.findById(accountId);
        if(accountResult.isPresent()){
            Account account = accountMapper.INSTANCE.accountRequestToAccount(accountRequest);
            account.setMovements(accountResult.get().getMovements());
            account.setState(accountResult.get().getState());
            return accountMapper.INSTANCE.accountToAccountDTO(accountRespository.save(account));
        }else{
            throw new AccountNotFoundException(accountId);
        }
    }

    public AccountDTO updateAccount(Map<String, Object> accountData, Integer accountId){
        Optional<Account> accountResult = accountRespository.findById(accountId);
        if(accountResult.isPresent()) {
            accountData.forEach((clave, valor) -> {
                Field campo = ReflectionUtils.findField(Account.class, clave);
                campo.setAccessible(true);
                ReflectionUtils.setField(campo, accountResult.get(), valor);
            });
            return accountMapper.INSTANCE.accountToAccountDTO(accountRespository.save(accountResult.get()));
        }else{
            throw new AccountNotFoundException(accountId);
        }
    }

    @Override
    public AccountDTO findByAccountNumber(String accountNumber) {
        Optional<Account> accountResult = accountRespository.findByNumber(accountNumber);
        if(accountResult.isPresent()){
            return accountMapper.INSTANCE.accountToAccountDTO(accountResult.get());
        }else{
            throw new AccountNotFoundException(accountNumber);
        }
    }

    @Override
    public List<AccountReportDTO> generateReportByDatesAndUser(Date startDate, Date endDate, String identification) {
        Optional<ClientDTO> clientResult = Optional.ofNullable(clientService.findClientByIdentification(identification));

        if(clientResult.isPresent()) {
            return calculateStateAccount(clientResult.get(), startDate, endDate);
        }else{
            throw new AccountNotFoundException(identification);
        }
    }

    @Override
    public List<AccountReportDTO> generateReportByUser(String identification) {
        Optional<ClientDTO> clientResult = Optional.ofNullable(clientService.findClientByIdentification(identification));

        if(clientResult.isPresent()) {
            return calculateStateAccount(clientResult.get());
        }else{
            throw new AccountNotFoundException(identification);
        }
    }

    private List<AccountReportDTO> calculateStateAccount(ClientDTO clientDTO, Date startDate, Date endDate){
        List<AccountReportDTO> reportAccountsList = new ArrayList<>();
        AccountReportDTO accountReportDTO;
        int withdrawalsValue = 0, creditValue=0;
        for (Account account : clientDTO.getAccounts()
        ) {
            for (Movement movement : account.getMovements()
            ) {
                if (movement.getDate().getTime() >= startDate.getTime() && movement.getDate().getTime() <= endDate.getTime() && movement.getMovementType().equals("R")) {
                    withdrawalsValue += abs(movement.getValue());
                } else {
                    creditValue += (movement.getValue());
                }
            }

            accountReportDTO = new AccountReportDTO(clientDTO.getIdentification(), clientDTO.getCode(), clientDTO.getFirstName(), clientDTO.getLastName(), account.getNumber(), account.getType(), account.getBalance(), withdrawalsValue, creditValue);
            reportAccountsList.add(accountReportDTO);
            withdrawalsValue = 0;
            creditValue = 0;
        }
        return reportAccountsList;
    }

    private List<AccountReportDTO> calculateStateAccount(ClientDTO clientDTO){
        List<AccountReportDTO> reportAccountsList = new ArrayList<>();
        AccountReportDTO accountReportDTO;
        int withdrawalsValue = 0, creditValue=0;
        for (Account account : clientDTO.getAccounts()
        ) {
            for (Movement movement : account.getMovements()
            ) {
                if (movement.getMovementType().equals("R")) {
                    withdrawalsValue += abs(movement.getValue());
                } else {
                    creditValue += (movement.getValue());
                }
            }

            accountReportDTO = new AccountReportDTO(clientDTO.getIdentification(), clientDTO.getCode(), clientDTO.getFirstName(), clientDTO.getLastName(), account.getNumber(), account.getType(), account.getBalance(), withdrawalsValue, creditValue);
            reportAccountsList.add(accountReportDTO);
            withdrawalsValue = 0;
            creditValue = 0;
        }
        return reportAccountsList;
    }

    public void deleteAccount(Integer accountId) {
        if(accountRespository.findById(accountId).isPresent()) {
            accountRespository.deleteById(accountId);
        }else{
            throw new AccountNotFoundException(accountId);
        }
    }

}
