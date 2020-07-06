package util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <pre>
 * 作者：shenliang
 * 项目：monitor.common.util
 * 说明：
 * 日期：2019年10月31日
 * 备注：
 * </pre>
 */
public class FileUtil {

  public static final String FILE_SIZE_TYPE_B = "b";

  public static final String FILE_SIZE_TYPE_KB = "KB";

  public static final String FILE_SIZE_TYPE_MB = "MB";

  private static final   String SEPERATOR = System.getProperty("file.separator");

  /**
   * 获取文件名【1】和文件所在目录【0】
   *
   * @param file
   * @return
   */
  public static String[] resolveFile(File file) {
    if (isValidFile(file) && file.isFile()) {
      return new String[]{file.getParent(), file.getName()};
    }
    return new String[]{"", ""};
  }

  public static String resolveFileName(File file) {
    if (isValidFile(file)  && file.isFile()) {
      return file.getName();
    }
    return "";
  }

  public static String resolveFilePath(File file) {
    if (isValidFile(file) && file.isFile()) {
      return file.getParent();
    }
    return "";
  }

  public  static  String getFileNameByPath(String filePath){
    if(null == filePath){
      return "";
    }else {
      int index = filePath.lastIndexOf(SEPERATOR);
      if(index < 0){
        return  "";
      }
      return filePath.substring(index);
    }


  }

  public static long getFileSize(File file, String fileSizeType) {
    switch (fileSizeType) {
      case FILE_SIZE_TYPE_KB:
        return getFileSize(file) / 1024;
      case FILE_SIZE_TYPE_MB:
        return getFileSize(file) / 1024 / 1024;
      default:
        return getFileSize(file);
    }
  }

  private static long getFileSize(File file) {
    AtomicLong size = new AtomicLong(0);
    getFileSize(file, size);
    return size.get();
  }

  private static long getFileSize(File file, AtomicLong atomicLong) {
    if (!isValidFile(file)) {
      return atomicLong.get();
    }
    if (file.isFile()) {
      return atomicLong.addAndGet(file.length());
    }
    if (file.isDirectory()) {
      File[] files = file.listFiles();
      if (null == files || files.length > 0) {
        for (File f : files) {
          getFileSize(f, atomicLong);
        }
      }
    }
    return atomicLong.get();
  }

  /**
   * 获取多个文件信息（json）
   *
   * @param files
   * @return
   */
  public static JSONArray getFilesJsonInfo(File[] files) {
    JSONArray jsonArray = null;
    if (isValidFileArray(files)) {
      jsonArray = new JSONArray();
      for (File file : files) {
        jsonArray.add(getJsonInfo(file));
      }
    }
    return jsonArray;
  }

  /**
   * 获取单个文件信息（json）
   *
   * @param file
   * @return
   */
  public static JSONObject getJsonInfo(File file) {
    JSONObject jsonObject = null;
    if (isValidFile(file)) {
      jsonObject = new JSONObject();
      jsonObject.put("filePath", file.getAbsolutePath());//文件路径
      jsonObject.put("fileName", file.getName());//文件名
      jsonObject.put("isDirectory", file.isDirectory());//是否文件夹
      jsonObject.put("fileSize", getFileSize(file));//文件大小
      jsonObject.put("updateTime", file.lastModified());//最近修改时间
    }
    return jsonObject;
  }

  /**
   * 读取文件
   * 支持最大文件Integer.MAX_VALUE字节
   *
   * @param file
   * @return
   */
  public static byte[] readFile(File file) {
    return readFile(file, Integer.MAX_VALUE);
  }


  /**
   * 写入文件
   *
   * @param file
   * @param content
   * @param append  是否追加
   * @return
   */
  public static boolean saveFile(File file, byte[] content, boolean append) {
    if (isValidFile(file)) {
      FileOutputStream fos = null;
      try {
        fos = new FileOutputStream(file, append);
        fos.write(content);
        return true;
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        if (null != fos) {
          try {
            fos.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    }
    return false;

  }


  public static byte[] readFile(File file, int readMaxSize) {
    long fileSize = file.length();
    if (fileSize > Integer.MAX_VALUE) {
      fileSize = Integer.MAX_VALUE;
    }
    byte[] bytes = new byte[fileSize > readMaxSize ? readMaxSize : (int) fileSize];
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(file);
      fis.read(bytes);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (null != fis) {
        try {
          fis.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return bytes;
  }

  /**
   * 删除文件夹及文件下的文件(递归)
   * @param file
   * @return
   */
  public static void deleteFolder(File file){
    if(!isValidFile(file)){
      return ;
    }
    //判断是否是快捷方式：软链接
    if(!Files.isSymbolicLink(file.toPath())){
      //清空文件夹
      clearFolder(file);
    }
    file.delete();
  }

  /**
   * 清空文件夹
   * @param file
   */
  public static void clearFolder(File file){
    if(isValidFile(file)){
      return;
    }
    File[] files = file.listFiles();
    if(isValidFileArray(files)){
      for(File f: files){
        if(f.isDirectory()){
          deleteFolder(f);
        } else{
          f.delete();
        }
      }
    }
  }


  public static boolean isValidFile(File file) {
    return null != file && file.exists();
  }

  public static boolean isValidFileArray(File[] files) {
    return null != files && files.length > 0;
  }

  /**
   *
   * @param base64Img 图片内容，base64格式的字符串
   * @param filePath 图片保存路径
   */
  public static void savebase64ImgFile(String base64Img,String filePath){
    // Base64解码
    BASE64Decoder decoder = new BASE64Decoder();
    byte[] bytes = new byte[0];
    try {
      //去除base64中无用的部分
      bytes = decoder.decodeBuffer(base64Img.replace("data:image/jpeg;base64,", ""));
      for (int i = 0; i < bytes.length; ++i) {
        if (bytes[i] < 0) {// 调整异常数据
          bytes[i] += 256;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (bytes.length <= 1) {
      return;
    }
    try {
      File file = new File(filePath);
      if (!file.exists()) {
        File parent = file.getParentFile();
        if (null != parent) {
          parent.mkdirs();
          file.createNewFile();
        }
      }
      FileOutputStream outputStream = new FileOutputStream(file);
      outputStream.write(bytes);
      outputStream.flush();
      outputStream.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public static void main(String[] args) {
    deleteFolder(new File("D:\\jetty-distribution-9.2.22.v20170606\\README.TXT"));
    System.out.println();
  }
}
