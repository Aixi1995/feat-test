package com.example.hive;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/12/9 13:01
 * @description
 */
@Slf4j
public class HiveTest {

    private static JdbcTemplate TEMPLATE;
    private static HikariDataSource DS;

    @BeforeClass
    public static void beforeClass() throws Exception {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.apache.hive.jdbc.HiveDriver");
        // 这里笔者修改过hive-site.xml的对应配置,因为端口不是默认的10000
        //config.setJdbcUrl("jdbc:hive2://172.31.231.204:10000");
        config.setJdbcUrl("jdbc:hive2://172.31.231.204:10000/db_test");
        DS = new HikariDataSource(config);
        TEMPLATE = new JdbcTemplate(DS);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        DS.close();
    }

    @Test
    public void testCreateDb() throws Exception {
        TEMPLATE.execute("CREATE DATABASE db_test");
    }

    @Test
    public void testCreateTable() throws Exception {
        TEMPLATE.execute("CREATE TABLE IF NOT EXISTS t_student(id INT,name string,major string)");
        log.info("创建t_student表成功");
    }

    @Test
    public void testInsert() throws Exception {
        long start = System.currentTimeMillis();
        int update = TEMPLATE.update("INSERT INTO TABLE t_student(id,name,major) VALUES(?,?,?)", p -> {
            p.setInt(1, 10088);
            p.setString(2, "throwable");
            p.setString(3, "math");
        });
        int update1 = TEMPLATE.update("INSERT INTO TABLE t_student(id,name,major) VALUES(?,?,?)", p -> {
            p.setInt(1, 10088);
            p.setString(2, "throwable");
            p.setString(3, "math");
        });
        log.info("写入t_student cost : {}", System.currentTimeMillis() - start);
        log.info("写入t_student成功,更新记录数:{}", update);
    }

    @Test
    public void testSelect() throws Exception {
        List<Student> result = TEMPLATE.query("SELECT * FROM t_student", rs -> {
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getLong("id"));
                student.setName(rs.getString("name"));
                student.setMajor(rs.getString("major"));
                list.add(student);
            }
            return list;
        });
        // 打印日志：查询t_student成功,结果:[HiveJdbcTest.Student(id=10087, name=throwable, major=math)]
        log.info("查询t_student成功,结果:{}", result);
    }

    @Data
    private static class Student {

        private Long id;
        private String name;
        private String major;
    }
}
