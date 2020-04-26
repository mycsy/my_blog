package com.csy.csy_blog.controller.front;

import com.alibaba.fastjson.JSONObject;
import com.csy.csy_blog.domain.Label;
import com.csy.csy_blog.pojo.Result;
import com.csy.csy_blog.utils.PdfUtils;
import com.csy.csy_blog.utils.ResultHelper;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/front/pdf")
public class FrontPdfUtilsController {
    /**
     * 获取所有标签
     */
    @RequestMapping("/delete/one")
    public void deleteOne(@RequestParam("file") MultipartFile file, HttpServletResponse response, int pageNum) {
        Result result = new Result();
        result.setSuccess(true);
        try {
            /*
            PdfReader pdfReader = new PdfReader(inputStream);
            PDDocument document = new PDDocument();
            document = PDDocument.load(inputStream);
            document.save(response.getOutputStream());*/
            InputStream inputStream = file.getInputStream();
            PdfUtils.cutPdf(inputStream, pageNum, response);
        } catch (Exception e) {

        }
    }
}
