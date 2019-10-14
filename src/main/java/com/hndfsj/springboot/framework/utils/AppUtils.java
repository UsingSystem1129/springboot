package com.hndfsj.springboot.framework.utils;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;

/**
 * 应用相关方法
 *
 */
public class AppUtils {
	public static boolean isWeiXinDevice(HttpServletRequest request) {
        String signature = request.getParameter("signature");// 微信加密签名
        String timestamp = request.getParameter("timestamp");// 时间戳
        String nonce = request.getParameter("nonce");       // 随机数
        String token = request.getParameter("token");       // 随机数
//		return TokenUtil.checkSignature(token, signature, timestamp, nonce);
		return false;
	}

	public static boolean isUnicom(HttpServletRequest request) {
		String appName = request.getHeader("appname");
		if (StringUtils.isNotBlank(appName) && appName.contains("unicom_")) {
			return true;
		}
		return false;
	}
}
