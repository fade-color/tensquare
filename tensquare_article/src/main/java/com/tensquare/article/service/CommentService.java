package com.tensquare.article.service;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(String commentId) {
        Optional<Comment> optional = commentRepository.findById(commentId);
        return optional.orElse(null);
    }

    public void save(Comment optional) {
        // 使用分布式ID生成器生成ID，保证唯一性
        String id = idWorker.nextId() + "";
        optional.set_id(id);

        // 初始化点赞数据，发布时间等
        optional.setThumbUp(0);
        optional.setPublishDate(new Date());

        // 保存数据
        commentRepository.save(optional);
    }

    public void updateById(Comment optional) {
        // 使用的是MongoRepository的方法
        // 其中save方法，主键如果存在，执行修改，如果不存在执行新增
        commentRepository.save(optional);
    }

    public void delete(String commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> findByArticleId(String articleId) {
        // 调用持久层，根据文章id查询
        List<Comment> commentList = commentRepository.findByArticleId(articleId);
        return commentList;
    }

    public void thumbUp(String commentId) {
//        ---------------------------
//        不能保证线程安全且性能较低
//        // 根据评论ID查询数据
//        Optional<Comment> optional = commentRepository.findById(commentId);
//        // 点赞数据加1
//        if (optional.isPresent()) {
//            Comment comment = optional.get();
//            comment.setThumbUp(comment.getThumbUp() + 1);
//            // 保存修改数据
//            commentRepository.save(comment);
//        }
//        ----------------------------

        // 点赞功能优化

        // 封装修改的条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(commentId));
        // 封装修改的数值
        Update update = new Update();
        update.inc("thumbUp", 1);
        mongoTemplate.updateFirst(query, update, "comment");
    }
}
