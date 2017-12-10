package demo.bw.com.jingdong.Api;

import java.util.List;

import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.bean.SearchDetailBean;

/**
 * Created by 李岳峰 on 2017/12/5.
 */

public interface SearchDetailActivityApi {
    public void showData(SearchDetailBean.DataBean list);
    public void btnData(String str);
}
