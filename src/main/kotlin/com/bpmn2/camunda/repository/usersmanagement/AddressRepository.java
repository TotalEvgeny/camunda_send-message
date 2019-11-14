package com.bpmn2.camunda.repository.usersmanagement;

import com.bpmn2.camunda.entity.usersmanagement.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
