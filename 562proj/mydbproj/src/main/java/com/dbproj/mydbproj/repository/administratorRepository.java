package com.dbproj.mydbproj.repository;

import com.dbproj.mydbproj.entity.administrator;
import com.dbproj.mydbproj.entity.book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface administratorRepository extends JpaRepository<administrator, Integer> {

    @Query("select u from administrator u")
    Page<administrator> findList(Pageable pageable);




}
