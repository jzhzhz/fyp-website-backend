package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.FacultyExample;
import com.fyp.websitebackend.csweb.mapper.FacultyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    @Autowired
    FacultyMapper facultyMapper;

    List<Faculty> getCertainFaculty(String phone, String email) {
        FacultyExample facultyExample = new FacultyExample();
        facultyExample.createCriteria()
                .andPhoneEqualTo(phone)
                .andEmailEqualTo(email);

        List<Faculty> requiredFaculty = facultyMapper.selectByExample(facultyExample);
        if (requiredFaculty == null || requiredFaculty.size() <= 0) {
            return null;
        }

        return requiredFaculty;
    }
}
