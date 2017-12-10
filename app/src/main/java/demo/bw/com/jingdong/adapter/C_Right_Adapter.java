package demo.bw.com.jingdong.adapter;

import android.content.ComponentName;
import android.content.Context;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import demo.bw.com.jingdong.Api.OnClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.ProductCatagoryBean;

/**
 * Created by 李岳峰 on 2017/12/8.
 */

public class C_Right_Adapter extends BaseExpandableListAdapter {
     private List<ProductCatagoryBean.DataBean> grouplist;
     private    List<List<ProductCatagoryBean.DataBean.ListBean>> childlist;
    private Context context;
    private LayoutInflater inflater;

    public C_Right_Adapter(List<ProductCatagoryBean.DataBean> grouplist, List<List<ProductCatagoryBean.DataBean.ListBean>> childlist, Context context) {
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
        return  1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return  grouplist.get(groupPosition);
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
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder;
        if(convertView==null){
            holder=new GroupViewHolder();
            convertView=inflater.inflate(R.layout.c_groupitem,null);
            holder.g_tv=convertView.findViewById(R.id.g_title);
            convertView.setTag(holder);
        }else{
             holder= (GroupViewHolder) convertView.getTag();
        }
        ProductCatagoryBean.DataBean dataBean = grouplist.get(groupPosition);

        holder.g_tv.setText(dataBean.getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holder;
        if(convertView==null){
          holder=new ChildViewHolder();
          convertView=inflater.inflate(R.layout.c_childitem,null);
          holder.rv=convertView.findViewById(R.id.child_rv);
          convertView.setTag(holder);
        }else{
          holder= (ChildViewHolder) convertView.getTag();
        }
        List<ProductCatagoryBean.DataBean.ListBean> listBeans = childlist.get(childPosition);
        c_Child_Adapter adapter=new c_Child_Adapter(listBeans,context );
         holder.rv.setLayoutManager(new GridLayoutManager(context,4));
         holder.rv.setAdapter(adapter);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder{
       private TextView g_tv;
    }
    class ChildViewHolder{
     private RecyclerView rv;
    }
}
