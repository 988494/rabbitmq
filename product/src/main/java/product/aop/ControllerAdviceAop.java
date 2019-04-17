package product.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@ControllerAdvice
@Slf4j
public class ControllerAdviceAop {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String handler(Exception e){
//        log.error("异常{}",e);
        return e.toString();
    }
}
