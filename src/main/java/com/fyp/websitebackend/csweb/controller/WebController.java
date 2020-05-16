package com.fyp.websitebackend.csweb.controller;

import com.fyp.websitebackend.common.entity.CustomResponseEntity;
import com.fyp.websitebackend.csweb.controller.vo.HomeTextBlockVO;
import com.fyp.websitebackend.csweb.domain.Faculty;
import com.fyp.websitebackend.csweb.domain.HomeCard;
import com.fyp.websitebackend.csweb.domain.Label;
import com.fyp.websitebackend.csweb.service.WebsiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/csWeb")
@CrossOrigin
public class WebController {
    private Logger logger = LoggerFactory.getLogger(WebController.class);

    @Autowired
    WebsiteService websiteService;

    @RequestMapping("/getLabels")
    public CustomResponseEntity getAboutLabels(String labelType) {
        System.out.println("handling get " + labelType + " labels request!");
        List<Label> labels = websiteService.findAllLabels(labelType);
        System.out.println("Returning " + labels.get(0).toString());

        return CustomResponseEntity.success(labels);
    }

    @RequestMapping("/getHomeTextBlockByType")
    public CustomResponseEntity getHomeTextBlockByType(String type) {
        List<HomeTextBlockVO> homeTextBlockVOs = websiteService.getHomeTextBlockByType(type);
        System.out.println("returning home text blocks: " + homeTextBlockVOs.get(0).toString());
        return CustomResponseEntity.success(homeTextBlockVOs);
    }

    @RequestMapping("/getLabelByUrl")
    public CustomResponseEntity getLabelByUrl(String url) {
        System.out.println("handling get label with url: " + url);
        List<Label> labels = websiteService.getLabelByUrl(url);
        System.out.println("Returning " + labels.get(0).toString());

        return CustomResponseEntity.success(labels);
    }

    @RequestMapping("/getFacultyByType")
    public CustomResponseEntity getFacultyByType(String type) {
        System.out.println("handling get " + type + " faculty");
        List<Faculty> faculties = websiteService.getFacultyByType(type);
        System.out.println("returning faculty..." + faculties.get(0).toString());

        return CustomResponseEntity.success(faculties);
    }

    @RequestMapping("/getAllCards")
    public CustomResponseEntity getAllCards() {
        List<HomeCard> homeCards = websiteService.getAllCards();

        return CustomResponseEntity.success(homeCards);
    }

    @RequestMapping("/getCardImgByUrl")
    public ResponseEntity<Object> getCardImgByUrl(String visitUrl) {
        byte[] imgBytes;
        String imgFormat = visitUrl.substring(visitUrl.lastIndexOf(".") + 1);

        try {
            imgBytes = websiteService.getCardImgByUrl(visitUrl);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.notFound().build();
        }

        if (imgFormat.equals("jpg"))
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imgBytes);
        else if (imgFormat.equals("png"))
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imgBytes);
        else
            return ResponseEntity.badRequest().body("invalid url: wrong img format");
    }
}
