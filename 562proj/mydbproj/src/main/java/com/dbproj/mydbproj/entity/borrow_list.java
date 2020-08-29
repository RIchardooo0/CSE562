package com.dbproj.mydbproj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class borrow_list {

    @Id
    @GeneratedValue
    int borrow_id;

    @Column
    int student_id;

    @Column
    int book_id;

    @Column
    int isReturned;

    @Column
    Date borrowing_time;

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(int isReturned) {
        this.isReturned = isReturned;
    }

    public Date getBorrowing_time() {
        return borrowing_time;
    }

    public void setBorrowing_time(Date borrowing_time) {
        this.borrowing_time = borrowing_time;
    }
}
