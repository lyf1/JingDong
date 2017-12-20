package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.AddAddrActivityApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.modle.SetAddrModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/12/16.
 */

public class SetAddrPresenter {
    private AddAddrActivityApi sa;
    private SetAddrModel sm;

    public SetAddrPresenter(AddAddrActivityApi sa) {
        this.sa = sa;
        sm=new SetAddrModel();
    }
    public void getSetAddr(String addrid){
        sm.OnImm(addrid, new Observer<BaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
              sa.setAddr(baseBean.getMsg());
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
