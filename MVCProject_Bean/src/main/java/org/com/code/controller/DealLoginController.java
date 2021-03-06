package org.com.code.controller;

import org.com.code.servers.StudentJDBC;
import org.com.code.servers.TeacherJDBC;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Controller
public class DealLoginController extends HttpServlet {


    private TeacherJDBC teacherJDBC;
    private StudentJDBC studentJDBC;

    @Autowired
    public DealLoginController(TeacherJDBC teacherJDBC,StudentJDBC studentJDBC) {
        this.studentJDBC = studentJDBC;
        this.teacherJDBC = teacherJDBC;
    }


    @RequestMapping(value = "/dealLogin")
    public String DealLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        String userName = req.getParameter("myname");
        System.out.println(new String(req.getParameter("myname") .getBytes("utf-8"),"utf-8"));
        String password =req.getParameter("mypwd");
        System.out.println(new String(req.getParameter("mypwd") .getBytes("iso8859-1"),"utf-8"));


        if(teacherJDBC.getTeacherIdentity(userName,password)){
            System.out.println("teacher");
            req.getSession().setAttribute("username1",userName);
            return "teacherManage";

        }else if(studentJDBC.getStudentIdentity(userName,password)){
            System.out.println("student");
            req.getSession().setAttribute("username1",userName);

            return "studentManage";
        }else{

            return "login";
        }
    }
}
