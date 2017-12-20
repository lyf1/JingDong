package demo.bw.com.jingdong.modle;



import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.getDefaultAddrOnImm;
import demo.bw.com.jingdong.bean.GetDefaultBean;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.utils.RetrofitHelper;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lenovo on 2017/12/16.
 */

public class getDefaultAddrModel implements getDefaultAddrOnImm {
    @Override
    public void OnImm(final Observer<GetDefaultBean> observer) {
        Observable.create(new ObservableOnSubscribe<GetDefaultBean>() {
            @Override
            public void subscribe(ObservableEmitter<GetDefaultBean> e) throws Exception {
                ServiceApi si= RetrofitHelper.getService();
                si.getDefaultAddr("100").enqueue(new Callback<GetDefaultBean>() {
                    @Override
                    public void onResponse(Call<GetDefaultBean> call, Response<GetDefaultBean> response) {
                         observer.onNext(response.body());
                         observer.onComplete();
                    }

                    @Override
                    public void onFailure(Call<GetDefaultBean> call, Throwable t) {

                    }
                });

            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);
    }
}
