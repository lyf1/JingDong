package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.c_childBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/9.
 */

public interface productsImm {
    void OnImm(String pscid, String page, Observer<c_childBean> observer);
}
