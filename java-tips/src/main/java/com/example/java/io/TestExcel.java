package com.example.java.io;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yangguang
 * @Title: TestExcel
 * @ProjectName learn
 * @Description: 用来生成想要的EXcel
 * @date 2019/10/29 9:35
 * @Version 1.0.0
 */
public class TestExcel {

    public static boolean exportFrom(String absolutePath, String version, String ownedUnit, List<ArrayList<String>>... dyadics) {

        int index = absolutePath.lastIndexOf(".");
        String suffix = absolutePath.substring(index + 1);
        boolean flag = false;
        Workbook wb = null;
        if ("xls".equals(suffix)) {
            wb = new HSSFWorkbook();
        } else if ("xlsx".equals(suffix)) {
            wb = new XSSFWorkbook();
        } else {
            return false;
        }
        Sheet firstSheet = wb.createSheet();

//        CellStyle style = wb.createCellStyle();
//        style.setAlignment(HorizontalAlignment.CENTER);
//        style.setVerticalAlignment(VerticalAlignment.CENTER);

        List<ArrayList<String>> firstDyadic = dyadics[0];//一个数组，第一个表示第一个sheet
        int size = firstDyadic.size();
        System.out.println(size);
        for (int k = 0; k < size; k++) {
            short height=1;
            Row row = firstSheet.createRow(k);
            row.setHeight(height);
            int size_2 = firstDyadic.get(k).size();
            System.out.println("size_2=" + size_2);
            for (int i = 0; i < size_2; i++) {
                String tempString = firstDyadic.get(k).get(i);
                Cell cell = row.createCell(i);
                System.out.println("tempString11=" + tempString);
                //固定表头的设置
                if (k <= 2) {
                    CellStyle style1 = wb.createCellStyle();
                    style1.setAlignment(HorizontalAlignment.CENTER);
                    style1.setVerticalAlignment(VerticalAlignment.CENTER);
                    cell.setCellStyle(style1);
                }
                //奇数行
                if (k % 2 == 1 && k > 2) {
                    CellStyle style1 = wb.createCellStyle();
                    style1.setBorderBottom(BorderStyle.HAIR);
                    style1.setBorderTop(BorderStyle.THIN);
                    style1.setBorderRight(BorderStyle.THIN);
                    style1.setAlignment(HorizontalAlignment.CENTER);
                    style1.setVerticalAlignment(VerticalAlignment.CENTER);
                    cell.setCellStyle(style1);
                    System.out.println("tempString777=" + tempString);
                }

                if (k % 2 == 0 && k > 2) {
                    CellStyle style1 = wb.createCellStyle();
                    style1.setBorderBottom(BorderStyle.THIN);
                    style1.setBorderRight(BorderStyle.THIN);
                    style1.setAlignment(HorizontalAlignment.CENTER);
                    style1.setVerticalAlignment(VerticalAlignment.CENTER);

                    cell.setCellStyle(style1);
                    System.out.println("tempString333=" + tempString + ",i=" + i);
                }
                cell.setCellValue(tempString);
            }
        }

        // 下面是做了一些单元格的合并
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 13);
        firstSheet.addMergedRegion(region);
        CellRangeAddress region1 = new CellRangeAddress(1, 1, 0, 12);
        firstSheet.addMergedRegion(region1);
        CellRangeAddress region2 = new CellRangeAddress(2, 2, 1, 2);
        firstSheet.addMergedRegion(region2);
        CellRangeAddress region3 = new CellRangeAddress(2, 2, 4, 12);
        firstSheet.addMergedRegion(region3);
        //下面这些需要逻辑生成，然后在合并了
        CellRangeAddress region4 = new CellRangeAddress(3, 6, 0, 0);
        firstSheet.addMergedRegion(region4);
        CellRangeAddress region5 = new CellRangeAddress(3, 4, 1, 1);
        firstSheet.addMergedRegion(region5);
        CellRangeAddress region6 = new CellRangeAddress(5, 6, 1, 1);
        firstSheet.addMergedRegion(region6);


        int len = dyadics.length;
        for (int i = 1; i < len; i++) {
            List<ArrayList<String>> dyadic = dyadics[i];
            Sheet sheet = wb.createSheet();
            //隐藏sheet.true为隐藏, flase为不隐藏
            wb.setSheetHidden(i, false);

            int dyadicSize = dyadic.size();
            for (int k = 0; k < dyadicSize; k++) {
                Row row = sheet.createRow(k);
                int size_2 = dyadic.get(k).size();
                for (int j = 0; j < size_2; j++) {
                    String tempString = dyadic.get(k).get(j);
                    row.createCell(j).setCellValue(tempString);
                }
                if (version.equals("1"))
                    row.createCell(size_2).setCellValue(ownedUnit);
            }
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(absolutePath);
            wb.write(out);
            flag = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static void main(String[] args) {

        List<ArrayList<String>> dyadics = new ArrayList<>();
        ArrayList<String> header = new ArrayList<>();
        header.add("行车计划表");
        ArrayList<String> header1 = new ArrayList<>(16);
        header1.add("所属线路");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("");
        header1.add("日期");
        ArrayList<String> header2 = new ArrayList<>(16);
        header2.add("班次");
        header2.add("出车站");
        header2.add("");
        header2.add("到离站");
        header2.add("时间");
        header2.add("");
        header2.add("");
        header2.add("");
        header2.add("");
        header2.add("");
        header2.add("");
        header2.add("");
        header2.add("");
        header2.add("");
        ArrayList<String> header3 = new ArrayList<>(16);
        header3.add("1");
        header3.add("中建七局.B");
        header3.add("发");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        header3.add("5:50:00");
        ArrayList<String> header4 = new ArrayList<>(16);
        header4.add("1");
        header4.add("中建七局.B");
        header4.add("到");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        header4.add("5:50:00");
        ArrayList<String> header5 = new ArrayList<>(16);
        header5.add("1");
        header5.add("夷陵广场3.DF");
        header5.add("发");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        header5.add("5:50:00");
        ArrayList<String> header6 = new ArrayList<>(16);
        header6.add("1");
        header6.add("夷陵广场3.DF");
        header6.add("到");
        header6.add("5:53:00");
        header6.add("5:54:00");
        header6.add("5:55:00");
        header6.add("5:56:00");
        header6.add("5:53:00");
        header6.add("5:53:00");
        header6.add("5:53:00");
        header6.add("5:53:00");
        header6.add("5:53:00");
        header6.add("5:53:00");
        header6.add("5:53:00");


        dyadics.add(header);
        dyadics.add(header1);
        dyadics.add(header2);
        dyadics.add(header3);
        dyadics.add(header4);
        dyadics.add(header5);
        dyadics.add(header6);

        exportFrom("F:\\test.xlsx", "1", "data_part", dyadics);

    }
}
