package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ConcurrentModificationException;
import java.util.List;

import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.NewlistBean;

/**
 * Created by lenovo on 2017/12/18.
 */

public class NewListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewlistBean.GoodsListBean> list;
    private Context context;
    private LayoutInflater inflater;

    public static final int TYPE_PULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;

    public NewListAdapter(List<NewlistBean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        RecyclerView.ViewHolder holder=null;
        switch (viewType){
            case 0:
                view=inflater.inflate(R.layout.newlistitem1,parent,false);
                holder=new ViewHolderOne(view);
                break;
            case 1:
                view=inflater.inflate(R.layout.newlistitem2,parent,false);
                holder=new ViewHolderTwo(view);
                break;
            case 2:
                view=inflater.inflate(R.layout.newlistitem3,parent,false);
                holder=new ViewHolderThree(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
      final   NewlistBean.GoodsListBean goodsListBean = list.get(position);

        switch (getItemViewType(position)){
            case 0:
            ViewHolderOne holderOne= (ViewHolderOne) holder;
            Uri uri=Uri.parse(goodsListBean.getImage_url());
            holderOne.image.setImageURI(uri);
            holderOne.name.setText(goodsListBean.getGoods_name());
            holderOne.price.setText("￥:"+goodsListBean.getNormal_price());
            break;
            case 1:
            ViewHolderTwo holderTwo= (ViewHolderTwo) holder;
            Uri uri1=Uri.parse(goodsListBean.getImage_url());
            holderTwo.image.setImageURI(uri1);
            holderTwo.name.setText(goodsListBean.getGoods_name());
            holderTwo.price.setText("￥:"+goodsListBean.getNormal_price());
            break;
            case 2:
            ViewHolderThree holderThree= (ViewHolderThree) holder;
           Uri uri2=Uri.parse(goodsListBean.getImage_url());
            holderThree.image.setImageURI(uri2);
            holderThree.name.setText(goodsListBean.getGoods_name());
            holderThree.price.setText("￥:"+goodsListBean.getNormal_price());
            break;
        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 3 == 0) {
            return 0;
        } else if (position % 3 == 1) {
            return 1;
        } else{
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewHolderOne extends RecyclerView.ViewHolder{
        private SimpleDraweeView image;
        private TextView name;
        private TextView price;
        private SimpleDraweeView head1;
        private SimpleDraweeView head2;
        public ViewHolderOne(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.ni1_image);
            name=itemView.findViewById(R.id.ni1_text);
            price=itemView.findViewById(R.id.ni1_price);
            head1=itemView.findViewById(R.id.ni1_image1);
            head2=itemView.findViewById(R.id.ni1_image2);
        }
    }
    class ViewHolderTwo extends RecyclerView.ViewHolder{
        private SimpleDraweeView image;
        private TextView name;
        private TextView price;
        private SimpleDraweeView head1;
        public ViewHolderTwo(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.ni2_image);
            name=itemView.findViewById(R.id.ni2_text);
            price=itemView.findViewById(R.id.ni2_price);
            head1=itemView.findViewById(R.id.ni2_image1);
        }
    }
    class ViewHolderThree extends RecyclerView.ViewHolder{
        private SimpleDraweeView image;
        private TextView name;
        private TextView price;
        public ViewHolderThree(View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.ni3_image);
            name=itemView.findViewById(R.id.ni3_text);
            price=itemView.findViewById(R.id.ni3_price);
        }
    }
}
