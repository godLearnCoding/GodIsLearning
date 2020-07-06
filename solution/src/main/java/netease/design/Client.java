package netease.design;

/**
 * <pre>
 * 作者：shenliang
 * 项目：netease.design
 * 说明：
 * 日期：2020年05月15日
 * 备注：
 * </pre>
 */
public class Client {

  public static void main(String[] args) {
    GodsStringBuffer2 buffer = new GodsStringBuffer2();
    for(int i = 0;i < 26;i++){
      buffer.append((char)('a'+i));
    }
    buffer.append('0').append('1').append('2').append('3').append('4').append('5').append('6').append('7').append('8');
    System.out.println(buffer);
    System.out.println("当前长度："+buffer.getLen());
    System.out.println("当前容量："+buffer.getCapacity());


  }

}
