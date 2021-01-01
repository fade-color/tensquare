package com.tensquare.article.controller;

import com.tensquare.article.pojo.Comment;
import com.tensquare.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RedisTemplate redisTemplate;

    // PUT /comment/thumbup/{commentId}
    @RequestMapping(value = "thumbup/{commentId}", method = RequestMethod.PUT)
    public Result thumbUp(@PathVariable String commentId) {
        // 把用户点赞信息保存到Redis中
        // 每次点赞之前，先查询用户点赞信息
        // 如果没有点赞信息，用户可以点赞
        // 如果存在点赞信息，用户不能重复点赞

        // 模拟用户id
        String userid = "123";

        // 根据用户id和评论id，查询用户点赞信息
        Object flag = redisTemplate.opsForValue().get("thumbup_" + userid + "_" + commentId);

        // 判断查询到的结果是否为空
        if (flag == null) {
            commentService.thumbUp(commentId);

            // 点赞成功，保存点赞信息
            redisTemplate.opsForValue().set("thumbup_" + userid + "_" + commentId, 1);

            return new Result(true, StatusCode.OK, "点赞成功");
        }

        // 如果为空，表示没有点过赞，可以点赞
        return new Result(false, StatusCode.REP_ERROR, "不能重复点赞");
    }

    // GET /comment/article/{articleId} 根据文章id查询评论
    @RequestMapping(value = "article/{articleId}", method = RequestMethod.GET)
    public Result findByArticleId(@PathVariable String articleId) {
        List<Comment> commentList = commentService.findByArticleId(articleId);
        return new Result(true, StatusCode.OK, "查询成功", commentList);
    }

    // GET /comment 查询所有评论
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        List<Comment> list = commentService.findAll();

        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    // GET /comment/{commentId} 根据Id查询评论数据
    @RequestMapping(value = "{commentId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String commentId) {
        Comment comment = commentService.findById(commentId);
        return new Result(true, StatusCode.OK, "查询成功", comment);
    }

    // POST /comment 新增评论
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Comment comment) {
        commentService.save(comment);
        return new Result(true, StatusCode.OK, "新增成功");
    }

    // PUT /comment/{commentId} 修改评论
    @RequestMapping(value = "{commentId}", method = RequestMethod.PUT)
    public Result updateById(@PathVariable String commentId,
                             @RequestBody Comment comment) {
        // 设置评论主键
        comment.set_id(commentId);
        // 执行修改
        commentService.updateById(comment);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    // DELETE /comment/{commentId}
    @RequestMapping(value = "{commentId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String commentId) {
        commentService.delete(commentId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

}
