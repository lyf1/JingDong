package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.android.percent.support.PercentLinearLayout;

import java.util.List;

import demo.bw.com.jingdong.Api.OnClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.CatagoryBean;
import demo.bw.com.jingdong.view.activity.C_childActivity;
import demo.bw.com.jingdong.view.activity.Fa_activity;

/**
 * Created by 李岳峰 on 2017/12/13.
 */

public class fa_view_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CatagoryBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;


    public fa_view_Adapter(List<CatagoryBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.fa_viewitem1,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;

        final CatagoryBean.DataBean dataBean = list.get(position);
        myViewHolder.tv.setText(dataBean.getName());
        myViewHolder.image.setImageURI(dataBean.getIcon());
        myViewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Fa_activity.class);
                intent.putExtra("name",dataBean.getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView image;
        private LinearLayout linear;
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.fa_view_name);
            image=itemView.findViewById(R.id.fa_view_image);
            linear=itemView.findViewById(R.id.fa_view_linear);
        }
    }
}
