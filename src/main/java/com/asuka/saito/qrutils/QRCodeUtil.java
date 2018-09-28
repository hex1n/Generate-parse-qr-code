package com.asuka.saito.qrutils;

import com.swetake.util.Qrcode;
import jp.sourceforge.qrcode.QRCodeDecoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: hex1n
 * @Date: 2018/9/28 10:12
 */
public class QRCodeUtil {


    /**
     * 生成二维码
     *
     * @param encodeddata 二维码内容
     * @param destFile    二维码文件路径+名称
     * @throws IOException
     */
    public static void qrCodeEncode(String encodeddata, File destFile) throws IOException {

        Qrcode qrcode = new Qrcode();
        /**
         * 纠错级别(L 7%  M 15%  Q 25%  H 30%)
         * 和版本有关
         */
        qrcode.setQrcodeErrorCorrect('M');
        //设置QRCode包的版本
        qrcode.setQrcodeVersion(7);
        qrcode.setQrcodeEncodeMode('B');

        //字符集
        byte[] bytes = encodeddata.getBytes("UTF-8");
        BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);

        //创建图层
        Graphics2D gs = bi.createGraphics();
        //设置背景颜色(白色)
        gs.setBackground(Color.WHITE);
        //矩形X,Y,width,heiht
        gs.clearRect(0, 0, 139, 139);
        //设置图像颜色(黑色)
        gs.setColor(Color.black);

        if (bytes.length > 0 && bytes.length < 123) {
            boolean[][] b = qrcode.calQrcode(bytes);
            for (int i = 0; i < b.length; i++) {
                for (int j = 0; j < b.length; j++) {
                    if (b[j][i]) {
                        gs.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
                    }
                }
            }
        }
        gs.dispose();
        bi.flush();
        ImageIO.write(bi, "png", destFile);
    }

    /**
     * 解析二维码,返回解析内容
     *
     * @param imageFile
     * @return
     */
    public static String qrCodeDecode(File imageFile) {

        String decodedData = null;
        QRCodeDecoder decoder = new QRCodeDecoder();
        BufferedImage image = null;

        try {
            image = ImageIO.read(imageFile);

        } catch (IOException e) {

            System.out.println("Error:" + e.getMessage());
        }

        try {

            decodedData = new String(decoder.decode(new J2SEImage(image)), "UTF-8");

        } catch (Exception e) {

            System.out.println("Error:" + e.getMessage());
        }
        return decodedData;
    }
}
