package com.example.demo.common;

//统一返回json模型
public class JsonResponse {

    private int status = 0; //状态 -1失败,0成功

    private String msg; // 消息

    private Object data;//数据

    private long total; //总条数

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


}
