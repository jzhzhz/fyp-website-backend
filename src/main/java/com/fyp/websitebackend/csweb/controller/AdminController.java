package com.fyp.websitebackend.csweb.controller;

import com.fyp.websitebackend.common.constants.WebConstants;
import com.fyp.websitebackend.common.entity.CustomResponseEntity;
import com.fyp.websitebackend.csweb.controller.param.CheckAdminParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateHomeCardParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateHomeTextBlockParam;
import com.fyp.websitebackend.csweb.controller.param.UpdateLabelParam;
import com.fyp.websitebackend.csweb.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {
    @Autowired
    AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping("/checkAdmin")
    public CustomResponseEntity checkAdminValidity(CheckAdminParam checkAdminParam) {
        System.out.println("handling admin check: " + checkAdminParam.toString());
        boolean result = adminService.checkAdmin(checkAdminParam);
        System.out.println("returning check admin result: " + result);
        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/updateHomeTextBlock")
    public CustomResponseEntity updateHomeTextBlock(UpdateHomeTextBlockParam updateHomeTextBlockParam) {
        System.out.println("handling text block update request...");
        System.out.println("params are: " + updateHomeTextBlockParam.toString());
        int result = adminService.updateHomeTextBlock(updateHomeTextBlockParam);
        if (result == 1) {
            return CustomResponseEntity.success(result);
        } else {
            return CustomResponseEntity.error("error happened when updating home text block");
        }
    }

    @RequestMapping("/updateCardById")
    public CustomResponseEntity updateCardById(UpdateHomeCardParam updateHomeCardParam) {
        int result = adminService.updateCardById(updateHomeCardParam);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/createNewCard")
    public CustomResponseEntity createNewCard(UpdateHomeCardParam updateHomeCardParam) {
        int result = adminService.createNewCard(updateHomeCardParam);
        System.out.println("return new card id: " + result);
        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/updateLabel")
    public CustomResponseEntity updateLabel(UpdateLabelParam updateLabelParam) {
        System.out.println("handling label update request...");
        System.out.println("params are: " + updateLabelParam.toString());

        int result = adminService.updateNavBarLabel(updateLabelParam);
        if (result == 1) {
            return CustomResponseEntity.success(result);
        } else {
            return CustomResponseEntity.error("error happened when updating label");
        }
    }

    @RequestMapping("/createNewLabel")
    public CustomResponseEntity createNewLabel(UpdateLabelParam updateLabelParam) {
        System.out.println("handling create new label request...");
        System.out.println("params are: " + updateLabelParam.toString());
        int result = adminService.createNewLabel(updateLabelParam);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/uploadCardPic")
    public CustomResponseEntity uploadCardPic(@RequestParam("file") MultipartFile file) {
        String fileType = file.getContentType();

        if (WebConstants.CARD_PIC_TYPES.contains(fileType)) {
            String visitUrl = adminService.saveCardPicture(file);

            logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
            return CustomResponseEntity.success(visitUrl);
        } else {
            logger.info(String.format("File name '%s' has invalid file type, which is %s",
                    file.getOriginalFilename(),
                    file.getContentType()));
            return CustomResponseEntity.error("invalid file type!");
        }
    }
}
