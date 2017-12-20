package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.DefaultAddrApi;
import demo.bw.com.jingdong.bean.GetDefaultBean;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.modle.getDefaultAddrModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/12/16.
 */

public class getDefaultAddrPresenter {
    private DefaultAddrApi da;
    private getDefaultAddrModel gm;

    public getDefaultAddrPresenter(DefaultAddrApi da) {
        this.da = da;
        gm=new getDefaultAddrModel();
    }
    public  void getDefaultAddr(){
        gm.OnImm(new Observer<GetDefaultBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(GetDefaultBean getDefaultBean) {
                 da.getDefaultAddr(getDefaultBean);
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
