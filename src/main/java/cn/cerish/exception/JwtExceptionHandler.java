package cn.cerish.exception;

import cn.cerish.entity.RespBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtException.class)
    @ResponseBody
    public RespBean handle(JwtException je, HttpServletResponse response){
        response.setStatus(401);
        return RespBean.error(je.getCode(),je.getMsg());
    }

}
