package com.hpin.assistant.utils;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huaiku
 * @date 2019年1月23日
 * @desc 文件导出工具类
 */
public class ExportUtils {

    public static void exportExcel(List<String> titles, List<Object[]> objects, OutputStream out) throws IOException {
        // 声明一个工作薄
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        // 单元格样式
        XSSFCellStyle cellStyle = (XSSFCellStyle) workbook.createCellStyle();
        setStyle(workbook, cellStyle);
        // 表头样式
        XSSFCellStyle headerCellStyle = (XSSFCellStyle) workbook.createCellStyle();
        XSSFCellStyle headerCellStyleTmp = (XSSFCellStyle) workbook.createCellStyle();
        setHeaderStyle(workbook, cellStyle, headerCellStyleTmp, headerCellStyle);

        SXSSFSheet sheet = workbook.createSheet("导出");
        Row titlerow = sheet.createRow(0);
        for (int i=0; i<titles.size(); i++) {
            SXSSFCell cell = (SXSSFCell)titlerow.createCell(i);
            cell.setCellStyle(headerCellStyle);
            cell.setCellValue(titles.get(i));
        }

        int rowNo = 0;
        for (int i=0; i< objects.size(); i++) {
            Object[] record = objects.get(i);

            rowNo++;
            Row row = sheet.createRow(rowNo);

            for (int j=0; j<titles.size(); j++) {
                Object obj = record[j];
                SXSSFCell cell = (SXSSFCell)row.createCell(j);
                cell.setCellStyle(cellStyle);

                if (obj == null) {
                    cell.setCellValue("");
                }
                else if(obj instanceof Long) {
                    cell.setCellType(CellType.NUMERIC);
                    cell.setCellValue(Long.parseLong(obj.toString()));
                }
                else if(obj instanceof Integer){
                    cell.setCellType(CellType.NUMERIC);
                    cell.setCellValue(Integer.parseInt(obj.toString()));
                }
                else if(obj instanceof Double
                        || obj instanceof BigDecimal) {
                    cell.setCellType(CellType.NUMERIC);
                    cell.setCellValue(Double.parseDouble(obj.toString()));
                }
                else {
                    cell.setCellValue(obj.toString());
                }
            }
        }
        workbook.write(out);
        out.flush();
    }

    /**
     * i. 样式设置
     * @param workbook
     * @param cellStyle
     */
    private static void setStyle(SXSSFWorkbook workbook, XSSFCellStyle cellStyle) {
        // 边框
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBottomBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setLeftBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setRightBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setTopBorderColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());

        // 水平左对齐
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        // 垂直居中对齐
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        cellStyle.setWrapText(false);

        // 文字样式
        XSSFFont tempFont = (XSSFFont) workbook.createFont();
        // 字体号码
        tempFont.setFontHeightInPoints((short) 10);
        // 字体名称
        tempFont.setFontName("微软雅黑");

        cellStyle.setFont(tempFont);
    }

    /**
     * i. 设置标题样式
     * @param workbook
     * @param cellStyle
     * @param childrenTitleCellStyle
     * @param fristTtitleCellStyle
     */
    private static void setHeaderStyle(SXSSFWorkbook workbook,
                                       XSSFCellStyle cellStyle,
                                       XSSFCellStyle childrenTitleCellStyle,
                                       XSSFCellStyle fristTtitleCellStyle) {
        // 非一级标题样式
        childrenTitleCellStyle.cloneStyleFrom(cellStyle);
        // 背景色
        childrenTitleCellStyle.setFillForegroundColor(new XSSFColor(new Color(
                114, 180, 226)));
        childrenTitleCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        childrenTitleCellStyle.setAlignment(HorizontalAlignment.CENTER);

        // 一级标题样式
        fristTtitleCellStyle.cloneStyleFrom(childrenTitleCellStyle);
        // 文字样式
        XSSFFont boldFont = (XSSFFont) workbook.createFont();
        boldFont.setBold(true);
        // 字体号码
        boldFont.setFontHeightInPoints((short) 10);
        // 字体名称
        boldFont.setFontName("微软雅黑");
        fristTtitleCellStyle.setFont(boldFont);

    }
}
