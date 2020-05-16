package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.HomeCard;
import com.fyp.websitebackend.csweb.domain.HomeCardExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface HomeCardMapper {
    int countByExample(HomeCardExample example);

    int deleteByExample(HomeCardExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(HomeCard record);

    int insertSelective(HomeCard record);

    List<HomeCard> selectByExample(HomeCardExample example);

    HomeCard selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") HomeCard record, @Param("example") HomeCardExample example);

    int updateByExample(@Param("record") HomeCard record, @Param("example") HomeCardExample example);

    int updateByPrimaryKeySelective(HomeCard record);

    int updateByPrimaryKey(HomeCard record);
}