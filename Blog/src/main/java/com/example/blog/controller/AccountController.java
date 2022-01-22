package com.example.blog.controller;

import com.example.blog.VO.Login;
import com.example.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/account")
@Controller
public class AccountController {
    @Autowired
    AccountService accountService;

    /**
     *
     * @param login
     * @return token
     */
    @PostMapping("/login")
    @ResponseBody
    public String isPass(@RequestBody Login login){
        return accountService.isPass(login.getAccount(),login.getPassword());
    }

    /**
     *
     * @param login
     * @return isSucceed
     */
    @PostMapping("/register")
    @ResponseBody
    public boolean registerAccount(@RequestBody Login login){
        return accountService.registerAccount(login.getAccount(),login.getPassword());
    }

}
