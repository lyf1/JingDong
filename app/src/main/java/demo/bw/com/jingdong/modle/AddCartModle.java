package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.AddCarApi;
import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.utils.OkHttpUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李岳峰 on 2017/12/6.
 */

public class AddCartModle implements AddCarApi{
    @Override
    public void onAddCart(String pid, final OnListenerApi<BaseBean> onListenerApi) {
        ServiceApi service = OkHttpUtils.getService();
        Call<BaseBean> baseBeanCall = service.addCartData(pid);
        baseBeanCall.enqueue(new Callback<BaseBean>() {
            @Override
            public void onResponse(Call<BaseBean> call, Response<BaseBean> response) {
                BaseBean body = response.body();
                onListenerApi.onSuccess(body);
            }

            @Override
            public void onFailure(Call<BaseBean> call, Throwable t) {

            }
        });
    }
}
