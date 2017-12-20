package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.AddAddrActivityApi;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.modle.AddAddrModel;
import demo.bw.com.jingdong.modle.getAddrsModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/12/15.
 */

public class getAddrsPresenter {
    private AddAddrActivityApi ai;
    private final getAddrsModel gm;


    public getAddrsPresenter(AddAddrActivityApi ai) {
        this.ai = ai;
        gm = new getAddrsModel();
    }
    public  void getAddrs(){
      gm.OnImm(new Observer<getAddrsBean>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(getAddrsBean getAddrsBean) {
              ai.showData(getAddrsBean.getData());
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
