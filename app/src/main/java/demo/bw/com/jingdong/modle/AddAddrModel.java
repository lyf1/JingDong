package demo.bw.com.jingdong.modle;

import java.util.HashMap;
import java.util.Map;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.addAddrOnImm;
import demo.bw.com.jingdong.bean.BaseBean;
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

public class AddAddrModel implements addAddrOnImm {
    @Override
    public void OnImm(final String addr, final String mobile, final String name, final Observer<BaseBean> observer) {

        Observable.create(new ObservableOnSubscribe<BaseBean>() {
            @Override
            public void subscribe(ObservableEmitter<BaseBean> e) throws Exception {
                Map<String,String> parmas=new HashMap<>();
                parmas.put("uid","100");
                parmas.put("addr",addr);
                parmas.put("name",name);
                parmas.put("mobile",mobile);
                ServiceApi si= RetrofitHelper.getService();
                si.addAddr(parmas).enqueue(new Callback<BaseBean>() {
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
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observer);


    }
}
