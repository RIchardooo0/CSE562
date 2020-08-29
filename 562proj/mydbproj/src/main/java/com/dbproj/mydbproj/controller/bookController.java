package com.dbproj.mydbproj.controller;

import com.dbproj.mydbproj.entity.book;
import com.dbproj.mydbproj.repository.bookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class bookController {

    @Autowired
    private bookRepository bookRepository;



    @RequestMapping("/")
    public String index()
    {
        return "book/login";
    }

    ///管理员视角的书
    @RequestMapping("/adminlist")
    public String list(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
                       @RequestParam(value="size",defaultValue = "5")Integer size)
    {
        //ort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size);
        //Page<book> list = bookRepository.findList(pageable);
        List<book> list = bookRepository.findAll();

        model.addAttribute("books",list);

        //分页的话，第几页，每一页有几条
        return "book/adminlist";
    }

    //学生视角的书
    @RequestMapping("/studentBook")
    public String stuBookList(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
                              @RequestParam(value="size",defaultValue = "5")Integer size)
    {
        Pageable pageable = PageRequest.of(page,size);
        //Page<book> list = bookRepository.findList(pageable);
        List<book> list = bookRepository.findAll();

        model.addAttribute("books",list);

        //分页的话，第几页，每一页有几条
        return "book/studentBook";

    }

    @RequestMapping("/studentfirst")
    public String direct(){
        return "book/studentlistall";
    }

    @RequestMapping("/studentfirstBook")
    public String stubook(String bookname, Model model)
    {

        //Page<book> list = bookRepository.findList(pageable);
        book thisbook = bookRepository.findByBook_name(bookname);

        model.addAttribute("books",thisbook);

        //分页的话，第几页，每一页有几条
        return "book/studentnamereturn";
    }


    @RequestMapping("/toAddBook")
    public String toAdd()
    {
        return "book/bookAdd";
    }

    @RequestMapping("/addBook")
    public String add(@RequestParam(value="bookName")String bookName,@RequestParam(value="author")String author,
                      @RequestParam(value="classification")int classification,@RequestParam(value="status")String status,ModelMap model)
    {
//        String errorMsg="";
//
//
//        Userinfo u = userRepository.findByUserName(userParam.getUserName());
//        if(u!=null)
//        {
//            model.addAttribute("errorMsg","用户名已存在");
//            return "user/userAde";
//        }
//        else {
            book book = new book();
            book.setAuthor(author);
            book.setBook_name(bookName);
            book.setClassification_id(classification);
            book.setBook_status(status);
            bookRepository.save(book);
            return "redirect:/adminlist";

        }


        @RequestMapping("/toDelete")
        public String deletebook(@RequestParam(value="id")int id)
        {
            bookRepository.deleteById(id);
            return "redirect:/adminlist";
        }
/////////////////////////////////
    @RequestMapping("/toEdit")
    public String toEdit(@RequestParam("id")int id,@RequestParam("cla_id")int cla_id,Model model)
    {
        model.addAttribute("id",id);
        model.addAttribute("cla_id",cla_id);
        return "book/bookEdit";
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam("id")int id,@RequestParam("cla_id")int cla_id,@RequestParam(value="status")String status,Model model,book book)
    {

        System.out.println("77777777777777777777"+status);
        book newBook = new book();
        newBook.setBook_id(id);
        newBook.setClassification_id(cla_id);
        newBook.setBook_status(status);
        newBook.setBook_name(book.getBook_name());
        newBook.setAuthor(book.getAuthor());

        bookRepository.save(newBook);
        return "redirect:/adminlist";
    }
    ///////////////////////////////////////////




    }





