package com.hndfsj.springboot.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import com.hndfsj.springboot.framework.config.Configuration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 项目文件上传保存处理
 * 
 * @author zxj
 *
 */
public class FileUploadUtils {
	static Logger log=LoggerFactory.getLogger(FileUploadUtils.class);
	public static String renameFile(String fileExt) {
		if (StringUtils.isNotBlank(fileExt)) {
			return UUIDGenerator.UUIDValue() + "." + fileExt;
		}
		return UUIDGenerator.UUIDValue();
	}

	/**
	 * @param file
	 * @param filename
	 *            id 当filename
	 * @return zxj
	 */
	public static boolean writeUploadFile(File file,String dir, String filename) {
		String path = getPathByUploadDir(dir,filename);
		try {
			Files.copy(file.toPath(), new FileOutputStream(path));
			return true;
		} catch (IOException e) {
		}
		return false;
	}

	public static byte[] readUploadFile(String dir,String filename) {
		String path = getPathByUploadDir(dir,filename);
		try {
			return Files.readAllBytes(new File(path).toPath());
		} catch (IOException e) {
		}
		return new byte[] {};
	}

	public static boolean writeUploadFile(byte[] b, String dir,String filename) {
		String path = getPathByUploadDir(dir,filename);
		try {
			OutputStream stream=new FileOutputStream(path);
			IOUtils.write(b, stream);
			IOUtils.closeQuietly(stream);
			return true;
		} catch (IOException e) {
			log.error("",e);
		}
		return false;

	}

	public  static String getPathByUploadDir(String dir,String filename) {
		String uploadDir = Configuration.getInstance().getAppPropsValue("file.attach.dir")+"/"+dir;
		String path = uploadDir + "/" + filename;
		File dirs = new File(uploadDir);
		if (!dirs.exists()) {
            dirs.mkdirs();
        }
		return path;
	}

}
