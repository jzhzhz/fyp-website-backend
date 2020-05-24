package com.fyp.websitebackend.csweb.controller;

import com.fyp.websitebackend.common.constants.WebConstants;
import com.fyp.websitebackend.common.entity.CustomResponseEntity;
import com.fyp.websitebackend.csweb.controller.param.*;
import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.ProfileCardBlock;
import com.fyp.websitebackend.csweb.domain.ProfileCustomBlock;
import com.fyp.websitebackend.csweb.domain.ProfileGeneral;
import com.fyp.websitebackend.csweb.service.AdminService;
import com.fyp.websitebackend.csweb.service.FacultyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @Autowired
    AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(FacultyController.class);

    @RequestMapping("/searchFacultyByNameWithType")
    public CustomResponseEntity searchFaculty(SearchFacultyParam searchFacultyParam) {
        List<Faculty> facultyList = facultyService.getFacultyByNameWithType(searchFacultyParam);

        return CustomResponseEntity.success(facultyList);
    }

    @RequestMapping("/getFacultyByUsername")
    public CustomResponseEntity getFacultyByUsername(String username) {
        List<Faculty> facultyList = facultyService.getFacultyByUsername(username);

        return CustomResponseEntity.success(facultyList);
    }

    @RequestMapping("/getGeneralProfile")
    public CustomResponseEntity getGeneralProfile(String username) {
        List<ProfileGeneral> profileGenerals = facultyService.getGeneralProfileByUsername(username);

        return CustomResponseEntity.success(profileGenerals);
    }

    @RequestMapping("/updateFacultyUrl")
    public CustomResponseEntity updateFacultyUrl(UpdateFacultyUrlParam updateFacultyUrlParam) {
        int result = facultyService.updateFacultyUrl(updateFacultyUrlParam);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/updateGeneralProfile")
    public CustomResponseEntity updateGeneralProfile(UpdateProfileGeneralParam param) {
        int result = facultyService.updateGeneralProfile(param);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/getProfileCard")
    public CustomResponseEntity getProfileCard(String username) {
        List<ProfileCardBlock> profileCardBlocks = facultyService.getProfileCardBlockByUsername(username);

        return CustomResponseEntity.success(profileCardBlocks);
    }

    @RequestMapping("/updateProfileCard")
    public CustomResponseEntity updateProfileCard(UpdateProfileCardParam param) {
        int result = facultyService.updateProfileCard(param);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/getProfileCustom")
    public CustomResponseEntity getProfileCustom(String username) {
        List<ProfileCustomBlock> profileCustomBlocks = facultyService.getProfileCustomBlockByUsername(username);

        return CustomResponseEntity.success(profileCustomBlocks);
    }

    @RequestMapping("/updateProfileCustom")
    public CustomResponseEntity updateProfileCustom(UpdateProfileCustomParam param) {
        int result = facultyService.updateCustomProfile(param);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/uploadProfileImg")
    public CustomResponseEntity uploadProfileImg(@RequestParam("file") MultipartFile file) {
        String fileType = file.getContentType();

        if (WebConstants.CARD_PIC_TYPES.contains(fileType)) {
            String visitUrl = adminService.saveCardPicture(file, "profile");

            logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
            return CustomResponseEntity.success(visitUrl);
        } else {
            logger.warn(String.format("File name '%s' has invalid file type, which is %s",
                    file.getOriginalFilename(),
                    file.getContentType()));
            return CustomResponseEntity.error("invalid file type!");
        }
    }
}
