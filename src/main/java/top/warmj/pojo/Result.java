package top.warmj.pojo;

import java.io.Serializable;
import java.util.List;

public class Result<T> implements Serializable {
    private static final int serialVersionUID = 1;

    public static final int SUCCESS = 0;

    public static final int FAIL = -1;

    public static final int NO_PERMESSION = 2;

    private String msg = "SUCCESS";

    private int code = SUCCESS;

    private T data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    private int count;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result() {
        super();
    }

    public Result(T data) {
        super();
        this.data = data;
        if (data instanceof List) {
            this.count = ((List) data).size();
        }
    }

    public Result(T data, int count) {
        super();
        this.data = data;
        this.count = count;
    }

    public Result(Throwable e) {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
}