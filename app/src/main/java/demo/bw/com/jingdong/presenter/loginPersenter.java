package demo.bw.com.jingdong.presenter;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import demo.bw.com.jingdong.Api.LoginApi;
import demo.bw.com.jingdong.bean.BaseBean;
import demo.bw.com.jingdong.modle.LoginModle;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by 李岳峰 on 2017/12/12.
 */

public class loginPersenter {
    private LoginApi loginApi;
    private final LoginModle lm;

    public loginPersenter(LoginApi loginApi) {
        this.loginApi = loginApi;
        lm = new LoginModle();
    }
    public  void getOnLogin(String mob,String pwd){
          if(checkPwd(pwd)&&chekckMobile(mob)){
              lm.onImm(mob, pwd, new Observer<BaseBean>() {
                  @Override
                  public void onSubscribe(Disposable d) {

                  }

                  @Override
                  public void onNext(BaseBean baseBean) {
                      loginApi.getLogin(baseBean.getMsg());
                  }

                  @Override
                  public void onError(Throwable e) {


                  }

                  @Override
                  public void onComplete() {

                  }
              });
          }

    }
    public boolean chekckMobile(String mob){
          if(TextUtils.isEmpty(mob)){
                loginApi.getLogin("用户名不能为空");
                return false;
          }
          if(!isMobileNO(mob)){
              loginApi.getLogin("手机号格式错误");
              return false;
          }

          return  true;
    }
    public boolean checkPwd(String pwd){
        if(TextUtils.isEmpty(pwd)){
            loginApi.getLogin("密码不能为空");
            return false;
        }
        if(pwd.length()<3){
            loginApi.getLogin("密码必须大于3位数");
            return  false;
        }
        return  true;
    }
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
}
