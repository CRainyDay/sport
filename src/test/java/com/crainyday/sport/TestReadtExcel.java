package com.crainyday.sport;

import org.junit.Test;

public class TestReadtExcel {
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，
    	// 要每次读取excel都要new,然后里面用到spring可以构造方法传进去
//        String fileName = "/home/crainyday/ew/sport/src/main/webapp/WEB-INF/uploads/student.xlsx";
//        ExcelReader excelReader = EasyExcel.read(fileName, GeneralData.class, new TestReadExcelListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
    }
}
