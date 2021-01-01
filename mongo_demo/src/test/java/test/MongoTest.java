package test;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MongoTest {

    private MongoClient mongoClient;
    private MongoCollection<Document> comment;

    @Before
    public void init() {
        // 1.创建操作Mongodb的客户端
        mongoClient = new MongoClient("81.70.164.129", 27117);

        // 2.选择数据库 ---- use commentdb
        MongoDatabase commentdb = mongoClient.getDatabase("commentdb");

        // 3.获取集合 ---- db.comment
        comment = commentdb.getCollection("comment");
    }

    // 查询所有数据
    // db.comment.find()
    @Test
    public void test1() {
        // 4.使用集合进行查询，查询所有数据
        FindIterable<Document> documents = comment.find();

        // 5.解析结果集（打印）
        for (Document document : documents) {
            System.out.println("------------------------");
            System.out.println("_id:" + document.get("_id"));
            System.out.println("content:" + document.get("content"));
            System.out.println("userid:" + document.get("userid"));
            System.out.println("thumbup:" + document.get("thumbup"));
            System.out.println("------------------------");
        }
    }

    @Test
    public void test2() {
        // 封装查询条件
        BasicDBObject bson = new BasicDBObject("_id", "1");

        // 执行查询
        FindIterable<Document> documents = comment.find(bson);

        for (Document document : documents) {
            System.out.println("------------------------");
            System.out.println("_id:" + document.get("_id"));
            System.out.println("content:" + document.get("content"));
            System.out.println("userid:" + document.get("userid"));
            System.out.println("thumbup:" + document.get("thumbup"));
            System.out.println("------------------------");
        }
    }

    @Test
    public void Test3() {
        // 封装新增数据
        Map<String, Object> map = new HashMap<>();
        map.put("_id", "6");
        map.put("content", "新增测试");
        map.put("userid", "1017");
        map.put("thumbup", "666");

        // 封装新增文档对象
        Document document = new Document(map);

        // 执行添加
        comment.insertOne(document);
    }

    // 修改，db.comment.update({"_id":"5"}, {$set:{"userid":"888"}})
    @Test
    public void test4() {
        // 创建修改的条件
        BasicDBObject filter = new BasicDBObject("_id", "5");

        // 创建修改的值
        BasicDBObject update = new BasicDBObject("$set", new Document("userid", "999"));

        // 执行修改
        comment.updateOne(filter, update);
    }

    @Test
    public void test5() {
        // 创建删除条件
        BasicDBObject bson = new BasicDBObject("_id", "6");

        // 执行删除
        comment.deleteOne(bson);
    }

    @After
    public void after() {
        // 释放资源，关闭客户端
        mongoClient.close();
    }

}
