package com.example.blog.service;

import com.example.blog.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    /**
     * 
     */
    @Autowired
    Account account;
    public Boolean isPass(){
        try{

        }catch (Exception e){

        }finally {
            return false;
        }
    }
}
