package net.zlg.wxlettertofutureserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.sf.json.JSONObject;



//@WebServlet(description = "用来获取用户基本信息的Servlet处理代码", urlPatterns = { "/GetIfoServlet" })
@SuppressWarnings("serial")
public class GetIfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String APPID = "wxa4c96fedd6265d97";

	private static final String SECRET = "7bd8bcc42fa08f17795a73d2421b693b";

	private String code;
       
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

          //连接微信服务器并获取相应的OpenID和sessionID
      //调用访问微信服务器工具方法，传入三个参数获取带有openid、session_key的json字符串
      String jsonId=connect(code);
      JSONObject jsonObject = JSONObject.fromObject(jsonId); 
      //从json字符串获取openid和session_key
      String openid=jsonObject.getString("openid");
      String session_key=jsonObject.getString("session_key");
      
      System.out.println(openid+"8888"+session_key);


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
	
	private String connect(String userCode){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+

        		"&secret="+SECRET+"&js_code="+ userCode +"&grant_type=authorization_code";
        

        BufferedReader in = null;
    	        try {  
    				URL weChatUrl = new URL(url);  
    	            // 打开和URL之间的连接  
    	            URLConnection connection = weChatUrl.openConnection();  
    	            // 设置通用的请求属性  
    	            connection.setConnectTimeout(5000);  
    	            connection.setReadTimeout(5000);  
    	            // 建立实际的连接  
    	            connection.connect();  
    	            // 定义 BufferedReader输入流来读取URL的响应  
    	            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));  
    	            StringBuffer sb = new StringBuffer();  
    	            String line;  
    	            while ((line = in.readLine()) != null) {  
    	                sb.append(line);  
    	            }  
    	            return sb.toString();  
    	        } catch (Exception e1) {  
    	        	throw new RuntimeException(e1);
    	        }  
    	        // 使用finally块来关闭输入流  
    	        finally {  
    	            try {  
    	                if (in != null) {  
    	                    in.close();  
    	                }  
    	            } catch (Exception e2) {  
    	                e2.printStackTrace();  
    	            }  
    	        }  
	}

}
