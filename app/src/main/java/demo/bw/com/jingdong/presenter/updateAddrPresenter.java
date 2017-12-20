package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.AddAddrActivityApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.modle.updateAddrModle;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/12/16.
 */

public class updateAddrPresenter {
    private AddAddrActivityApi sa;
    private updateAddrModle um;

    public updateAddrPresenter(AddAddrActivityApi sa) {
          this.sa=sa;
        um=new updateAddrModle();
    }
    public void getUpDateAddr( String addrid, String mobile, String name,  String addr){
         um.OnImm(addrid, mobile, name, addr, new Observer<BaseBean>() {
             @Override
             public void onSubscribe(Disposable d) {

             }

             @Override
             public void onNext(BaseBean baseBean) {
               sa.updateAddr(baseBean.getMsg());
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
