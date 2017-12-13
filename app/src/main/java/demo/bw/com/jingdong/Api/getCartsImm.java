package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.CartBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/10.
 */

public interface getCartsImm {
     void onImm(Observer<CartBean> observer);
}
