package demo.bw.com.jingdong.modle;

import java.util.List;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.childActivityApi;
import demo.bw.com.jingdong.Api.productsImm;
import demo.bw.com.jingdong.bean.c_childBean;
import demo.bw.com.jingdong.utils.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李岳峰 on 2017/12/9.
 */

public class ProductsModle implements productsImm {


    @Override
    public void OnImm(final String pscid, final String page, final Observer<c_childBean> observer) {
        Observable.create(new ObservableOnSubscribe<c_childBean>() {
            @Override
            public void subscribe(ObservableEmitter<c_childBean> e) throws Exception {
                ServiceApi si= RetrofitHelper.getService();
                Call<c_childBean> products = si.getProducts(pscid, page);
                products.enqueue(new Callback<c_childBean>() {
                    @Override
                    public void onResponse(Call<c_childBean> call, Response<c_childBean> response) {
                        observer.onNext(response.body());
                        observer.onComplete();
                    }

                    @Override
                    public void onFailure(Call<c_childBean> call, Throwable t) {

                    }
                });
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
