package demo.bw.com.jingdong.Api;



import demo.bw.com.jingdong.bean.BaseBean;
import io.reactivex.Observer;

/**
 * Created by lenovo on 2017/12/15.
 */

public interface addAddrOnImm {
    void OnImm( String addr, String mobile, String name, Observer<BaseBean> observer);
}
