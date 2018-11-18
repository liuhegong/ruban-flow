package cn.com.yusys.yusp.workflow.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OGNLUtil {

    /**
     * @方法名称: toList
     * @方法描述: 根据类型将ids字符串转为list.
     *            目前支持 String Integer BigDecimal
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public static List<?> toList(String type, String ids) {
        if (ids == null) {
            return new ArrayList<String>();
        }
        String[] split = ids.split(",");
        List<String> stringList = Arrays.asList(split);
        if (String.class.getName().equalsIgnoreCase(type.trim())) {
            return stringList;
        }
        if (Integer.class.getName().equalsIgnoreCase(type.trim())) {
            List<Integer> integerList = new ArrayList<Integer>();
            for (String id : stringList) {
                integerList.add(Integer.valueOf(id));
            }
            return integerList;
        }
        if (BigDecimal.class.getName().equalsIgnoreCase(type.trim())) {
            List<BigDecimal> bigDecimalList = new ArrayList<BigDecimal>();
            for (String id : stringList) {
                bigDecimalList.add(new BigDecimal(id));
            }
            return bigDecimalList;
        }
        return stringList;
    }

    /**
     * @方法名称: isBasic
     * @方法描述: 判断是否为基本数据类型
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public static boolean isBasic(Object param) {
        if (param.getClass().isPrimitive()) {
            return true;
        }
        // 字符
        if (param instanceof Character) {
            return true;
        }
        // 数字
        if (param instanceof Number) {
            return true;
        }
        // 布尔
        if (param instanceof Boolean) {
            return true;
        }
        // 字符串
        if (param instanceof String) {
            return true;
        }
        // 日期
        if (param instanceof Date) {
            return true;
        }
        return false;
    }

    /**
     * @方法名称: like
     * @方法描述: 判断是否为like查询
     * @参数与返回说明: 
     * @算法描述: 无
     */
    public static boolean like(Object param) {
        if (param != null) {
            String paramStr = param.toString();
            if (paramStr.startsWith("%") || paramStr.endsWith("%")) {
                return true;
            }
        }
        return false;
    }
}
