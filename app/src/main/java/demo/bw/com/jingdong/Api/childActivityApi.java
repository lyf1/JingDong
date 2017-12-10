package demo.bw.com.jingdong.Api;

import java.util.List;

import demo.bw.com.jingdong.bean.c_childBean;
import io.reactivex.Observer;

/**
 * Created by 李岳峰 on 2017/12/9.
 */

public interface childActivityApi {
   void onProducts(List<c_childBean.DataBean> list);
}
