package demo.bw.com.jingdong.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import demo.bw.com.jingdong.Api.NewListApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.adapter.NewListAdapter;
import demo.bw.com.jingdong.bean.NewlistBean;
import demo.bw.com.jingdong.presenter.NewlistPresenter;

/**
 * Created by 李岳峰 on 2017/12/1.
 */

public class FindFragment extends Fragment implements NewListApi {
    @BindView(R.id.ff_rv)
    RecyclerView ffRv;
    Unbinder unbinder;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.video_view)
    VideoView videoView;


    private NewlistPresenter np;
    private NewListAdapter newListAdapter;
    private int page = 1;
    private List<List<NewlistBean.GoodsListBean>> lists;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);

        unbinder = ButterKnife.bind(this, view);
        ffRv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        np = new NewlistPresenter(this);
        np.getNewList("1");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        np.getNewList("1");
                        newListAdapter.notifyDataSetChanged();
                    }
                }, 3000);

            }
        });
        ffRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager lm = (LinearLayoutManager) ffRv.getLayoutManager();
                    if (lm.findLastVisibleItemPosition() == lists.size() - 1) {
                        page++;
                        if (page == 3) {
                            return;
                        }
                        np.getNewList(page + "");
                        newListAdapter.notifyDataSetChanged();
                    }
                }

            }
        });
        //视频播放
        videoView.setVideoPath("http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4");
        videoView.start();


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void ShowData(List<NewlistBean.GoodsListBean> list) {
        lists = new ArrayList<>();
        lists.add(list);
        newListAdapter = new NewListAdapter(list, getActivity());
        ffRv.setAdapter(newListAdapter);
        /*;*/
    }


}
