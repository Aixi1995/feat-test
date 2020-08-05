package com.example.elasticsearch;

import com.example.elasticsearch.DO.MyIndexDO;
import org.junit.Test;

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
}
