package com.oddskings.utilFiles;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class MailUtils {  
 public static void main(String agrs[]) {  
  String to = "yyyyyyyyy@gmail.com";  
  final String user = "xxxxxxx@gmail.com";  
  final String password = "zzzzzzzzzz";  
  Properties properties = System.getProperties();  
  properties.setProperty("mail.smtp.host", "mail.c-sharpcorner.com");  
  properties.put("mail.smtp.auth", "true");  
  Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPassworAuthentication() {  
    return new PasswordAuthentication(user, password);  
   }  
  });  
  try {  
   MimeMessage message = new MimeMessage(session);  
   message.setFrom(new InternetAddress(user));  
   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));  
   message.setSubject("Message Alert");  
   BodyPart messageBodyPart1 = new MimeBodyPart();  
   messageBodyPart1.setText("This is the message body");  
   MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
   String filename = "C:\\Users\\dhivy\\eclipse-workspace\\oddsKingAutomation\\TestData\\TestResult.xlsx";  
   DataSource source = new FileDataSource(filename);  
   messageBodyPart2.setDataHandler(new DataHandler(source));  
   messageBodyPart2.setFileName(filename);  
   Multipart multipart = new MimeMultipart();  
   multipart.addBodyPart(messageBodyPart1);  
   multipart.addBodyPart(messageBodyPart2);  
   message.setContent(multipart);  
   Transport.send(message);  
   System.out.println("Message has been sent");  
  } catch (MessagingException ex) {  
   ex.printStackTrace();  
  }  
 }  
} 