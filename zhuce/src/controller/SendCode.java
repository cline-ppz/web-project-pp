package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//发送验证码接口
@WebServlet("/UserServlet")
public class SendCode extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 初始化ZhenziSmsClient
        ZhenziSmsClient client = new ZhenziSmsClient("https://sms_developer.zhenzikj.com","107508","173f9a59-93c6-4bbf-9f05-617a24a3db66");
        // 添加短信的参数到map中
        Map<String, String> params = new HashMap<>();
        // 生成6位随机验证码
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        // 短信内容体
        params.put("message", "亲爱的用户，您的短信验证码为" + verifyCode + "，,2分钟内有效，若非本人操作请忽略。");
        // 接收短信的手机号码
        params.put("number", phoneNumber);
        // 发送短信
        String result = client.send(params);

        PrintWriter out = resp.getWriter();
        out.println(verifyCode);
        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
