package com.example.demo.test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

/**
 * 压缩
 * Compresssion<BR>
 * 创建人：zhangqi <BR>
 * 时间：2019年8月7日-下午9:48:39 <BR>
 * @version 1.0.0
 * 
 */
public class Compresssion {
	
	public static void main(String[] args) {
		try {
			File input = new File("‪C:\\Users\\admin\\Desktop\\test.png");
			BufferedImage image = ImageIO.read(input);
			
			File compressedImageFile = new File("‪C:\\Users\\admin\\Desktop\\test_compress.png");
			OutputStream os = new FileOutputStream(compressedImageFile);
			
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("png");
			ImageWriter writer = (ImageWriter) writers.next();
			
			ImageOutputStream ios = ImageIO.createImageOutputStream(os);
			writer.setOutput(ios);
			
			ImageWriteParam param = writer.getDefaultWriteParam();
			
			param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
			param.setCompressionQuality(0.75f);  // Change the quality value you prefer
			writer.write(null, new IIOImage(image, null, null), param);
			
			os.close();
			ios.close();
			writer.dispose();
			
		}catch(IOException e) {
			System.out.println("这不是一个图片文件,e="+e);
		}
	}

}
