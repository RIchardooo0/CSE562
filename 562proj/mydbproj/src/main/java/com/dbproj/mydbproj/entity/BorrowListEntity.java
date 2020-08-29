package com.dbproj.mydbproj.entity;

import java.util.Date;

public class BorrowListEntity {
    String book_name;
    String stu_name;
    String isReturned;
    Date borrow_time;
    int Borrow_id;
    int Book_id;
    int Student_id;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(String isReturned) {
        this.isReturned = isReturned;
    }

    public Date getBorrow_time() {
        return borrow_time;
    }

    public void setBorrow_time(Date borrow_time) {
        this.borrow_time = borrow_time;
    }

    public int getBorrow_id() {
        return Borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        Borrow_id = borrow_id;
    }

    public int getBook_id() {
        return Book_id;
    }

    public void setBook_id(int book_id) {
        Book_id = book_id;
    }

    public int getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(int student_id) {
        Student_id = student_id;
    }
}
