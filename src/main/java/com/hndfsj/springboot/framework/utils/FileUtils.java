package com.hndfsj.springboot.framework.utils;

import org.apache.commons.lang3.StringUtils;

public class FileUtils {
	public static String renameFile(String fileExt) {
		if (StringUtils.isNotBlank(fileExt)) {
			return UUIDGenerator.UUIDValue() + "." + fileExt;
		}
		return UUIDGenerator.UUIDValue();
	}
}
