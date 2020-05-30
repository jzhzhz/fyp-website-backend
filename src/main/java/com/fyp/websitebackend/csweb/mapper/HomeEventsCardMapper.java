package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.HomeEventsCard;
import com.fyp.websitebackend.csweb.domain.HomeEventsCardExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HomeEventsCardMapper {
    int countByExample(HomeEventsCardExample example);

    int deleteByExample(HomeEventsCardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeEventsCard record);

    int insertSelective(HomeEventsCard record);

    List<HomeEventsCard> selectByExample(HomeEventsCardExample example);

    HomeEventsCard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeEventsCard record, @Param("example") HomeEventsCardExample example);

    int updateByExample(@Param("record") HomeEventsCard record, @Param("example") HomeEventsCardExample example);

    int updateByPrimaryKeySelective(HomeEventsCard record);

    int updateByPrimaryKey(HomeEventsCard record);
}