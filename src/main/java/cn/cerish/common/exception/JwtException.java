package cn.cerish.common.exception;

public class JwtException extends RuntimeException {
    private Integer code;
    private String msg;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public JwtException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
