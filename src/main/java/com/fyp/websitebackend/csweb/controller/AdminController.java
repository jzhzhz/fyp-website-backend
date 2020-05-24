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
import org.springframework.http.ResponseEntity;
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
        boolean result = adminService.checkAdmin(checkAdminParam);
        if (!result) {
            logger.warn("invalid login information! returning false");
        }

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/updateHomeTextBlock")
    public CustomResponseEntity updateHomeTextBlock(UpdateHomeTextBlockParam updateHomeTextBlockParam) {
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

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/updateLabel")
    public CustomResponseEntity updateLabel(UpdateLabelParam updateLabelParam) {
        int result = adminService.updateNavBarLabel(updateLabelParam);
        if (result == 1) {
            return CustomResponseEntity.success(result);
        } else {
            return CustomResponseEntity.error("error happened when updating label");
        }
    }

    @RequestMapping("/createNewLabel")
    public CustomResponseEntity createNewLabel(UpdateLabelParam updateLabelParam) {
        int result = adminService.createNewLabel(updateLabelParam);

        return CustomResponseEntity.success(result);
    }

    @RequestMapping("/uploadCardPic")
    public CustomResponseEntity uploadCardPic(@RequestParam("file") MultipartFile file) {
        String fileType = file.getContentType();

        if (WebConstants.CARD_PIC_TYPES.contains(fileType)) {
            String visitUrl = adminService.saveCardPicture(file, "card");

            logger.info(String.format("File name '%s' uploaded successfully.", file.getOriginalFilename()));
            return CustomResponseEntity.success(visitUrl);
        } else {
            logger.warn(String.format("File name '%s' has invalid file type, which is %s",
                    file.getOriginalFilename(),
                    file.getContentType()));
            return CustomResponseEntity.error("invalid file type!");
        }
    }

    @RequestMapping("/getFacultyExcelFile")
    public ResponseEntity<?> getFacultyExcel() {
        byte[] excelBytes;
        try {
            excelBytes = adminService.getFacultyExcelFile();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.badRequest().body(e);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition",
                        "attachment; filename=faculty-list.xlsx")
                .body(excelBytes);
    }
}
