package com.fyp.websitebackend.csweb.controller;

import com.fyp.websitebackend.common.constants.WebConstants;
import com.fyp.websitebackend.common.entity.CustomResponseEntity;
import com.fyp.websitebackend.csweb.controller.param.*;
import com.fyp.websitebackend.csweb.service.AdminService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

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

    @RequestMapping("/updateEventsCardById")
    public CustomResponseEntity updateEventsCard(UpdateHomeEventsCardParam param) {
        int result = adminService.updateEventsCardById(param);

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

    @RequestMapping("/getBackendData")
    public ResponseEntity<?> getBackendData() {
        byte[] zipBytes;
        try {
            zipBytes = adminService.getBackendData();
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }

        return ResponseEntity.ok()
                .header("Content-Disposition",
                        "attachment; filename=backend-data.zip")
                .body(zipBytes);
    }

    @RequestMapping("/uploadExcelFile")
    public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        String fileType = file.getContentType();

        if (WebConstants.EXCEL_FILE_TYPES.contains(fileType)) {
            logger.info("successfully received excel file: " + file.getOriginalFilename());
        } else {
            logger.warn(file.getOriginalFilename() + " has invalid file type: " + file.getContentType());
            return ResponseEntity.badRequest().body("invalid file type!");
        }

        int result = 0;
        try {
            result = adminService.updateDataByExcelFile(file);
        } catch (IllegalArgumentException | InvalidFormatException | IOException e) {
            logger.error(Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }

        return ResponseEntity.ok("update success with " + result);
    }
}
