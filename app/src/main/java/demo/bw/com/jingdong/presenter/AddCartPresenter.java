package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.SearchDetailActivityApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.modle.AddCartModle;

/**
 * Created by 李岳峰 on 2017/12/6.
 */

public class AddCartPresenter {
    private SearchDetailActivityApi sda;
    private final AddCartModle cartModle;

    public AddCartPresenter(SearchDetailActivityApi sda) {
        this.sda = sda;
        cartModle = new AddCartModle();
    }
    public void getAddCart(String pid){
        cartModle.onAddCart(pid, new OnListenerApi<BaseBean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(BaseBean baseBean) {
              sda.btnData(baseBean.getMsg());
            }
        });
    }
}
