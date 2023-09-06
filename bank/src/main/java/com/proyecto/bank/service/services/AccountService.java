package com.proyecto.bank.service.services;

import com.proyecto.bank.service.dto.AccountDTO;
import com.proyecto.bank.service.dto.AccountReportDTO;
import com.proyecto.bank.controller.request.AccountRequest;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface AccountService {

    AccountDTO createAccount(AccountRequest accountRequest);

    List<AccountDTO> listAccounts();

    AccountDTO findAccount(Integer accountId);

    AccountDTO replaceAccount(AccountRequest accountRequest, Integer accountId);

    AccountDTO updateAccount(Map<String, Object> accountData, Integer accountId);

    void deleteAccount(Integer accountId);

    AccountDTO findByAccountNumber(String accountNumber);

    List<AccountReportDTO> generateReportByDatesAndUser(Date startDate, Date endDate, String identification);

    List<AccountReportDTO> generateReportByUser(String identification);
}
