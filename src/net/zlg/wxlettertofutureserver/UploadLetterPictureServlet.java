package net.zlg.wxlettertofutureserver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;




//@WebServlet(description = "������ȡ�û�������Ϣ��Servlet�������", urlPatterns = { "/GetIfoServlet" })
@SuppressWarnings("serial")
public class UploadLetterPictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * @see HttpServlet#HttpServlet()
     */
    public UploadLetterPictureServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException { 
		
		PrintWriter Pout = response.getWriter();		
        
        request.setCharacterEncoding("utf-8");
        
              
        //��ȡ�ļ��ϴ���Ҫ�����·����upload�ļ�������ڡ�  
        String path = this.getServletContext().getRealPath("upload/photo");  
       
        
        // ��ȡϵͳĬ�ϵ���ʱ�ļ�����·������·��ΪTomcat��Ŀ¼�µ�temp�ļ���
        String temp = System.getProperty("java.io.tmpdir");
         //��ô����ļ���Ŀ������  
        DiskFileItemFactory factory = new DiskFileItemFactory(); 
        //������ʱ����ļ��Ĵ洢�ң�����洢�ҿ��Ժ����մ洢�ļ����ļ��в�ͬ����Ϊ���ļ��ܴ�Ļ���ռ�ù����ڴ��������ô洢�ҡ�  
        factory.setRepository(new File(temp));  
        //���û���Ĵ�С�����ϴ��ļ���������������ʱ���ͷŵ���ʱ�洢�ҡ�  
        factory.setSizeThreshold(1024*1024*5); 
        
        //�ϴ��������ࣨ��ˮƽAPI�ϴ�������  
        ServletFileUpload upload = new ServletFileUpload(factory);  
          
        try{  
            //���� parseRequest��request������  ����ϴ��ļ� FileItem �ļ���list ��ʵ�ֶ��ļ��ϴ���  
            List<FileItem> list = upload.parseRequest(new ServletRequestContext(request));  
            
            for(FileItem item:list){  
                //��ȡ���������֡�  
                String name = item.getFieldName();  
               
                //�����ȡ�ı���Ϣ����ͨ���ı���Ϣ����ͨ��ҳ�����ʽ���������ַ�����  
                if(item.isFormField()){  
                    //��ȡ�û�����������ַ�����  
                    String value = item.getString();  
                    request.setAttribute(name, value);  
                }  
                //���������ǷǼ��ַ���������ͼƬ����Ƶ����Ƶ�ȶ������ļ���  
                else{   
                    //��ȡ·����  
                    String value = item.getName();  
                    //ȡ�����һ����б�ܡ�  
                    int start = value.lastIndexOf("\\");  
                    //��ȡ�ϴ��ļ��� �ַ������֡�+1��ȥ����б�ܡ�  
                    String filename = value.substring(start+1);                    
                    request.setAttribute(name, filename);  
                      
                    /*�������ṩ�ķ���ֱ��д���ļ��С� 
                     * item.write(new File(path,filename));*/  
                    //�յ�д�����յ��ļ��С�  
                    OutputStream out = new FileOutputStream(new File(path,filename));  
                    InputStream in = item.getInputStream();  
                    
                    System.out.println("Picture Path�� "+path+"/"+filename);
                    
                    int length = 0;  
                    byte[] buf = new byte[1024];  
                    System.out.println("��ȡ�ļ�����������:"+ item.getSize());  
                      
                    while((length = in.read(buf))!=-1){  
                        out.write(buf,0,length);  
                    }  
                    in.close();  
                    out.close();  
                }  
            }  
            Pout.write(path);  //�����Ұѷ���˳ɹ��󣬷��ظ��ͻ��˵����ϴ��ɹ���·��
            
            
        }catch(Exception e){  
            e.printStackTrace();  
            e.printStackTrace();
            System.out.println("Upload file failure");
            Pout.write("Upload file failure");
        }  
          
        Pout.flush();
        Pout.close();
          
    }  
	
	

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
	

}
