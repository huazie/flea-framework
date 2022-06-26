package com.huazie.fleaframework.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * 图像处理公共类
 *
 * @author huazie
 * @version 1.0.0
 * @since 1.0.0
 */
public class ImageUtils {

    private ImageUtils() {
    }

    /**
     * 图像字节流输出对象
     *
     * @param code   生成的校验码
     * @param width  该校验码图片的宽度
     * @param height 该校验码图片的高度
     * @return 图像字节流输出对象
     * @throws IOException IO异常
     * @since 1.0.0
     */
    public static ByteArrayOutputStream generate(String code, int width, int height) throws IOException {
        // 将验证码转换为字符数组
        char[] singleCode = code.toCharArray();
        // 创建随机数对象
        Random random = new Random();
        // 在内存中创建图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获得图形上下文对象
        Graphics graphics = image.getGraphics();
        // 设定背景色
        graphics.setColor(new Color(255, 224, 205)); // #FFEBCD
        graphics.fillRect(0, 0, width, height);
        // 设定字体
        graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        // 随机产生干扰线,使图像中的认证码不易被其他程序探测到
        for (int i = 0; i < 200; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            graphics.drawLine(x, y, x + x1, y + y1);
        }

        for (int i = 0; i < singleCode.length; i++) {
            // 将认证码显示到图像中
            graphics.setColor(new Color(30 + random.nextInt(160), 40 + random.nextInt(170), 40 + random.nextInt(180)));
            graphics.drawString(singleCode[i] + "", (width / singleCode.length) * i + 3, 18);
        }

        // 画出明显的一条干扰线
        int line1 = random.nextInt(10) + 10;
        int line2 = random.nextInt(10) + 10;
        graphics.setColor(new Color(30 + random.nextInt(160), 40 + random.nextInt(170), 40 + random.nextInt(180)));
        graphics.drawLine(0, line1, width, line2);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "JPEG", out);
        return out;
    }

}
