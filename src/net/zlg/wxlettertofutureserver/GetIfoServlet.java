package net.zlg.wxlettertofutureserver;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(description = "������ȡ�û�������Ϣ��Servlet�������", urlPatterns = { "/GetIfoServlet" })
@SuppressWarnings("serial")
public class GetIfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetIfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//�������һ���ǹ̶�
        response.setContentType("text/html;charset=utf-8");          
        // ������Ӧͷ����ajax�������  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // �Ǻű�ʾ���е��������󶼿��Խ���  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  

        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        String Ustr = new String(request.getParameter("message").getBytes("ISO-8859-1"), "UTF-8");
        //��ȡ΢��С����get�Ĳ���ֵ����ӡ
        System.out.println("message="+Ustr);


        //����ֵ��΢��С����
        Writer out = response.getWriter(); 
        out.write("hello world"+"message="+Ustr);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
	}

}
