package demo.bw.com.jingdong.presenter;

import android.content.DialogInterface;

import demo.bw.com.jingdong.Api.ShopFragmentApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.modle.DeleteCartModle;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/12.
 */

public class DeleteCartPresenter {
    private ShopFragmentApi si;
    private final DeleteCartModle dc;

    public DeleteCartPresenter(ShopFragmentApi  si) {
        this.si = si;
        dc = new DeleteCartModle();
    }
    public void getDelete(String pid){
        dc.OnImm(pid, new Observer<BaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(BaseBean baseBean) {
                  si.showData(baseBean.getMsg());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    };
}
