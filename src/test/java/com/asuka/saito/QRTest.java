package com.asuka.saito;

import com.asuka.saito.qrutils.QRCodeUtil;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: hex1n
 * @Date: 2018/9/28 10:36
 */
public class QRTest {


    /**
     * 生成二维码
     *
     * @throws IOException
     */
    @Test
    public void test() throws IOException {


        String filePath = "E:\\QR\\" + UUID.randomUUID();
        File file = new File(filePath);

        String encodeddata = "https://www.iqiyi.com/v_19rrcg1nds.html";

        QRCodeUtil.qrCodeEncode(encodeddata, file);
    }


    /**
     * 测试解码
     */
    @Test
    public void test01() {
        String filePath = "E:\\好东西";

        File qrFile = new File(filePath);


        //解码
        String reText = QRCodeUtil.qrCodeDecode(qrFile);
        System.out.println(reText);

    }
}
