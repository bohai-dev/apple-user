package com.apple.appleuser.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apple.appleuser.vo.ResponseHeader;

/**
 * 全局异常处理类
 * @author caoxx
 *
 */
@ControllerAdvice
public class GolbalExceptionHandler {
    
    @ExceptionHandler(value = MilkTeaException.class)
    @ResponseBody
    public ResponseHeader jsonErrorHandler(HttpServletRequest req, MilkTeaException e) {
        ResponseHeader header = new ResponseHeader();
        header.setRspCode(e.getErrorCode());
        header.setCnErrorMsg(e.getCnErrorMsg());
        header.setUsErrorMsg(e.getUsErrorMsg());
        return header;
    }

}
