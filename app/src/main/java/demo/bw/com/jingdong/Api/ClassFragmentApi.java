package demo.bw.com.jingdong.Api;

import java.util.List;

import demo.bw.com.jingdong.bean.CatagoryBean;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public interface ClassFragmentApi {
    public void  ctagoryData(List<CatagoryBean.DataBean> list);
    public void  productCatagoryData(List<ProductCatagoryBean.DataBean> grouplist,List<List<ProductCatagoryBean.DataBean.ListBean>> childlist);
}
