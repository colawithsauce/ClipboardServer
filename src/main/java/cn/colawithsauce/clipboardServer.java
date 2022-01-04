package cn.colawithsauce;

import ActionHandler.*;
import ActionHandler.WriteActionHandler;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(urlPatterns = "/")
public class clipboardServer extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    PrintWriter pw  = new PrintWriter(resp.getWriter());
    ActionHandler handler = new ReadActionHandler(pw);
    handler.execute();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // 如果用户的申请类型不是JSON则直接返回错误
    if (!req.getContentType().equals("application/json")) {
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Must be json");
      return;
    }

    // Log 一下看看输入
    System.out.println("Method: " + req.getMethod());

    // 设置响应类型
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");

    // 获取输出流
    PrintWriter pw = new PrintWriter(resp.getWriter());

    // 获取输入的JSON
    BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8));
    String line;
    StringBuilder stringBuilder = new StringBuilder();

    while ((line = br.readLine())!=null) {
      stringBuilder.append(line);
    }

    JSONObject json = JSONObject.parseObject(stringBuilder.toString());

    // 根据用户请求进行回应
    String option;
    if ((option = json.getString("action")) == null) {
      // 如果用户输入有误
      resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request format error!");
    } else {
      ActionHandler handler;
      switch (option) {
        case "read":
          handler = new ReadActionHandler(pw);
          break;
        case "write":
          handler = new WriteActionHandler(pw, json);
          break;
        default:
          resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "option should be \"read\" or \"write\"!");
          return;
      }
       handler.execute();
    }
  }
}
