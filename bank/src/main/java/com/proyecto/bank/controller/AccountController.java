package com.proyecto.bank.controller;

import com.proyecto.bank.service.dto.AccountDTO;
import com.proyecto.bank.controller.request.AccountRequest;
import com.proyecto.bank.service.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping
    public ResponseEntity<List<AccountDTO>> listAccounts(){
        return new ResponseEntity<>(accountService.listAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> findAccount(@PathVariable Integer accountId){
        return new ResponseEntity<>(accountService.findAccount(accountId), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountRequest accountRequest){
        return new ResponseEntity<>(accountService.createAccount(accountRequest), HttpStatus.CREATED);
    }
    @PutMapping("/{accountId}")
    public ResponseEntity<AccountDTO> replaceAccount(@RequestBody AccountRequest accountRequest, @PathVariable Integer accountId){
        return new ResponseEntity<>(accountService.replaceAccount(accountRequest, accountId), HttpStatus.OK);
    }

    @PatchMapping("/{accountId}")
    public ResponseEntity<AccountDTO> updateAccount(@RequestBody Map<String, Object> accountData, @PathVariable Integer accountId){
        return new ResponseEntity<>(accountService.updateAccount(accountData, accountId), HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer accountId){
        try{
            accountService.deleteAccount(accountId);
            return ResponseEntity.ok().build();
        }catch(EmptyResultDataAccessException e){
            return ResponseEntity.noContent().build();
        }
    }

}
