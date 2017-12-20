package demo.bw.com.jingdong.Api;

import java.util.Map;

import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.bean.CartBean;
import demo.bw.com.jingdong.bean.CatagoryBean;
import demo.bw.com.jingdong.bean.GetDefaultBean;
import demo.bw.com.jingdong.bean.NewlistBean;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;
import demo.bw.com.jingdong.bean.SearchBean;
import demo.bw.com.jingdong.bean.SearchDetailBean;
import demo.bw.com.jingdong.bean.c_childBean;
import demo.bw.com.jingdong.bean.fristBean;
import demo.bw.com.jingdong.bean.getAddrsBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 李岳峰 on 2017/12/4.
 */

public interface ServiceApi {
    @GET("product/searchProducts")
    Call<SearchBean>  SearchData(@QueryMap Map<String,String> parmas );

    @GET("product/getProductDetail")
    Call<SearchDetailBean> SearchDetailData(@Query("pid") String pid,@Query("source") String source);

    @GET("product/addCart?uid=100&source=android")
    Call<BaseBean> addCartData(@Query("pid") String pid);

    @GET("ad/getAd")
    Call<fristBean>  fristData();

    @GET("product/getCatagory")
    Call<CatagoryBean> CatagoryData();

    @GET("product/getProductCatagory")
    Call<ProductCatagoryBean> getProductCatagory(@Query("cid") String cid);

    @GET("product/getProducts")
    Call<c_childBean> getProducts(@Query("pscid") String pscid, @Query("page") String page);

    @GET("product/getCarts?source=android")
    Call<CartBean> getCarts(@Query("uid") String uid);

    @GET("product/deleteCart?uid=100")
    Call<BaseBean>  DeleteCart(@Query("pid") String pid);

    @GET("user/login")
    Call<BaseBean> login(@Query("mobile") String mob,@Query("password") String pwd);

     @GET("user/reg")
    Call<BaseBean> regist(@Query("mobile") String mob,@Query("password") String pwd);

     @GET("user/addAddr")
     Call<BaseBean> addAddr(@QueryMap Map<String,String> parmas);

     @GET("user/getAddrs")
    Call<getAddrsBean> getAdds(@Query("uid") String uid);

     @GET("user/setAddr")
     Call<BaseBean> setAddr(@Query("uid") String uid,@Query("addrid") String addrid,@Query("status") String statu);

    @GET("user/getDefaultAddr")
    Call<GetDefaultBean> getDefaultAddr(@Query("uid") String uid);

    @GET("user/updateAddr")
    Call<BaseBean> updateAddr(@QueryMap Map<String,String> parmas);

    @GET("v5/newlist?size=20&ver=1512745500001&pdduid=3470667255")
    Call<NewlistBean> newlist(@Query("page") String page);
}
