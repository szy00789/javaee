package com.qfedu.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;

@ControllerAdvice  //控制器的增强   需要在springmvc中进行配置扫描
@ResponseBody //可以用来修饰类，类中的方法都是需要返回一个json字符串的注解
public class CommonException {
    //使用springmvc来处理异常
    @ExceptionHandler(ConstraintViolationException.class) //这个注解的目的是为了处理异常的
    public JsonResult constraint(ConstraintViolationException ex) {
        //验证不通过的信息
        Iterator<ConstraintViolation<?>> iterator = ex.getConstraintViolations().iterator();
        String message = null;

        if(iterator.hasNext()) {
           message = iterator.next().getMessage();
            System.out.println(message);
        }
        return new JsonResult(0, message);
    }

    //处理没有预料的异常
    @ExceptionHandler
    public JsonResult commonException(Exception ex) {
        return new JsonResult(0, ex.getMessage());
    }
}
