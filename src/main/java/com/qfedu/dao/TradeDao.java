package com.qfedu.dao;

import com.qfedu.entity.Trade;
import com.qfedu.vo.VTradeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TradeDao {
    //@param 如果你的映射器的方法需要多个参数的时候，这个注解可以用于映射器方法的参数来给每一个参数进行赋值
    List<VTradeInfo> findAll(@Param("uid")Integer uid, @Param("beginTime") Date beginTime, @Param("endTime") Date endTime);
    void add(Trade trade);
}
