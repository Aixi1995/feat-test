package com.example.elasticsearch;

import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 15:55
 */
public class ClusterServiceTest {

    ClusterService clusterService = new ClusterService();


    @Test
    public void testClient() {
        var status = clusterService.getClusterHealth();
        System.out.println(status);
    }
}
