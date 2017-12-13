package demo.bw.com.jingdong.Api;

import java.util.List;

import demo.bw.com.jingdong.bean.CartBean;

/**
 * Created by 李岳峰 on 2017/12/10.
 */

public interface ShopFragmentApi {
    void getShop(List<CartBean.DataBean> grouplist,List<List<CartBean.DataBean.ListBean>> childlist);
     void showData(String str);
}
