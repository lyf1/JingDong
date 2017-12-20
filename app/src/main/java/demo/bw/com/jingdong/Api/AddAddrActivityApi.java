package demo.bw.com.jingdong.Api;

import java.util.List;

import demo.bw.com.jingdong.bean.getAddrsBean;

/**
 * Created by lenovo on 2017/12/15.
 */

public interface AddAddrActivityApi {
    void showData(List<getAddrsBean.DataBean> list);
    void addData(String str);
    void setAddr(String str);
    void updateAddr(String str);

}
