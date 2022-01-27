package com.example.blog.service;

import com.example.blog.dao.Mappers.AccountMapper;
import com.example.blog.tools.Token;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class AccountService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     *
     * @param account
     * @param password
     * @return token
     */
    @Transactional
    public String isPass(String account,String password)throws Exception{
        if(password.equals((String)redisTemplate.opsForValue().get(account))){
            String token="";
            token = Token.getToken(account, password);
            redisTemplate.opsForHash().put(token," account",account);
            redisTemplate.expire(token,30*60, TimeUnit.SECONDS);
            return token;
        }
        else
            return "Password Error";
    }

    /**
     *
     * @param account
     * @param password
     * @return isSucceed
     */
    @Transactional
    public boolean registerAccount(String account,String password)throws Exception{
        redisTemplate.opsForValue().set(account,password);
        return true;
    }
}