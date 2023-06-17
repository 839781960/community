package pers.jcl.community.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.lang.model.element.NestingKind;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @RequestMapping("/hello")
    public String sayHello(){
        return "hello,spring boot";
    }

//处理请求数据

    @RequestMapping("/http")
    public void http(
            HttpServletRequest request,
            HttpServletResponse response
    ){
        //请求行
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        //请求头
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String nextElement = enumeration.nextElement();
            String header = request.getHeader(nextElement);
            System.out.println(nextElement+": "+header);
        }
        //请求体
        System.out.println(request.getParameter("code"));


        //处理相应数据
        //设置返回的格式
        response.setContentType("text/html;charset=utf-8");
        try (
                //在小括号里创建可以自动销毁我们就不用管了，省了写finally关闭这个流
                //已经封装好的向浏览器输的方法
                PrintWriter writer = response.getWriter();
                ){
            //向浏览器输出
            writer.write("<h1>哈哈哈<h1>");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //GET请求
    // / students?current=1&limit=20

    @RequestMapping(path = "/student",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(
            @RequestParam(name="current",required = false,defaultValue = "1") int current ,
            @RequestParam(name="limit",required = false,defaultValue = "10") int limit){

        System.out.println(current);
        System.out.println(limit);
        return "some student";
    }

    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student ";
    }

    //post请求
    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name,int age){
        System.out.println(name);
        System.out.println(age);
        return "sucess";
    }

    //响应html数据
    //dispacherservlet 调度controller的方法，需要给他们返回model数据，也需要返回视图相关的数据
    //把ModelAndView都提交给模板引擎，由模板引擎渲染生成动态的html、
    //下面的内容就是给dispacherservlet返回的model和view 数据
    @RequestMapping(path="/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age",30);
        modelAndView.setViewName("/demo/view");
        return modelAndView;
    }

    @RequestMapping(path="/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","家里蹲");
        model.addAttribute("age",90);
        return "/demo/view";
    }

    //响应Json数据(异步请求)
    //json跨语言对象转换
    @RequestMapping(path="/emp",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",25);
        emp.put("salary",9000);
        return emp;
    }

    @RequestMapping(path="/emps",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",25);
        emp.put("salary",9000);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","李四");
        emp.put("age",24);
        emp.put("salary",6666);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王五");
        emp.put("age",30);
        emp.put("salary",10000);
        list.add(emp);

        return list;
    }

}
