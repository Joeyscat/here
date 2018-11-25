package fun.oook.here.processor;

import java.io.Serializable;

/**
 * @author Joey
 * @date 2018-11-25
 * @since 1.0
 */
public class ResponseEntity implements Serializable {

    private boolean success;
    private String msg;
    private String data;
    private String reqUri;

    public ResponseEntity() {
    }

    public ResponseEntity(boolean success, String msg, String data, String reqUri) {
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.reqUri = reqUri;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", reqUri='" + reqUri + '\'' +
                '}';
    }
}