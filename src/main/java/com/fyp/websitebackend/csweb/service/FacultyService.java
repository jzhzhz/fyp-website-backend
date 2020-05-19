package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.param.SearchFacultyParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateFacultyUrlParam;
import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.FacultyExample;
import com.fyp.websitebackend.csweb.mapper.FacultyMapper;
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

    public List<Faculty> getFacultyByNameWithType(SearchFacultyParam searchFacultyParam) {
        String nameToSearch = "%" + searchFacultyParam.getName() + "%";
        List<Faculty> facultyList = websiteMapper
                .searchFacultyByName(nameToSearch, searchFacultyParam.getType());
        if (facultyList == null && facultyList.size() <= 0) {
            return null;
        }

        return facultyList;
    }

    public int updateFacultyUrl(UpdateFacultyUrlParam updateFacultyUrlParam) {
        Faculty faculty = new Faculty();
        BeanUtils.copyProperties(updateFacultyUrlParam, faculty);

        FacultyExample facultyExample = new FacultyExample();
        facultyExample.createCriteria().andIdEqualTo(updateFacultyUrlParam.getId());

        return facultyMapper.updateByExampleSelective(faculty, facultyExample);
    }
}
