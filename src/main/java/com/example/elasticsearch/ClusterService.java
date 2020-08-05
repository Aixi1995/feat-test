package com.example.elasticsearch;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 10:29
 */
public class ClusterService {

    private final RestHighLevelClient client = ClientService.getClient();

    /**
     * cluster health
     *
     * @return status for the cluster
     */
    public String getClusterHealth() {
        ClusterHealthRequest request = new ClusterHealthRequest();
        try {
            var response = client.cluster().health(request, RequestOptions.DEFAULT);
            return response.getStatus().name();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
