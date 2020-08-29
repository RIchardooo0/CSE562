package com.dbproj.mydbproj.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

    @Entity
    public class book {
        @Id
        @GeneratedValue
        private int book_id;

        @Column
        private String book_name;


        @Column
        private String author;

        @Column
        private int classification_id;

        @Column
        private String book_status;

        public int getBook_id() {
            return book_id;
        }

        public void setBook_id(int book_id) {
            this.book_id = book_id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getClassification_id() {
            return classification_id;
        }

        public void setClassification_id(int classification_id) {
            this.classification_id = classification_id;
        }

        public String getBook_status() {
            return book_status;
        }

        public void setBook_status(String book_status) {
            this.book_status = book_status;
        }
    }
