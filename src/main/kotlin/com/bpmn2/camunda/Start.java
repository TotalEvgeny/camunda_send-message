/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bpmn2.camunda;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import com.bpmn2.camunda.entity.camunda.Account;
import com.bpmn2.camunda.entity.camunda.Request;
import com.bpmn2.camunda.entity.usersmanagement.Address;
import com.bpmn2.camunda.entity.usersmanagement.Users;
import com.bpmn2.camunda.service.camunda.AccountService;
import com.bpmn2.camunda.service.camunda.RequestService;
import com.bpmn2.camunda.service.usersmanagement.AddressService;
import com.bpmn2.camunda.service.usersmanagement.UserService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.EventListener;
import org.camunda.bpm.extension.mail.config.MailConfiguration;
import org.camunda.bpm.extension.mail.config.MailConfigurationFactory;
import org.camunda.bpm.extension.mail.notification.MailNotificationService;
import org.camunda.bpm.extension.mail.service.MailService;
import org.camunda.bpm.extension.mail.service.MailServiceFactory;
import org.camunda.bpm.engine.variable.Variables;


@SpringBootApplication
@EnableProcessApplication
public class Start {

    @Autowired
    private RuntimeService runtimeService;

    public static void main(String... args) {
        SpringApplication.run(Start.class, args);
    }

    @EventListener
    private void processPostDeploy(PostDeployEvent event) throws Exception {
//        runtimeService.startProcessInstanceByKey("loanApproval");

        configuration = MailConfigurationFactory.getConfiguration();
        notificationService = new MailNotificationService(configuration);

        notificationService.registerMailHandler(mail -> {
            runtimeService.startProcessInstanceByKey("printProcess",
                    Variables.createVariables()
                            .putValue("mail", mail)
                            .putValue("invoice", getInvoicePath()));
        });
        notificationService.start();

        //Service JPA work with tables in this and other db
//        CallMethodJPA();
//        CallUsersManagementMethodJPA();
    }

    @Autowired
    private AccountService accountService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserService userService;

    protected void CallMethodJPA(){
        Account account = new Account();
        account.setName("Ромашка");
        accountService.createAccount(account);
//        Account account = accountService.findById(UUID.fromString("72d131c9-7af4-4aa2-a981-637369822a46"));
        Request request = new Request();
        request.setAccount(account);
        request.setName("Заявка 1");
        requestService.createRequest(request);
    }

    protected void CallUsersManagementMethodJPA(){
        Address address = new Address();
        address.setCity("Kiev");
        address.setHomeNumber("4");
        address.setStreet("Main Street");
        Address address1 = new Address();
        address1.setCity("Lviv");
        Users users = new Users();
        users.setAddress(address);
        users.setEmail("someEmail@gmail.com");
        users.setName("Smith");
        userService.createUsers(users);
        Users users1 = new Users();
        users1.setName("Jon Dorian");
        users1.setEmail("gmailEmail@gmail.com");
        users1.setAddress(address1);
        userService.createUsers(users1);
        userService.removeUsers(users1);

        userService.findAll().forEach(it-> System.out.println(it));

        userService.findAllByName("Smith").forEach(it-> System.out.println(it));

        userService.findWhereEmailIsGmail().forEach(it-> System.out.println(it));

        userService.findWhereNameStartsFromSmith().forEach(it-> System.out.println(it));
    }

    @EventListener
    private void processPostUnDeploy(PreUndeployEvent event) throws Exception {
        notificationService.stop();

        MailService mailService = MailServiceFactory.getService(configuration);
        mailService.close();
    }

    private MailConfiguration configuration;
    private MailNotificationService notificationService;

    protected String getInvoicePath() {

        URL resource = getClass().getResource("/invoice.pdf");
        if (resource == null) {
            throw new IllegalStateException("Cannot found invoice file: invoice.pdf");
        }

        try {
            File file = new File(resource.toURI());
            return file.getPath();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}