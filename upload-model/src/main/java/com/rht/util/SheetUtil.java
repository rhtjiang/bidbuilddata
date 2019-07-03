package com.rht.util;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SheetUtil {

    //读取excel页
    private static HSSFWorkbook wb;


    /**
     * 通过不同的sheet来调取读的任务
     */
    public static void readSheet(File file) {
        file = new File("C:\\Users\\Lee\\Desktop\\仪陇县国有林场棚户区(危旧房)改造工程.xls");
        String sheetName = null;
        try {
            wb = new HSSFWorkbook(new FileInputStream(file));
            //循环获取所有的sheet页名称
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                sheetName = wb.getSheetAt(i).getSheetName();

                if (sheetName.contains("分部分项工程")) {
                    readFenBu(file, sheetName);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    /**
     * 读取分部分项表
     *
     * @param file
     */
    public static void readFenBu(File file, String filename) {
        HSSFSheet sheet = wb.getSheet(filename);
        ExcelUtil excelUtil = new ExcelUtil();
        //sheet接收的数据
        ArrayList<ArrayList<Object>> arrayLists = excelUtil.readExcel(file, sheet);
        //重新包装的数据
        ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
        //工程名称
        String p_name = (String) arrayLists.get(1).get(2);
        //遍历元素
        for (int i = 0; i < arrayLists.size(); i++) {
            //清单名称
            String list_name = (String) arrayLists.get(i).get(3);
            //清单描述
            String miao = (String) arrayLists.get(i).get(4);
            if (!list_name.equals("分部小计") && !list_name.equals("项目名称") && !list_name.equals("") && !miao.equals("")) {
                arrayLists.get(i).set(11, p_name);
                rowList.add(arrayLists.get(i));
            }
        }


        /*for (int i = 0; i < rowList.size(); i++) {

            if (rowList.get(i).get(0).toString().equals("")&&rowList.get(i+1).get(0).toString().equals("")){
                String l_name1 = (String)rowList.get(i).get(3);
                String l_name2 = (String)rowList.get(i+1).get(3);
                String l_name = l_name1.concat(l_name2);
                System.out.println(l_name);
            }

        }*/

        System.out.println(rowList);


    }


    public static void main(String[] args) {
        File file = new File("");
        readSheet(file);
    }
}
