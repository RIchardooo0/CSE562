package com.dbproj.mydbproj.controller;


import com.dbproj.mydbproj.entity.book;
import com.dbproj.mydbproj.repository.bookRepository;
import com.dbproj.mydbproj.repository.usersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class loginController {

    @Autowired
    private usersRepository usersRepository;

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(value="userEmail",defaultValue = "null") String email,
                        @RequestParam(value="pwd",defaultValue = "null")String pwd, HttpServletRequest request)
    {
        String real_pwd = usersRepository.findPwdByEmail(email);
        if(real_pwd.equals(pwd))
        {
            int type = usersRepository.findTypeByEmail(email);
            int id = usersRepository.findIdByEmail(email);

            /////////////////////
            request.getSession().setAttribute("userId",id);
            //
            if(type==0)//student
            {

                return "redirect:/studentfirst";
            }
            else if(type==1)//admin
            {
                return "redirect:/adminlist";
            }
        }


        //分页的话，第几页，每一页有几条
        return "/login";
    }

}
