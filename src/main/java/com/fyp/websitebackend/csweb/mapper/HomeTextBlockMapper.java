package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.HomeTextBlock;
import com.fyp.websitebackend.csweb.domain.HomeTextBlockExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HomeTextBlockMapper {
    int countByExample(HomeTextBlockExample example);

    int deleteByExample(HomeTextBlockExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeTextBlock record);

    int insertSelective(HomeTextBlock record);

    List<HomeTextBlock> selectByExample(HomeTextBlockExample example);

    HomeTextBlock selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeTextBlock record, @Param("example") HomeTextBlockExample example);

    int updateByExample(@Param("record") HomeTextBlock record, @Param("example") HomeTextBlockExample example);

    int updateByPrimaryKeySelective(HomeTextBlock record);

    int updateByPrimaryKey(HomeTextBlock record);
}