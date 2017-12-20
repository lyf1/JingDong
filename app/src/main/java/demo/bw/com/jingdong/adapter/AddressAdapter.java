package demo.bw.com.jingdong.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import demo.bw.com.jingdong.Api.AddAddrActivityApi;
import demo.bw.com.jingdong.Api.AddSelectClickApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.bean.getAddrsBean;
import demo.bw.com.jingdong.presenter.SetAddrPresenter;
import demo.bw.com.jingdong.presenter.updateAddrPresenter;
import demo.bw.com.jingdong.view.activity.AplayActivity;

/**
 *
 * Created by lenovo on 2017/12/15.
 */

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements AddAddrActivityApi {
    private List<getAddrsBean.DataBean> list;
    private Context context;
    private LayoutInflater inflater;
     private updateAddrPresenter up;
    private SetAddrPresenter sp;
    private  AddSelectClickApi click;
    public AddressAdapter(List<getAddrsBean.DataBean> list, Context context,AddSelectClickApi click) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
        up=new updateAddrPresenter((AddAddrActivityApi) context);
        sp=new SetAddrPresenter((AddAddrActivityApi) context);
        this.click=click;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.addressitem,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final getAddrsBean.DataBean dataBean = list.get(position);
        final MyViewHolder myViewHolder= (MyViewHolder) holder;
        myViewHolder.name.setText(dataBean.getName());
        myViewHolder.mob.setText(dataBean.getMobile()+"");
        myViewHolder.addr.setText(dataBean.getAddr());
        myViewHolder.check.setChecked(dataBean.isCheck());

        myViewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                press(position);
                if(myViewHolder.check.isChecked()){
                      sp.getSetAddr(dataBean.getAddrid()+"");
                       notifyDataSetChanged();
                }
                notifyDataSetChanged();
            }
        });
        myViewHolder.editor.setOnClickListener(new View.OnClickListener() {

            private AlertDialog dialog;

            @Override
            public void onClick(View v) {
                View view1= inflater.inflate(R.layout.alertdialog,null);
                TextView title=view1.findViewById(R.id.alert_title);
                final TextView addr=view1.findViewById(R.id.alert_addr);
                final TextView mob=view1.findViewById(R.id.alert_mob);
                final TextView name=view1.findViewById(R.id.alert_name);
                Button qd=view1.findViewById(R.id.alert_sure);
                Button qx=view1.findViewById(R.id.alert_qx);
                title.setText("修改地址");
                final AlertDialog.Builder alert=new AlertDialog.Builder(context);

                qd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        up.getUpDateAddr(dataBean.getAddrid()+"",mob.getText().toString().trim(),name.getText().toString().trim(),addr.getText().toString().trim());
                        dialog.dismiss();
                    }
                });
                qx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                alert.setView(view1);
                dialog = alert.show();

                notifyDataSetChanged();
            }
        });
        myViewHolder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          click.setOnClick(dataBean.getName(),dataBean.getMobile()+"",dataBean.getAddr());

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() ;
    }

    @Override
    public void showData(List<getAddrsBean.DataBean> list) {

    }

    @Override
    public void addData(String str) {

    }

    @Override
    public void setAddr(String str) {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAddr(String str) {
        Toast.makeText(context,str,Toast.LENGTH_SHORT).show();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout linear;
        private TextView name;
        private TextView mob;
        private TextView addr;
        private CheckBox check;
        private TextView editor;
        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.add_name);
            mob=itemView.findViewById(R.id.add_mob);
            addr=itemView.findViewById(R.id.add_addr);
            check=itemView.findViewById(R.id.add_check);
            editor=itemView.findViewById(R.id.add_editor);

            linear=itemView.findViewById(R.id.add_linear);
        }
    }
    public void press(int position){
        for(int i=0;i<list.size();i++){
            getAddrsBean.DataBean dataBean = list.get(i);
            dataBean.setCheck(false);
        }
        getAddrsBean.DataBean dataBean = list.get(position);
        dataBean.setCheck(true);
        notifyDataSetChanged();
    }

}







