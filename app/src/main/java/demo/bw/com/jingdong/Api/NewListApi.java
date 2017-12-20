package demo.bw.com.jingdong.Api;

import java.util.List;

import demo.bw.com.jingdong.bean.NewlistBean;

/**
 * Created by lenovo on 2017/12/18.
 */

public interface NewListApi {
    void ShowData(List<NewlistBean.GoodsListBean> list);
}
