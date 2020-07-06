package ali;


import java.math.BigDecimal;

/**
 * float浮点数  double双位浮点数 在计算机存储是只能是近似值
 * 不推荐是 == 比较
 * 应该定义一个精度 e = 1e-7;
 * Created by god on 2019/6/17.
 */
public class FloatTest {

    public static void main(String[] args) {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        double e = 1e-7;
        System.out.println(a == b);//false
        System.out.println(a - b< e);//true
        System.out.println(Float.floatToIntBits(2.2f-2.1f));//浮点数二进制表示方式对应的整数
        System.out.println(Float.floatToIntBits(0.1f));
        //
        Float aw = new Float(a);
        Float bw = new Float(b);
        System.out.println(aw.equals(bw));//false

        BigDecimal biga = new BigDecimal(0.1);//不推荐
        System.out.println(biga);
        BigDecimal bigb = new BigDecimal("0.1");
        System.out.println(bigb);


    }
}
