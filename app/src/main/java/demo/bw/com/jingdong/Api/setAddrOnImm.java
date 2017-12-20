package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.BaseBean;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/12/16.
 */

public interface setAddrOnImm  {
    void OnImm(String addrid, Observer<BaseBean> observer);
}
