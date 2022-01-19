package com.example.blog.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Configuration
public class DbConfig {
    /**
     *
     * @return
     * @throws IOException
     */
    @Bean
    public SqlSessionFactory mybatisSqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("dbConfig.xml");
        return new SqlSessionFactoryBuilder().build(inputStream);
    }
}
