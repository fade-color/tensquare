package com.tensquare.article.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tensquare.article.dao.ArticleDao;
import com.tensquare.article.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private IdWorker idWorker;

    public List<Article> findAll() {
        return articleDao.selectList(null);
    }

    public Article findById(String articleId) {
        return articleDao.selectById(articleId);
    }

    public void save(Article article) {
        // 使用分布式ID生成器生成
        String id = idWorker.nextId() + "";
        article.setId(id);

        // 对部分数据进行初始化
        article.setVisits(0);   // 浏览量
        article.setThumbUp(0);  // 点赞数
        article.setComment(0);  // 评论数

        // 新增
        articleDao.insert(article);
    }

    public void updateById(Article article) {
        // 根据主键id修改
        articleDao.updateById(article);

        // 根据条件修改
        // 创建条件对象
        EntityWrapper<Article> wrapper = new EntityWrapper<>();
        wrapper.eq("id", article.getId());

        articleDao.update(article, wrapper);
    }

    public void deleteById(String articleId) {
        articleDao.deleteById(articleId);
    }

    public Page<Article> findByPage(Map<String, Object> article, Integer page, Integer size) {
        // 设置查询条件
        EntityWrapper<Article> wrapper = new EntityWrapper<>();
        Set<String> keySet = article.keySet();
        for (String key : keySet) {
//            if (article.get(key) != null) {
//                wrapper.eq(key, article.get(key));
//            }
            // 和上面if判断是一样的，实现动态sql
            wrapper.eq(article.get(key) != null, key, article.get(key));
        }

        // 设置分页参数
        Page<Article> pageData = new Page<>(page, size);

        // 执行查询
        // 第一个是分页参数，第二个是查询条件
        List<Article> list = articleDao.selectPage(pageData, wrapper);
        pageData.setRecords(list);

        // 返回
        return pageData;

    }
}
