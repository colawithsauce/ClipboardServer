package ActionHandler;

import cn.colawithsauce.ClipBoard;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.juli.logging.Log;

import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * User write string into the clipboard!
 */
public class WriteActionHandler implements ActionHandler{
  private String contents;
  private final ClipBoard cb = ClipBoard.getInstance();
  private PrintWriter pw = null;

  @Override
  public void execute() {
    Respond respond = new Respond(200, "Success!", contents);
    Logger logger = Logger.getGlobal();
    try {
      cb.setClipboardContents(contents);
      pw.write(JSON.toJSONString(respond));

      System.out.println("Write from client!");
      System.out.println(JSON.toJSONString(respond));
    } catch (Exception e) {
      respond.setCode(202);
      respond.setMessage(e.getMessage());
      pw.write(JSON.toJSONString(respond));

      System.err.println("Write [ERROR]!");
      System.err.println(JSON.toJSONString(respond));
    }
  }

  /**
   * @param pw After execute the function, print success message (json) into it.
   * @param json The input, this handler would get context from this json object.
   */
  public WriteActionHandler(PrintWriter pw, JSONObject json) {
    this.contents = json.getString("contents");
    this.pw = pw;
  }

  private WriteActionHandler(){}
}
