package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.getCartsImm;
import demo.bw.com.jingdong.bean.CartBean;
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
 * Created by 李岳峰 on 2017/12/10.
 */

public class GetCartsModle implements getCartsImm {
    @Override
    public void onImm(final Observer<CartBean> observer) {
        Observable.create(new ObservableOnSubscribe<CartBean>() {

            @Override
            public void subscribe(ObservableEmitter<CartBean> e) throws Exception {
                ServiceApi si=RetrofitHelper.getService();
                Call<CartBean> carts = si.getCarts();
                carts.enqueue(new Callback<CartBean>() {
                    @Override
                    public void onResponse(Call<CartBean> call, Response<CartBean> response) {
                        observer.onComplete();
                        observer.onNext(response.body());
                    }

                    @Override
                    public void onFailure(Call<CartBean> call, Throwable t) {

                    }
                });
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
