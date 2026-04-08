package org.example.bigevent.controller;

import org.example.bigevent.pojo.Category;
import org.example.bigevent.pojo.Result;
import org.example.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){

        categoryService.add(category);
        return Result.success();
    }

    @GetMapping
    public Result<List<Category>> list(){
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    @GetMapping("/detail")
    public Result<Category> detail( Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }

    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(Integer id){
        if (id == null){
            return Result.error("id不能为空");
        }
        List<Integer> ids = categoryService.findIds();
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) == id){
                categoryService.delete(id);
                return Result.success();
            }
        }
        return Result.error("该分类不存在");
    }
}
