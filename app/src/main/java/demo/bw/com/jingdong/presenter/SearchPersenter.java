package demo.bw.com.jingdong.presenter;

import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.SearchActivityapi;
import demo.bw.com.jingdong.bean.SearchBean;
import demo.bw.com.jingdong.modle.SearchModle;
import demo.bw.com.jingdong.view.activity.SearchActivity;

/**
 * Created by 李岳峰 on 2017/12/4.
 */

public class SearchPersenter {
    private SearchActivityapi searchActivity;
    private  SearchModle searchModle;

    public SearchPersenter(SearchActivityapi searchActivity) {
        this.searchActivity = searchActivity;
        searchModle = new SearchModle();
    }

    public void getSearch(String keywords, String page){
         searchModle.onSerarch(keywords, page, new OnListenerApi<SearchBean>() {
             @Override
             public void onError(Exception e) {

             }

             @Override
             public void onSuccess(SearchBean searchBean) {
                searchActivity.showData(searchBean.getData());
             }
         });
    }
}
