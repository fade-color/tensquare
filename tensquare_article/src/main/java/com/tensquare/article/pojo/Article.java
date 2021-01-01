package com.tensquare.article.pojo;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("tb_article")
public class Article implements Serializable {

    @TableId(type = IdType.INPUT)
    private String id;

    private String columnId;    // 专栏ID
    private String userId;      // 用户ID
    private String title;       // 标题
    private String content;     // 文章正文
    private String image;       // 文章封面
    private Date createTime;    // 发表日期
    private Date updateTime;    // 修改日期
    private String isPublic;    // 是否公开
    private String isTop;       // 是否置顶
    private Integer visits;     // 浏览量
    private Integer thumbUp;    // 点赞数
    private Integer comment;    // 评论数
    private String state;       // 审核状态
    private String channelId;   // 所属频道
    private String url;         // URL
    private String type;        // 类型

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", columnId='" + columnId + '\'' +
                ", userId='" + userId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isPublic='" + isPublic + '\'' +
                ", isTop='" + isTop + '\'' +
                ", visits=" + visits +
                ", thumbUp=" + thumbUp +
                ", comment=" + comment +
                ", state='" + state + '\'' +
                ", channelId='" + channelId + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    // getter and setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColumnId() {
        return columnId;
    }

    public void setColumnId(String columnId) {
        this.columnId = columnId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(String isPublic) {
        this.isPublic = isPublic;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbUp() {
        return thumbUp;
    }

    public void setThumbUp(Integer thumbUp) {
        this.thumbUp = thumbUp;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
