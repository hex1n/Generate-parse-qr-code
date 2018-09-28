package com.asuka.saito.qrutils;

import jp.sourceforge.qrcode.data.QRCodeImage;

import java.awt.image.BufferedImage;

/**
 * @Author: hex1n
 * @Date: 2018/9/28 10:28
 */
public class J2SEImage implements QRCodeImage {

    BufferedImage image;

    public J2SEImage(BufferedImage image) {

        this.image = image;
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public int getPixel(int x, int y) {
        return image.getRGB(x, y);
    }

}
