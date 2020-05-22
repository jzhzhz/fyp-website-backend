package com.fyp.websitebackend.csweb.controller;

import com.fyp.websitebackend.common.entity.CustomResponseEntity;
import com.fyp.websitebackend.csweb.controller.param.SearchFacultyParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateFacultyUrlParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateGeneralProfileParam;
import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.ProfileGeneral;
import com.fyp.websitebackend.csweb.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @RequestMapping("/searchFacultyByNameWithType")
    public CustomResponseEntity searchFaculty(SearchFacultyParam searchFacultyParam) {
        List<Faculty> facultyList = facultyService.getFacultyByNameWithType(searchFacultyParam);

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

    @RequestMapping("updateGeneralProfile")
    public CustomResponseEntity updateGeneralProfile(UpdateGeneralProfileParam param) {
        int result = facultyService.updateGeneralProfile(param);

        return CustomResponseEntity.success(result);
    }
}
