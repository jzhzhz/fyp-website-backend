package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.ProfileCustomBlock;
import com.fyp.websitebackend.csweb.domain.ProfileCustomBlockExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProfileCustomBlockMapper {
    int countByExample(ProfileCustomBlockExample example);

    int deleteByExample(ProfileCustomBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProfileCustomBlock record);

    int insertSelective(ProfileCustomBlock record);

    List<ProfileCustomBlock> selectByExample(ProfileCustomBlockExample example);

    ProfileCustomBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProfileCustomBlock record, @Param("example") ProfileCustomBlockExample example);

    int updateByExample(@Param("record") ProfileCustomBlock record, @Param("example") ProfileCustomBlockExample example);

    int updateByPrimaryKeySelective(ProfileCustomBlock record);

    int updateByPrimaryKey(ProfileCustomBlock record);
}