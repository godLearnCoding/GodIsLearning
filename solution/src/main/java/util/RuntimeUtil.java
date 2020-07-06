package util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：进程工具
 * 日期：2019年11月14日
 * 备注：
 * </pre>
 */
public class RuntimeUtil {



  public static void main(String[] args) {
    RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();

    System.out.println(runtimeMXBean.getName());
  }

}
