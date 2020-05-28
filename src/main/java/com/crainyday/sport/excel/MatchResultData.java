package com.crainyday.sport.excel;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 要下载的运动会比赛结果Excel信息对应的对象
 * @author crainyday
 *
 */
public class MatchResultData {
    @ExcelProperty(value = "项目名", index = 0)
    private String eventName;
    @ExcelProperty(value = "第一名", index = 1)
    private String first;
    @ExcelProperty(value = "第二名", index = 2)
    private String second;
    @ExcelProperty(value = "第三名", index = 3)
    private String third;
    @ExcelProperty(value = "第四名", index = 4)
    private String fourth;
    @ExcelProperty(value = "第五名", index = 5)
    private String fifth;
    @ExcelProperty(value = "第六名", index = 6)
    private String sixth;
    @ExcelProperty(value = "第七名", index = 7)
    private String seventh;
    @ExcelProperty(value = "第八名", index = 8)
    private String eighth;
}
