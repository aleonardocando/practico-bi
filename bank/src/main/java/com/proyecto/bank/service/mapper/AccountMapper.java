package com.proyecto.bank.service.mapper;

import com.proyecto.bank.controller.request.AccountRequest;
import com.proyecto.bank.persistence.entity.Account;
import com.proyecto.bank.service.dto.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account accountDTOToAccount(AccountDTO accountDTO);

    AccountDTO accountToAccountDTO(Account account);

    Account accountRequestToAccount(AccountRequest accountRequest);
}
