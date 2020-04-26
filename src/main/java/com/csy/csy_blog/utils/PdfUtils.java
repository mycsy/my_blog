package com.csy.csy_blog.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import org.apache.pdfbox.pdmodel.PDDocument;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class PdfUtils {
    /**
     * 截取pdfFile的第from页至第end页，组成一个新的文件名
     * @param respdfFile  需要分割的PDF
     * @param savepath  新PDF
     * @param from  起始页
     * @param end  结束页
     */
    public static void splitPDFFile(String respdfFile,
                                    String savepath, int from, int end) {
        Document document = null;
        PdfCopy copy = null;
        try {
            PdfReader reader = new PdfReader(respdfFile);
            int n = reader.getNumberOfPages();
            if(end==0){
                end = n;
            }
            ArrayList<String> savepaths = new ArrayList<String>();
            savepaths.add(savepath);
            document = new Document(reader.getPageSize(1));
            copy = new PdfCopy(document, new FileOutputStream(savepaths.get(0)));
            document.open();
            for(int j=from; j<=end; j++) {
                document.newPage();
                PdfImportedPage page = copy.getImportedPage(reader, j);
                copy.addPage(page);
            }
            document.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch(DocumentException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除pdf中的某一页
     * @param flag            0：第一页；1：最后一页 ；else : 要删除的页码
     */
    public static void cutPdf(InputStream inputStream, int flag, HttpServletResponse response)
    {
        PDDocument document = new PDDocument();
        try{
            document = PDDocument.load(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
        int noOfPages = document.getNumberOfPages();
        if(flag == 0)
            document.removePage(0);
        else if(flag == 1) {
            document.removePage(noOfPages-1);
        } else {
            document.removePage(flag-1);
        }
        try{
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + new String("删除完成".getBytes("GBK"),"ISO-8859-1") + ".pdf");
            response.setContentType("application/x-msdownload;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            document.save(response.getOutputStream());
            document.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("已经转完了哦");

    }

}
