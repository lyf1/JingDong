package demo.bw.com.jingdong.presenter;

import android.content.DialogInterface;

import demo.bw.com.jingdong.Api.AddAddrActivityApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.modle.AddAddrModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/12/15.
 */

public class AddAddrPresenter {
    private AddAddrActivityApi ai;
    private final AddAddrModel am;

    public AddAddrPresenter(AddAddrActivityApi ai) {
        this.ai = ai;
        am = new AddAddrModel();
    }
    public void getAddAddr( String addr, String mobile, String name){
        am.OnImm(addr, mobile, name, new Observer<BaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
              ai.addData(baseBean.getMsg());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
