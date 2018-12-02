package fun.oook.here.common;

/**
 * @author Joey
 * @date 2018-12-02
 * @since 1.0
 */
public class CommonException extends RuntimeException{

    private String errCode;

    public CommonException(String errCode,String message) {
        super(message);
        this.errCode=errCode;

    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

}
