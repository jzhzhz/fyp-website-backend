package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.param.CheckAdminParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateHomeCardParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateHomeTextBlockParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateLabelParam;
import com.fyp.websitebackend.csweb.domain.*;
import com.fyp.websitebackend.csweb.mapper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    HomeTextBlockMapper homeTextBlockMapper;

    @Autowired
    LabelMapper labelMapper;

    @Autowired
    WebsiteMapper websiteMapper;

    @Autowired
    HomeCardMapper homeCardMapper;

    private Logger logger = LoggerFactory.getLogger(AdminService.class);

    public boolean checkAdmin(CheckAdminParam checkAdminParam) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria()
                .andNameEqualTo(checkAdminParam.getName())
                .andPasswordEqualTo(checkAdminParam.getPassword())
                .andTypeEqualTo(checkAdminParam.getType());
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        return admins != null && admins.size() > 0;
    }

    public int updateHomeTextBlock(UpdateHomeTextBlockParam updateHomeTextBlockParam) {
        HomeTextBlockExample homeTextBlockExample = new HomeTextBlockExample();
        homeTextBlockExample.createCriteria()
                .andIdEqualTo(updateHomeTextBlockParam.getId());

        HomeTextBlock blockToUpdate = new HomeTextBlock();
        BeanUtils.copyProperties(updateHomeTextBlockParam, blockToUpdate);
        blockToUpdate.setType(null);

        return homeTextBlockMapper.updateByExampleSelective(blockToUpdate, homeTextBlockExample);
    }

    public int createNewCard(UpdateHomeCardParam updateHomeCardParam) {
        HomeCard homeCard = new HomeCard();
        BeanUtils.copyProperties(updateHomeCardParam, homeCard);

        websiteMapper.insertNewCard(homeCard);
        return homeCard.getId();
    }

    public int updateNavBarLabel(UpdateLabelParam updateLabelParam) {
        Label label = new Label();
        BeanUtils.copyProperties(updateLabelParam, label);

        LabelExample labelExample = new LabelExample();
        labelExample.createCriteria()
                .andTypeEqualTo(updateLabelParam.getType())
                .andIdEqualTo(updateLabelParam.getId());

        return labelMapper.updateByExampleSelective(label, labelExample);
    }

    public int createNewLabel(UpdateLabelParam updateLabelParam) {
        Label label = new Label();
        BeanUtils.copyProperties(updateLabelParam, label);
        websiteMapper.insertNewLabel(label);

        return label.getId();
    }

    public int updateCardById(UpdateHomeCardParam updateHomeCardParam) {
        HomeCard homeCard = new HomeCard();
        BeanUtils.copyProperties(updateHomeCardParam, homeCard);

        HomeCardExample homeCardExample = new HomeCardExample();
        homeCardExample.createCriteria()
                .andIdEqualTo(updateHomeCardParam.getId());

        return homeCardMapper.updateByExampleSelective(homeCard, homeCardExample);
    }

    public String saveCardPicture(MultipartFile pictureFile) {
        if (pictureFile.isEmpty()) {
            throw new NullPointerException("empty picture file");
        }
        // create a time stamp
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        // generate random number to avoid duplicate number
        Random random = new Random();
        int randomFileName = random.nextInt(1000);
        // get suffix of picture file
        String oldFileName = pictureFile.getOriginalFilename();
        String suffix = oldFileName.substring(oldFileName.lastIndexOf(".") + 1);
        // combine them together to get new unique file name
        String fileName = timeStamp + randomFileName +"." + suffix;

        String staticPath = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        // url path under static folder
        String urlPath = "card-img" + File.separator + fileName;
        // whole save path
        String savePath = staticPath + File.separator + urlPath;
        logger.info("save path：" + savePath);
        // visit path to return later
        String visitPath ="static" + File.separator + urlPath;
        logger.info("visit url："+visitPath);

        File saveFile = new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            pictureFile.transferTo(saveFile);  // saving picture
        } catch (IOException e) {
            e.printStackTrace();
        }

        return visitPath;
    }
}
