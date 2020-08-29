package com.dbproj.mydbproj.repository;

import com.dbproj.mydbproj.entity.book;
import com.dbproj.mydbproj.entity.borrow_list;
import com.dbproj.mydbproj.entity.return_list;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface borrow_listRepository extends JpaRepository<borrow_list, Integer> {

    //@Query("select u from borrow_list u")
    //Page<borrow_list> findList(Pageable pageable);

    @Query("select u from borrow_list u")
    List<borrow_list> findAll();

    //@Query("select u from borrow_list u where u.student_id=?1")
    //Page<borrow_list> findListByStudent_id(Pageable pageable,int id);


    @Query("select u from borrow_list u where u.student_id=?1")
    List<borrow_list> findListByStudent_id(int id);

    @Transactional
    @Modifying
    @Query(value = "update borrow_list u set u.isReturned=1 where u.borrow_id=?1")
    void setIsReturnToOne(int id);



}
