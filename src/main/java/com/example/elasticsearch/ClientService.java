package com.example.elasticsearch;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 15:42
 */
public class ClientService {

    private static RestHighLevelClient client = null;

    public static RestHighLevelClient getClient() {
        if (client == null) {
            client = new RestHighLevelClient(RestClient.builder(
                    new HttpHost("localhost", 9200, "http")
            ));
        }
        return client;
    }
}
