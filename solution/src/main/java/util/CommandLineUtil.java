package util;

import org.apache.commons.exec.DefaultExecutor;

import org.apache.commons.exec.*;

import java.io.*;
import java.nio.charset.Charset;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：命令行执行工具
 * 日期：2019年09月20日
 * 备注：依赖apache commons exec
 * </pre>
 */
public class CommandLineUtil {

  public static final  String  CMD_EXE_START = "cmd.exe /k start ";

  public static final  String  TASKLIST_EQ_JAVA = "tasklist  /fi \"IMAGENAME  eq java*\"";

  //public static final  String  JAVA_JAR_RUN_PREFIX = "java -server -jar ";
  /**
   * 指定目录下 执行命令
   * @param command 命令行字符串
   * @param workingDirectory 工作目录
   * @param timeOut 超时时间
   *                执行命令后timeOut时间内处于阻塞状态，阻塞状态结束后终止进程
   */
  public static String executeCommand(String command,File workingDirectory,long timeOut){
    CommandLine commandLine =  CommandLine.parse(command);
    //超时设置
    DefaultExecutor executor = new DefaultExecutor();
    //指定执行目录
    if(null !=workingDirectory && workingDirectory.exists()){
      executor.setWorkingDirectory(workingDirectory);
    }
    ExecuteWatchdog watchdog = new ExecuteWatchdog(timeOut);
    executor.setWatchdog(watchdog);
    // 重定向stdout和stderr到byte输出流
    ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream(8096);
    PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(byteArrayOutputStream);
    executor.setStreamHandler(pumpStreamHandler);
    try {
      //阻塞等待
      executor.execute(commandLine);
      byteArrayOutputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new String(byteArrayOutputStream.toByteArray(), Charset.forName("GBK"));
  }

  public static DataInputStream asyncExcuteCommand(String command,File workingDirectory) {
    CommandLine commandLine = CommandLine.parse(command);
    return asyncExcuteCommand(commandLine,workingDirectory);
  }

  public static DataInputStream asyncExcuteCommand(CommandLine command,File workingDirectory) {
    //超时设置
    //ExecuteWatchdog watchdog = new ExecuteWatchdog(Long.MAX_VALUE);//永久不超时
    DefaultExecutor executor = new DefaultExecutor();
    //executor.setWatchdog(watchdog);
    //指定执行目录
    if (null != workingDirectory && workingDirectory.exists()) {
      executor.setWorkingDirectory(workingDirectory);
    }
    DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
    executor.setExitValue(1);
    // 重定向stdout和stderr到byte输出流
    //ByteArrayOutputStream byteArrayOutputStream =  new ByteArrayOutputStream(8096);
    PipedOutputStream output = new PipedOutputStream();
    PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(output);
    executor.setStreamHandler(pumpStreamHandler);
    DataInputStream is = null;
    try {
      is = new DataInputStream(new PipedInputStream(output));
      //异步执行
      executor.execute(command, resultHandler);
      //resultHandler.waitFor(5000);
      //output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return is;
  }

  public static  void startPrintStreamThread(DataInputStream inputStream){
    Thread thread = new Thread(new Runnable() {
      byte[] bytes = null;
      @Override
      public void run() {
        while(true){
          try {
            Thread.sleep(500);
            bytes = new byte[1024];
            inputStream.read(bytes);
            System.out.print(new String(bytes,"gbk"));
            bytes = null;
          } catch (IOException e) {
            e.printStackTrace();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    });
    thread.start();
  }

  /**
   *获取执行jar包的命令行
   * @param jarFileName jar包名
   * @param args jvm附加参数
   * @return
   */
  public static String  getJarComandLine(String jarFileName,String[] args){
    StringBuffer buffer = new StringBuffer(CMD_EXE_START);
    buffer.append("javaw -server");
    if(args != null && args.length > 0){
      for(String arg: args){
        buffer.append(" ");
        buffer.append(arg);
      }
      buffer.append(" -jar ");
      buffer.append(jarFileName);
    }
    return buffer.toString();
  }

  public static void main(String[] args) {
    try {
      DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
      DataInputStream inputStream = asyncExcuteCommand("ping 127.0.0.1",(File)null);
      startPrintStreamThread(inputStream);
      //byte[] bytes = new byte[8096];
      //StringBuffer buffer = new StringBuffer();
      //int i = -1;
      //while((i =inputStream.read()) != -1){
      //  buffer.append((char)i);
      //}
      //System.out.println(buffer.toString());
    }catch (Exception e){

    }
  }

}
