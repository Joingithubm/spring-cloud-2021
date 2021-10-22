package com.athome.spring5.service.impl;

import com.athome.spring5.dao.PaymentDao;
import com.athome.spring5.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author Zengfc
 * @Date 2021/10/22 16:37
 * @Version 1.0
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentDao paymentDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void tranMoney(String name1, String name2, double money) {

        paymentDao.add(name1,money);
         //int c = 1/0;
        paymentDao.decr(name2,money);

    }
}
