package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.registImm;
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
 * Created by 李岳峰 on 2017/12/13.
 */

public class RegistModle implements registImm {
    @Override
    public void onImm(final String mob, final String pwd, final Observer<BaseBean> observer) {
        Observable.create(new ObservableOnSubscribe<BaseBean>() {
            @Override
            public void subscribe(ObservableEmitter<BaseBean> e) throws Exception {
                ServiceApi si= RetrofitHelper.getService();
                Call<BaseBean> login = si.regist(mob, pwd);
                login.enqueue(new Callback<BaseBean>() {
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
