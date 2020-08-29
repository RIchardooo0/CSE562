package com.dbproj.mydbproj.repository;

import com.dbproj.mydbproj.entity.book;
import com.dbproj.mydbproj.entity.users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface usersRepository extends JpaRepository<users, Integer> {

    @Query(value = "select u.user_password from users u where u.user_email = ?1", nativeQuery = true) // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    String findPwdByEmail(String email);

    @Query(value = "select u.type from users u where u.user_email = ?1", nativeQuery = true) // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    int findTypeByEmail(String email);

    @Query(value = "select u.user_id from users u where u.user_email = ?1", nativeQuery = true) // nativeQuery = true表示使用sql自己的方言查询，想查什么查什么， 按照字段数据类型返回就行了
    int findIdByEmail(String email);




}
