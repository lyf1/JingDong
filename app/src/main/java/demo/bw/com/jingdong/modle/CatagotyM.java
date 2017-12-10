package demo.bw.com.jingdong.modle;



import demo.bw.com.jingdong.Api.CatagoryImm;
import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.bean.CatagoryBean;
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
 * Created by 李岳峰 on 2017/12/8.
 */

public class CatagotyM implements CatagoryImm {

    @Override
    public void onImm(final Observer<CatagoryBean> observa) {
        Observable.create(new ObservableOnSubscribe<CatagoryBean>() {
            @Override
            public void subscribe(ObservableEmitter<CatagoryBean> e) throws Exception {
                ServiceApi serviceApi=RetrofitHelper.getService();
                Call<CatagoryBean> cb = serviceApi.CatagoryData();
                cb.enqueue(new Callback<CatagoryBean>() {
                    @Override
                    public void onResponse(Call<CatagoryBean> call, Response<CatagoryBean> response) {
                         observa.onNext(response.body());
                         observa.onComplete();
                    }

                    @Override
                    public void onFailure(Call<CatagoryBean> call, Throwable t) {

                    }
                });
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observa);
    }
}
