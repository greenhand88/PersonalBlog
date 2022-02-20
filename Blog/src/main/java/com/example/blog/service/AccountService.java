package com.example.blog.service;

//import com.example.blog.dao.Mappers.AccountMapper;
import com.example.blog.tools.Token;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class AccountService {
//    @Autowired
//    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /**
     *
     * @param account
     * @param password
     * @return token
     */
    public String isPass(String account,String password)throws Exception{
        if(password.equals((String)redisTemplate.opsForHash().get(account,"password"))){
            String userName=redisTemplate.opsForHash().get(account,"userName").toString();
            String token = Token.getToken(account,userName);

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
    public boolean registerAccount(String account,String password,String userName)throws Exception{
        if(!redisTemplate.hasKey(account)) {
            redisTemplate.opsForHash().put(account, "password", password);
            redisTemplate.opsForHash().put(account, "userName", userName);
        }
        else
            return false;
        return true;
    }

    /**
     *
     * @param account
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @Transactional
    public String changePassword(String account,String oldPassword,String newPassword){
        if(newPassword.equals(oldPassword))
            return "新密码不能与旧密码相同";
        if(redisTemplate.hasKey(account)&&oldPassword.equals((String) redisTemplate.opsForHash().get(account,"password"))) {
            redisTemplate.opsForHash().put(account,"password",newPassword);
        }
        else
            return "密码修改失败";
        return "密码修改成功";
    }
}
