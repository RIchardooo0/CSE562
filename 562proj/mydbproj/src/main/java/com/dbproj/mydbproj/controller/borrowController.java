package com.dbproj.mydbproj.controller;

import com.dbproj.mydbproj.entity.borrow_list;
import com.dbproj.mydbproj.repository.bookRepository;
import com.dbproj.mydbproj.repository.borrow_listRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
public class borrowController {

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private borrow_listRepository borrowListRepository;

    @RequestMapping("/toBorrow")
    public String borrow(@RequestParam("id") int bookid, Model model, HttpServletRequest request) {
       bookRepository.updatebookStatus(bookid);

       borrow_list borrowList = new borrow_list();
       borrowList.setBook_id(bookid);
       borrowList.setStudent_id((Integer) request.getSession().getAttribute("userId"));
       borrowList.setIsReturned(0);
       borrowList.setBorrowing_time(new Date());

       borrowListRepository.save(borrowList);


        return "redirect:/studentBook";
    }

}