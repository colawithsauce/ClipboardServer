package ActionHandler;

import com.alibaba.fastjson.annotation.JSONField;

public class Respond {
  @JSONField(name = "code")
  private int code;
  @JSONField(name = "message")
  private String message;
  @JSONField(name = "content")
  private String content;

  public Respond(int code, String message, String content){
    this.code = code;
    this.content = content;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
