package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.CartBean;
import demo.bw.com.jingdong.utils.EventCheck;
import demo.bw.com.jingdong.utils.EventCount;

/**
 * Created by 李岳峰 on 2017/12/10.
 */

public class ShopAdapter extends BaseExpandableListAdapter {
     private   List<CartBean.DataBean> grouplist;
   private List<List<CartBean.DataBean.ListBean>> childlist;
   private Context context;
   private LayoutInflater inflater;

    public ShopAdapter(List<CartBean.DataBean> grouplist, List<List<CartBean.DataBean.ListBean>> childlist, Context context) {
        this.grouplist = grouplist;
        this.childlist = childlist;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return grouplist.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childlist.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return grouplist.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return  childlist.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder holder;
        if(convertView==null){
            holder=new GroupViewHolder();
            convertView=inflater.inflate(R.layout.fs_group,null);
            holder.gcheck=convertView.findViewById(R.id.fs_g_check);
            holder.gname=convertView.findViewById(R.id.fs_g_name);
            convertView.setTag(holder);
        }else{
            holder= (GroupViewHolder) convertView.getTag();
        }
        final CartBean.DataBean dataBean = grouplist.get(groupPosition);
        holder.gcheck.setChecked(dataBean.isChecked());
        holder.gname.setText(dataBean.getSellerName());
        holder.gcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(holder.gcheck.isChecked());
                changechild(groupPosition,holder.gcheck.isChecked());
                PostValue(isgroupAll());
                EventBus.getDefault().postSticky(priceAndnum());
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder holder;
        if (convertView==null) {
            holder=new ChildViewHolder();
            convertView=inflater.inflate(R.layout.fs_child,null);
            holder.image=convertView.findViewById(R.id.fs_c_image);
            holder.ccheck=convertView.findViewById(R.id.fs_c_check);
            holder.cname=convertView.findViewById(R.id.fs_c_name);
            holder.num=convertView.findViewById(R.id.fs_c_num);
            holder.cprice=convertView.findViewById(R.id.fs_c_price);
            holder.add=convertView.findViewById(R.id.fs_c_add);
            holder.lose=convertView.findViewById(R.id.fs_c_lose);
            convertView.setTag(holder);
        }else{
           holder= (ChildViewHolder) convertView.getTag();
        }
     final CartBean.DataBean.ListBean listBeans = childlist.get(groupPosition).get(childPosition);
        holder.ccheck.setChecked(listBeans.isChecked());
        String[] split = listBeans.getImages().split("\\|");
        String s = split[0];
        holder.image.setImageURI(s);
        holder.cname.setText(listBeans.getTitle());
        holder.num.setText(listBeans.getNum()+"");
        holder.cprice.setText("￥"+listBeans.getPrice());
        holder.ccheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBeans.setChecked(holder.ccheck.isChecked());
                EventBus.getDefault().postSticky(priceAndnum());
                if(holder.ccheck.isChecked()){
                      if(ischildAll(groupPosition)){
                          changeGroup(groupPosition,true);
                          PostValue(isgroupAll());
                      }
                }else{
                    changeGroup(groupPosition,false);
                    PostValue(false);
                }
                notifyDataSetChanged();
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = listBeans.getNum();
                holder.num.setText(++num+"");
                listBeans.setNum(num);
                EventBus.getDefault().postSticky(priceAndnum());
                notifyDataSetChanged();
            }
        });
        holder.lose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = listBeans.getNum();
                if(num1==0){
                    return;
                }
                holder.num.setText(--num1+"");
                listBeans.setNum(num1);

                EventBus.getDefault().postSticky(priceAndnum());
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder{
        CheckBox gcheck;
        TextView gname;
    }
    class ChildViewHolder{
         CheckBox ccheck;
        SimpleDraweeView image;
        TextView cname;
        ImageView add;
        TextView num;
        ImageView lose;
        TextView cprice;
    }
    public EventCount priceAndnum(){
        int num=0;
         double price=0.0;
        for(int i=0;i<childlist.size();i++){
            List<CartBean.DataBean.ListBean> list = childlist.get(i);
            for(int j=0;j<list.size();j++){
                CartBean.DataBean.ListBean listBean = list.get(j);
                if(listBean.isChecked()){
                    num+=listBean.getNum();
                    price+=listBean.getPrice()*listBean.getNum();
                }
            }
        }
        EventCount count=new EventCount();
        count.setNum(num);
        count.setPrice(price);
        return count;
    }
    public boolean ischildAll(int gp){
        List<CartBean.DataBean.ListBean> listBeans = childlist.get(gp);
        for(int i=0;i<listBeans.size();i++){
            CartBean.DataBean.ListBean listBean = listBeans.get(i);
            if (!listBean.isChecked()) {
                return false;
            }

        }
        return  true;
    }
    public boolean isgroupAll(){

        for(int i=0;i<grouplist.size();i++){
            CartBean.DataBean dataBean = grouplist.get(i);
            if(!dataBean.isChecked()){
                return false;
            }
        }
        return true;
    }
    public void changeGroup(int gp,boolean flag){
        CartBean.DataBean dataBean = grouplist.get(gp);
        dataBean.setChecked(flag);
    }
    public void PostValue(boolean flag){
        EventCheck eventCheck=new EventCheck();
        eventCheck.setChecked(flag);
        EventBus.getDefault().postSticky(eventCheck);
    }
    public void changechild(int gp,boolean flag){
        List<CartBean.DataBean.ListBean> listBeans = childlist.get(gp);
        for(int i=0;i<listBeans.size();i++){
            CartBean.DataBean.ListBean listBean = listBeans.get(i);
                listBean.setChecked(flag);
        }
       notifyDataSetChanged();
    }
    public void changeAll(boolean flag){
        for(int i=0;i<grouplist.size();i++){
            changechild(i,flag);
            changeGroup(i,flag);

        }
        EventBus.getDefault().postSticky(priceAndnum());
        notifyDataSetChanged();
    }

    public List<String>  getPid(){
        List<String>  plist=new ArrayList<>();
        for(int i=0;i<childlist.size();i++){
            List<CartBean.DataBean.ListBean> list = childlist.get(i);
            for(int j=0;j<list.size();j++){
                CartBean.DataBean.ListBean listBean = list.get(j);
                if(listBean.isChecked()){
                    plist.add(listBean.getPid()+"");

                }
            }
        }

        notifyDataSetChanged();
       return  plist;
    }
    public void delSelect(){
        for(int i=0;i<childlist.size();i++){
            List<CartBean.DataBean.ListBean> list = childlist.get(i);
            for(int j=0;j<list.size();j++){
                CartBean.DataBean.ListBean listBean = list.get(j);
                if(listBean.isChecked()){
                    CartBean.DataBean.ListBean remove = list.remove(j);
                    if(j==0){
                        grouplist.remove(i);
                        childlist.remove(i);
                    }
                }
            }
        }
        EventBus.getDefault().postSticky(priceAndnum());
        notifyDataSetChanged();
    }
}
