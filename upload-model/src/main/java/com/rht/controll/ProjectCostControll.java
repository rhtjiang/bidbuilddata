package com.rht.controll;

import com.alibaba.fastjson.JSONObject;
import com.rht.util.SheetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mysql.jdbc.CharsetMapping.MAP_SIZE;

@RestController
@RequestMapping("/project")
public class ProjectCostControll {


    @Autowired
    private SheetUtil sheetUtil;

    //文件上传的地址
    public final static String UPLOAD_FILE_PATH = "F:\\project\\";

    @RequestMapping(value = "/uploads", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String uploads(@RequestParam("file") MultipartFile files) {
        String build_type = null;
        if (!files.isEmpty()) {
            Map<String, String> resObj = new HashMap<>(MAP_SIZE);
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(UPLOAD_FILE_PATH, files.getOriginalFilename())));
                out.write(files.getBytes());
                out.flush();
                out.close();

            } catch (IOException e) {
                resObj.put("msg", "error");
                resObj.put("code", "1");
                return JSONObject.toJSONString(resObj);
            }
            resObj.put("msg", "ok");
            resObj.put("code", "0");
            //调用解析类读取excel
            File file = new File(UPLOAD_FILE_PATH + files.getOriginalFilename());
            String mssg = sheetUtil.readSheet(file);
            return JSONObject.toJSONString(resObj);

        } else {
            return null;
        }


    }


}
