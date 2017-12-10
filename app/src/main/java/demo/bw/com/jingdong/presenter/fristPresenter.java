package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.fristFragmentApi;
import demo.bw.com.jingdong.bean.fristBean;
import demo.bw.com.jingdong.modle.FristModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/7.
 */

public class fristPresenter {
    private fristFragmentApi fa;
    private final FristModel fm;

    public fristPresenter(fristFragmentApi fa) {
        this.fa = fa;
        fm = new FristModel();
    }
    public void getFristP(){
        fm.onFrrstData(new Observer<fristBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(fristBean fristBean) {
               fa.ShowData(fristBean);
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
