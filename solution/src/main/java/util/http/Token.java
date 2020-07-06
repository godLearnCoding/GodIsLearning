package util.http;

import java.io.Serializable;

/**
 * <pre>
 * 作者：shenliang
 * 项目：util.http
 * 说明：令牌模型
 * 日期：2019年09月26日
 * 备注：
 * </pre>
 */
public class Token implements Serializable{

  /**
   * token标识
   * uuid
   */
  private String id;


  /**
   * 过期时长
   */
  private long timeout ;

  /**
   * 创建时间
   */
  private long createTime ;


  public Token(String id){
    this.id = id;
    this.createTime = System.currentTimeMillis();
  }

  public Token(String id,long timeout){
    this.id = id;
    this.timeout = timeout;
    this.createTime = System.currentTimeMillis();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public boolean isExpire() {
    if(this.timeout == 0){
      return false;
    }
    if(System.currentTimeMillis() - this.createTime >= this.timeout){
      return true;
    } else {
      return false;
    }
  }

  public long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(long createTime) {
    this.createTime = createTime;
  }

  public long getTimeout() {
    return timeout;
  }

  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }
}
