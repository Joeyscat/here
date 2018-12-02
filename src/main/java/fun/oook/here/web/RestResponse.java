package fun.oook.here.web;

/**
 * @author Joey
 * @version 1.0
 * @since 2018/10/23 11:47
 */
public class RestResponse<T> {

    //返回信息
    private String resultMsg;
    //返回代码
    private String resultCode;

    private T data;

    public RestResponse() {
    }

    public RestResponse(String resultCode, String resultMsg) {
        this.resultMsg = resultMsg;
        this.resultCode = resultCode;
    }

    public RestResponse<T> normalRestResponse() {
        this.setResultCode("1");
        this.setResultMsg("成功");
        return this;
    }

    public RestResponse<T> normalRestResponse(T data) {
        this.setResultCode("1");
        this.setResultMsg("成功");
        this.setData(data);
        return this;
    }

    public RestResponse<T> excetpionRestResponse() {
        this.setResultCode("E0001");
        this.setResultMsg("失败");
        return this;
    }

    public RestResponse<T> excetpionRestResponse(T data) {
        this.setResultCode("E0001");
        this.setResultMsg("失败");
        this.setData(data);
        return this;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
