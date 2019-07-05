package com.rht.util;

import com.rht.pojo.SubProject;
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
                    // readPartial(file, sheetName);
                } else if (sheetName.contains("总价措施项目")) {
                    // readMeasure(file, sheetName);
                } else if (sheetName.contains("税金项目")) {
                //    readTaxes(file, sheetName);
                } else if (sheetName.contains("材料")){
                    readMaterials(file,sheetName);
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
    public static void readPartial(File file, String filename) {
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
            if ((!list_name.equals("项目名称") && !miao.contains("null")) || (!list_name.equals("null") && !arrayLists.get(i).get(0).toString().contains("序号"))) {
                arrayLists.get(i).set(2, p_name);
                rowList.add(arrayLists.get(i));

            }
        }
        //循环进行字符串拼接
        for (int i = 0; i < rowList.size(); i++) {
            //拼接单一的项目描述
            if (rowList.get(i).get(3).toString().contains("null") && !rowList.get(i).get(4).toString().contains("null")) {
                //项目描述
                String m_name1 = (String) rowList.get(i - 1).get(4);
                String m_name2 = (String) rowList.get(i).get(4);
                String m_name = m_name1.concat(m_name2);
                rowList.get(i - 1).set(4, m_name);
            }

        }
        //移除多出来的描述
        for (int i = 0; i < rowList.size(); i++) {
            if (rowList.get(i).get(3).toString().contains("null") && !rowList.get(i).get(4).toString().contains("null")) {
                rowList.remove(i);
            }
        }
        for (int i = 0; i < rowList.size() - 1; i++) {
            //处理分部名称因为一个空格没有写完，遗留到下一行的进行拼接
            if (rowList.get(i).get(0).toString().contains("null") && rowList.get(i + 1).get(0).toString().contains("null") && rowList.get(i + 2).get(0).toString().contains("null") && !rowList.get(i + 2).get(3).toString().contains("null")) {
                if (i < rowList.size()) {
                    String f_name1 = rowList.get(i + 1).get(3).toString();
                    String f_name2 = rowList.get(i + 2).get(3).toString();
                    String f_name = f_name1.concat(f_name2);
                    rowList.get(i + 1).set(3, f_name);
                    rowList.remove(i + 2);
                } else {
                    break;
                }
            }
            //处理清单名称
            if (!rowList.get(i).get(0).toString().contains("null") && rowList.get(i + 1).get(0).toString().contains("null") && !rowList.get(i + 1).get(3).toString().contains("分部小计")) {
                if (i < rowList.size()) {
                    //清单名称
                    String l_name1 = (String) rowList.get(i).get(3);
                    String l_name2 = (String) rowList.get(i + 1).get(3);
                    String l_name = l_name1.concat(l_name2);
                    rowList.get(i).set(3, l_name);
                    //项目描述
                    String m_name1 = (String) rowList.get(i).get(4);
                    String m_name2 = (String) rowList.get(i + 1).get(4);
                    String m_name = m_name1.concat(m_name2);
                    rowList.get(i).set(4, m_name);
                    rowList.remove(i + 1);
                } else {
                    break;
                }
            }
        }
        //将分部名称放到数据中当成一个字段
        List list = new ArrayList();
        for (int i = 0; i < rowList.size(); i++) {
            if (rowList.get(i).get(0).toString().contains("null") && !rowList.get(i).get(3).toString().contains("分部小计")) {
                rowList.get(i).set(10, rowList.get(i).get(3).toString());
            }
        }
        for (int i = 0; i < rowList.size() - 1; i++) {
            if (!rowList.get(i).get(10).toString().contains("null") && rowList.get(i + 1).get(10).toString().contains("null")) {
                rowList.get(i + 1).set(10, rowList.get(i).get(10).toString());
            }
        }
        //将数据循环赋值给对象
        List<SubProject> sub = new ArrayList<>();
        for (int i = 0; i < rowList.size(); i++) {

            if (!rowList.get(i).get(0).toString().contains("null")) {
                //申明对象
                SubProject su = new SubProject();
                //给每个字段赋值
                su.setBop(rowList.get(i).get(1).toString());
                su.setP_name(rowList.get(i).get(3).toString());
                su.setP_detail(rowList.get(i).get(4).toString());
                su.setPrickle(rowList.get(i).get(5).toString());
                su.setAmount(Double.parseDouble(rowList.get(i).get(6).toString()));
                su.setI_unit(Double.parseDouble(rowList.get(i).get(8).toString()));
                if (rowList.get(i).get(11).toString().contains("null")) {
                    su.setEvaluate(0);
                } else {
                    su.setEvaluate(Double.parseDouble(rowList.get(i).get(11).toString()));

                }
                su.setPartial_name(rowList.get(i).get(10).toString());
                su.setB_name(rowList.get(i).get(2).toString());
                sub.add(su);
            }
        }
    }

    //**************************单价措施表暂时不写，确定下来之后再确定***************

    /**
     * 总价措施费
     *
     * @param file
     * @param filename
     */
    public static void readMeasure(File file, String filename) {
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
            String base = (String) arrayLists.get(i).get(4);
            if (!list_name.equals("项目名称") && !list_name.contains("null")) {
                arrayLists.get(i).set(2, p_name);
                rowList.add(arrayLists.get(i));
            }
        }
        //循环获取并赋值给总价措施对象
        for (int i = 0; i < rowList.size(); i++) {

        }


    }

    /**
     * 读取规费、税金并存储
     *
     * @param file
     * @param filename
     */
    public static void readTaxes(File file, String filename) {

        HSSFSheet sheet = wb.getSheet(filename);
        ExcelUtil excelUtil = new ExcelUtil();
        //sheet接收的数据
        ArrayList<ArrayList<Object>> arrayLists = excelUtil.readExcel(file, sheet);
        //重新包装的数据
        ArrayList<ArrayList<Object>> taxesList = new ArrayList<ArrayList<Object>>();
        //工程名称
        String p_name = (String) arrayLists.get(1).get(1);
        //遍历元素
        for (int i = 0; i < arrayLists.size(); i++) {
            if (!arrayLists.get(i).get(1).toString().equals("项目名称") && !arrayLists.get(i).get(1).toString().contains("null")) {
                arrayLists.get(i).add(p_name);
                if (!arrayLists.get(i).get(0).toString().contains("工程名称")) {
                    taxesList.add(arrayLists.get(i));
                }
            }

        }
        //循环获取并赋值给总价措施对象
        for (int i = 0; i < taxesList.size(); i++) {
            System.out.println(taxesList.get(i));
        }


    }


    /**
     * 读取材料与工程设备并存储
     *
     * @param file
     * @param filename
     */
    public static void readMaterials(File file, String filename) {

        HSSFSheet sheet = wb.getSheet(filename);
        ExcelUtil excelUtil = new ExcelUtil();
        //sheet接收的数据
        ArrayList<ArrayList<Object>> arrayLists = excelUtil.readExcel(file, sheet);
        //重新包装的数据
        ArrayList<ArrayList<Object>> mList = new ArrayList<ArrayList<Object>>();
        //工程名称
        String p_name = (String) arrayLists.get(1).get(1);
        //遍历元素
        for (int i = 0; i < arrayLists.size(); i++) {
           if (!arrayLists.get(i).get(1).toString().contains("null")&&!arrayLists.get(i).get(0).toString().contains("序号")){
              arrayLists.get(i).add(p_name);
              if (!arrayLists.get(i).get(0).toString().contains("工程名称")){
                  mList.add(arrayLists.get(i));
              }
           }

        }
        //循环获取并赋值给总价措施对象
        for (int i=0;i<mList.size();i++){
            System.out.println(mList.get(i));
        }



    }



    public static void main(String[] args) {
        File file = new File("");
        readSheet(file);
    }
}
