package demo.bw.com.jingdong.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.bw.com.jingdong.Api.ClassFragmentApi;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;
import demo.bw.com.jingdong.modle.ProductCatagoryM;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public class ProductCatagoryP {
    private ClassFragmentApi cfi;
    private final ProductCatagoryM pcm;

    public ProductCatagoryP(ClassFragmentApi cfi) {
        this.cfi = cfi;
        pcm = new ProductCatagoryM();
    }
    public void getProduct(String cid){
        pcm.onImm(cid, new Observer<ProductCatagoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ProductCatagoryBean productCatagoryBean) {
                List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
                List<List<ProductCatagoryBean.DataBean.ListBean>> childlist=new ArrayList<>();
                for(int i=0;i<data.size();i++){
                    ProductCatagoryBean.DataBean dataBean = data.get(i);
                     childlist.add(dataBean.getList());
                }
                cfi.productCatagoryData(data,childlist);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
