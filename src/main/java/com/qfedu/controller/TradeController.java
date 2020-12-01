package com.qfedu.controller;

import com.qfedu.common.JsonResult;
import com.qfedu.entity.User;
import com.qfedu.service.TradeService;
import com.qfedu.vo.VTradeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/list.do")
    @ResponseBody
    public JsonResult listInfo(HttpSession session, Date beginTime, Date endTime) {
        User u = (User) session.getAttribute("loginUser");

        List<VTradeInfo> list = tradeService.findAllTrades(u.getUid(), beginTime, endTime);
        JsonResult result = new JsonResult(1, list);
        return result;
    }

    @RequestMapping("/transfer.do")
    @ResponseBody
    public JsonResult transfer(String otherCode, Double money, HttpSession session) {
        User u =(User) session.getAttribute("loginUser");
        tradeService.updateMoney(u.getBankCode(), otherCode, money);
        return new JsonResult(1, "转账成功");
    }
}
