package com.dbproj.mydbproj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class users {
    @Id
    @GeneratedValue
    int user_id;

    @Column
    String user_email;

    @Column
    String user_password;

    @Column
    int type;

}
