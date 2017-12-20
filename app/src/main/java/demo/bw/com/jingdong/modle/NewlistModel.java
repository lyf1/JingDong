package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.newlistOnImm;
import demo.bw.com.jingdong.bean.NewlistBean;
import demo.bw.com.jingdong.utils.PRetrofitHelper;
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
 * Created by lenovo on 2017/12/18.
 */

public class NewlistModel implements newlistOnImm {

    @Override
    public void OnImm(final String page, final Observer<NewlistBean> observer) {
        Observable.create(new ObservableOnSubscribe<NewlistBean>() {
            @Override
            public void subscribe(ObservableEmitter<NewlistBean> e) throws Exception {
                ServiceApi si= PRetrofitHelper.getService();
                si.newlist(page).enqueue(new Callback<NewlistBean>() {
                    @Override
                    public void onResponse(Call<NewlistBean> call, Response<NewlistBean> response) {
                        observer.onNext(response.body());
                        observer.onComplete();
                    }

                    @Override
                    public void onFailure(Call<NewlistBean> call, Throwable t) {

                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }
}
