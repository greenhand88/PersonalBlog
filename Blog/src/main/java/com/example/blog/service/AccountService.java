package com.example.blog.service;

import com.example.blog.VO.Result;
import com.example.blog.dao.Mappers.AccountMapper;
import com.example.blog.tools.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class AccountService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private AccountMapper accountMapper;

    /**
     *
     * @param account
     * @param password
     * @return token
     */
    public Result isPass(String account, String password)throws Exception{
        String s=accountMapper.getPassword(account);
        if(password.equals(s)){
            String userName=accountMapper.getUserName(account);
            String token = Token.getToken(account,userName);
            redisTemplate.opsForValue().set(token,account,60*30,TimeUnit.SECONDS);
            return new Result(token,"200",true,"Success!");
        }
        else
            return new Result(new String(),"300",false,"Fail!");
    }

    /**
     *
     * @param account
     * @param password
     * @return isSucceed
     */
    @Transactional
    public boolean registerAccount(String account,String password,String userName)throws Exception{
        if(accountMapper.getUserName(account)==null)
            accountMapper.register(account,password,userName);
        else
            return false;
        return true;
    }

    /**
     *
     * @param account
     * @param newPassword
     * @return
     */
    @Transactional
    public String changePassword(String account,String newPassword){
        if(!accountMapper.getPassword(account).equals(newPassword))
            accountMapper.changePassword(account,newPassword);
        else
            return "密码修改失败";
        return "密码修改成功";
    }

    /**
     *
     * @param token
     * @return
     */
    public boolean vertifyToken(String token){
        if(redisTemplate.opsForValue().get(token)!=null)
            return true;
        else
            return false;
    }

    public String getAccountByToken(String token){
        return redisTemplate.opsForValue().get(token).toString();
    }
}
