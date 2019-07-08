package com.rht.mapper;

import com.rht.pojo.Materials;
import com.rht.pojo.Measure;
import com.rht.pojo.SubProject;
import com.rht.pojo.Taxes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectCostMapper {


    /**
     * 将读取的分部分项excel存入分部分项表
     *
     * @param sub
     * @return
     */
    int insertSubProject(@Param("sub") List<SubProject> sub);


    /**
     * 将读取的总价措施excel存入总价表
     *
     * @param mea
     * @return
     */
    int insertMeasure(@Param("mea") List<Measure> mea);

    /**
     * 将读取的规费税金excel存入规费税金表
     *
     * @param tax
     * @return
     */
    int insertTaxes(@Param("tax") List<Taxes> tax);

    /**
     * 将读取的材料设备excel存入材料项表
     *
     * @param mat
     * @return
     */
    int insertMaterials(@Param("mat") List<Materials> mat);

}
