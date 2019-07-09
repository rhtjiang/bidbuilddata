package com.rht.service.Impl;

import com.rht.mapper.ProjectCostMapper;
import com.rht.pojo.Materials;
import com.rht.pojo.Measure;
import com.rht.pojo.SubProject;
import com.rht.pojo.Taxes;
import com.rht.service.ProjectCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ProjectCostService")
public class ProjectCostServiceImpl implements ProjectCostService {
    @Autowired
    private ProjectCostMapper projectCostMapper;


    @Override
    @Transactional
    public boolean loadSubProject(List<SubProject> sub) {
        int i = projectCostMapper.insertSubProject(sub);
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean loadMeasure(List<Measure> mea) {
        int i = projectCostMapper.insertMeasure(mea);
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean loadTaxes(List<Taxes> tax) {
        int i = projectCostMapper.insertTaxes(tax);
        if (i < 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean loadMaterials(List<Materials> mat) {
        int i = projectCostMapper.insertMaterials(mat);
        if (i < 0) {
            return false;
        }
        return true;
    }
}
