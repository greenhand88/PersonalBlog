package com.example.blog.dao.Mappers;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AccountMapper {
    /**
     *
     * @param account
     * @return password
     */
    @Select("select password from account where account=#{account}")
    public String getPassword(String account);

    /**
     *
     * @param account
     * @return isExisted
     */
    @Select("select account from account where account=#{account}")
    public String isExisted(String account);

    /**
     *
     * @param account
     * @param password
     */
    @Insert("insert into account(account,password) values(#{account},#{password})")
    public void register(String account,String password);

}
