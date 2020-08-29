package com.dbproj.mydbproj.controller;

import com.dbproj.mydbproj.entity.BorrowListEntity;
import com.dbproj.mydbproj.entity.book;
import com.dbproj.mydbproj.entity.borrow_list;
import com.dbproj.mydbproj.repository.bookRepository;
import com.dbproj.mydbproj.repository.borrow_listRepository;
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

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class borrowListController {

    @Autowired
    private borrow_listRepository borrowlistRepository;

    @Autowired
    private bookRepository bookRepository;

    @Autowired
    private studentRepository stuRepository;

    @RequestMapping("/Adminborrowlist")
    public String list(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
                       @RequestParam(value="size",defaultValue = "5")Integer size)
    {
        //ort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = PageRequest.of(page,size);
        //Page<borrow_list> list = borrowlistRepository.findList(pageable);
        List<borrow_list> list = borrowlistRepository.findAll();
        ArrayList<BorrowListEntity> list_B = new ArrayList<BorrowListEntity>();
        for (int i = 0; i < list.size(); i++) {
            BorrowListEntity borrowL = new BorrowListEntity();

            borrow_list borrowList = list.get(i);
            Date date = borrowList.getBorrowing_time();
            int book_id = borrowList.getBook_id();//找到书的id
            String book_name=bookRepository.findbookNameById(book_id);
            System.out.println("&&&&&&"+book_name);
            int student_id = borrowList.getStudent_id();
            String student_name = stuRepository.findStuNameById(student_id);
            int isReturned = borrowList.getIsReturned();
            String return_qingkuang = "";
            if(isReturned==0)
            {
                return_qingkuang="not returned";
            }
            else{
                return_qingkuang="returned";
            }

            borrowL.setBook_name(book_name);
            borrowL.setStu_name(student_name);
            borrowL.setIsReturned(return_qingkuang);
            borrowL.setBorrow_time(date);
            borrowL.setBook_id(borrowList.getBook_id());
            borrowL.setBorrow_id(borrowList.getBorrow_id());
            borrowL.setStudent_id(borrowList.getStudent_id());

            list_B.add(borrowL);
        }

        model.addAttribute("adminBorrowList",list_B);

        //分页的话，第几页，每一页有几条
        return "book/Adminborrowlist";
    }

    @RequestMapping("/toAdminlistDelete")
    public String deleteBorrowList(Model model,@RequestParam(value="id")int id)
    {
        borrowlistRepository.deleteById(id);
        return "redirect:/Adminborrowlist";
    }

    @RequestMapping("/toAdminlistEdit")
    public String editBorrowList(Model model,@RequestParam(value="id")int id)
    {
        borrowlistRepository.deleteById(id);
        return "/Adminborrowlist";
    }

//    @RequestMapping("/toDeleteBorrow")
//    public String deleteBorrowList(Model model,@RequestParam(value="id")int id)
//    {
//        borrowlistRepository.deleteById(id);
//        return "/AdminReturn";
//    }




    @RequestMapping("/Studentborrowlist")
    public String stuBookList(Model model, @RequestParam(value="page",defaultValue = "0") Integer page,
                              @RequestParam(value="size",defaultValue = "5")Integer size, HttpServletRequest request)
    {

        Pageable pageable = PageRequest.of(page,size);
        int stu_id = (int) request.getSession().getAttribute("userId");
        List<borrow_list> list = borrowlistRepository.findListByStudent_id(stu_id);

        ArrayList<BorrowListEntity> list_B = new ArrayList<BorrowListEntity>();
        for (int i = 0; i < list.size(); i++) {
            BorrowListEntity borrowL = new BorrowListEntity();

            borrow_list borrowList = list.get(i);
            Date date = borrowList.getBorrowing_time();
            int book_id = borrowList.getBook_id();//找到书的id
            String book_name=bookRepository.findbookNameById(book_id);
            System.out.println("&&&&&&"+book_name);
            int student_id = borrowList.getStudent_id();
            String student_name = stuRepository.findStuNameById(student_id);
            int isReturned = borrowList.getIsReturned();
            String return_qingkuang = "";
            if(isReturned==0)
            {
                return_qingkuang="not returned";
            }
            else{
                return_qingkuang="returned";
            }

            borrowL.setBook_name(book_name);
            borrowL.setStu_name(student_name);
            borrowL.setIsReturned(return_qingkuang);
            borrowL.setBorrow_time(date);
            borrowL.setBook_id(borrowList.getBook_id());
            borrowL.setBorrow_id(borrowList.getBorrow_id());
            borrowL.setStudent_id(borrowList.getStudent_id());

            list_B.add(borrowL);
        }
        model.addAttribute("stuBorrowList",list_B);

        //分页的话，第几页，每一页有几条
        return "book/Studentborrowlist";

    }

//    @RequestMapping("/AdminToAddBorrowlist")
//    public String toAdd()
//    {
//        return "book/Add";
//    }

    @RequestMapping("/AdminAddBorrowlist")
    public String add(@RequestParam(value="studentId")int studentId, @RequestParam(value="bookId")int bookId,
                      @RequestParam(value="isReturned")int isReturned, @RequestParam(value="borrowingTime") Date borrowingTime, ModelMap model)
    {
//        String errorMsg="";
//        Userinfo u = userRepository.findByUserName(userParam.getUserName());
//        if(u!=null)
//        {
//            model.addAttribute("errorMsg","用户名已存在");
//            return "user/userAde";
//        }
//        else {
        borrow_list borrowlist = new borrow_list();
        borrowlist.setBook_id(bookId);
        borrowlist.setBorrowing_time(borrowingTime);
        borrowlist.setIsReturned(isReturned);
        borrowlist.setStudent_id(studentId);

        return "redirect:/list";

    }




}
