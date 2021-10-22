package com.athome.spring5.dao;

import com.athome.spring5.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/22 15:17
 * @Version 1.0
 */
@Component
public class PaymentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void  add(String name, double money){
        int update = jdbcTemplate.update("update bank set money = money + ? where name = ?", money, name);
        System.out.println(update);
    }

    public void decr(String name, double money){
        int update = jdbcTemplate.update("update bank set money = money - ? where name = ?", money, name);
        System.out.println(update);
    }



    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from payment");
        System.out.println(mapList);

        List<Payment> query = jdbcTemplate.query("select * from payment where id = ?", new RowMapper<Payment>() {
            @Override
            public Payment mapRow(ResultSet resultSet, int i) throws SQLException {
                Payment payment = new Payment();
                payment.setId(resultSet.getInt("id"));
                payment.setSerial(resultSet.getString("serial"));
                return payment;
            }
        },2);
        System.out.println(query);

        jdbcTemplate.query("select * from payment where id =?", new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {

            }
        });
    }
}
