package util;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * Created by god on 2019/6/19.
 */
public class Word2pdfUtil {

    public static void main(String[] args) {
        String wordFile = "C:\\Users\\god\\Desktop\\运营管理系统强平流程测试及问题反馈20190612.docx";
        String pdfFile = "C:\\Users\\god\\Desktop\\1.pdf";
        // System.out.print(System.getProperty("java.library.path"));//保证jacob-1.19-x64.dll放在jdk/bin目录下 或者  设置jacob.dll.path
        // System.setProperty("jacob.dll.path","C:\\mecrt\\java\\jdk\\lib\\jacob-1.19-x64.dll");//dll路径
        long s = System.currentTimeMillis();
        ActiveXComponent app  = new ActiveXComponent("Word.Application");
        app.setProperty("Visible",false);
        Dispatch docs = app.getProperty("Documents").getDispatch();
        Variant doc = Dispatch.call(docs,
                "Open",//调用Documents对象的Open方法
                wordFile,// 输入文件路径全名
                false, //ConfirmConversions，设置为false表示不显示转换框
                true//ReadOnly
        );
        Dispatch.call(doc.toDispatch(),//要转换的文档
                "SaveAS",
                pdfFile,//要保存的PDF文件名
                17//转换后的文件格式宏，值为17，可查询MSDN得到
        );
        //关闭打开的Word文件
        Dispatch.call(doc.toDispatch(),
                "Close",
                false);//设置不保存改变);
        //关闭Word应用程序
                app.invoke("Quit",0);

        System.out.println("转换耗时："+(System.currentTimeMillis()-s));
    }
}
