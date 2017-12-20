package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.GetDefaultBean;
import demo.bw.com.jingdong.bean.getAddrsBean;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/12/16.
 */

public interface getDefaultAddrOnImm {
    void OnImm(Observer<GetDefaultBean> observer);
}
