package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.fristBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/6.
 */

public interface FristModleApi {
    public void onFrrstData(Observer<fristBean> observer);
}

