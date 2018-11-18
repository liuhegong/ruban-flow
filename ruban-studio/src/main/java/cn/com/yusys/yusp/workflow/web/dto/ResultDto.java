package cn.com.yusys.yusp.workflow.web.dto;

import com.github.pagehelper.Page;

public class ResultDto<T> {
    private int code;
    private long total;
    private String message;
    private T data;

    public ResultDto() {
        super();
    }

    public ResultDto(T data) {
        super();
        if (data instanceof Page) {
            Page<?> page = (Page<?>) data;
            this.total = page.getTotal();
        }

        this.data = data;
    }

    public ResultDto(long total, T data) {
        super();
        this.total = total;
        this.data = data;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

}
