package com.csy.csy_blog.controller.common;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.utils.ResultHelper;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/common/upload")
public class UploadController {
    private static String IMAGE_PATH = "/opt/my-blog/image/";

    /*
    * 上传图片
    * */
    @RequestMapping(value = "/image", method = {RequestMethod.POST, RequestMethod.GET})
    public JSONObject create_backup(@RequestParam("img") MultipartFile img) throws Exception {
        Result result = new Result();
        result.setSuccess(true);
        try {
            //如果文件不为空
            if (!img.isEmpty()) {
                String imgName = System.nanoTime()+ "-" + img.getOriginalFilename();
                //开始上传图片
                FileOutputStream outputStream = new FileOutputStream(IMAGE_PATH + imgName);
                outputStream.write(img.getBytes());
                outputStream.flush();
                outputStream.close();
                result.addModel("fileName", imgName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultHelper.renderAsJsonWipeData(result);
    }
}
