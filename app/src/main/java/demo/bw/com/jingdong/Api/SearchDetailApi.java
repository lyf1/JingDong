package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.SearchDetailBean;

/**
 * Created by 李岳峰 on 2017/12/5.
 */

public interface  SearchDetailApi {
    public void onSearchDetail(String pid, OnListenerApi<SearchDetailBean> onListenerApi);
}
