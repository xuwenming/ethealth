package com.mobian.util;

import com.mobian.absx.F;
import com.mobian.thirdpart.oss.OSSUtil;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by wenming on 2017/11/11.
 */
public class ICEPdfUtil {

    public static String pdfToImg(String pdfFileUrl, String fileType){
        return pdfToImg(pdfFileUrl, fileType, "jpg");
    }

    /**
     * 转换指定pdf文件为图片到指定的文件夹目录下
     * @param pdfFileUrl	需要转换的pdf文件路径
     * @param suffix		需要转换的图片格式(如:jpg/png等)
     * @return	转换后图片的文件名集合
     */
    public static String pdfToImg(String pdfFileUrl, String fileType, String suffix){
        //定义Document,用于转换图片
        Document document = new Document();
        String filePaths = "";
        try {
            document.setUrl(new URL(pdfFileUrl));

            // save page caputres to file.
            float rotation = 0f;
            float imgScaling = 1.0f;
            // 循环把每页的数据转换成对应的图片
            for (int i = 0; i < document.getNumberOfPages(); i++) {
                BufferedImage image = (BufferedImage)
                        document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, imgScaling);

                String fileName = getYmdPath(fileType, suffix);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                baos.flush();
                ImageIO.write(image, suffix, baos);

                String result = OSSUtil.putInputStream(OSSUtil.bucketName, new ByteArrayInputStream(baos.toByteArray()), fileName);
                if(!F.empty(filePaths)) filePaths += ",";
                filePaths += result;

                image.flush();
            }
            // 清理document资源
            document.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return filePaths;
    }

    protected static String getYmdPath(String fileType,String suffix){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String path = fileType+"/"+calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DAY_OF_MONTH)+"/"+calendar.getTimeInMillis()+"."+suffix;
        return path;
    }
}
