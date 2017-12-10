package demo.bw.com.jingdong.presenter;

import java.util.List;

import demo.bw.com.jingdong.Api.OnListenerApi;
import demo.bw.com.jingdong.Api.SearchDetailActivityApi;
import demo.bw.com.jingdong.bean.SearchDetailBean;
import demo.bw.com.jingdong.modle.searchDetailModle;

/**
 * Created by 李岳峰 on 2017/12/5.
 */

public class SearchDetailPresenter {
  private SearchDetailActivityApi searchDetailActivityApi;
    private final searchDetailModle sm;

    public SearchDetailPresenter(SearchDetailActivityApi searchDetailActivityApi) {
        this.searchDetailActivityApi = searchDetailActivityApi;
        sm = new searchDetailModle();
    }
    public void getSearchDetail(String pid){
        sm.onSearchDetail(pid, new OnListenerApi<SearchDetailBean>() {
            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onSuccess(SearchDetailBean searchDetailBean) {
                 searchDetailActivityApi.showData(searchDetailBean.getData());
            }
        });
    }
}
