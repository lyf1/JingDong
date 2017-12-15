package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.fristBean;

/**
 * Created by 李岳峰 on 2017/12/13.
 */

public class SeckllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<fristBean.MiaoshaBean.ListBeanX> list;
    private LayoutInflater inflater;

    public SeckllAdapter(Context context, List<fristBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view=inflater.inflate(R.layout.seckllitem,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      MyViewHolder myViewHolder= (MyViewHolder) holder;
        fristBean.MiaoshaBean.ListBeanX listBean= list.get(position);
        myViewHolder.bar.setProgress(listBean.getPid());
        myViewHolder.pid.setText("已售"+listBean.getPid()+"%");
        myViewHolder.price.setText("￥"+listBean.getPrice());
        myViewHolder.oldprice.setText("￥"+listBean.getBargainPrice());
        myViewHolder.oldprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.title.setText(listBean.getTitle());
        String[] split = listBean.getImages().split("\\|");
        myViewHolder.image.setImageURI(split[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
       private SimpleDraweeView image;
       private TextView title;
       private TextView price;
       private TextView oldprice;
       private ProgressBar bar;
       private TextView pid;
        public MyViewHolder(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.sitemm_image);
            title=itemView.findViewById(R.id.sitemm_name);
            price=itemView.findViewById(R.id.sitemm_price);
            oldprice=itemView.findViewById(R.id.sitemm_oldprice);
            bar=itemView.findViewById(R.id.sitemm_progress);
            pid=itemView.findViewById(R.id.sitemm_pid);

        }
    }
}
