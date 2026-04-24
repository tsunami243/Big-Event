package org.example.bigevent.mapper;

import org.apache.ibatis.annotations.*;
import org.example.bigevent.pojo.Article;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Insert("insert into article (title,content,cover_img,state,category_id,create_user,create_time,update_time) values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
    void add(Article article);

    List<Article> list(@Param("categoryId") Integer categoryId, @Param("state") String state, @Param("userId") Integer userId);
    //@Param注解用于将方法参数绑定到SQL语句中的占位符
    @Update("update article set title=#{article.title},content=#{article.content},cover_img=#{article.coverImg},state=#{article.state},category_id=#{article.categoryId},update_time=#{article.updateTime} where id=#{article.id} and create_user=#{article.createUser}")
    void update(@Param("article") Article article);
    //根据文章id查询文章详情
    @Select("select * from article where id=#{id} and create_user=#{userId}")
    Article detail(@Param("id") Integer id, @Param("userId") Integer userId);

    //根据文章id删除文章
    @Delete("delete from article where id=#{id} and create_user=#{userId}")
    void delete(@Param("id") Integer id, @Param("userId") Integer userId);
}
