package com.szyl.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
//import java.nio.file.FileVisitResult;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.SimpleFileVisitor;
//import java.nio.file.attribute.BasicFileAttributes;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
 
public class Eml {    

    /**
     * 获取的邮件简历字符串
     */
    private static String eml;
    private static String UUID;
 
	//http://blog.csdn.net/aassdd_zz/article/details/8204344
	private static void parserFile(String emlPath) throws Exception {
//		System.out.println(emlPath);
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		InputStream inMsg;
		inMsg = new FileInputStream(emlPath);
		Message msg = new MimeMessage(session, inMsg);
		inMsg.close();
		parseEml(msg);
	}
 
	private static void parseEml(Message msg) throws Exception {
		// 发件人信息
		Address[] froms = msg.getFrom();
		if (froms != null) {
			// System.out.println("发件人信息:" + froms[0]);
			InternetAddress addr = (InternetAddress) froms[0];
			eml+="发件人地址:" + addr.getAddress()+"\n";
			eml+="发件人显示名:" + addr.getPersonal()+"\n";
//			System.out.println("发件人地址:" + addr.getAddress());
//			System.out.println("发件人显示名:" + addr.getPersonal());
		}
		eml+="邮件主题:" + msg.getSubject()+"\n";
//		System.out.println("邮件主题:" + msg.getSubject());
		// getContent() 是获取包裹内容, Part相当于外包装
		Object o = msg.getContent();
		if (o instanceof Multipart) {
			Multipart multipart = (Multipart) o;
			reMultipart(multipart);
		} else if (o instanceof Part) {
			Part part = (Part) o;
			rePart(part);
		} else {
		    eml+="类型" + msg.getContentType()+"\n";
		    eml+="内容" + msg.getContent()+"\n";
//			System.out.println("类型" + msg.getContentType());
//			System.out.println("内容" + msg.getContent());
		}
	}
	
 
	/**
	 * @param part
	 *            解析内容
	 * @throws Exception
	 */
	private static void rePart(Part part) throws Exception {
		if (part.getDisposition() != null) {
			String strFileNmae = part.getFileName();
			if(!StringUtils.isEmpty(strFileNmae))
			{	// MimeUtility.decodeText解决附件名乱码问题
				strFileNmae=MimeUtility.decodeText(strFileNmae);
				eml+="发现附件: "+ strFileNmae+"\n";
//				System.out.println("发现附件: "+ strFileNmae);
				
				InputStream in = part.getInputStream();// 打开附件的输入流
				// 读取附件字节并存储到文件中
				java.io.FileOutputStream out = new FileOutputStream(strFileNmae);
				int data;
				while ((data = in.read()) != -1) {
					out.write(data);
				}
				in.close();
				out.close();
			}
			eml+="内容类型: "+ MimeUtility.decodeText(part.getContentType())+"\n";
			eml+="附件内容:" + part.getContent()+"\n";
//			System.out.println("内容类型: "+ MimeUtility.decodeText(part.getContentType()));
//			System.out.println("附件内容:" + part.getContent());
		} else {
			if (part.getContentType().startsWith("text/plain")) {
			    eml += "文本内容：" + part.getContent().toString();
//				System.out.println("文本内容：" + part.getContent());
//				FileIO.write(eml_file,part.getContent().toString(),"UTF-8");
			} else {
//				 System.out.println("HTML内容：" + part.getContent());
			    FileIO.write(Default.emlPath + UUID + ".html",  
			            part.getContent().toString(),"utf-8");
			}
		}
	}
 
	/**
	 * @param multipart
	 *            // 接卸包裹（含所有邮件内容(包裹+正文+附件)）
	 * @throws Exception
	 */
	private static void reMultipart(Multipart multipart) throws Exception {
//		 System.out.println("邮件共有" + multipart.getCount() + "部分组成");
		// 依次处理各个部分
		for (int j = 0, n = multipart.getCount(); j < n; j++) {
//			 System.out.println("处理第" + j + "部分");
			Part part = multipart.getBodyPart(j);// 解包, 取出 MultiPart的各个部分,
													// 每部分可能是邮件内容,
			// 也可能是另一个小包裹(MultipPart)
			// 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
			if (part.getContent() instanceof Multipart) {
				Multipart p = (Multipart) part.getContent();// 转成小包裹
				// 递归迭代
				reMultipart(p);
			} else {
				rePart(part);
			}
		}
	}
	
	/**
	 * 获取eml文件内容字符串
	 * @param pathname 文件的绝对路径
	 * @return
	 */
	public static String getContent(String pathname, String uuid) throws Exception {
	    try {
	        UUID = uuid;
            parserFile(pathname);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	    String emlStr = eml;
	    eml="";
	    return emlStr;
	}
	
	public static void main(String[] args) {
        try {
            System.out.println(getContent("D://LinuxWorkspace//SZYL//WebContent//eml//无.eml", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
 
}
