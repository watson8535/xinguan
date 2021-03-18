package com.edward.handler;

import com.edward.response.Result;
import com.edward.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理，没有指定异常的类型，不管是什么异常都可以捕获
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody // 返回json数据
    public Result error(Exception e){
        //e.printStackTrace(); 打印错误到控制台
        log.error(e.getMessage());
        return Result.error();
    }

    //算数异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody // 返回json数据
    public Result error(ArithmeticException e){
        //e.printStackTrace();
        log.error(e.getMessage());
        return Result.error().code(ResultCode.ARITHMETIC_EXCEPTION.getCode())
                .message(ResultCode.ARITHMETIC_EXCEPTION.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public Result error(BusinessException e){
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode()).message(e.getErrMsg());
    }
}
