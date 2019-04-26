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



//@WebServlet(description = "������ȡ�û�������Ϣ��Servlet�������", urlPatterns = { "/GetIfoServlet" })
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

          //����΢�ŷ���������ȡ��Ӧ��OpenID��sessionID
      //���÷���΢�ŷ��������߷�������������������ȡ����openid��session_key��json�ַ���
      String jsonId=connect(code);
      JSONObject jsonObject = JSONObject.fromObject(jsonId); 
      //��json�ַ�����ȡopenid��session_key
      String openid=jsonObject.getString("openid");
      String session_key=jsonObject.getString("session_key");
      
      System.out.println(openid+"8888"+session_key);


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
	
	private String connect(String userCode){
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+

        		"&secret="+SECRET+"&js_code="+ userCode +"&grant_type=authorization_code";
        

        BufferedReader in = null;
    	        try {  
    				URL weChatUrl = new URL(url);  
    	            // �򿪺�URL֮�������  
    	            URLConnection connection = weChatUrl.openConnection();  
    	            // ����ͨ�õ���������  
    	            connection.setConnectTimeout(5000);  
    	            connection.setReadTimeout(5000);  
    	            // ����ʵ�ʵ�����  
    	            connection.connect();  
    	            // ���� BufferedReader����������ȡURL����Ӧ  
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
    	        // ʹ��finally�����ر�������  
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
