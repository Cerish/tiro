package cn.cerish.entity;

public class RespBean<T> {
    private Integer code;
    private T data;
    private String msg;

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean success(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean success(String msg, Object data) {
        return new RespBean(200,data, msg );
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }
    public static RespBean error(Integer code, String msg) {
        return new RespBean(500, null, msg);
    }
    public static RespBean error(String msg, Object data) {
        return new RespBean(500, data, msg);
    }
    public static RespBean error(Integer code, String msg, Object data) {
        return new RespBean(code, data, msg);
    }


    public Integer getCode() {
        return code;
    }

    public RespBean setCode(Integer code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public RespBean setData(T data) {
        this.data = data;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public RespBean() {
    }

    public RespBean(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespBean(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "RespBean{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
