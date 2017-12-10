package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.ClassFragmentApi;
import demo.bw.com.jingdong.bean.CatagoryBean;
import demo.bw.com.jingdong.modle.CatagotyM;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public class CotagortyP {
   private ClassFragmentApi ca;
    private final CatagotyM cm;

    public CotagortyP(ClassFragmentApi ca) {
        this.ca = ca;
        cm = new CatagotyM();
    }
    public void getCotagory(){
        cm.onImm(new Observer<CatagoryBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CatagoryBean catagoryBean) {
                  ca.ctagoryData(catagoryBean.getData());
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
