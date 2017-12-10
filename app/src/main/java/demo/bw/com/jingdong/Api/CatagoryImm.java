package demo.bw.com.jingdong.Api;



import demo.bw.com.jingdong.bean.CatagoryBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public interface CatagoryImm {
    public void onImm(Observer<CatagoryBean> observa);
}
