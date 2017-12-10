package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.childActivityApi;
import demo.bw.com.jingdong.bean.c_childBean;
import demo.bw.com.jingdong.modle.ProductsModle;
import demo.bw.com.jingdong.view.activity.C_childActivity;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/9.
 */

public class ProductsPresenter {
   private childActivityApi ca;
    private ProductsModle pm;

    public ProductsPresenter(childActivityApi ca) {
        this.ca = ca;
        pm=new ProductsModle();
    }
    public void getProducts(String pscid,String page){
        pm.OnImm(pscid, page, new Observer<c_childBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(c_childBean c_childBean) {
                  ca.onProducts(c_childBean.getData());
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
