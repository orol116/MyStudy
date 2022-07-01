package edu.kh.comm.common;

import java.text.SimpleDateFormat;

public class Util {
	// ���ϸ� ���� �޼ҵ�
    public static String fileRename(String originFileName) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    	String date = sdf.format(new java.util.Date(System.currentTimeMillis()));

    	int ranNum = (int) (Math.random() * 100000); // 5�ڸ� ���� ���� ����

    	String str = "_" + String.format("%05d", ranNum);

    	String ext = originFileName.substring(originFileName.lastIndexOf("."));
  
    	return date + str + ext;
   }
    
    // ũ�ν� ����Ʈ ��Ʈ��Ʈ ������ ���� �ϱ� ���� �޼ҵ�
    public static String XSSHandling(String content) {
       if(content != null) {
          content = content.replaceAll("&", "&amp;");
          content = content.replaceAll("<", "&lt;");
          content = content.replaceAll(">", "&gt;");
          content = content.replaceAll("\"", "&quot;");
       }
       return content;
    }
    
    // ũ�ν� ����Ʈ ��Ʈ��Ʈ ����
    public static String XSSClear(String content) {
       if(content != null) {
          content = content.replaceAll("&amp;", "&");
          content = content.replaceAll("&lt;", "<" );
          content = content.replaceAll("&gt;", ">");
          content = content.replaceAll("&quot;", "\"");
       }
       return content;
    }
    
    // ���๮�� ó�� 
    public static String newLineHandling(String content) {
       return content.replaceAll("(\r\n|\r|\n|\n\r)", "<br>");
    }
    
    // ���๮�� ����
    public static String newLineClear(String content) {
       return content.replaceAll("<br>", "\n");
    }
}