package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.FacultyExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FacultyMapper {
    int countByExample(FacultyExample example);

    int deleteByExample(FacultyExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Faculty record);

    int insertSelective(Faculty record);

    List<Faculty> selectByExample(FacultyExample example);

    Faculty selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Faculty record, @Param("example") FacultyExample example);

    int updateByExample(@Param("record") Faculty record, @Param("example") FacultyExample example);

    int updateByPrimaryKeySelective(Faculty record);

    int updateByPrimaryKey(Faculty record);
}