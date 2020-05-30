package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.param.*;
import com.fyp.websitebackend.csweb.domain.*;
import com.fyp.websitebackend.csweb.mapper.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    @Autowired
    HomeEventsCardMapper homeEventsCardMapper;

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

    public int updateEventsCardById(UpdateHomeEventsCardParam param) {
        HomeEventsCard homeEventsCard = new HomeEventsCard();
        BeanUtils.copyProperties(param, homeEventsCard);

        // set example with id in the param to search existing record
        HomeEventsCardExample example = new HomeEventsCardExample();
        example.createCriteria().andIdEqualTo(param.getId());

        List<HomeEventsCard> retEvents = homeEventsCardMapper.selectByExample(example);

        if (retEvents == null || retEvents.size() <= 0) {
            websiteMapper.insertNewEventsCard(homeEventsCard);

            return homeEventsCard.getId();
        } else {
            // return 0
            return homeEventsCardMapper.updateByExampleSelective(homeEventsCard, example) - 1;
        }
    }

    public String saveCardPicture(MultipartFile pictureFile, String picType) {
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
        String urlPath = null;
        switch (picType) {
            case "card":
                urlPath = "card-img" + File.separator + fileName;
                break;
            case "profile":
                urlPath = "profile-img" + File.separator + fileName;
                break;
            case "carousel":
                urlPath = "carousel-img" + File.separator + fileName;
                break;
            default:
                logger.error("unknown image type!");
                throw new IllegalArgumentException("invalid image type");
        }

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

    public byte[] getFacultyExcelFile() throws IOException {
        Workbook wb = new XSSFWorkbook();

        /* copy faculty information to excel sheet */
        List<Faculty> facultyList = websiteMapper.getAllFaculties();
        Sheet facultySheet = wb.createSheet("faculty list");
        String[] headCol = {"Id", "Name", "Username", "Phone", "Office",
            "Email", "URL", "Type"};
        Row headerRow = facultySheet.createRow(0);

        // assign values for header
        for (int i = 0; i < headCol.length; i++) {
            headerRow.createCell(i).setCellValue(headCol[i]);
        }

        Row eachRow = null;
        for (int i = 0; i < facultyList.size(); i++) {
            eachRow = facultySheet.createRow(i+1);
            Faculty faculty = facultyList.get(i);

            eachRow.createCell(0).setCellValue(faculty.getId());
            eachRow.createCell(1).setCellValue(faculty.getName());
            eachRow.createCell(2).setCellValue(faculty.getUsername());
            eachRow.createCell(3).setCellValue(faculty.getPhone());
            eachRow.createCell(4).setCellValue(faculty.getOffice());
            eachRow.createCell(5).setCellValue(faculty.getEmail());
            eachRow.createCell(6).setCellValue(faculty.getUrl());
            eachRow.createCell(7).setCellValue(faculty.getType());
        }

        for (int i = 1; i < headCol.length; i++) {
            facultySheet.autoSizeColumn(i);
        }
        /* end faculty info copying */

        List<Admin> adminList = websiteMapper.getAllAdmins();
        Sheet adminSheet = wb.createSheet("admin list");
        String[] adminHeader = {"Id", "Name", "Password", "Type"};
        Row adminHeaderRow = adminSheet.createRow(0);

        for (int i = 0; i < adminHeader.length; i++) {
            adminHeaderRow.createCell(i).setCellValue(adminHeader[i]);
        }

        for (int i = 0; i < adminList.size(); i++) {
            eachRow = adminSheet.createRow(i+1);
            Admin admin = adminList.get(i);

            eachRow.createCell(0).setCellValue(admin.getId());
            eachRow.createCell(1).setCellValue(admin.getName());
            eachRow.createCell(2).setCellValue(admin.getPassword());
            eachRow.createCell(3).setCellValue(admin.getType());
        }

        for (int i = 1; i < adminHeader.length; i++) {
            adminSheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wb.write(bos);

        return bos.toByteArray();
    }

    private String getHomeSidebarCode() {
        HomeTextBlockExample example = new HomeTextBlockExample();
        example.createCriteria().andTypeEqualTo("sidebar");
        List<HomeTextBlock> homeTextBlocks = homeTextBlockMapper.selectByExample(example);

        if (homeTextBlocks == null || homeTextBlocks.size() <= 0) {
            return null;
        } else {
            // sidebar code segment is stored in content
            return homeTextBlocks.get(0).getContent();
        }
    }

    public byte[] getBackendData() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);

        // put bytes in the excel zip entry
        zos.putNextEntry(new ZipEntry("faculty-list.xlsx"));
        byte[] facultyWbBytes = this.getFacultyExcelFile();
        zos.write(facultyWbBytes);
        zos.closeEntry();

        String sidebarCode = this.getHomeSidebarCode();
        if (sidebarCode != null) {
            zos.putNextEntry(new ZipEntry("sidebar-code-segment.txt"));
            zos.write(sidebarCode.getBytes());
            zos.closeEntry();
        }

        // ending zos writing
        zos.close();

        return bos.toByteArray();
    }
}
