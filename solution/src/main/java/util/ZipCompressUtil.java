package util;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：压缩zip工具
 * 日期：2019年09月20日
 * 备注：
 * </pre>
 */
public class ZipCompressUtil {

  private static  final String SUFFIX_ZIP = ".zip";

  public static  void  compressFiles2Zip(File[] files,File destZip) throws  Exception{
      if(null != files && files.length > 0){
        if(endWithZip(destZip)){
          ZipArchiveOutputStream zaos = null;
          try {
            zaos = new ZipArchiveOutputStream(destZip);
            //Use Zip64 extensions for all entries where they are required
            zaos.setUseZip64(Zip64Mode.AsNeeded);
          }catch ( Exception e){
            e.printStackTrace();
          }
         for(File file:files){
            if(file.isDirectory()){
              zipDirectory(file,zaos,"");
            } else {
              zipFile(file,zaos,"");
            }
         }
          zaos.finish();
          if(null != zaos){
            zaos.close();
          }
        } else {
          throw  new Exception("pelease compress to  a zip file, others not support yet");
        }
      }

  }

  /**
   * 压缩单个文件
   * @param file
   * @param zaos
   */
  private static void zipFile(File file, ZipArchiveOutputStream zaos,String prefix){
    if(zaos == null){
      return;
    }

    if(null != file && file.exists()){
      ZipArchiveEntry zipArchiveEntry = null;
      if(file.isFile()){
        zipArchiveEntry = new ZipArchiveEntry(file,prefix+file.getName());
        InputStream is = null;
        try {
          zaos.putArchiveEntry(zipArchiveEntry);
          is = new FileInputStream(file);
          byte[] buffer = new byte[1024 * 8];
          int len = -1;
          while((len = is.read(buffer)) != -1) {
            //把缓冲区的字节写入到ZipArchiveEntry
            zaos.write(buffer, 0, len);
          }
          //Writes all necessary data for this entry.
          zaos.closeArchiveEntry();
        }catch (Exception e){
          e.printStackTrace();
        } finally {
          if(is != null){
            try {
              is.close();
            }catch (Exception e){
              e.printStackTrace();
            }
          }
        }
      }
    }

  }


  private static void zipDirectory(File file,ZipArchiveOutputStream zaos,String prefix) throws  Exception{
    if(null != file && file.isDirectory()){
      File[] files = file.listFiles();
      if(files.length == 0){
        ZipArchiveEntry zipEntry = new ZipArchiveEntry(prefix + file.getName() + "/");
        zaos.putArchiveEntry(zipEntry);
        zaos.closeArchiveEntry();
      } else {
        prefix += file.getName() + File.separator;
        for(File file_ : files){
          if(file_.isDirectory()){
            zipDirectory(file_,zaos,prefix);
          } else {
           zipFile(file_.getAbsoluteFile(),zaos,prefix);
          }
        }
      }

    }
  }



  private static  boolean endWithZip(File file){
    if(null != file &&  null != file.getName() && file.getName().endsWith(SUFFIX_ZIP)){
      return  true;
    }
    return  false;
  }
  //递归获取单个文件
  private static  List<File> getFiles(File[] files){
    List<File> lstFiles =  new ArrayList<>();
    for(File f : files){
      if(f.isDirectory()){
        lstFiles.addAll(getFiles(f.listFiles()));
      }else{
        lstFiles.add(f);
      }
    }
    return  lstFiles;

  }

  public static void main(String[] args) {

  }


}
