package cn.colawithsauce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/")
public class clipboardServer extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws IOException {
    // 设置响应类型:
    resp.setContentType("text/html");

    String name = req.getParameter("name");
    if (name == null) {
      name = "world";
    }

    // 获取输出流:
    PrintWriter pw = resp.getWriter();
    // 写入响应:
    pw.format("<h1>Hello, %s! </h1>" + name);
    // 最后不要忘记flush强制输出:
    pw.flush();
  }
}
