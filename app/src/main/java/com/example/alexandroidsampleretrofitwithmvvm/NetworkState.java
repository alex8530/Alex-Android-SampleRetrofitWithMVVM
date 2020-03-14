
package com.example.alexandroidsampleretrofitwithmvvm;


public class NetworkState {
    private final Status status;
    private final String msg;

    public static final NetworkState LOADED;
    public static final NetworkState LOADING;
    public static final NetworkState FALIED;

    public NetworkState(Status status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    static {
        LOADED=new NetworkState(Status.SUCCESS,"Success");
        LOADING=new NetworkState(Status.RUNNING,"Running");
        FALIED=new NetworkState(Status.FAILED,"Failed");
    }

    public Status getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }



}
