package com.qfedu.service;

import com.qfedu.entity.User;

public interface UserService {

    //User findByCode(String bankCode, String password);

    User findByCode(String bankCode, String password);

    void updateHeadImg(Integer uid, String imgPath);
    User selectByCode(String bankCode);
}
