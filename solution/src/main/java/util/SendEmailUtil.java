package  util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;



/**
 *
 * <pre>
 * 作者：shenliang
 * 项目：
 * 类说明：发送邮件
 * 日期：2018年8月27日
 * 备注：
 * </pre>
 */
public class SendEmailUtil {

  /**
   * 发送邮件，正文支持图片资源
   *
   * @param user
   *            发件人邮箱
   * @param password
   *            授权码（注意不是邮箱登录密码）
   * @param host
   *            发件服务器地址
   * @param from
   *            发件人
   * @param to
   *            接收者邮箱
   * @param subject
   *            邮件主题
   * @param content
   *            邮件内容
   * @param realPath 资源真实路径
   * @return success 发送成功 failure 发送失败
   * @throws Exception
   */
  public static boolean sendMail(String user, String password, String host, String from, String to, String subject,String content,String realPath) throws Exception {
    if (to != null) {
      Properties props = System.getProperties();
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.auth", "true");
      Authenticator auth = new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          return new PasswordAuthentication(user,password);
        }
      };
      Session session = Session.getInstance(props, auth);
      //开启调试模式
      session.setDebug(true);
      try {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        if (!to.trim().equals(""))
          message.addRecipient(Message.RecipientType.TO, new InternetAddress(to.trim()));
        // 抄送人(抄送一份发件人)
        message.addRecipient(Message.RecipientType.CC, new InternetAddress(from.trim()));
        message.setSubject(subject);
        MimeMultipart mm_text_image = new MimeMultipart("related");
        phraseContent(mm_text_image, content, realPath);
        // 添加附件
        message.setContent(mm_text_image);
        message.setSentDate(new Date());
        message.saveChanges();
        Transport trans = session.getTransport("smtp");
        trans.send(message);
      } catch (Exception e) {
        e.printStackTrace();
        return false;
      }
      return true;
    } else {
      return true;
    }

  }

  private static void phraseContent(MimeMultipart filepart, String content, String realPath) throws MessagingException {
    if (null != realPath && realPath.length() != 0) {
      List<String> urlList = new ArrayList<>();
      int index = -9;
      int len = content.length();
      while (index != -1) {
        index = content.indexOf("<img src=", index + 9);
        if (index == -1) {
          break;
        }
        int i = index + 9;
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        while (i < len) {
          char c = content.charAt(i);
          if (c == 39 || c == 34) {
            count++;
          } else {
            buffer.append(c);
          }
          if (count == 2) {
            break;
          }
          i++;
        }
        urlList.add(buffer.toString());
      }
      if (urlList.size() > 0) {
        for (String imgUrl : urlList) {
          if (imgUrl.startsWith("http")) {
            continue;
          }
          MimeBodyPart image = new MimeBodyPart(); // 图片
          DataSource fds = new FileDataSource(realPath + imgUrl);
          String imgUrl_ = imgUrl.replace("/", File.separator);
          int lastIndex = imgUrl_.lastIndexOf(File.separator);
          String contentId = imgUrl_.substring(lastIndex);
          image.setDataHandler(new DataHandler(fds));
          image.setContentID("<" + contentId + ">");
          content = content.replace(imgUrl, "cid:" + contentId);
          image.setDisposition(MimeBodyPart.INLINE);
          filepart.addBodyPart(image);
        }
      }
    }
    MimeBodyPart textContend = new MimeBodyPart(); // 正文
    textContend.setContent(content, "text/html;charset=utf-8");
    System.out.println(content);
    filepart.addBodyPart(textContend);
  }
}