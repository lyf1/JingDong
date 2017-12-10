package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.SearchBean;

/**
 * Created by 李岳峰 on 2017/12/4.
 */

public interface SearchmodleApi {
   public void onSerarch(String keywords, String page, OnListenerApi<SearchBean> onListenerApi);
}
