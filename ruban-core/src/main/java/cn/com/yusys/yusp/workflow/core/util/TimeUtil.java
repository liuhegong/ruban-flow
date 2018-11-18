package cn.com.yusys.yusp.workflow.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
	public static String getDateyyyyMMddHHmmss() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
}
