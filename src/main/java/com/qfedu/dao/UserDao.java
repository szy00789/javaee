package com.qfedu.dao;

import com.qfedu.entity.Trade;
import com.qfedu.entity.User;

public interface UserDao {

    //User findByCode(String bankCode);

    void update(User user);

    User findByCode(String bankCode);

}
