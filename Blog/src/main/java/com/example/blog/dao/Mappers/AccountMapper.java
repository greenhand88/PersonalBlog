package com.example.blog.dao.Mappers;

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

}
