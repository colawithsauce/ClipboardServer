package ActionHandler;

import cn.colawithsauce.ClipBoard;
import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;

public class ReadActionHandler implements ActionHandler{
  private final ClipBoard cb = ClipBoard.getInstance();
  private PrintWriter pw;

  @Override
  public void execute() {
    Respond respond = new Respond(200, "Success!", cb.getClipboardContents());
    try {
      pw.write(JSON.toJSONString(respond));
      System.out.println("Read from client!");
      System.out.println(JSON.toJSONString(respond));
    } catch (Exception e) {
      respond.setCode(202);
      respond.setMessage(e.getMessage());
      pw.write(JSON.toJSONString(respond));
    }
  }

  public ReadActionHandler(PrintWriter pw) {
    this.pw = pw;
  }

  private ReadActionHandler() {}
}
