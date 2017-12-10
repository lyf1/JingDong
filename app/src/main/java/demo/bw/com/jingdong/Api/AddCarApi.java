package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.BaseBean;

/**
 * Created by 李岳峰 on 2017/12/5.
 */

public interface AddCarApi {
    public void onAddCart(String pid, OnListenerApi<BaseBean> onListenerApi);
}
