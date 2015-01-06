package com.coo.s.cloud.qrcode;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;

/**
 * 二维码生成器工具
 * 
 * @author boqing.shen
 * @since 1.0.0.0
 * 
 */
public class QrcodeUtil {

	public static void main(String[] args) {
		// generateQrcode("http://www.baidu.com", "C:/test/test2.jpg");
		// generate("http://10.253.46.75/portal/ngbf-m-ngbf-0.6.0.apk",
		// "C:/test/ic_launcher.png", "C:/test/apk-ngbf.png");

		QrcodeUtil.generate("http://gdown.baidu.com/data/wisegame/07995b1aad7046f4/xiaomo_1.apk", "C:/test/gplus_64.png","C:/test/gplus_qr.png");
		System.out.println("OK...");
		// generateQrcodeAttachLogo("http://10.253.46.75/portal/ngbf-m-ngbf-0.6.0.apk",
		// "C:/test/apk-ngbf.jpg","C:/test/ngbf-t-2.png");
	}

	/**
	 * 
	 * @param content
	 *            生成的
	 * @param fileName
	 *            生成二维码图片的名称
	 */
	public static void generate(String content, String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			QrcodeBuilder builder = new QrcodeBuilder();
			BufferedImage bim = builder.getQR_CODEBufferedImage(content,
					BarcodeFormat.QR_CODE, 300, 300,
					builder.getDecodeHintType());
			ImageIO.write(bim, "jpeg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param content二维码内容
	 * @param fileName生成二维码图片名称
	 * @param logoPath
	 *            logo图片路径
	 */
	public static void generate(String content, String logoPath, String filePath) {
		try {
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			QrcodeBuilder builder = new QrcodeBuilder();
			BufferedImage bim = builder.getQR_CODEBufferedImage(content,
					BarcodeFormat.QR_CODE, 300, 300,
					builder.getDecodeHintType());
			ImageIO.write(bim, "jpeg", file);
			builder.addLogo_QRCode(file, new File(logoPath), new LogoConfig());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
