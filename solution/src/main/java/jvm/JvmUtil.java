package jvm;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import util.CommandLineUtil;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * <pre>
 * 作者：shenliang
 * 项目：jvm
 * 说明：虚拟机工具
 * 日期：2019年10月30日
 * 备注：
 * </pre>
 */
public class JvmUtil {


  /**
   * 获取当前系统运行的java程序个数
   * @return
   */
  public static int getJavaCount(){
    List<VirtualMachineDescriptor> vmList =  VirtualMachine.list();
    return vmList != null ?vmList.size():0;
  }


  /**
   * 根据标签名获取pidMap
   * @param tagName
   * @return
   */
  public static Map<String,String> getPidTagMap(String tagName){
    Map<String,String> pidTagMap = new HashMap<>();
    List<VirtualMachineDescriptor> vmList =  VirtualMachine.list();
    for(VirtualMachineDescriptor virtualMachineDescriptor :vmList){
      VirtualMachine virtualMachine = null;
      try {
        virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
      } catch (AttachNotSupportedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if(virtualMachine != null){
        Properties properties = null;
        try {
          properties = virtualMachine.getAgentProperties();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if(null != properties){
          String javaCmArg = (String) properties.get("sun.java.command");//命令行参数
          int index = javaCmArg.indexOf(tagName);
          if(null != javaCmArg &&  index!= -1){
            int endIndex  = javaCmArg.indexOf(" ",index);
            String tag =null;
            if(endIndex != -1){
               tag = javaCmArg.substring(index,endIndex).split("=")[1];
            } else {
              tag = javaCmArg.substring(index,javaCmArg.length()).split("=")[1];
            }
            pidTagMap.put(tag,virtualMachine.id());
          }
        }
      }
    }
    return  pidTagMap;
  }

  /**
   * 根据标签值获取pid
   * @param tag
   * @return
   */
  public static String getPidByTag(String tag){
    List<VirtualMachineDescriptor> vmList =  VirtualMachine.list();
    for(VirtualMachineDescriptor virtualMachineDescriptor :vmList){
      VirtualMachine virtualMachine = null;
      try {
        virtualMachine = VirtualMachine.attach(virtualMachineDescriptor);
      } catch (AttachNotSupportedException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
      if(virtualMachine != null){
        Properties properties = null;
        try {
          properties = virtualMachine.getAgentProperties();
        } catch (IOException e) {
          e.printStackTrace();
        }
        if(null != properties){
         String javaCmArg = (String) properties.get("sun.java.command");//命令行参数
          if(null != javaCmArg && javaCmArg.indexOf(tag) != -1){
            return virtualMachine.id();
          }
        }
      }
      }
      return  null;
  }


  public static void main(String[] args) throws IOException, AttachNotSupportedException {
   String runtimeBeanName  = ManagementFactory.getRuntimeMXBean().getName();
    System.out.println(runtimeBeanName);
    //获取当前运行的java程序
    String  taskList  = CommandLineUtil.executeCommand(CommandLineUtil.TASKLIST_EQ_JAVA,null,5000);
    System.out.println(taskList);
    List<VirtualMachineDescriptor> vmList =  VirtualMachine.list();
    System.out.println("java程序个数："+vmList.size());
    System.out.println(getPidTagMap("JmonitorServer"));
  }

}
