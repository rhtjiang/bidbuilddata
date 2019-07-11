package com.rht.util;

import com.rht.pojo.Materials;
import com.rht.pojo.Measure;
import com.rht.pojo.SubProject;
import com.rht.pojo.Taxes;
import com.rht.service.ProjectCostService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SheetUtil {

    //读取excel页
    private static HSSFWorkbook wb;
    @Autowired
    private ProjectCostService projectCostService;


    /**
     * 通过不同的sheet来调取读的任务
     */

    public static String readSheet(File file) {
        file = new File("C:\\Users\\Lee\\Desktop\\仪陇县国有林场棚户区(危旧房)改造工程(1定额).xls");
        String sheetName = null;
        boolean su = false;
        boolean me = false;
        boolean ta = false;
        boolean ma = false;
        try {
            wb = new HSSFWorkbook(new FileInputStream(file));
            //循环获取所有的sheet页名称

            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                sheetName = wb.getSheetAt(i).getSheetName();
                //  if (sheetName.contains("分部分项工程")) {
                //  List<SubProject> subProjects = readPartial(file, sheetName);
                //    su = projectCostService.loadSubProject(subProjects);
                //    }
                /*else if (sheetName.contains("总价措施项目")) {
                    List<Measure> measures = readMeasure(file, sheetName);
                   // me = projectCostService.loadMeasure(measures);
                } else if (sheetName.contains("税金项目")) {
                    List<Taxes> taxes = readTaxes(file, sheetName);
                   // ta = projectCostService.loadTaxes(taxes);
                } else if (sheetName.contains("材料")) {
                    List<Materials> materials = readMaterials(file, sheetName);
                  //  ma = projectCostService.loadMaterials(materials);
                }*/
                if (sheetName.contains("分析表")) {
                    readAnalyze(file, sheetName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (su && me && ta && ma) {
            return "解析成功";
        } else {
            return "解析失败";
        }


    }


    /**
     * 读取分部分项表
     *
     * @param file
     */
    public List<SubProject> readPartial(File file, String filename) {
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
        return sub;
    }

    //**************************单价措施表暂时不写，确定下来之后再确定***************

    /**
     * 总价措施费
     *
     * @param file
     * @param filename
     */
    public List<Measure> readMeasure(File file, String filename) {
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
                arrayLists.get(i).add(p_name);
                rowList.add(arrayLists.get(i));
            }
        }
        //循环获取并赋值给总价措施对象
        List<Measure> me = new ArrayList<>();
        for (int i = 0; i < rowList.size(); i++) {
            //申明总价措施对象
            Measure mea = new Measure();
            mea.setBop(rowList.get(i).get(1).toString());
            mea.setP_name(rowList.get(i).get(3).toString());
            mea.setBase(rowList.get(i).get(5).toString());
            mea.setRate(rowList.get(i).get(6).toString());
            mea.setMoney(rowList.get(i).get(7).toString());
            mea.setAdjust_rate(rowList.get(i).get(8).toString());
            mea.setAdjust_after_money(rowList.get(i).get(10).toString());
            mea.setComment(rowList.get(i).get(11).toString());
            mea.setB_name(rowList.get(i).get(12).toString());
            me.add(mea);
        }
        return me;
    }

    /**
     * 读取规费、税金并存储
     *
     * @param file
     * @param filename
     */
    public List<Taxes> readTaxes(File file, String filename) {

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
        List<Taxes> taxes = new ArrayList<>();
        for (int i = 0; i < taxesList.size(); i++) {
            Taxes ta = new Taxes();
            ta.setP_name(taxesList.get(i).get(1).toString());
            ta.setBase(taxesList.get(i).get(2).toString());
            ta.setBase_number(taxesList.get(i).get(3).toString());
            ta.setRate(taxesList.get(i).get(4).toString());
            ta.setMoney(taxesList.get(i).get(5).toString());
            ta.setB_name(taxesList.get(i).get(6).toString());
            taxes.add(ta);
        }
        return taxes;
    }


    /**
     * 读取材料与工程设备并存储
     *
     * @param file
     * @param filename
     */
    public List<Materials> readMaterials(File file, String filename) {

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
            if (!arrayLists.get(i).get(1).toString().contains("null") && !arrayLists.get(i).get(0).toString().contains("序号")) {
                arrayLists.get(i).add(p_name);
                if (!arrayLists.get(i).get(0).toString().contains("工程名称")) {
                    mList.add(arrayLists.get(i));
                }
            }

        }
        //循环获取并赋值给总价措施对象
        List<Materials> materials = new ArrayList<>();
        for (int i = 0; i < mList.size(); i++) {
            Materials mat = new Materials();
            mat.setM_name(mList.get(i).get(1).toString());
            mat.setPrickle(mList.get(i).get(2).toString());
            mat.setAmount(mList.get(i).get(3).toString());
            mat.setRisk_facto(mList.get(i).get(4).toString());
            mat.setBase_unit(mList.get(i).get(6).toString());
            mat.setTender_unit(mList.get(i).get(7).toString());
            mat.setAck_unit(mList.get(i).get(8).toString());
            mat.setComment(mList.get(i).get(9).toString());
            mat.setB_name(mList.get(i).get(10).toString());
            materials.add(mat);
        }

        return materials;
    }

    /**
     * 读取分析表数据
     *
     * @param file
     * @param filename
     */
    public static void readAnalyze(File file, String filename) {
        HSSFSheet sheet = wb.getSheet(filename);
        ExcelUtil excelUtil = new ExcelUtil();
        //sheet接收的数据
        ArrayList<ArrayList<Object>> arrayLists = excelUtil.readExcel(file, sheet);
        //重新包装的数据
        ArrayList<ArrayList<Object>> analyList = new ArrayList<ArrayList<Object>>();
        String p_name = (String) arrayLists.get(1).get(1);
        //循环清洗
        for (int i = 0; i < arrayLists.size(); i++) {
            if (!arrayLists.get(i).get(1).toString().contains("null") || !arrayLists.get(i).get(2).toString().contains("null")) {
                arrayLists.get(i).add(p_name);
                if (!arrayLists.get(i).get(0).toString().contains("工程名称") && !arrayLists.get(i).get(0).toString().contains("材") && !arrayLists.get(i).get(0).toString().contains("定额编号") && !arrayLists.get(i).get(0).toString().contains("人工单价") && !arrayLists.get(i).get(0).toString().contains("工日")) {


                    analyList.add(arrayLists.get(i));

                }
            }
        }
        //将项目编码以及项目名称添加到每一行中
        for (int i = 0; i < analyList.size(); i++) {
            if (analyList.get(i).get(0).toString().contains("项目编码")) {
                String bop = (String) analyList.get(i).get(2);
                String substring = bop.substring(bop.indexOf(")") + 1);
                analyList.get(i).add(substring);
                analyList.get(i).add(analyList.get(i).get(6));
            }

        }
        //循环将每条数据加上清单名称和清单编码
        for (int i = 0; i < analyList.size() - 1; i++) {
            if (analyList.get(i).size() > analyList.get(i + 1).size()) {
                analyList.get(i + 1).add(analyList.get(i).get(13));
                analyList.get(i + 1).add(analyList.get(i).get(14));
            }
        }
        //循环清洗不需要的数据
        for (int i = 0; i < analyList.size(); i++) {
           if (!analyList.get(i).get(1).toString().contains("null") && !analyList.get(i).get(2).toString().contains("单位") && !analyList.get(i).get(1).toString().contains("材料费小计") ){
              if (!analyList.get(i).get(9).toString().contains("null") || !analyList.get(i).get(10).toString().contains("null")){
                  System.out.println(analyList.get(i));
              }
           }
        }

    }

    public static void main(String[] args) {
        File file = new File("");
        readSheet(file);
    }

}
