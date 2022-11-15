package com.lening.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lening.controller.utils.R;
import com.lening.entity.Student;
import com.lening.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 马晓峰（乐柠教育）
 * @since 2022-10-03
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private IStudentService service;
    @GetMapping
    public R getAll(){

       return new R(true, service.list());
    }

    @PostMapping
    public R save(@RequestBody Student student) throws IOException {
        if(student.getSname().equals("123")) {
            throw  new IOException();
        }
        boolean flag = service.save(student);
        return new R(flag,flag ? "添加成功 ( ^_^ )":"添加失败╰(￣▽￣)╮") ;
    }
    @PutMapping
    public R update(@RequestBody Student student){
        boolean flag = service.updateById(student);
        return new R(flag,flag ? "修改成功 ( ^_^ )":"修改失败╰(￣▽￣)╮") ;
    }

    @DeleteMapping("/{id}")
    public R delete(@PathVariable int id){
        boolean flag = service.removeById(id);

        return new R(flag,flag ? "删除成功 ( ^_^ )":"数据同步失败,请刷新页面╰(￣▽￣)╮") ;
    }
    @GetMapping("/{id}")
    public R getByid(@PathVariable int id){
        return new R(true, service.getById(id));
    }

/*    @GetMapping("/{currentPage}/{pageSize}")
    public R getpage(@PathVariable int currentPage,@PathVariable int pageSize){
            IPage iPage = new Page(currentPage,pageSize);
        System.out.println(currentPage);

        IPage page = service.page(iPage);
        System.out.println(page.getCurrent());
        //如果当前页码值大于总页面值 那么重新执行查询操作 使最大页面值作为当前也
        if(currentPage > page.getPages()){
            iPage = new Page(page.getPages(),pageSize);
            page = service.page(iPage);
        }
        return new R(true,page);
    }*/

        @GetMapping("/{currentPage}/{pageSize}")
    public R getpage(@PathVariable int currentPage,@PathVariable int pageSize,Student student) {

           /* IPage iPage = new Page(currentPage,pageSize);
        IPage page = service.page(iPage);
        //如果当前页码值大于总页面值 那么重新执行查询操作 使最大页面值作为当前也
        if(currentPage > page.getPages()){
            iPage = new Page(page.getPages(),pageSize);
            page = service.page(iPage);
        }
        return new R(true,page)*/;
            IPage<Student> page = service.selectConnAndPages(currentPage,pageSize,student);

            return new R(true,page);
    }


}

