package ruankao;

/**
 * <pre>
 * 作者：shenliang
 * 项目：ruankao
 * 说明：海明码
 * 日期：2019年09月09日
 * 备注：
 * </pre>
 */
public class HammingCode {

  public static void main(String[] args) {

    System.out.println(getCheckCodeNums(11));

    byte[] bytes = {1,0,0,1,0,1,1,0};

    System.out.println(getHammingCode(bytes));

  }

  /**
   *  获取检验码最小位数
   * @param  n 数据位数 n
   *
   *  2^k - 1>= n +k;
   * @return
   */
  public static int getCheckCodeNums(int  n){
    if(0 ==  n){
      return  0;
    }
    int i = 0;
    while(Math.pow(2,i)- i < n+1){
      i++;
    }
    return  i;
  }

  public static byte[] getHammingCode(byte[] bytes){
    int nums = getCheckCodeNums(bytes.length);
    int hammingCodeLen = nums+ bytes.length;
    byte[] hammingCode = new byte[hammingCodeLen];
    //计算效验码的位置Math.pow(2,i)-1
    int[] position = new int[nums];
    position[0] = 1;
    int i = 1;
    while( i < nums && Math.pow(2,i) <= hammingCodeLen){
      position[i] =(int)Math.pow(2,i);
      i++;
    }
    //初始化海明码
    //效验码初始为0，数据位填充余位
    int j = 0;
    for(int k= 0;k < hammingCodeLen;k++){
      if(((k+1)&k) == 0 ){
        continue;
      }
      hammingCode[k] = bytes[j++];
    }
    //计算效验码的值
    for(int p = 0;p < position.length;p++){
      byte v = 0;
      for(int k= 0;k< hammingCodeLen;k++){
        if(k != position[p]-1 && ((k+1)>>p & 1) == 1){
          v ^= hammingCode[k];
        }
      }
      hammingCode[position[p]-1] = v;
    }
    return   hammingCode;
  }



}
