package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.HomeCard;
import com.fyp.websitebackend.csweb.domain.HomeTextBlock;
import com.fyp.websitebackend.csweb.domain.Label;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WebsiteMapper {
    @Select("SELECT * FROM home_textblock WHERE type = #{blockType} AND deprecated = 0")
    List<HomeTextBlock> selectHomeTextBlockByType(String blockType);

    @Select(("SELECT * , code_content AS codeContent FROM navbar_labels WHERE deprecated = 0 AND url = #{labelUrl} "))
    List<Label> selectLabelByUrl(String labelUrl);

    @Insert("INSERT INTO cards (title, text, url, img_name, img_url, date, deprecated)" +
            "VALUES (#{title}, #{text}, #{url}, #{imgName}, #{imgUrl}, #{date}, #{deprecated})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertNewCard(HomeCard homeCard);

    @Select("SELECT * , code_content AS codeContent FROM navbar_labels WHERE deprecated = 0 AND type = #{labelType}")
    List<Label> selectAllLabels(String labelType);

    @Insert("INSERT INTO navbar_labels (label, url, code_content, deprecated, type)" +
            "VALUES (#{label}, #{url}, #{codeContent}, #{deprecated}, #{type})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int insertNewLabel(Label label);

    @Select("SELECT * FROM faculty WHERE type = #{facultyType} ORDER BY name")
    List<Faculty> getFacultyByType(String facultyType);

    @Select("SELECT *, img_url AS imgUrl FROM cards WHERE deprecated = 0")
    List<HomeCard> getAllCard();

    List<Faculty> searchFacultyByName(@Param("name") String name, @Param("facultyType") String facultyType);
}
