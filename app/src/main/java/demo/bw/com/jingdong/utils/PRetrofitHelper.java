package demo.bw.com.jingdong.utils;

import demo.bw.com.jingdong.Api.ServiceApi;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lenovo on 2017/12/18.
 */

public class PRetrofitHelper {
    private static ServiceApi si;
     public static <T> T createApi(Class<T> tClass,String url){
         Retrofit retrofit=new Retrofit.Builder()
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .baseUrl(url)
                 .build();
         return  retrofit.create(tClass);
     }
     public static ServiceApi getService(){
         if(si==null){
             synchronized (PRetrofitHelper.class){
                 if(si==null){
                     si=PRetrofitHelper.createApi(ServiceApi.class,UrlUtils.HOSTPDD);
                 }
             }
         }
         return  si;
     }
}
