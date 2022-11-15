package com.lening.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lening.entity.Student;
import com.lening.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 马晓峰（乐柠教育）
 * @since 2022-10-03
 */
//@RestController
@RequestMapping("/students")
public class StudentController2 {
    @Autowired
    private IStudentService service;
    @GetMapping
    public List<Student> getAll(){

        return service.list();
    }

    @PostMapping
    public Boolean save(@RequestBody Student student){
        return service.save( student);
    }
    @PutMapping
    public Boolean update(@RequestBody Student student){
        return service.updateById(student);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable int id){
        return service.removeById(id);
    }
    @GetMapping("/{id}")
    public Student getByid(@PathVariable int id){
        return service.getById(id);
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public IPage getpage(@PathVariable int pageNum,@PathVariable int pageSize){
        IPage iPage = new Page(pageNum,pageSize);
        IPage page = service.page(iPage);

        return page;
    }


}

