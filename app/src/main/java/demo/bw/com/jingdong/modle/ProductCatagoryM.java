package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.ProductCatagoryImm;
import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;
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
 * Created by 李岳峰 on 2017/12/8.
 */

public class ProductCatagoryM implements ProductCatagoryImm {
    @Override
    public void onImm(final String cid, final Observer<ProductCatagoryBean> observer) {
        Observable.create(new ObservableOnSubscribe<ProductCatagoryBean>() {
            @Override
            public void subscribe(ObservableEmitter<ProductCatagoryBean> e) throws Exception {
                ServiceApi sa=RetrofitHelper.getService();
                Call<ProductCatagoryBean> pC = sa.getProductCatagory(cid);
                 pC.enqueue(new Callback<ProductCatagoryBean>() {
                     @Override
                     public void onResponse(Call<ProductCatagoryBean> call, Response<ProductCatagoryBean> response) {
                         observer.onNext(response.body());
                         observer.onComplete();
                     }

                     @Override
                     public void onFailure(Call<ProductCatagoryBean> call, Throwable t) {

                     }
                 });
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
