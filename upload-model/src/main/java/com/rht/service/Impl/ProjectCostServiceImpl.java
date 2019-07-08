package com.rht.service.Impl;

import com.rht.mapper.ProjectCostMapper;
import com.rht.pojo.Materials;
import com.rht.pojo.Measure;
import com.rht.pojo.SubProject;
import com.rht.pojo.Taxes;
import com.rht.service.ProjectCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ProjectCostService")
public class ProjectCostServiceImpl implements ProjectCostService {
    @Autowired
    private ProjectCostMapper projectCostMapper;


    @Override
    public int loadSubProject(List<SubProject> sub) {
        return 0;
    }

    @Override
    public int loadMeasure(List<Measure> mea) {
        return 0;
    }

    @Override
    public int loadTaxes(List<Taxes> tax) {
        return 0;
    }

    @Override
    public int loadMaterials(List<Materials> mat) {
        return 0;
    }
}
