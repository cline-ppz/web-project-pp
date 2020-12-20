package controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;

import domain.User;
import jdbcUtils.JDBCUtils;


@WebServlet("/registerController")
public class registerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RegisterController");
        /**
         * 判断输入的验证码是否正确
         * 接收所有参数
         * 把参数封装成user对象
         * 设置uid
         * 写入到数据库
         * */
        //解决乱码问题
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //获取参数   验证码
        String code = request.getParameter("code");
        System.out.println("code="+code);
        //获取服务器生成的验证码
        String word = (String) this.getServletContext().getAttribute("checkCode");
        //判断输入的验证
        if (code.equals(word)) {
            //如果正确
            //1.接收所有参数
            Map<String, String[]> parameterMap = request.getParameterMap();

            for(Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                System.out.println("----------");
                System.out.println(entry.getKey()+":"+Arrays.toString(entry.getValue()));
            }

            User u = new User();
            //2.把接收的参数封装成user对象
            try {
                BeanUtils.populate(u, parameterMap);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            System.out.println(u);

            //3.设置uid
            u.setPhone(UUID.randomUUID().toString());
            //4.写入数据库
            QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
            String sql="insert into user value(?,?,?,?)";
            try {
                qr.update(sql,u.getPhone(),u.getPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //跳转到登录页面
            response.getWriter().write("注册成功");
            response.setHeader("refresh", "3,url=/web/index.jsp");
        } else {
            //不正确，用户验证码错误，跳转回注册页
            response.getWriter().write("验证码错误");
            response.setHeader("refresh", "3;url=/web/register.jsp");
        }
    }

}

