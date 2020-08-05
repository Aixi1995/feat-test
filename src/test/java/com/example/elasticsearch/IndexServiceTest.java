package com.example.elasticsearch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/4 16:25
 */
public class IndexServiceTest {

    IndexService indexService;
    String index = "my_index";
    String alias = "my_alias";

    @Before
    public void init() {
        indexService = new IndexService();
    }

    @Test
    public void testGetSettings() {
        var settings = indexService.getSettings("my_index");
        settings.forEach((k,v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void testGetMappings() {
        var mappings = indexService.getMappings(index);
        mappings.forEach((k,v) -> System.out.println(k + ":" + v));
    }

    @Test
    public void testExistIndices() {
        Assert.assertTrue(indexService.existIndices("my_index", "kibana_sample_data_logs"));
    }

    @Test
    public void testAlias() {
        Assert.assertTrue(indexService.addAlias(index, alias));
        Assert.assertTrue(indexService.existAliasFromIndex(index, alias));
        Assert.assertTrue(indexService.delAlias(index, alias));
        Assert.assertFalse(indexService.existAliases(index));
    }

    @Test
    public void testRefreshIndex() {
        indexService.refreshIndices(index);
    }

    @Test
    public void testCreateIndex() {
        var index = "my_index_20200805";
        Assert.assertTrue(indexService.createIndices(index));//创建索引
        Assert.assertTrue(indexService.putMappings(index));//更新mappings
        Assert.assertTrue(indexService.updateNumOfReplicas( 1, index));
        Assert.assertTrue(indexService.addAlias(index, "my_alias"));
    }
}
