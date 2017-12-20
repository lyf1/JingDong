package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.setAddrOnImm;
import demo.bw.com.jingdong.bean.BaseBean;
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
 * Created by lenovo on 2017/12/16.
 */

public class SetAddrModel implements setAddrOnImm {

    @Override
    public void OnImm(final String addrid, final Observer<BaseBean> observer) {
        Observable.create(new ObservableOnSubscribe<BaseBean>() {
            @Override
            public void subscribe(ObservableEmitter<BaseBean> e) throws Exception {
                ServiceApi si= RetrofitHelper.getService();
                si.setAddr("100",addrid,"1").enqueue(new Callback<BaseBean>() {
                    @Override
                    public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                         observer.onNext(response.body());
                         observer.onComplete();
                    }

                    @Override
                    public void onFailure(Call<BaseBean> call, Throwable t) {

                    }
                });
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
