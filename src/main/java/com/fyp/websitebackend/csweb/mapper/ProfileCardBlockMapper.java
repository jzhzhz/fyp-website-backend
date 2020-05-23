package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.ProfileCardBlock;
import com.fyp.websitebackend.csweb.domain.ProfileCardBlockExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ProfileCardBlockMapper {
    int countByExample(ProfileCardBlockExample example);

    int deleteByExample(ProfileCardBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ProfileCardBlock record);

    int insertSelective(ProfileCardBlock record);

    List<ProfileCardBlock> selectByExample(ProfileCardBlockExample example);

    ProfileCardBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ProfileCardBlock record, @Param("example") ProfileCardBlockExample example);

    int updateByExample(@Param("record") ProfileCardBlock record, @Param("example") ProfileCardBlockExample example);

    int updateByPrimaryKeySelective(ProfileCardBlock record);

    int updateByPrimaryKey(ProfileCardBlock record);
}