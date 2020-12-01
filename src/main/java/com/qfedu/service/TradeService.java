package com.qfedu.service;

import com.qfedu.vo.VTradeInfo;

import java.util.Date;
import java.util.List;

public interface TradeService {
    List<VTradeInfo> findAllTrades(Integer uid, Date beginTime, Date endTime);
    void updateMoney(String code, String otherCode, Double money);
}
