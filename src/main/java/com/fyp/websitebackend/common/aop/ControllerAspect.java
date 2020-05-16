package com.fyp.websitebackend.common.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fyp.websitebackend.common.entity.CustomResponseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;


@Configuration
@Aspect
@Order(value = 0)
public class ControllerAspect {
    private Logger logger = LoggerFactory.getLogger(ControllerAspect.class);

    private final String getResultDataPoint = "execution(* com.fyp.websitebackend.csweb.controller..*.*(..))";

    private final ObjectMapper mapper = new ObjectMapper();

    private ThreadLocal<String> requestStringLocal = new ThreadLocal<>();

    @Before(value = getResultDataPoint)
    public void doBeforeAdvice(JoinPoint joinPoint) {
        try {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            StringBuffer stringBuffer = new StringBuffer();
            String requestURL = request.getRequestURI();
            stringBuffer.append("current request url: [" + requestURL + "] ");
            Map<String, ?> params = request.getParameterMap();
            if(!params.isEmpty()) {
                stringBuffer.append("current request parameters: " + print(request.getParameterMap()));
            }
            logger.debug("[BEFORE]" + stringBuffer.toString());
            requestStringLocal.set("[REQUEST]" + stringBuffer.toString());
        } catch (Exception e) {
            logger.error("error happened in interceptor: ", e);
        }
    }

    @AfterReturning(value = getResultDataPoint, returning = "keys")
    public void doAfterReturningAdvice(JoinPoint joinPoint, CustomResponseEntity keys) {
        try {
            String requestString = requestStringLocal.get();
            logger.debug(requestString + "[RESPONSE]" + mapper.writeValueAsString(keys));
        } catch (JsonProcessingException e) {
            logger.error("error happened when transferring json: ", e);
        }
    }

    private String print(Map<String, ?> map) {
        StringBuffer buffer = new StringBuffer();
        if (map != null) {
            Set<String> keys = map.keySet();
            for (String name : keys) {
                if (name.contains("org.springframework.validation.BindingResult")) {
                    continue;
                }
                String value = "";
                try {
                    value = mapper.writeValueAsString(map.get(name));
                } catch (Exception e) {
                    logger.error("error happened when transferring params: " + name, e);
                }
                // use append instead of + for faster processing
                buffer.append(name).append("=").append(value).append(";");
            }
        }

        return buffer.toString();
    }
}
