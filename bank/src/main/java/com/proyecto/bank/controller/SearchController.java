package com.proyecto.bank.controller;

import com.proyecto.bank.persistence.entity.Account;
import com.proyecto.bank.service.dto.AccountReportDTO;
import com.proyecto.bank.service.dto.ClientDTO;
import com.proyecto.bank.service.dto.EmployeeDTO;
import com.proyecto.bank.service.dto.ReportDTO;
import com.proyecto.bank.service.services.AccountService;
import com.proyecto.bank.service.services.ClientService;
import com.proyecto.bank.service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/searcher")
public class SearchController {
    @Autowired
    ClientService clientService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AccountService accountService;

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping
    public ReportDTO searchClientAndEmployeeByCode(@RequestParam("code") String code){
        ReportDTO reportDTO= null;

        ClientDTO clientDTO = clientService.findClientByCode(code);

        if(clientDTO!=null){

            Account account = clientDTO.getAccounts().get(0);
            List<AccountReportDTO> accountReportDTOS = accountService.generateReportByUser(clientDTO.getIdentification());
            reportDTO = new ReportDTO(clientDTO);
            reportDTO.setAccount(account.getNumber());
            reportDTO.setDate(account.getDate());
            reportDTO.setBalance((double) accountReportDTOS.get(0).getSaldo());
            reportDTO.setMovements(account.getMovements());

        }

        return reportDTO;
    }

    @CrossOrigin(origins = "http://localhost:4200/")
    @GetMapping(value = "/employee/")
    public EmployeeDTO searchEmployeeByCode(@RequestParam("code") String code){
        String roleEmployee= null;
        EmployeeDTO employeeDTO = employeeService.findEmployeeByCode(code);

        if(employeeDTO!=null){
            return employeeDTO;
        }

        return new EmployeeDTO();
    }
}
