package demo.bw.com.jingdong.utils;

import android.support.v7.widget.RecyclerView;

import demo.bw.com.jingdong.Api.ServiceApi;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 李岳峰 on 2017/12/6.
 */

public class RetrofitHelper {
    private static ServiceApi serviceApi;
    private static OkHttpClient okHttpClient;
    static {
        initOkHttpClient();
    }

    private static void initOkHttpClient() {
        if(okHttpClient==null){
            synchronized (OkHttpClient.class){
                if(okHttpClient==null){
                    okHttpClient=new OkHttpClient.Builder()
                            .build();
                }
            }
        }
    }
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
