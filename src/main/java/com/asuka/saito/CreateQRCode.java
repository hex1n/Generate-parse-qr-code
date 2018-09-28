package com.asuka.saito;

import com.swetake.util.Qrcode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: hex1n
 * @Date: 2018/9/28 9:53
 */
public class CreateQRCode {


    public static void main(String[] args) throws IOException {

        Qrcode qrcode = new Qrcode();
        //纠错等级(分为L,M,H 三个等级)
        qrcode.setQrcodeErrorCorrect('M');
        //N代表数字,A代表a-Z,B代表其他字符
        qrcode.setQrcodeEncodeMode('B');
        //版本
        qrcode.setQrcodeVersion(7);
        //生成二维码要存储的信息
        String qrData = "https://www.iqiyi.com/v_19rrmucyoc.html";
        //设置二维码像素
        int width = 300;
        int height = 300;

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //绘图
        Graphics2D gs = bufferedImage.createGraphics();
        gs.setBackground(Color.WHITE);
        gs.setColor(Color.BLACK);
        //清除下画板内容
        gs.clearRect(0, 0, width, height);

        //设置下偏移量,如果不加偏移量,有时会导致出错
        int pixoff = 2;

        byte[] d = qrData.getBytes("gb2312");
        if (d.length > 0 && d.length < 120) {
            boolean[][] s = qrcode.calQrcode(d);
            for (int i = 0; i < s.length; i++) {
                for (int j = 0; j < s.length; j++) {
                    if (s[j][i]) {
                        gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
                    }
                }
            }
        }

        double v = Math.random();
        gs.dispose();
        bufferedImage.flush();
        ImageIO.write(bufferedImage, "png", new File("E:\\myqrcode" + v));
    }
}
