package com.bpmn2.camunda.entity.camunda;

import com.bpmn2.camunda.entity.camunda.Account;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Request")
public class Request {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id", nullable = true)
    private Account account;

    @Column
    private Date created;
    @Column
    private Date updated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
        updated = created;
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }



}
