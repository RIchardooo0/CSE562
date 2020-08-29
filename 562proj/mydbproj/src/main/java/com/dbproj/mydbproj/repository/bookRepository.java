package com.dbproj.mydbproj.repository;

import com.dbproj.mydbproj.entity.book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface bookRepository extends JpaRepository<book, Integer> {



    //@Query("select u from book u")
    //Page<book> findList(Pageable pageable);
    @Query("select u from book u")
    List<book> findAll();

    @Query(value="from book where book_name=?1")
    book findByBook_name(String bookname);

    @Query("select book_name from book where book_id=?1")
    String findbookNameById(int id);

    @Transactional
    @Modifying
    @Query(value = "update book u set u.book_status='borrowed' where u.book_id=?1")
    int updatebookStatus(int id);


    @Transactional
    @Modifying
    @Query(value = "update book u set u.book_status='available' where u.book_id=?1")
    int updatebookStatus1(int id);


}

