package com.qfedu.service.UserServiceImpl;

import com.qfedu.dao.TradeDao;
import com.qfedu.dao.UserDao;
import com.qfedu.entity.Trade;
import com.qfedu.entity.User;
import com.qfedu.service.TradeService;
import com.qfedu.vo.VTradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeDao tradeDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<VTradeInfo> findAllTrades(Integer uid, Date beginTime, Date endTime) {
        return tradeDao.findAll(uid, beginTime, endTime);
    }

    //转账功能
    @Override
    public void updateMoney(String code, String otherCode, Double money) {
        //被扣钱的人（也就我们登录的用户，去给别人转账的人）
        User loginUser = userDao.findByCode(code);
        //收钱的人  进数据库去查看将要给谁来转账
        User otherUser = userDao.findByCode(otherCode);

        if (loginUser == null) {
            throw new RuntimeException("该账户未登录");
        }
        if (loginUser.getBankCode().equals(otherCode)) {
            throw new RuntimeException("大聪明！ 不能给自己转账");
        }
        if (loginUser.getBalance() < money) {
            throw new RuntimeException("账户余额不足");
        }

        //当前用户减钱的业务代码
        Trade outTrade = new Trade();
        outTrade.setUid(loginUser.getUid());
        outTrade.setOtherUid(otherUser.getUid());
        outTrade.setMoney(0 - money);
        outTrade.setCreateTime(new Date());
        outTrade.setBalance(loginUser.getBalance() - money);
        tradeDao.add(outTrade);
        loginUser.setBalance(loginUser.getBalance()- money);
        userDao.update(loginUser);

        //另一个用户加钱
        Trade inTrade =  new Trade();
        inTrade.setUid(otherUser.getUid());
        inTrade.setOtherUid(loginUser.getUid());
        inTrade.setMoney(money);
        inTrade.setCreateTime(new Date());
        inTrade.setBalance(otherUser.getBalance() + money);
        tradeDao.add(inTrade);
        otherUser.setBalance(otherUser.getBalance() + money);
        userDao.update(otherUser);
    }


}
