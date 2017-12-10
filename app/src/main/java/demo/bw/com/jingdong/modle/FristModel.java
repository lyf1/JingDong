package demo.bw.com.jingdong.modle;



import demo.bw.com.jingdong.Api.FristModleApi;
import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.bean.fristBean;
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
 * Created by 李岳峰 on 2017/12/7.
 */

public class FristModel implements FristModleApi {
    @Override
    public void onFrrstData(final Observer<fristBean> observer) {
        Observable.create(new ObservableOnSubscribe<fristBean>() {
            @Override
            public void subscribe(ObservableEmitter<fristBean> e) throws Exception {
                ServiceApi serviceApi=RetrofitHelper.getService();
                Call<fristBean> fC= serviceApi.fristData();
                fC.enqueue(new Callback<fristBean>() {
                    @Override
                    public void onResponse(Call<fristBean> call, Response<fristBean> response) {
                        observer.onNext(response.body());
                        observer.onComplete();
                    }

                    @Override
                    public void onFailure(Call<fristBean> call, Throwable t) {

                    }
                });
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
