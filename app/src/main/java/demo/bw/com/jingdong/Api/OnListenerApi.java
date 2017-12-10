package demo.bw.com.jingdong.Api;

/**
 * Created by 李岳峰 on 2017/12/4.
 */

public interface OnListenerApi<T> {
    public void onError(Exception e);
    public void onSuccess(T t);
}
