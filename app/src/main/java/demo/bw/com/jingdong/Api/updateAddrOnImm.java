package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.BaseBean;
import io.reactivex.Observable;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/12/16.
 */

public interface updateAddrOnImm {
    void OnImm(String addrid,String mobile,String name,String addr,Observer<BaseBean> observer);
}
