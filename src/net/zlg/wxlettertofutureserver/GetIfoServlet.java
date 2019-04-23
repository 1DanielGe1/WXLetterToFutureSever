package net.zlg.wxlettertofutureserver;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(description = "用来获取用户基本信息的Servlet处理代码", urlPatterns = { "/GetIfoServlet" })
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
		
		//这里代码一般是固定
        response.setContentType("text/html;charset=utf-8");          
        // 设置响应头允许ajax跨域访问  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // 星号表示所有的异域请求都可以接受  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  

        //获取微信小程序get的参数值并打印
        String Ustr = new String(request.getParameter("message").getBytes("ISO-8859-1"), "UTF-8");
        //获取微信小程序get的参数值并打印
        System.out.println("message="+Ustr);


        //返回值给微信小程序
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
