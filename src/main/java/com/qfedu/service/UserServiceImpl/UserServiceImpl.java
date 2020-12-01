package com.qfedu.service.UserServiceImpl;

import com.qfedu.dao.UserDao;
import com.qfedu.entity.User;
import com.qfedu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


//    @Override
//    public User findByCode(String bankCode, String password) {
//        User user = userDao.findByCode(bankCode);
//        if(user == null) {
//            throw new RuntimeException("此账户不存在");
//        }
//        if (!user.getPassword().equals(password)) {
//            throw new RuntimeException("密码错误");
//        }
//        return user;
//    }

//    @Override
//    public User findByCode(String bankCode, String password) {
//        User user = userDao.findByCode(bankCode);
//        if (user == null) {
//            throw new RuntimeException("此账户不存在");
//        }
//        if (!user.getPassword().equals(password)) {
//            throw new RuntimeException("密码错误");
//        }
//        return user;
//    }

    @Override
    public User findByCode(String bankCode, String password) {

        User user = userDao.findByCode(bankCode);
        if (user == null) {
            throw new RuntimeException("此账户不存在");
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }

        return user;
    }

    @Override
    public void updateHeadImg(Integer uid, String imgPath) {
        User user = new User();
        user.setUid(uid);
        user.setImgPath(imgPath);
        userDao.update(user);
    }

    @Override
    public User selectByCode(String bankCode) {

        return userDao.findByCode(bankCode);
    }
}
