package demo.bw.com.jingdong.Api;

import demo.bw.com.jingdong.bean.ProductCatagoryBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public interface ProductCatagoryImm {
    public void onImm(String cid,Observer<ProductCatagoryBean> observer);
}
