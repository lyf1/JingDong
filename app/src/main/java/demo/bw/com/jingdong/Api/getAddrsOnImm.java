package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.getAddrsBean;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/12/15.
 */

public interface getAddrsOnImm {
    void OnImm(Observer<getAddrsBean> observer);
}
