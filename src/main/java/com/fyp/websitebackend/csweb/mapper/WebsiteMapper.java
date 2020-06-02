package com.fyp.websitebackend.csweb.mapper;

import com.fyp.websitebackend.csweb.domain.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WebsiteMapper {
    @Select("SELECT * FROM home_textblock WHERE type = #{blockType} AND deprecated = 0")
    List<HomeTextBlock> selectHomeTextBlockByType(String blockType);

    @Select("SELECT * FROM home_textblock")
    List<HomeTextBlock> getAllHomeTextBlock();

    @Select(("SELECT * , code_content AS codeContent FROM navbar_labels WHERE deprecated = 0 AND url = #{labelUrl} "))
    List<Label> selectLabelByUrl(String labelUrl);

    @Insert("INSERT INTO cards (title, text, url, img_name, img_url, date, deprecated)" +
            "VALUES (#{title}, #{text}, #{url}, #{imgName}, #{imgUrl}, #{date}, #{deprecated})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertNewCard(HomeCard homeCard);

    @Insert("INSERT INTO home_events (title, subtitle, content, url, deprecated)" +
            "VALUES (#{title}, #{subtitle}, #{content}, #{url}, #{deprecated})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertNewEventsCard(HomeEventsCard homeEventsCard);

    @Select("SELECT * , code_content AS codeContent FROM navbar_labels WHERE deprecated = 0 AND type = #{labelType}")
    List<Label> getAllValidLabels(String labelType);

    @Insert("INSERT INTO navbar_labels (label, url, code_content, deprecated, type)" +
            "VALUES (#{label}, #{url}, #{codeContent}, #{deprecated}, #{type})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertNewLabel(Label label);

    @Select("SELECT *, img_url AS imgUrl FROM cards WHERE deprecated = 0")
    List<HomeCard> getAllValidCards();

    @Select("SELECT *, img_url AS imgUrl FROM cards")
    List<HomeCard> getAllCards();

    @Select("SELECT * FROM home_events")
    List<HomeEventsCard> getAllEvents();

    @Select("SELECT * FROM faculty")
    List<Faculty> getAllFaculties();

    @Select("SELECT * FROM admins")
    List<Admin> getAllAdmins();

    @Select("SELECT * FROM faculty WHERE type = #{facultyType} ORDER BY name")
    List<Faculty> getFacultyByType(String facultyType);

    List<Faculty> searchFacultyByName(@Param("name") String name, @Param("facultyType") String facultyType);

    @Select("SELECT *, img_url AS imgUrl FROM profile_general WHERE username = #{username}")
    List<ProfileGeneral> getGeneralProfileByUsername(String username);

    @Insert("INSERT INTO profile_general (username, intro, sidebar, img_url)" +
            "VALUES (#{username}, #{intro}, #{sidebar}, #{imgUrl})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertNewGeneralProfile(ProfileGeneral profileGeneral);

    @Select("SELECT *, img_url AS imgUrl, img_name AS imgName FROM profile_card_block WHERE username = #{username} AND deprecated = 0")
    List<ProfileCardBlock> getProfileCardBlockByUsername(String username);

    @Insert("INSERT INTO profile_card_block (username, title, text, url, img_name, img_url, type, deprecated)" +
            "VALUES (#{username}, #{title}, #{text}, #{url}, #{imgName}, #{imgUrl}, #{type}, #{deprecated})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertNewCardProfile(ProfileCardBlock profileCardBlock);

    @Select("SELECT *, date_bar AS dateBar, code_segment AS codeSegment FROM profile_custom_block WHERE username = #{username} AND deprecated = 0")
    List<ProfileCustomBlock> getProfileCustomBlockByUsername(String username);

    @Insert("INSERT INTO profile_custom_block (username, date_bar, code_segment, type, deprecated)" +
            "VALUES (#{username}, #{dateBar}, #{codeSegment}, #{type}, #{deprecated})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insertNewCustomProfile(ProfileCustomBlock profileCustomBlock);
}
