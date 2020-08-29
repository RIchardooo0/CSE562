package com.dbproj.mydbproj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class administrator {

    @Id
    @GeneratedValue
    int admin_id;

    @Column
    String admin_name;

    @Column
    int user_id;

}
