package org.example.bigevent.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.bigevent.mapper.ArticleMapper;
import org.example.bigevent.pojo.Article;
import org.example.bigevent.pojo.PageBean;
import org.example.bigevent.service.ArticleService;
import org.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServicelmpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public void addArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String,Object> map= ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("userId"));
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        //创建pageBean对象
        PageBean<Article> pageBean = new PageBean<>();

        //开启分页查询pageHelper
        //他会将查询结果封装到Page对象中，还会将传入的参数拼接在sql语句中，实现动态sql查询
        PageHelper.startPage(pageNum, pageSize);
        //从ThreadLocal中获取用户id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("userId");
        //调用mapper查询方法
        List<Article> list = articleMapper.list(categoryId,state,userId);
        if (list instanceof Page) {
            Page<Article> page = (Page<Article>) list;
            pageBean.setTotal(page.getTotal());
            pageBean.setItems(page.getResult());
        } else {
            // 如果不是Page类型，手动设置
            pageBean.setTotal((long) list.size());
            pageBean.setItems(list);
        }

        return pageBean;
    }

    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        //从ThreadLocal中获取用户id
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("userId");
        article.setCreateUser(userId);
        articleMapper.update(article);
    }

    @Override
    public Article detail(Integer id) {
        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("userId");
        //调用mapper查询方法
        Article article = articleMapper.detail(id,userId);
        return article;
    }

    @Override
    public void delete(Integer id) {

        Map<String,Object> map= ThreadLocalUtil.get();
        Integer userId=(Integer)map.get("userId");
        //调用mapper查询方法
        articleMapper.delete(id,userId);
    }
}
