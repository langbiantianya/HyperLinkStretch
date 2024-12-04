package com.kxxnzstdsw.linkhorizon.chain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration(proxyBeanMethods = false)
public class ChainRouter {
    @Bean
    public RouterFunction<ServerResponse> chainRouterFunctions(ChainHandler chainHandler) {
        return RouterFunctions
                .route(GET("/f/{uuid}/{randomStr}"), chainHandler::redirect)
                .andRoute(GET("/api/v1/generate"), chainHandler::generate);
    }
}
