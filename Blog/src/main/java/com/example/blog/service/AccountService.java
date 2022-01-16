package com.example.blog.service;

import com.example.blog.dao.Mappers.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    /**
     * identify
     */
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    public boolean isPass(String account,String password){
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            AccountMapper accountMapper=sqlSession.getMapper(AccountMapper.class);
            if(accountMapper.getPassword(account).equals(password))
                return true;
            else
                return false;
        }catch (Exception e){
            System.out.println("AccountService Error");
            e.printStackTrace();
            return false;
        }
    }
}
