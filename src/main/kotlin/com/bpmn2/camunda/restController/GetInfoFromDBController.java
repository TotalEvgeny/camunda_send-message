package com.bpmn2.camunda.restController;

import com.bpmn2.camunda.entity.usersmanagement.Users;
import com.bpmn2.camunda.repository.usersmanagement.UsersRepository;
import com.bpmn2.camunda.service.usersmanagement.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetInfoFromDBController {
    private final UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @Autowired
    GetInfoFromDBController(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @RequestMapping("/foobar/{id}")
    public String userName(@PathVariable("id") Long id) {
        Users user = userService.findById(id);

        return user.getName();
    }
}
