package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.ProfileGeneral;
import com.fyp.websitebackend.csweb.domain.ProfileGeneralExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProfileGeneralMapper {
    int countByExample(ProfileGeneralExample example);

    int deleteByExample(ProfileGeneralExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProfileGeneral record);

    int insertSelective(ProfileGeneral record);

    List<ProfileGeneral> selectByExample(ProfileGeneralExample example);

    ProfileGeneral selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProfileGeneral record, @Param("example") ProfileGeneralExample example);

    int updateByExample(@Param("record") ProfileGeneral record, @Param("example") ProfileGeneralExample example);

    int updateByPrimaryKeySelective(ProfileGeneral record);

    int updateByPrimaryKey(ProfileGeneral record);
}