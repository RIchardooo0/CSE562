package com.dbproj.mydbproj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class student {

    @Id
    @GeneratedValue
    int student_id;

    @Column
    String student_name;

    @Column
    String grade;

    @Column
    String gender;

    @Column
    int user_id;

}
