package com.fish.learn.dao;

import com.fish.learn.model.Resume;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Component;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author noName
 * @since 学历增删改查
 */
@Component
public class ResumeDao extends BaseJsonDao<Resume> {

    /**
     * 生成二维码
     * @return
     */
    public void createVerifyImg(int id , String showPath, String filePath) throws Exception{
        int width = 200;
        int height = 200;
        String format = "jpg";
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(showPath,
                BarcodeFormat.QR_CODE, width, height, hints);
        Path path = FileSystems.getDefault().getPath(filePath);

        MatrixToImageWriter.writeToPath(bitMatrix, format, path);
    }

}
