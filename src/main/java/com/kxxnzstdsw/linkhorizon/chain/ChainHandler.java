package com.kxxnzstdsw.linkhorizon.chain;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.kxxnzstdsw.linkhorizon.utils.UrlUtils;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Component
public class ChainHandler {
    private final ReactiveRedisOperations<String, String> redisOps;

    ChainHandler(ReactiveRedisOperations<String, String> redisOps) {
        this.redisOps = redisOps;
    }

    public Mono<ServerResponse> generate(ServerRequest request) {
        String originUrl = request.queryParam("url").orElse(null);
        if (!UrlUtils.validURL(originUrl)) {
            return ServerResponse.badRequest().build();
        } else {
            assert originUrl != null;
            return redisOps.hasKey(originUrl).flatMap(hasKey -> {
                if (hasKey) {
                    return redisOps.opsForValue().get(originUrl).flatMap(targetURL -> {
                        return redisOps.opsForValue().set(originUrl, targetURL, Duration.ofHours(1)).then(
                                        redisOps.opsForValue().set(targetURL.split("/")[2], originUrl, Duration.ofHours(1)))
                                .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(new Chain(targetURL, originUrl)));
                    });
                } else {
                    UUID uuid = UUID.randomUUID();
                    String randomStr = RandomUtil.randomString(1024);
                    String targetURL = "/f/" + uuid.toString() + "/" + randomStr;
                    return redisOps.opsForValue().set(originUrl, targetURL, Duration.ofHours(1))
                            .then(redisOps.opsForValue().set(uuid.toString(), originUrl, Duration.ofHours(1)))
                            .then(ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(new Chain(targetURL, originUrl)));
                }
            });
        }
    }

    public Mono<ServerResponse> redirect(ServerRequest request) {
        String uuid = request.pathVariable("uuid");
        return redisOps.opsForValue().get(uuid)
                .flatMap(originURL -> ServerResponse.temporaryRedirect(URI.create(originURL)).build());
    }

}
