package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import demo.bw.com.jingdong.Api.OnListenerClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.SearchBean;

/**
 * Created by 李岳峰 on 2017/12/5.
 */

public class SearchAdapater extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<SearchBean.DataBean> list;
    private LayoutInflater inflater;
    private OnListenerClickApi onListenerClickApi;

    public SearchAdapater(Context context, List<SearchBean.DataBean> list, OnListenerClickApi onListenerClickApi) {
        this.context = context;
        this.list = list;
        this.onListenerClickApi = onListenerClickApi;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.searchitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        final SearchBean.DataBean dataBean = list.get(position);
        myViewHolder.title.setText(dataBean.getTitle());
        myViewHolder.price.setText("￥"+dataBean.getPrice());
        myViewHolder.time.setText(dataBean.getCreatetime());
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        String s = split[0];
        Uri uri = Uri.parse(s);
        myViewHolder.sd.setImageURI(uri);
        myViewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onListenerClickApi.setOnClieck(dataBean.getPid()+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        private SimpleDraweeView sd;
        private TextView title;
        private TextView time;
        private TextView price;
        private LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            price=itemView.findViewById(R.id.si_price);
            sd=itemView.findViewById(R.id.si_dw);
            title=itemView.findViewById(R.id.si_title);
            time=itemView.findViewById(R.id.si_time);
            linearLayout=itemView.findViewById(R.id.si_linear);
        }
    }
}
