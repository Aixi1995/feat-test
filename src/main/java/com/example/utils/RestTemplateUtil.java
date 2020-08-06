package com.example.utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 16:17
 * @desc 可以单独配置超时时间和重试次数的RestTemplate工具类
 */
public class RestTemplateUtil {

    public static <T> T get(String url, Class<T> clazz, long timeout, int limit) throws Exception {
        return reTry(url, timeout, limit, u -> {
            var template = new RestTemplate();
            return template.getForEntity(u, clazz).getBody();
        });
    }

    public static <T> T post(String url, Object params, Class<T> clazz, long timeout, int limit) throws Exception {
        return reTry(url, timeout, limit, u -> {
            var template = new RestTemplate();
            //设置请求头，以json形式入参。。。
            var headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            var entity = new HttpEntity<>(params, headers);
            return template.postForEntity(u, entity, clazz).getBody();
        });
    }

    private static <T> T reTry(String url, long timeout, int limit, Function<String, T> function) throws Exception {
        var exception = new Exception("try failed...");
        for (int i = 0; i < limit; i++) {
            try {
                CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> function.apply(url));
                return future.get(timeout, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                exception = e;
            }
        }
        throw exception;
    }
}
