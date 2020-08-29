package com.dbproj.mydbproj.repository;

import com.dbproj.mydbproj.entity.book;
import com.dbproj.mydbproj.entity.borrow_list;
import com.dbproj.mydbproj.entity.return_list;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface return_listRepository extends JpaRepository<return_list, Integer> {

    //@Query("select u from return_list u")
    //Page<return_list> findList(Pageable pageable);

    @Query("select u from return_list u")
    List<return_list> findAll();

//    @Query("select u from return_list u where u.student_id=?1")
//    Page<return_list> findListByStudent_id(Pageable pageable,int id);

    @Query("select u from return_list u where u.student_id=?1")
    List<return_list>  findListByStudent_id(int id);



}
