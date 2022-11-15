package com.lening.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lening.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 马晓峰（乐柠教育）
 * @since 2022-10-03
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
