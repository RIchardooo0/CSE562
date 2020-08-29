package com.dbproj.mydbproj.repository;

import com.dbproj.mydbproj.entity.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface studentRepository extends JpaRepository<student, Integer> {

    @Query("select student_name from student where user_id=?1")
    String findStuNameById(int id);



}
