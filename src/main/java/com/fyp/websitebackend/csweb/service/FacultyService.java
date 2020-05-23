package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.param.*;
import com.fyp.websitebackend.csweb.domain.*;
import com.fyp.websitebackend.csweb.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {
    @Autowired
    FacultyMapper facultyMapper;

    @Autowired
    WebsiteMapper websiteMapper;

    @Autowired
    ProfileGeneralMapper profileGeneralMapper;

    @Autowired
    ProfileCardBlockMapper profileCardBlockMapper;

    @Autowired
    ProfileCustomBlockMapper profileCustomBlockMapper;

    public List<Faculty> getFacultyByNameWithType(SearchFacultyParam searchFacultyParam) {
        String nameToSearch = "%" + searchFacultyParam.getName() + "%";
        List<Faculty> facultyList = websiteMapper
                .searchFacultyByName(nameToSearch, searchFacultyParam.getType());
        if (facultyList == null || facultyList.size() <= 0) {
            return new ArrayList<>(0);
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

    public int updateGeneralProfile(UpdateProfileGeneralParam param) {
        ProfileGeneral profileGeneral = new ProfileGeneral();
        BeanUtils.copyProperties(param, profileGeneral);

        List<ProfileGeneral> retProfile = websiteMapper.getGeneralProfileByUsername(param.getUsername());

        if (retProfile == null || retProfile.size() <= 0) {
            websiteMapper.insertNewGeneralProfile(profileGeneral);

            return profileGeneral.getId();
        } else {
            ProfileGeneralExample example = new ProfileGeneralExample();
            example.createCriteria().andUsernameEqualTo(param.getUsername());

            return profileGeneralMapper.updateByExampleSelective(profileGeneral,example);
        }
    }

    public List<ProfileCardBlock> getProfileCardBlockByUsername(String username) {
        List<ProfileCardBlock> profileCardBlocks = websiteMapper.getProfileCardBlockByUsername(username);

        if (profileCardBlocks == null || profileCardBlocks.size() <= 0) {
            return new ArrayList<>(0);
        } else {
            return profileCardBlocks;
        }
    }

    public int updateProfileCard(UpdateProfileCardParam param){
        ProfileCardBlock profileCardBlock = new ProfileCardBlock();
        BeanUtils.copyProperties(param, profileCardBlock);

        // search existing card with id and username
        ProfileCardBlockExample selectExample = new ProfileCardBlockExample();
        selectExample.createCriteria().andIdEqualTo(param.getId())
                .andUsernameEqualTo(param.getUsername());

        List<ProfileCardBlock> retCardBlock = profileCardBlockMapper.selectByExample(selectExample);

        if (retCardBlock == null || retCardBlock.size() <= 0) {
            websiteMapper.insertNewCardProfile(profileCardBlock);

            return profileCardBlock.getId();
        } else {
            ProfileCardBlockExample example = new ProfileCardBlockExample();
            example.createCriteria().andUsernameEqualTo(param.getUsername())
                .andIdEqualTo(param.getId());

            // return 0
            return profileCardBlockMapper.updateByExampleSelective(profileCardBlock, example) - 1;
        }
    }

    public List<ProfileCustomBlock> getProfileCustomBlockByUsername(String username) {
        List<ProfileCustomBlock> profileCustomBlocks = websiteMapper.getProfileCustomBlockByUsername(username);

        if (profileCustomBlocks == null || profileCustomBlocks.size() <= 0) {
            return new ArrayList<>(0);
        } else {
            return profileCustomBlocks;
        }
    }

    public int updateCustomProfile(UpdateProfileCustomParam param){
        ProfileCustomBlock profileCustomBlock = new ProfileCustomBlock();
        BeanUtils.copyProperties(param, profileCustomBlock);

        ProfileCustomBlockExample selectExample = new ProfileCustomBlockExample();
        selectExample.createCriteria().andIdEqualTo(param.getId())
                .andUsernameEqualTo(param.getUsername());

        List<ProfileCustomBlock> retCustomBlock = profileCustomBlockMapper.selectByExample(selectExample);

        if (retCustomBlock == null || retCustomBlock.size() <= 0) {
            websiteMapper.insertNewCustomProfile(profileCustomBlock);

            return profileCustomBlock.getId();
        } else {
            ProfileCustomBlockExample example = new ProfileCustomBlockExample();
            example.createCriteria().andUsernameEqualTo(param.getUsername())
                .andIdEqualTo(param.getId());

            // return 0
            return profileCustomBlockMapper.updateByExampleSelective(profileCustomBlock, example) - 1;
        }
    }
}
