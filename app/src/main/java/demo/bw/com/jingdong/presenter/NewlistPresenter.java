package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.NewListApi;
import demo.bw.com.jingdong.bean.NewlistBean;
import demo.bw.com.jingdong.modle.NewlistModel;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lenovo on 2017/12/18.
 */

public class NewlistPresenter {
    private NewListApi na;
    private NewlistModel nm;

    public NewlistPresenter(NewListApi na) {
        this.na = na;
        nm=new NewlistModel();
    }
    public void getNewList(String page){
        nm.OnImm(page, new Observer<NewlistBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(NewlistBean newlistBean) {
             na.ShowData(newlistBean.getGoods_list());
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
