package com.bpmn2.camunda.repository.camunda;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import com.bpmn2.camunda.entity.camunda.Request;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepository extends JpaRepository<Request, UUID> {
    List<Request> findAllByName(String name);

    @Query("select r from Request r where name like '%1%'")
    List<Request> findQueryByName();

}