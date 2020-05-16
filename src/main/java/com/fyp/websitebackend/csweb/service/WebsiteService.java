package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.vo.HomeTextBlockVO;
import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.HomeCard;
import com.fyp.websitebackend.csweb.domain.HomeTextBlock;
import com.fyp.websitebackend.csweb.domain.Label;
import com.fyp.websitebackend.csweb.mapper.WebsiteMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class WebsiteService {
    @Autowired
    WebsiteMapper websiteMapper;

    public List<Label> findAllLabels(String labelType) {
        return websiteMapper.selectAllLabels(labelType);
    }

    public List<HomeTextBlockVO> getHomeTextBlockByType(String type) {
        List<HomeTextBlock> rawHomeTextBlocks = websiteMapper.selectHomeTextBlockByType(type);
        if (rawHomeTextBlocks == null || rawHomeTextBlocks.size() <= 0) {
            return null;
        }

        List<HomeTextBlockVO> homeTextBlockVOs = new ArrayList<>();
//        Hashtable<Integer, String> blockTitles = new Hashtable<>();
//        int i = -1; // index for the homeTextBlocksVO list

        for (HomeTextBlock block : rawHomeTextBlocks) {
            HomeTextBlockVO homeTextBlockVO = new HomeTextBlockVO();
            BeanUtils.copyProperties(block, homeTextBlockVO);
            homeTextBlockVOs.add(homeTextBlockVO);
        }

        return homeTextBlockVOs;
    }

    public List<Label> getLabelByUrl(String url) {
        return websiteMapper.selectLabelByUrl(url);
    }

    public List<Faculty> getFacultyByType(String facultyType) {
        return websiteMapper.getFacultyByType(facultyType);
    }

    public List<HomeCard> getAllCards() {
        return websiteMapper.getAllCard();
    }

    public byte[] getCardImgByUrl(String visitUrl) throws IOException {
        Resource imgResource = new ClassPathResource(visitUrl);
        InputStream in = imgResource.getInputStream();

        return StreamUtils.copyToByteArray(in);
    }
}
