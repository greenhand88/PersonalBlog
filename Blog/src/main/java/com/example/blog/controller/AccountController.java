package com.example.blog.controller;

import com.example.blog.VO.ChangePassword;
import com.example.blog.VO.Login;
import com.example.blog.entity.Account;
import com.example.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
        try {
            return accountService.isPass(login.getAccount(), login.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return "Password Error";
        }
    }

    /**
     *
     * @param account
     * @return isSucceed
     */
    @PostMapping("/register")
    @ResponseBody
    public boolean registerAccount(@RequestBody Account account){
        try{
            return accountService.registerAccount(account.getAccount(),account.getPassword(),account.getUserName());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     *
     * @param changePassword
     * @return
     */
    @PostMapping("/changePassword")
    @ResponseBody
    public String changePassword(@RequestBody ChangePassword changePassword){
        try{
            return accountService.changePassword(changePassword.getAccount(), changePassword.getNewPassword());
        }catch (Exception e){
            e.printStackTrace();
            return "密码修改失败";
        }
    }

    /**
     *
     * @param token
     * @return
     */
    @PostMapping("/api/token")
    @ResponseBody
    public boolean vertifyToken(@RequestHeader String token){
        return accountService.vertifyToken(token);
    }

    @PostMapping("/api/getAccount")
    @ResponseBody
    public String getAccount(@RequestHeader String token){
        return accountService.getAccountByToken(token);
    }

}
