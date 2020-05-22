package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.param.SearchFacultyParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateFacultyUrlParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateGeneralProfileParam;
import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.FacultyExample;
import com.fyp.websitebackend.csweb.domain.ProfileGeneral;
import com.fyp.websitebackend.csweb.domain.ProfileGeneralExample;
import com.fyp.websitebackend.csweb.mapper.FacultyMapper;
import com.fyp.websitebackend.csweb.mapper.ProfileGeneralMapper;
import com.fyp.websitebackend.csweb.mapper.WebsiteMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    FacultyMapper facultyMapper;

    @Autowired
    WebsiteMapper websiteMapper;

    @Autowired
    ProfileGeneralMapper profileGeneralMapper;

    public List<Faculty> getFacultyByNameWithType(SearchFacultyParam searchFacultyParam) {
        String nameToSearch = "%" + searchFacultyParam.getName() + "%";
        List<Faculty> facultyList = websiteMapper
                .searchFacultyByName(nameToSearch, searchFacultyParam.getType());
        if (facultyList == null || facultyList.size() <= 0) {
            return null;
        }

        return facultyList;
    }

    public List<ProfileGeneral> getGeneralProfileByUsername(String username) {
        List<ProfileGeneral> profileGenerals = websiteMapper.getGeneralProfileByUsername(username);

        if (profileGenerals == null || profileGenerals.size() <= 0) {
            return null;
        }

        return profileGenerals;
    }

    public int updateFacultyUrl(UpdateFacultyUrlParam updateFacultyUrlParam) {
        Faculty faculty = new Faculty();
        BeanUtils.copyProperties(updateFacultyUrlParam, faculty);

        FacultyExample facultyExample = new FacultyExample();
        facultyExample.createCriteria().andIdEqualTo(updateFacultyUrlParam.getId());

        return facultyMapper.updateByExampleSelective(faculty, facultyExample);
    }

    public int updateGeneralProfile(UpdateGeneralProfileParam param) {
        ProfileGeneral profileGeneral = new ProfileGeneral();
        BeanUtils.copyProperties(param, profileGeneral);

        List<ProfileGeneral> retProfile = websiteMapper.getGeneralProfileByUsername(param.getUsername());

        if (retProfile == null || retProfile.size() <= 0) {
            // insert

            return profileGeneral.getId();
        } else {
            ProfileGeneralExample example = new ProfileGeneralExample();
            example.createCriteria().andUsernameEqualTo(param.getUsername());

            return profileGeneralMapper.updateByExampleSelective(profileGeneral,example);
        }
    }
}
