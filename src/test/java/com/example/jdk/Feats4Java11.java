package com.example.jdk;

import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/7/30 15:56
 */
public class Feats4Java11 {

    @Test
    public void testStringAPI() {
        Assert.assertTrue("".isEmpty());
        Assert.assertTrue("".isBlank());
        Assert.assertEquals("java11", "  java11  ".strip());
        Assert.assertEquals("java11", "java11  ".stripTrailing());
        Assert.assertEquals("java11", "   java11".stripLeading());
        Assert.assertEquals("java11java11", "java11".repeat(2));
    }

    @Test
    public void testHttpClient() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder().uri(URI.create("https://api.gugudata.com/sms/mobileattribution/demo")).build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(System.out::println).join();
    }

}
