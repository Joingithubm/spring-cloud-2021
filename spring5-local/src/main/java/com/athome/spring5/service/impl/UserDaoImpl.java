package com.athome.spring5.service.impl;

import com.athome.spring5.base.LogRecord;
import com.athome.spring5.service.UserDao;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/19 14:45
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {

    @Override
    @LogRecord()
    public int add(int a, int b) {
        System.out.println("add 方法执行了");
        return a + b;
    }

    @Override
    @LogRecord()
    public String update(String b) {
        System.out.println("update 方法执行拉");
        return b;
    }
}
