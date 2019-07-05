package com.rht.util;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ExcelUtil {

    //默认单元格内容为数字时格式

    private static DecimalFormat df = new DecimalFormat("0");

    // 默认单元格格式化日期字符串

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    // 格式化数字

    private static DecimalFormat nf = new DecimalFormat("0.00");

    public  ArrayList<ArrayList<Object>> readExcel(File file,HSSFSheet sheet) {

        if (file == null) {

            return null;

        }

        if (file.getName().endsWith("xlsx")) {

            //处理ecxel2007

            return readExcel2007(file);

        } else {

            //处理ecxel2003

            return readExcel2003(file,sheet);

        }

    }

    /*

     * @return 将返回结果存储在ArrayList内，存储结构与二位数组类似

     * lists.get(0).get(0)表示过去Excel中0行0列单元格

     */

    public static ArrayList<ArrayList<Object>> readExcel2003(File file,HSSFSheet sheet) {
       // System.out.println(sheet);
        try {

            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();

            ArrayList<Object> colList;

            HSSFRow row;

            HSSFCell cell;
            Object value;

            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                colList = new ArrayList<Object>();
                if (row == null) {
                    //当读取行为空时
                    if (i != sheet.getPhysicalNumberOfRows()) {//判断是否是最后一行

                        rowList.add(colList);
                    }
                    continue;

                } else {

                    rowCount++;

                }

                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {

                    cell = row.getCell(j);
                    if (cell == null || cell.equals(" ") || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                        //当该单元格为空
                       if (j != row.getLastCellNum()) {//判断是否是该行中最后一个单元格
                            colList.add(null);

                        }
                        continue;

                    }

                    switch (cell.getCellType()) {

                        case XSSFCell.CELL_TYPE_STRING:
                            value = cell.getStringCellValue();
                            break;

                        case XSSFCell.CELL_TYPE_NUMERIC:

                            if ("@".equals(cell.getCellStyle().getDataFormatString())) {

                                value = df.format(cell.getNumericCellValue());

                            } else if ("General".equals(cell.getCellStyle()

                                    .getDataFormatString())) {

                                value = nf.format(cell.getNumericCellValue());

                            } else {

                                value = sdf.format(HSSFDateUtil.getJavaDate(cell

                                        .getNumericCellValue()));
                            }
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            value = Boolean.valueOf(cell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            value = null;
                            break;
                        default:
                            value = cell.toString();


                    }
                    if (value.equals("") ||value.equals(" ") ||value.equals("  ")){
                        colList.add("null");
                    }else {
                        colList.add(value);
                    }


                }
                if (colList.size()>0){
                    rowList.add(colList);
                }

            }//end for i

            return rowList;

        } catch (Exception e) {

            return null;

        }

    }


    public static ArrayList<ArrayList<Object>> readExcel2007(File file) {
        try {

            ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();

            ArrayList<Object> colList;

            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));

            XSSFSheet sheet = wb.getSheetAt(0);

            XSSFRow row;

            XSSFCell cell;

            Object value;

            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++) {

                row = sheet.getRow(i);

                colList = new ArrayList<Object>();

                if (row == null) {

                    //当读取行为空时

                    if (i != sheet.getPhysicalNumberOfRows()) {//判断是否是最后一行

                        rowList.add(colList);

                    }

                    continue;

                } else {

                    rowCount++;

                }

                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {

                    cell = row.getCell(j);

                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

                        //当该单元格为空

                        if (j != row.getLastCellNum()) {//判断是否是该行中最后一个单元格

                            colList.add("");

                        }

                        continue;

                    }


                    colList.add(cell.toString());

                }//end for j

                rowList.add(colList);

            }//end for i


            return rowList;

        } catch (Exception e) {

            System.out.println("exception");

            return null;

        }

    }


    /**
     * 将解析的数据写入指定的位置
     *
     * @param result
     * @param path
     */
    public static void writeExcel(ArrayList<ArrayList<Object>> result, String path) {

        if (result == null) {

            return;

        }

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("sheet1");

        for (int i = 0; i < result.size(); i++) {

            HSSFRow row = sheet.createRow(i);

            if (result.get(i) != null) {

                for (int j = 0; j < result.get(i).size(); j++) {

                    HSSFCell cell = row.createCell(j);

                    cell.setCellValue(result.get(i).get(j).toString());

                }

            }

        }

        ByteArrayOutputStream os = new ByteArrayOutputStream();

        try {

            wb.write(os);

        } catch (IOException e) {

            e.printStackTrace();

        }

        byte[] content = os.toByteArray();

        File file = new File(path);//Excel文件生成后存储的位置。

        OutputStream fos = null;

        try {

            fos = new FileOutputStream(file);

            fos.write(content);

            os.close();

            fos.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static DecimalFormat getDf() {

        return df;

    }

    public static void setDf(DecimalFormat df) {

        ExcelUtil.df = df;

    }

    public static SimpleDateFormat getSdf() {

        return sdf;

    }

    public static void setSdf(SimpleDateFormat sdf) {

        ExcelUtil.sdf = sdf;

    }

    public static DecimalFormat getNf() {

        return nf;

    }

    public static void setNf(DecimalFormat nf) {

        ExcelUtil.nf = nf;

    }

/*

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Lee\\Desktop\\仪陇县国有林场棚户区(危旧房)改造工程.xls");
        ArrayList<ArrayList<Object>> arrayLists = readExcel(file);
      //  System.out.println(arrayLists + "------");

    }
*/


}
