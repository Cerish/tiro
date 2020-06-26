package cn.cerish.common.exception;

import cn.cerish.entity.Response;
import cn.cerish.util.ResponseUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtException.class)
    @ResponseBody
    public Response handle(JwtException je, HttpServletResponse response){
        response.setStatus(401);
        return ResponseUtils.error(je.getCode(),je.getMsg());
    }

}
