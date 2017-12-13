package demo.bw.com.jingdong.presenter;

import java.util.ArrayList;
import java.util.List;

import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.ShopFragmentApi;
import demo.bw.com.jingdong.bean.CartBean;
import demo.bw.com.jingdong.modle.GetCartsModle;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/10.
 */

public class GetCartsPersenter {
    private ShopFragmentApi si;
    private final GetCartsModle modle;

    public GetCartsPersenter(ShopFragmentApi si) {
        this.si = si;
        modle = new GetCartsModle();
    }
    public void getCarts(){


        modle.onImm(new Observer<CartBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CartBean cartBean) {
                List<CartBean.DataBean> data = cartBean.getData();
                List<List<CartBean.DataBean.ListBean>> childlist=new ArrayList<>();
                for(int i=0;i<data.size();i++){
                    CartBean.DataBean dataBean = data.get(i);
                    childlist.add(dataBean.getList());
                }
                si.getShop(cartBean.getData(),childlist);
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
