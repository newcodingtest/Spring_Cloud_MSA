package com.gateway.gatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;



/*
    yml 설정에서
    - name: LoggingFilter
    지정

 */
@Component
@Slf4j
public class LoggingFilter extends AbstractGatewayFilterFactory<LoggingFilter.Config> {


    public LoggingFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        //OrderedGatewayFilter 필터들의 우선순위를 지정할 수 있음
        GatewayFilter filter = new OrderedGatewayFilter((exchange, chain) -> {

            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Logging PRE baseMessage: request id -> {}",config.getBaseMessage());

            if(config.isPostLogger()){
                log.info("Logging Filter Start: requestid -> {}", request.getId());
            }

            //비동기 방식 으로 전달
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                log.info("Logging POST End: response code -> {}",response.getStatusCode());
            }));

            //필터의 우선순위로 인해서 3개의 필터(Custom, Global, Logging)중 Logging-Global-Custom 으로 동작함
        }, Ordered.HIGHEST_PRECEDENCE);

        return filter;
    }

    @Data
    public static class Config{
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}
