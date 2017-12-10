package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import demo.bw.com.jingdong.Api.OnClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.CatagoryBean;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public class C_catagory_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CatagoryBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;
    private OnClickApi onClickApi;

    public C_catagory_Adapter(List<CatagoryBean.DataBean> list, Context context,OnClickApi onClickApi) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.onClickApi=onClickApi;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.c_catagory_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
          MyViewHolder myViewHolder= (MyViewHolder) holder;
        final CatagoryBean.DataBean dataBean = list.get(position);
        myViewHolder.tv.setText(dataBean.getName());
        myViewHolder.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onClickApi.setOnClickListener(dataBean.getCid()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.cc_tv);
        }
    }
}
