package demo.bw.com.jingdong.modle;

import java.util.HashMap;
import java.util.Map;

import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.SearchmodleApi;
import demo.bw.com.jingdong.Api.ServiceApi;
import demo.bw.com.jingdong.bean.SearchBean;
import demo.bw.com.jingdong.utils.OkHttpUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 李岳峰 on 2017/12/4.
 */

public class SearchModle implements SearchmodleApi {

    @Override
    public void onSerarch(String keywords, String page, final OnListenerApi<SearchBean> onListenerApi) {
        ServiceApi service = OkHttpUtils.getService();
        Map<String,String> parmas=new HashMap<>();
        parmas.put("keywords",keywords);
        parmas.put("page",page);
        parmas.put("source","android");
        Call<SearchBean> searchBeanCall = service.SearchData(parmas);
        searchBeanCall.enqueue(new Callback<SearchBean>() {
            @Override
            public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                SearchBean body = response.body();
                onListenerApi.onSuccess(body);
            }

            @Override
            public void onFailure(Call<SearchBean> call, Throwable t) {

            }
        });
    }
}
