package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.NewlistBean;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/12/18.
 */

public interface newlistOnImm {
    void OnImm(String page,Observer<NewlistBean> observer);
}
