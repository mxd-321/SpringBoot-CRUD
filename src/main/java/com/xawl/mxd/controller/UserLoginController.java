package com.xawl.mxd.controller;


import com.xawl.mxd.bean.LoginUser;
import com.xawl.mxd.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserLoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping("/user/login")
    public String login(LoginUser loginUser, Model model, HttpSession session){
        if(loginUser.getUsername()==null || loginUser.getPassword()==null){
            model.addAttribute("msg","用户名和密码不能为空");
            return "index";
        }

        LoginUser sessionUser = loginService.selectByUsername(loginUser.getUsername());
        if(sessionUser!=null){
            if(loginUser.getPassword().equals(sessionUser.getPassword())){
                session.setAttribute("sessionUser",sessionUser);
                return "redirect:/main.html";
            }else{
                model.addAttribute("msg","密码错误");
            }
        }else{
            model.addAttribute("msg","用户名错误");
        }

        return "index";
    }

    @RequestMapping("/user/logout")
    public String logout(HttpSession session,Model model){
        session.invalidate();
        model.addAttribute("msg","已注销");
        return "index";
    }
}
