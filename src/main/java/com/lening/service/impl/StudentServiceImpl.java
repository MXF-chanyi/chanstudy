package com.lening.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lening.entity.Student;
import com.lening.mapper.StudentMapper;
import com.lening.service.IStudentService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 马晓峰（乐柠教育）
 * @since 2022-10-03
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private  StudentMapper studentMapper;

    @Override
    public IPage<Student> selectConnAndPages(int currentPage, int pageSize, Student student) {
        IPage iPage = new Page(currentPage,pageSize);
        LambdaQueryWrapper<Student> lqw = new LambdaQueryWrapper<>();
       /* lqw.between(Student::getSid, student.getSid(),student.getSid());
        lqw.between(Student::getAge, student.getAge(),student.getAge());*/
   lqw.like(Strings.isNotEmpty(student.getSname()),Student::getSname,student.getSname());
        IPage page = studentMapper.selectPage(iPage,lqw );
        return page;
    }
}
