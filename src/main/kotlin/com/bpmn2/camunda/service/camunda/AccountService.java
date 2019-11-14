package com.bpmn2.camunda.service.camunda;

import com.bpmn2.camunda.entity.camunda.Account;
import com.bpmn2.camunda.repository.camunda.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    };

    public void createAccount(Account account){
        accountRepository.save(account);
    }

    public Account findById(UUID accountId){
        return accountRepository.findById(accountId).orElse(null);
    }
}
