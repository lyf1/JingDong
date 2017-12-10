package demo.bw.com.jingdong.utils;

import android.support.v7.widget.RecyclerView;

import demo.bw.com.jingdong.Api.ServiceApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李岳峰 on 2017/12/6.
 */

public class RetrofitHelper {
    private static ServiceApi serviceApi;

    public static  <T> T createApi(Class<T> tClass,String url){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return  retrofit.create(tClass);

    }
    public static ServiceApi getService(){
        if(serviceApi==null){
            synchronized (RetrofitHelper.class){
                if(serviceApi==null){
                    serviceApi=RetrofitHelper.createApi(ServiceApi.class,UrlUtils.HOST);
                }
            }
        }
        return serviceApi;
    }
}
