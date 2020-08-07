package com.example.elasticsearch;

import com.example.elasticsearch.DO.MyIndexDO;
import com.example.entity.UserInfo;
import org.elasticsearch.common.Strings;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/8/5 13:31
 */
public class DocumentServiceTest {

    DocumentService documentService = new DocumentService();
    String index = "my_index_20200805";

    @Test
    public void testIndexByJson() {

        var json = """
                {
                    "addr":"shanghai",
                    "age":25,
                    "email":"131@qq.com",
                    "itemId":2,
                    "score":99.9
                }
                """;
        var id = documentService.index(index, json);
        System.out.println(id);
    }

    @Test
    public void testIndexByObject() {
        MyIndexDO myIndexDO = new MyIndexDO();
        myIndexDO.setAddr("beijing");
        myIndexDO.setAge(25);
        myIndexDO.setEmail("133@qq.com");
        myIndexDO.setName("wang133");
        myIndexDO.setItemId(3);
        myIndexDO.setScore(99);
        var id = documentService.index(index, myIndexDO);
        System.out.println(id);
    }

    @Test
    public void testGetById() {
        System.out.println(documentService.getById(index, "50hJwXMBxRPVwbzykwey", UserInfo.class));
    }

    @Test
    public void testMultiGetByIds() {
        List<String> ids = new ArrayList<>();
        ids.add("50hJwXMBxRPVwbzykwey");
        ids.add("3khOvXMBxRPVwbzy5wfU");
        ids.add("4EhXvXMBxRPVwbzyUwds");
        documentService.multiGetByIds(index, ids, UserInfo.class).forEach(System.out::println);
    }

    @Test
    public void testExist() {
        Assert.assertTrue(documentService.exist(index, "50hJwXMBxRPVwbzykwey"));
    }

    @Test
    public void testUpdate() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "wangzhiqiang1");
        Assert.assertTrue(documentService.update(index, "50hJwXMBxRPVwbzykwey", map));
    }
}
