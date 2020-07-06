package util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.Statement;

/**
 * 用于批量执行sql语句
 * Created by god on 2019/5/7.
 */
public class BatchExcuteSql {

    private static String ip = "127.0.0.1";

    private static String port = "1433";

    private static  String dbName = "monitorDB";

    private static  String userName = "sa";

    private static  String password = "1";

    private static String sql_path = "F:\\svn\\platform\\monitor-prt\\monitor-sql\\src\\main\\resources\\MSSQL\\1_tables\\2_init";

    ///////////////////////////////////////////////////////////////////////////////////////////////////

    private static  String separator = System.getProperty("line.separator");

    //jdbc config
    private static String driverClassName = "net.sourceforge.jtds.jdbc.Driver";

    private static  String jdbcUrl = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+dbName;

    public static void main(String[] args){

        //获取数据库连接
        Connection connection = getConnection();
        //获取脚本
        List<String> sqls = getSql(new ArrayList<String>(), new File(sql_path));
        System.out.println("共有:"+sqls.size()+"个脚本,准备执行");
       Statement st =  null;
        try {
            connection.setAutoCommit(false);
          st =  connection.createStatement();
          for(String sql: sqls){
              st.addBatch(sql);
          }
          st.executeBatch();
          connection.commit();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(null != st){
                try {
                    st.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
            if(null != connection){
                try {
                    connection.close();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

      System.out.println("所有脚本执行完成！");

    }

  /**
   * 获取目录下所有sql脚本文件
   * @param list 脚本list
   * @param file
   * @return list 脚本
   */
    public static List<String> getSql(List<String> list,File file){
      if(file.exists()){
        if(file.isDirectory()){
          File[] sonfiles = file.listFiles();
          //递归读取所有sql文件
          for(File sonFile :sonfiles){
            getSql(list,sonFile);
          }
        }else if(file.isFile()){
          list.add(readFileSql(file));
        }
      }
      return list;
    }

  /**
   * 读取sql文件脚本
   * @param file
   * @return
   */
  private static String readFileSql(File file){
      String s = null;
      if(null != file  && file.isFile()){
        try {
          BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsoluteFile()),"GBK"));
          String line = null;
          StringBuffer buffer = new StringBuffer();
          while((line = br.readLine()) != null){
            buffer.append(line);
            buffer.append(separator);
          }
          s = buffer.toString();
          s = s.replace("go","");//去掉go关键字
          s = s.replace("${db.name}",dbName);//替换${db.name}
        }catch (Exception e){
          e.printStackTrace();
        }
      }
      return s;
    }

    public static  Connection getConnection(){
        Connection connection = null;
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(userName);
        config.setPassword(password);
        //!-- 连接只读数据库时配置为true， 保证安全 -->
        config.setReadOnly(false);
        //<!-- 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 缺省:30秒 -->
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        //<!-- 连接池中允许的最大连接数。缺省值：10；推荐的公式：((core_count * 2) + effective_spindle_count) -->
        config.setMaximumPoolSize(10);
        //<!-- 一个连接的生命时长（毫秒），超时而且没被使用则被释放（retired），缺省:30分钟，建议设置比数据库超时时长少30秒，参考MySQL
       // wait_timeout参数（show variables like '%timeout%';） -->
        config.setMaxLifetime(1800000);
        config.setMinimumIdle(10);
        //#跳过测试 否则报错：JtdsConnection.isValid() java.lang.AbstractMethodError: null
        config.setConnectionTestQuery("SELECT 1");
        HikariDataSource source  = new HikariDataSource(config);
        try {
            connection = source.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
         return connection;
    }

}
