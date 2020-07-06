package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util
 * 说明：文件上传和下周工具
 * 日期：2019年11月13日
 * 备注：
 * </pre>
 */
public class FileUploadUtil {

  public static final Map<String,List<UploadFile>> uploadFilesCache = new HashMap<>();


  public static void addFileToCache(UploadFile file){

  }



  private class UploadFile{

    /**
     *文件组
     */
    private String fileGroupCode;

    /**
     * 文件标识
     */
    private String fileCode;
    /**
     * 文件类型
     */
    private String fileType;


    /**
     *文件名
     */
    private String fileName;

    /**
     *文件路径
     */
    private String filePath;

    /**
     * 上传位置
     */
    private String destPath;

    /**
     *文件数据
     */
    private byte[] fileData;

    public UploadFile(){

    }

    public String getFileGroupCode() {
      return fileGroupCode;
    }

    public void setFileGroupCode(String fileGroupCode) {
      this.fileGroupCode = fileGroupCode;
    }

    public String getFileCode() {
      return fileCode;
    }

    public void setFileCode(String fileCode) {
      this.fileCode = fileCode;
    }

    public String getFileType() {
      return fileType;
    }

    public void setFileType(String fileType) {
      this.fileType = fileType;
    }

    public String getFileName() {
      return fileName;
    }

    public void setFileName(String fileName) {
      this.fileName = fileName;
    }

    public String getFilePath() {
      return filePath;
    }

    public void setFilePath(String filePath) {
      this.filePath = filePath;
    }

    public String getDestPath() {
      return destPath;
    }

    public void setDestPath(String destPath) {
      this.destPath = destPath;
    }

    public byte[] getFileData() {
      return fileData;
    }

    public void setFileData(byte[] fileData) {
      this.fileData = fileData;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }

      UploadFile that = (UploadFile) o;

      if (fileGroupCode != null ? !fileGroupCode.equals(that.fileGroupCode) : that.fileGroupCode != null) {
        return false;
      }
      if (fileCode != null ? !fileCode.equals(that.fileCode) : that.fileCode != null) {
        return false;
      }
      if (fileType != null ? !fileType.equals(that.fileType) : that.fileType != null) {
        return false;
      }
      if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) {
        return false;
      }
      if (filePath != null ? !filePath.equals(that.filePath) : that.filePath != null) {
        return false;
      }
      if (destPath != null ? !destPath.equals(that.destPath) : that.destPath != null) {
        return false;
      }
      return Arrays.equals(fileData, that.fileData);
    }

    @Override
    public int hashCode() {
      int result = fileGroupCode != null ? fileGroupCode.hashCode() : 0;
      result = 31 * result + (fileCode != null ? fileCode.hashCode() : 0);
      result = 31 * result + (fileType != null ? fileType.hashCode() : 0);
      result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
      result = 31 * result + (filePath != null ? filePath.hashCode() : 0);
      result = 31 * result + (destPath != null ? destPath.hashCode() : 0);
      result = 31 * result + Arrays.hashCode(fileData);
      return result;
    }
  }

}
