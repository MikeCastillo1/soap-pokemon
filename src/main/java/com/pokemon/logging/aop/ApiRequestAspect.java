package com.pokemon.logging.aop;

import com.pokemon.logging.model.ApiRequestLog;
import com.pokemon.logging.repositories.ApiRequestLogRepo;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpServletConnection;

import java.time.LocalDateTime;

@Aspect
@Component
public class ApiRequestAspect {
    private static final Logger logger = LoggerFactory.getLogger(ApiRequestAspect.class);

    private ApiRequestLogRepo apiRequestLogRepo;

    public ApiRequestAspect(ApiRequestLogRepo apiRequestLogRepo) {
        this.apiRequestLogRepo = apiRequestLogRepo;
    }

    @Transactional
    @Before("execution(* com.pokemon.soap.endpoint.PokemonEndPoint.*(..))")
    public void apiRequest(JoinPoint joinPoint) {
        logger.info("LOGGING API REQUEST");
        TransportContext context = TransportContextHolder.getTransportContext();
        HttpServletConnection connection = (HttpServletConnection )context.getConnection();
        HttpServletRequest request1 = connection.getHttpServletRequest();

        String originIp = request1.getRemoteAddr();
        LocalDateTime requestTime = LocalDateTime.now();
        String methodRequested = joinPoint.getSignature().getName();
        logger.info("Origin Address: {}", originIp);
        logger.info("Method Requested: {}", methodRequested);
        logger.info("RequestDate: {}", requestTime);

        ApiRequestLog apiRequestLog = new ApiRequestLog();
        apiRequestLog.setRequestTime(requestTime);
        apiRequestLog.setMethodName(methodRequested);
        apiRequestLog.setOriginIp(originIp);

        apiRequestLogRepo.save(apiRequestLog);
    }
}
