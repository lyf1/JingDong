package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.BaseBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/12.
 */

public interface DeleteCartImm {
    void OnImm(String pid, Observer<BaseBean> observer);
}
