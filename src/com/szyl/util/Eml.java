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
     * ��ȡ���ʼ������ַ���
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
		// ��������Ϣ
		Address[] froms = msg.getFrom();
		if (froms != null) {
			// System.out.println("��������Ϣ:" + froms[0]);
			InternetAddress addr = (InternetAddress) froms[0];
			eml+="�����˵�ַ:" + addr.getAddress()+"\n";
			eml+="��������ʾ��:" + addr.getPersonal()+"\n";
//			System.out.println("�����˵�ַ:" + addr.getAddress());
//			System.out.println("��������ʾ��:" + addr.getPersonal());
		}
		eml+="�ʼ�����:" + msg.getSubject()+"\n";
//		System.out.println("�ʼ�����:" + msg.getSubject());
		// getContent() �ǻ�ȡ��������, Part�൱�����װ
		Object o = msg.getContent();
		if (o instanceof Multipart) {
			Multipart multipart = (Multipart) o;
			reMultipart(multipart);
		} else if (o instanceof Part) {
			Part part = (Part) o;
			rePart(part);
		} else {
		    eml+="����" + msg.getContentType()+"\n";
		    eml+="����" + msg.getContent()+"\n";
//			System.out.println("����" + msg.getContentType());
//			System.out.println("����" + msg.getContent());
		}
	}
	
 
	/**
	 * @param part
	 *            ��������
	 * @throws Exception
	 */
	private static void rePart(Part part) throws Exception {
		if (part.getDisposition() != null) {
			String strFileNmae = part.getFileName();
			if(!StringUtils.isEmpty(strFileNmae))
			{	// MimeUtility.decodeText�����������������
				strFileNmae=MimeUtility.decodeText(strFileNmae);
				eml+="���ָ���: "+ strFileNmae+"\n";
//				System.out.println("���ָ���: "+ strFileNmae);
				
				InputStream in = part.getInputStream();// �򿪸�����������
				// ��ȡ�����ֽڲ��洢���ļ���
				java.io.FileOutputStream out = new FileOutputStream(strFileNmae);
				int data;
				while ((data = in.read()) != -1) {
					out.write(data);
				}
				in.close();
				out.close();
			}
			eml+="��������: "+ MimeUtility.decodeText(part.getContentType())+"\n";
			eml+="��������:" + part.getContent()+"\n";
//			System.out.println("��������: "+ MimeUtility.decodeText(part.getContentType()));
//			System.out.println("��������:" + part.getContent());
		} else {
			if (part.getContentType().startsWith("text/plain")) {
			    eml += "�ı����ݣ�" + part.getContent().toString();
//				System.out.println("�ı����ݣ�" + part.getContent());
//				FileIO.write(eml_file,part.getContent().toString(),"UTF-8");
			} else {
//				 System.out.println("HTML���ݣ�" + part.getContent());
			    FileIO.write(Default.emlPath + UUID + ".html",  
			            part.getContent().toString(),"utf-8");
			}
		}
	}
 
	/**
	 * @param multipart
	 *            // ��ж�������������ʼ�����(����+����+����)��
	 * @throws Exception
	 */
	private static void reMultipart(Multipart multipart) throws Exception {
//		 System.out.println("�ʼ�����" + multipart.getCount() + "�������");
		// ���δ����������
		for (int j = 0, n = multipart.getCount(); j < n; j++) {
//			 System.out.println("�����" + j + "����");
			Part part = multipart.getBodyPart(j);// ���, ȡ�� MultiPart�ĸ�������,
													// ÿ���ֿ������ʼ�����,
			// Ҳ��������һ��С����(MultipPart)
			// �жϴ˰��������ǲ���һ��С����, һ����һ������ ���� Content-Type: multipart/alternative
			if (part.getContent() instanceof Multipart) {
				Multipart p = (Multipart) part.getContent();// ת��С����
				// �ݹ����
				reMultipart(p);
			} else {
				rePart(part);
			}
		}
	}
	
	/**
	 * ��ȡeml�ļ������ַ���
	 * @param pathname �ļ��ľ���·��
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
            System.out.println(getContent("D://LinuxWorkspace//SZYL//WebContent//eml//��.eml", ""));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
 
}
