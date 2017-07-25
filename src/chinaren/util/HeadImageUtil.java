package chinaren.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;


/**
 * @ClassName HeadImageUtil 
 * @author 李浩然
 * @date 2017年7月21日
 * @version 1.0
 */
public class HeadImageUtil {
	
	public static final String SEPARATOR = File.separator;
	public static final String PATH_NAME = "WEB-INF" + SEPARATOR + "images";
	public static final String BASE_NAME = "headImage_";
	public static final String POSTFIX = ".png";
	public static final String DEFAULT_IMAGE = "defaultHeadImage" + POSTFIX;
	
	/**
	 * 私有构造方法
	 */
	private HeadImageUtil() {

	}

	/**
	 * 为一个用户创建一个默认的头像图片文件
	 * @author 李浩然
	 * @param path 图片文件夹路径
	 * @param userId 用户ID
	 * @return 若创建成功，返回true；否则，返回false
	 */
	public static boolean createDefaultHeadImage(String path, long userId) {
		try {
			FileInputStream fis = new FileInputStream(path + SEPARATOR + DEFAULT_IMAGE);
			FileOutputStream fos = new FileOutputStream(path + SEPARATOR + BASE_NAME + userId + POSTFIX);
			int temp;
			while((temp = fis.read()) != -1) {
				fos.write(temp);
			}
			fis.close();
			fos.close();
			return true;
		} catch (IOException e) {
			return false;
		} 
	}
	
	/**
	 * 为一个用户设置头像
	 * @author 李浩然
	 * @param path 图片文件夹路径
	 * @param userId 用户ID
	 * @param file 新头像文件
	 * @return 若设置成功，则返回true；否则，返回false
	 */
	public static boolean modifyHeadImage(String path, long userId, MultipartFile file) {
		try {
			File targetFile = new File(path, BASE_NAME + userId + POSTFIX);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	/**
	 * 输出用户的头像图片
	 * @author 李浩然
	 * @param path 头像图片文件夹路径
	 * @param userId 用户ID
	 * @param response HTTP响应头
	 * @return 若成功，则返回true；否则，返回false
	 */
	public static boolean outputHeadImage(String path, long userId, HttpServletResponse response) {
		try {
			response.setContentType("image/png");
			BufferedImage bi = ImageIO.read(new File(path + SEPARATOR + BASE_NAME + userId + POSTFIX));
			ImageIO.write(bi, "png", response.getOutputStream());
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
}
