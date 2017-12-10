package demo.bw.com.jingdong.modle;

import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.SearchDetailApi;
import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.bean.SearchDetailBean;
import demo.bw.com.jingdong.utils.OkHttpUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李岳峰 on 2017/12/5.
 */

public class searchDetailModle implements SearchDetailApi {
    @Override
    public void onSearchDetail(String pid, final OnListenerApi<SearchDetailBean> onListenerApi) {
        ServiceApi service = OkHttpUtils.getService();
        Call<SearchDetailBean> searchDetailBeanCall = service.SearchDetailData(pid,"android");
        searchDetailBeanCall.enqueue(new Callback<SearchDetailBean>() {
            @Override
            public void onResponse(Call<SearchDetailBean> call, Response<SearchDetailBean> response) {
                SearchDetailBean body = response.body();
                onListenerApi.onSuccess(body);
            }

            @Override
            public void onFailure(Call<SearchDetailBean> call, Throwable t) {

            }
        });
    }
}
