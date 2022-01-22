package com.example.blog.service;

import com.example.blog.dao.Mappers.AccountMapper;
import com.example.blog.tools.Token;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     *
     * @param account
     * @param password
     * @return token
     */
    public String isPass(String account,String password){
//        redisTemplate.opsForValue().set(account,password);
//        System.out.println(redisTemplate.opsForValue().get(account));
//        return true;
        String token="";
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
            if(accountMapper.getPassword(account).equals(password)) {
                token = Token.getToken(account, password);
                redisTemplate.opsForValue().set(token,account);
                System.out.println(redisTemplate.opsForValue().get(token));
            }
        }catch (Exception e){
            System.out.println("AccountService Error");
            e.printStackTrace();
        }finally {
            return token;
        }
    }

    /**
     *
     * @param account
     * @param password
     * @return isSucceed
     */
    @Transactional
    public boolean registerAccount(String account,String password){
        try(SqlSession sqlSession = sqlSessionFactory.openSession(true)){//打开自动提交
            AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
            if(accountMapper.isExisted(account)!=null) {
                System.out.println("Account is already exist!");
                return false;
            }
            accountMapper.register(account,password);
            return true;
        }catch (Exception e){
            System.out.println("RegisterAccount Fail");
            e.printStackTrace();
            return false;
        }
    }
}
