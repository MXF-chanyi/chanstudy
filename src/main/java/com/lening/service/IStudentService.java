package com.lening.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lening.entity.Student;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 马晓峰（乐柠教育）
 * @since 2022-10-03
 */
public interface IStudentService extends IService<Student> {
    IPage<Student> selectConnAndPages(int currentPage, int pageSize, Student student);
}
