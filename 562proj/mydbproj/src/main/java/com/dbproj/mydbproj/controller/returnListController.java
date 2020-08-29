package com.dbproj.mydbproj.controller;

import com.dbproj.mydbproj.entity.*;
import com.dbproj.mydbproj.repository.bookRepository;
import com.dbproj.mydbproj.repository.borrow_listRepository;
import com.dbproj.mydbproj.repository.return_listRepository;
import com.dbproj.mydbproj.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class returnListController {
    @Autowired
    return_listRepository returnListRepository;

    @Autowired
    borrow_listRepository borrowListRepository;

    @Autowired
    bookRepository book_Repository;

    @Autowired
    private studentRepository stuRepository;

    @RequestMapping("/toAdminReturn")
    public String AddReturn(Model model,@RequestParam(value="borrow_Id")int borrow_Id,@RequestParam(value="book_Id")int book_Id,@RequestParam(value="student_Id")int student_Id)
    {
        //插入ReturnList
        return_list returnListRecord = new return_list();
        returnListRecord.setBook_id(book_Id);
        returnListRecord.setBorrow_id(borrow_Id);
        returnListRecord.setStudent_id(student_Id);
        returnListRecord.setReturn_time(new Date());
        returnListRepository.save(returnListRecord);
        //更改borrowList
        borrowListRepository.setIsReturnToOne(borrow_Id);
        //更改booklist
        book_Repository.updatebookStatus1(book_Id);

        //回到借阅list
        return "redirect:/AdminReturnList";
    }

    @RequestMapping("/toDeleteReturn")
    public String deleteBorrowList(Model model,@RequestParam(value="id")int id)
    {
        returnListRepository.deleteById(id);
        return "redirect:/AdminReturnList";
    }

    @RequestMapping("/StuReturnList")
    public String StuReturnList(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
                                @RequestParam(value="size",defaultValue = "5")Integer size, HttpServletRequest request)
    {
        int id = (int) request.getSession().getAttribute("userId");
        Pageable pageable = PageRequest.of(page,size);
        //Page<return_list> list = returnListRepository.findListByStudent_id(pageable,id);
        List<return_list> list = returnListRepository.findListByStudent_id(id);

        ArrayList<ReturnListEntity> list_B = new ArrayList<ReturnListEntity>();
        for (int i = 0; i < list.size(); i++) {
            ReturnListEntity returnL = new ReturnListEntity();

            return_list returnList = list.get(i);
            Date date = returnList.getReturn_time();
            int book_id = returnList.getBook_id();//找到书的id
            String book_name=book_Repository.findbookNameById(book_id);
            System.out.println("&&&&&&"+book_name);
            int student_id = returnList.getStudent_id();
            String student_name = stuRepository.findStuNameById(student_id);

            returnL.setBook_name(book_name);
            returnL.setStu_name(student_name);
            returnL.setReturn_time(date);
            returnL.setBook_id(returnList.getBook_id());
            returnL.setStudent_id(returnList.getStudent_id());
            returnL.setReturn_id(returnList.getReturn_id());

            list_B.add(returnL);
        }



        model.addAttribute("studentReturnList",list_B);
        return "book/StudentReturnList";
    }


    @RequestMapping("/AdminReturnList")
    public String AdminReturnList(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
                       @RequestParam(value="size",defaultValue = "5")Integer size)
    {
        //ort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size);
        //Page<return_list> list = returnListRepository.findList(pageable);
        List<return_list> list = returnListRepository.findAll();

        ArrayList<ReturnListEntity> list_B = new ArrayList<ReturnListEntity>();
        for (int i = 0; i < list.size(); i++) {
            ReturnListEntity returnL = new ReturnListEntity();

            return_list returnList = list.get(i);
            Date date = returnList.getReturn_time();
            int book_id = returnList.getBook_id();//找到书的id
            String book_name=book_Repository.findbookNameById(book_id);
            System.out.println("&&&&&&"+book_name);
            int student_id = returnList.getStudent_id();
            String student_name = stuRepository.findStuNameById(student_id);

            returnL.setBook_name(book_name);
            returnL.setStu_name(student_name);
            returnL.setReturn_time(date);
            returnL.setBook_id(returnList.getBook_id());
            returnL.setStudent_id(returnList.getStudent_id());
            returnL.setReturn_id(returnList.getReturn_id());

            list_B.add(returnL);
        }




        model.addAttribute("adminReturnList",list_B);

        //分页的话，第几页，每一页有几条
        return "book/AdminReturnList";
    }













}

//    private borrow_listRepository borrowlistRepository;
//
//    @RequestMapping("/Adminborrowlist")
//    public String list(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
//                       @RequestParam(value="size",defaultValue = "5")Integer size)
//    {
//        //ort sort = new Sort(Sort.Direction.DESC,"id");
//        Pageable pageable = PageRequest.of(page,size);
//        Page<borrow_list> list = borrowlistRepository.findList(pageable);
//
//        model.addAttribute("books",list);
//
//        //分页的话，第几页，每一页有几条
//        return "book/adminlist";
//    }
//
//
//    @RequestMapping("/Studentborrowlist")
//    public String stuBookList(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
//                              @RequestParam(value="size",defaultValue = "5")Integer size)
//    {
//
//
//
//        Pageable pageable = PageRequest.of(page,size);
//        Page<borrow_list> list = borrowlistRepository.findList(pageable);
//
//        model.addAttribute("books",list);
//
//        //分页的话，第几页，每一页有几条
//        return "book/studentBook";
//
//    }
//
//    @RequestMapping("/AdminToAddBorrowlist")
//    public String toAdd()
//    {
//        return "book/bookAdd";
//    }

//    @RequestMapping("/AdminAddBorrowlist")
//    public String add(@RequestParam(value="bookName")String bookName, @RequestParam(value="author")String author,
//                      @RequestParam(value="classification")int classification, @RequestParam(value="stats")String status, ModelMap model)
//    {
////        String errorMsg="";
////
////
////        Userinfo u = userRepository.findByUserName(userParam.getUserName());
////        if(u!=null)
////        {
////            model.addAttribute("errorMsg","用户名已存在");
////            return "user/userAde";
////        }
////        else {
//        book book = new book();
//        book.setAuthor(author);
//        book.setBook_name(bookName);
//        book.setClassification_id(classification);
//        book.setBook_status(status);
//        bookRepository.save(book);
//        return "redirect:/list";
//
//    }





