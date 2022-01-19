package com.example.blog.service;

import com.example.blog.dao.Mappers.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    /**
     *
     * @param account
     * @param password
     * @return token
     */
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
