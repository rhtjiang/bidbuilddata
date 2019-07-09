package com.rht.service;

import com.rht.pojo.Materials;
import com.rht.pojo.Measure;
import com.rht.pojo.SubProject;
import com.rht.pojo.Taxes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectCostService {




    /**
     * 将读取的分部分项excel存入分部分项表
     *
     * @param sub
     * @return
     */
    boolean loadSubProject(@Param("sub") List<SubProject> sub);


    /**
     * 将读取的总价措施excel存入总价措施表
     *
     * @param mea
     * @return
     */
    boolean loadMeasure(@Param("mea") List<Measure> mea);

    /**
     * 将读取的规费税金excel存入规费税金表
     *
     * @param tax
     * @return
     */
    boolean loadTaxes(@Param("tax") List<Taxes> tax);

    /**
     * 将读取的材料设备excel存入材料表
     *
     * @param mat
     * @return
     */
    boolean loadMaterials(@Param("mat") List<Materials> mat);
}
