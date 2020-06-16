package com.fyp.websitebackend.csweb.service;

import com.fyp.websitebackend.csweb.controller.param.*;
import com.fyp.websitebackend.csweb.domain.*;
import com.fyp.websitebackend.csweb.mapper.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Autowired
    FacultyMapper facultyMapper;

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

    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

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

        /* copy admin information to excel sheet */
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
        /* end admin info copying */

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

    public byte[] getHomePageExcelFile() throws IOException {
        Workbook wb = new XSSFWorkbook();

        List<HomeTextBlock> homeTextBlocks = websiteMapper.getAllHomeTextBlock();
        Sheet homeTextBlockSheet = wb.createSheet("home text blocks");
        String[] homeTextBlockHeader = {"Id", "Title", "Content", "Url", "Type", "deprecated"};
        Row headerRow = homeTextBlockSheet.createRow(0);

        for (int i = 0; i < homeTextBlockHeader.length; i++) {
            headerRow.createCell(i).setCellValue(homeTextBlockHeader[i]);
        }

        Row eachRow = null;
        for (int i = 0; i < homeTextBlocks.size(); i++) {
            eachRow = homeTextBlockSheet.createRow(i+1);
            HomeTextBlock homeTextBlock = homeTextBlocks.get(i);

            eachRow.createCell(0).setCellValue(homeTextBlock.getId());
            eachRow.createCell(1).setCellValue(homeTextBlock.getTitle());
            eachRow.createCell(2).setCellValue(homeTextBlock.getContent());
            eachRow.createCell(3).setCellValue(homeTextBlock.getUrl());
            eachRow.createCell(4).setCellValue(homeTextBlock.getType());
            eachRow.createCell(5).setCellValue(homeTextBlock.getDeprecated());
        }

        Sheet homeCardsSheet = wb.createSheet("home cards");
        List<HomeCard> homeCards = websiteMapper.getAllCards();
        String[] homeCardsHeader = {"Id", "Title", "Text", "Date", "Url", "ImgName", "ImgUrl", "Deprecated"};
        headerRow = homeCardsSheet.createRow(0);

        for (int i = 0; i < homeCardsHeader.length; i++) {
            headerRow.createCell(i).setCellValue(homeCardsHeader[i]);
        }

        for (int i = 0; i < homeCards.size(); i++) {
            eachRow = homeCardsSheet.createRow(i+1);
            HomeCard homeCard = homeCards.get(i);

            eachRow.createCell(0).setCellValue(homeCard.getId());
            eachRow.createCell(1).setCellValue(homeCard.getTitle());
            eachRow.createCell(2).setCellValue(homeCard.getText());
            eachRow.createCell(3).setCellValue(homeCard.getDate());
            eachRow.createCell(4).setCellValue(homeCard.getUrl());
            eachRow.createCell(5).setCellValue(homeCard.getImgName());
            eachRow.createCell(6).setCellValue(homeCard.getImgUrl());
            eachRow.createCell(7).setCellValue(homeCard.getDeprecated());
        }

        Sheet homeEventsSheet = wb.createSheet("home events");
        List<HomeEventsCard> eventsCards = websiteMapper.getAllEvents();
        String[] eventsHeader = {"Id", "Title", "Subtitle", "Content", "Url", "Deprecated"};
        headerRow = homeEventsSheet.createRow(0);

        for (int i = 0; i < eventsHeader.length; i++) {
            headerRow.createCell(i).setCellValue(eventsHeader[i]);
        }

        for (int i = 0; i < eventsCards.size(); i++) {
            eachRow = homeEventsSheet.createRow(i+1);
            HomeEventsCard homeEventsCard = eventsCards.get(i);

            eachRow.createCell(0).setCellValue(homeEventsCard.getId());
            eachRow.createCell(1).setCellValue(homeEventsCard.getTitle());
            eachRow.createCell(2).setCellValue(homeEventsCard.getSubtitle());
            eachRow.createCell(3).setCellValue(homeEventsCard.getContent());
            eachRow.createCell(4).setCellValue(homeEventsCard.getUrl());
            eachRow.createCell(5).setCellValue(homeEventsCard.getDeprecated());
        }

        for (int i = 1; i < homeCardsHeader.length; i++) {
            homeTextBlockSheet.autoSizeColumn(i);
            homeCardsSheet.autoSizeColumn(i);
            homeEventsSheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        wb.write(bos);

        return bos.toByteArray();
    }

    public byte[] getBackendData() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(bos);

        // put bytes in the excel zip entry
        zos.putNextEntry(new ZipEntry("faculty-list.xlsx"));
        byte[] facultyWbBytes = this.getFacultyExcelFile();
        zos.write(facultyWbBytes);
        zos.closeEntry();

        zos.putNextEntry(new ZipEntry("home-information.xlsx"));
        byte[] homeBytes = this.getHomePageExcelFile();
        zos.write(homeBytes);
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

    public int updateFacultyDataByExcelFile(MultipartFile excelFile) throws IOException, InvalidFormatException {
//        File retExcelFile = CommonUtils.multipartToFile(excelFile, excelFile.getOriginalFilename());
        Workbook wb = WorkbookFactory.create(excelFile.getInputStream());

        Sheet facultySheet = wb.getSheet("faculty list");

        if (facultySheet == null || isSheetEmpty(facultySheet)) {
            throw new IllegalArgumentException("cannot find faculty sheet!");
        }

        List<Faculty> faculties = websiteMapper.getAllFaculties();
        if (facultySheet.getLastRowNum() != faculties.size()) {
            throw new IllegalArgumentException("invalid number of rows (invalid faculty number)!");
        }

        for (int i = 0; i < facultySheet.getPhysicalNumberOfRows(); i++) {
            Field[] fields = Faculty.class.getDeclaredFields();
            if (facultySheet.getRow(i).getLastCellNum() != fields.length) {
                throw new IllegalArgumentException("invalid number of columns!");
            }
        }

        IntStream.range(1, facultySheet.getLastRowNum())
                .forEach(index -> {
                    Row row = facultySheet.getRow(index);
                    Faculty faculty = new Faculty();
                    // go thorough faculty row to put in faculty values
                    faculty.setId((int) row.getCell(0).getNumericCellValue());
                    faculty.setName(row.getCell(1).getStringCellValue());
                    faculty.setUsername(row.getCell(2).getStringCellValue());
                    faculty.setPhone(row.getCell(3).getStringCellValue());
                    faculty.setOffice(row.getCell(4).getStringCellValue());
                    faculty.setEmail(row.getCell(5).getStringCellValue());
                    faculty.setUrl(row.getCell(6).getStringCellValue());
                    faculty.setType(row.getCell(7).getStringCellValue());

                    FacultyExample example = new FacultyExample();
                    example.createCriteria().andIdEqualTo(faculty.getId());
                    facultyMapper.updateByExampleSelective(faculty, example);
                });

        return 0;
    }

    private boolean isSheetEmpty(Sheet sheet) {
        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            Row row = (Row) rows.next();
            Iterator cells = row.cellIterator();
            if (cells.hasNext()) {
                Cell cell = (Cell) cells.next();
                DataFormatter dataFormatter = new DataFormatter();
                return dataFormatter.formatCellValue(cell).isEmpty();
            }
        }
        return false;
    }
}
