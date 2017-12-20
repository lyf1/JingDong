package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.getAddrsOnImm;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.utils.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2017/12/15.
 */

public class getAddrsModel implements getAddrsOnImm {
    @Override
    public void OnImm(final Observer<getAddrsBean> observer) {
        Observable.create(new ObservableOnSubscribe<getAddrsBean>() {
            @Override
            public void subscribe(ObservableEmitter<getAddrsBean> e) throws Exception {
                ServiceApi si= RetrofitHelper.getService();
                 si.getAdds("100").enqueue(new Callback<getAddrsBean>() {
                     @Override
                     public void onResponse(Call<getAddrsBean> call, Response<getAddrsBean> response) {
                         observer.onNext(response.body());
                         observer.onComplete();
                     }

                     @Override
                     public void onFailure(Call<getAddrsBean> call, Throwable t) {

                     }
                 });
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
