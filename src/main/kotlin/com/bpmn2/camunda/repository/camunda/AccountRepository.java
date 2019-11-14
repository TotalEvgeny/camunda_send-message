package com.bpmn2.camunda.repository.camunda;

import com.bpmn2.camunda.entity.camunda.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

}
