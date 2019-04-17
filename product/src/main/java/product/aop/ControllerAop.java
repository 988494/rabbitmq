package product.aop;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import product.util.ExceptionUtil;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class ControllerAop {


    //注解切入点
    @Pointcut("execution(* product.controller..*.*(..))")
    public void pointController() {
    }

    @AfterThrowing(value = "pointController()",throwing="throwable")
    public void afterThrowing(JoinPoint point,Throwable throwable) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        ApiOperation apiOperation = method.getDeclaredAnnotation(ApiOperation.class);
        if(!StringUtils.isEmpty(apiOperation)){
            log.error("{}接口执行出现异常",apiOperation.value());
        }
        System.out.println();
        System.out.println("doThrowing::method "+ point.getTarget().getClass().getName() + "."+ point.getSignature().getName() + " throw exception");
        System.out.println(ExceptionUtil.errInfo(throwable));
    }
}
