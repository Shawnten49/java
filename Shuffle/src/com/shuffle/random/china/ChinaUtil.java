package com.shuffle.random.china;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class ChinaUtil {
	/**

     * 原理是从汉字区位码找到汉字。在汉字区位码中分高位与底位， 且其中简体又有繁体。位数越前生成的汉字繁体的机率越大。

     * 所以在本例中高位从171取，底位从161取， 去掉大部分的繁体和生僻字。但仍然会有！！

     *

     */


    public static void create() throws Exception {

       String str = null;

       int hightPos, lowPos; // 定义高低位

       Random random = new Random();

       hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值

       lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值

       byte[] b = new byte[2];

       b[0] = (new Integer(hightPos).byteValue());

       b[1] = (new Integer(lowPos).byteValue());

       str = new String(b, "GBk");//转成中文

       System.err.println(str);

    }

    public static void main(String[] args) {
    	try {
			create();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 

    /**

     * 旋转和缩放文字

     * 必须要使用Graphics2d类

     */

  /*  public void trans(HttpServletRequest req, HttpServletResponse resp) throws Exception{

       int width=88;

       int height=22;

       BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

       Graphics g = img.getGraphics();

       Graphics2D g2d = (Graphics2D) g;

       g2d.setFont(new Font("黑体",Font.BOLD,17));

       Random r = new Random();

       for(int i=0;i<4;i++){

           String str = ""+r.nextInt(10);

           AffineTransform aff = new AffineTransform();

           aff.rotate(Math.random(),i*18,height-5);

           aff.scale(0.6+Math.random(), 0.6+Math.random());

           g2d.setTransform(aff);

           g2d.drawString(str,i*18,height-5);

           System.err.println(">:"+str);

       }

       g2d.dispose();

       ImageIO.write(img, "JPEG",resp.getOutputStream());

    }*/
}
