package com.bpmn2.camunda.service.camunda;

import com.bpmn2.camunda.entity.camunda.Request;
import com.bpmn2.camunda.repository.camunda.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RequestService {
    @Autowired
    private final RequestRepository requestRepository;

    public RequestService(RequestRepository requestRepository){
        this.requestRepository = requestRepository;
    }

    public void createRequest(Request request){
        requestRepository.save(request);
    }

    public List<Request> findAll(){
        return requestRepository.findAll();
    }

    public Request findById(UUID requestId){
        return requestRepository.findById(requestId).orElse(null);
    }

    public List<Request> findAllByName(String name){
        return requestRepository.findAllByName(name);
    }

    public List<Request> findQueryByName(){
        return requestRepository.findQueryByName();
    }
}
