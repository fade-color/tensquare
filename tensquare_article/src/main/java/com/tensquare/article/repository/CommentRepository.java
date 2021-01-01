package com.tensquare.article.repository;

import com.tensquare.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> { // 操作对象，对象主键类型
    // SpringDataMongoDB，支持通过查询方法名进行查询定义的方式

    // 根据文章id查询文章评论数据
    List<Comment> findByArticleId(String articleId);

    // 根据发布时间和点赞数查询
    List<Comment> findByPublishDateAndThumbUp(Date date, Integer thumbUp);

    // 根据用户ID查询，并根据时间倒序排序
    List<Comment> findByUserIdOrderByPublishDateDesc(String userId);
}
