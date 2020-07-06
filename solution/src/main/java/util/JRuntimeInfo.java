package util;

import java.io.Serializable;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：java运行环境参数类
 * 日期：2019年09月24日
 * 备注：
 * </pre>
 */

public class JRuntimeInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  private final String JAVA_RUNTIME_NAME = System.getProperty("java.runtime.name");
  private final String JAVA_RUNTIME_VERSION = System.getProperty("java.runtime.version");
  private final String JAVA_HOME = System.getProperty("java.home");
  private final String JAVA_EXT_DIRS = System.getProperty("java.ext.dirs");
  private final String JAVA_ENDORSED_DIRS = System.getProperty("java.endorsed.dirs");
  private final String JAVA_CLASS_PATH = System.getProperty("java.class.path");
  private final String JAVA_CLASS_VERSION = System.getProperty("java.class.version");
  private final String JAVA_LIBRARY_PATH = System.getProperty("java.library.path");
  private final String SUN_BOOT_CLASS_PATH = System.getProperty("sun.boot.class.path");
  private final String SUN_ARCH_DATA_MODEL = System.getProperty("sun.arch.data.model");

  public JRuntimeInfo() {
  }

  public final String getSunBoothClassPath() {
    return this.SUN_BOOT_CLASS_PATH;
  }

  public final String getSunArchDataModel() {
    return this.SUN_ARCH_DATA_MODEL;
  }

  public final String getName() {
    return this.JAVA_RUNTIME_NAME;
  }

  public final String getVersion() {
    return this.JAVA_RUNTIME_VERSION;
  }

  public final String getHomeDir() {
    return this.JAVA_HOME;
  }

  public final String getExtDirs() {
    return this.JAVA_EXT_DIRS;
  }

  public final String getEndorsedDirs() {
    return this.JAVA_ENDORSED_DIRS;
  }

  public final String getClassPath() {
    return this.JAVA_CLASS_PATH;
  }

  public final String[] getClassPathArray() {
    return this.getClassPath().split(System.getProperty("path.separator"));
  }

  public final String getClassVersion() {
    return this.JAVA_CLASS_VERSION;
  }

  public final String getLibraryPath() {
    return this.JAVA_LIBRARY_PATH;
  }

  public final String[] getLibraryPathArray() {
    return this.getLibraryPath().split(System.getProperty("path.separator"));
  }

  public final String getProtocolPackages() {
    return System.getProperty("java.protocol.handler.pkgs");
  }
  
  private void styleToString(StringBuilder builder,String name,String value){
    builder.append(name+"    ");
    builder.append(value);
    builder.append(OsInfo.LINE_SEPARATOR);
  }

  public final String toString() {
    StringBuilder builder = new StringBuilder();
    styleToString(builder,"Java Runtime Name:      ",this.getName());
    styleToString(builder,"Java Runtime Version:   ",this.getVersion());
    styleToString(builder,"Java Class Version:     ",getClassVersion());
    styleToString(builder,"Java Home Dir:          ",this.getHomeDir());
    styleToString(builder,"Java Extension Dirs:    ",getExtDirs());
    styleToString(builder,"Java Endorsed Dirs:     ",getEndorsedDirs());
    styleToString(builder,"Java Class Path:        ",getClassPath());
    styleToString(builder,"Java Library Path:      ",getLibraryPath());
    styleToString(builder,"Java Protocol Packages: ",getProtocolPackages());
    return builder.toString();
  }

  public static void main(String[] args) {
    System.out.println(new JRuntimeInfo().toString());
  }
}
