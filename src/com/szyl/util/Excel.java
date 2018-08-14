package com.szyl.util;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Excel{

	// 文件
	public WritableWorkbook book = null;
	// 第一页
	public WritableSheet first_sheet = null;
	// 已写了多少行
	public int rows = 0;

	/**
	 * 新建excel表（使用默认名字）
	 * @param path路径
	 */
	public void newXLS(String path, String name) {
		newXLS(path+"\\"+name);
	}

	/**
	 * 新建excel表
	 * @param path路径
	 * @param name文件名
	 */
	public void newXLS(String pathname) {

		try {
			// 新建一个文件
			book = Workbook.createWorkbook(new File(pathname));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			first_sheet = book.createSheet("简历数据", 0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 写入一行数据（默认输入标题栏数据）
	 */
	public void writeXLS() {
		String[] row = { "候选人姓名", "性别", "应聘岗位", "年龄", "工作年限", "专业", "专科毕业院校", "本科毕业院校", "硕士毕业院校", "技能（前台，后台）", "其他关键技能",
				"简历附件" };
		writeXLS(row);
	}

	/**
	 * 写入一行数据
	 * @param row该行数据的具体内容
	 */
	@SuppressWarnings("deprecation")
    public void writeXLS(String[] row) {

		try {
			Label[] label = new Label[row.length];
			for (int i = 0; i < row.length; i++) {
				label[i] = new Label(i, rows, row[i]);
				// 将定义好的单元格添加到工作表中
				first_sheet.addCell(label[i]);
			}
			
			//设置每列宽
			for(int i=0;i<row.length;i++){    
	            if(first_sheet.getColumnWidth(i)<getWordCount(row[i])+4){
	                first_sheet.setColumnView(i, getWordCount(row[i])+4);
	            }	            
	        }
			rows++;// 已写行数=1
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 写入一行Work数据
	 * @param work
	 */
//	public void writeXLS(int index, Datas datas) {
//		String[] row = { ""+index,datas.getName(),datas.getPhone(),
//		        datas.getEmail(),datas.getPost(),datas.getReporttime(),datas.getStarttime(),
//		        datas.getPosition(),datas.getDirection(),datas.getBirthday()};
//		writeXLS(row);
//	}

	/**
	 * 写入数据并关闭文件
	 */
	public void writeclose() {
		try {
			// 写入数据
			book.write();
			// 关闭文件
			book.close();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*由于Java是基于Unicode编码的，因此，一个汉字的长度为1，而不是2。 
     * 但有时需要以字节单位获得字符串的长度。例如，“123abc长城”按字节长度计算是10，而按Unicode计算长度是8。 
     * 为了获得10，需要从头扫描根据字符的Ascii来获得具体的长度。如果是标准的字符，Ascii的范围是0至255，如果是汉字或其他全角字符，Ascii会大于255。 
     * 因此，可以编写如下的方法来获得以字节为单位的字符串长度。*/  
    private static int getWordCount(String s)  
    {  
        int length = 0;  
        for(int i = 0; i < s.length(); i++)  
        {  
            int ascii = Character.codePointAt(s, i);  
            if(ascii >= 0 && ascii <=255)  
                length++;  
            else  
                length += 2;  

        }  
        return length;  
    }
    
    public static void main(String[] args) {
        //使用举例
        Excel excel = new Excel();
        excel.newXLS("D://LinuxWorkspace//SZYL//WebContent//xls//导出文件.xls");
        String[] row = { "候选人姓名", "性别", "应聘岗位", "年龄", "工作年限", "专业", "专科毕业院校", "本科毕业院校", "硕士毕业院校", "技能（前台，后台）", "其他关键技能",
        "简历附件" };
        //写入一行数据
        excel.writeXLS(row);
        excel.writeclose();
    }

}
