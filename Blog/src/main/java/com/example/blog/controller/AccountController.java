package com.example.blog.controller;

import com.example.blog.VO.ChangePassword;
import com.example.blog.VO.Login;
import com.example.blog.VO.RequestToken;
import com.example.blog.VO.Result;
import com.example.blog.entity.Account;
import com.example.blog.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
    public Result isPass(@RequestBody Login login){
        try {
            return accountService.isPass(login.getAccount(), login.getPassword());
        }catch (Exception e){
            e.printStackTrace();
            return new Result(new String(), "404",false,"Exception!");
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
    public Result vertifyToken(@RequestBody String token){
        return accountService.vertifyToken(token);
    }

    @PostMapping("/api/getAccount")
    @ResponseBody
    public String getAccount(@RequestBody RequestToken token){
        return accountService.getAccountByToken(token.getToken());
    }

}
