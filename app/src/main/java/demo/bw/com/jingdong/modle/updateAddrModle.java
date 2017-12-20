package demo.bw.com.jingdong.modle;

import java.util.HashMap;
import java.util.Map;

import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.Api.updateAddrOnImm;
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

public class updateAddrModle implements updateAddrOnImm {

    @Override
    public void OnImm(final String addrid, final String mobile, final String name, final String addr, final Observer<BaseBean> observer) {

        Observable.create(new ObservableOnSubscribe<BaseBean>() {
            @Override
            public void subscribe(ObservableEmitter<BaseBean> e) throws Exception {
                Map<String,String> pamas=new HashMap<>();
                pamas.put("uid","100");
                pamas.put("addrid",addrid);
                pamas.put("mobile",mobile);
                pamas.put("name",name);
                pamas.put("addr",addr);
                ServiceApi si= RetrofitHelper.getService();
                si.updateAddr(pamas).enqueue(new Callback<BaseBean>() {
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
