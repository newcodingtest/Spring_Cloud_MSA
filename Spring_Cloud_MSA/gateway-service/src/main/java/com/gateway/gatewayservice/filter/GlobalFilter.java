package com.gateway.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//customFilter와 달리 하나만 yml에 등록 하면 전체 필터로 등록됨
//      default-filters:
//        - name: GlobalFilter
//          args:
//            baseMessage: Spring Cloud Gateway Global Filter
//            preLogger: true
//            postLogger: true
@Component
@Slf4j
public class GlobalFilter extends AbstractGatewayFilterFactory<GlobalFilter.Config> {


    public GlobalFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global PRE baseMessage: request id -> {}",config.getBaseMessage());

            if(config.isPostLogger()){
                log.info("Global Filter Start: requestid -> {}", request.getId());
            }

            //비동기 방식 으로 전달
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Global POST End: response code -> {}",response.getStatusCode());
            }));
        };
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
