package demo.bw.com.jingdong.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import demo.bw.com.jingdong.Api.LoginApi;
import demo.bw.com.jingdong.R;
import demo.bw.com.jingdong.presenter.loginPersenter;
import demo.bw.com.jingdong.view.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginApi{

    @BindView(R.id.l_back)
    ImageView lBack;
    @BindView(R.id.l_mob)
    EditText lMob;
    @BindView(R.id.l_pwd)
    EditText lPwd;
    @BindView(R.id.l_log)
    Button lLog;
    @BindView(R.id.l_reg)
    TextView lReg;
    @BindView(R.id.l_qq)
    ImageView lQq;
    @BindView(R.id.l_wexin)
    ImageView lWexin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick({R.id.l_back, R.id.l_log, R.id.l_reg, R.id.l_qq, R.id.l_wexin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.l_back:
                Intent intent2=new Intent(LoginActivity.this, MainActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("account","登录/注册>");
                intent2.putExtras(bundle);
                LoginActivity.this.setResult(2,intent2);
                finish();
                break;
            case R.id.l_log:
                loginPersenter lp=new loginPersenter((LoginApi) this);
                lp.getOnLogin(lMob.getText().toString().trim(),lPwd.getText().toString().trim());
                break;
            case R.id.l_reg:
                Intent intent1=new Intent(LoginActivity.this,RegistActivity.class);
                startActivity(intent1);
                break;
            case R.id.l_qq:
                break;
            case R.id.l_wexin:
                break;
        }
    }

    @Override
    public void getLogin(String str) {
        Toast.makeText(LoginActivity.this,str,Toast.LENGTH_SHORT).show();
        if(str.equals("登录成功")){
           Intent intent=new Intent(LoginActivity.this, MainActivity.class);
           Bundle bundle=new Bundle();
           bundle.putString("account",lMob.getText().toString().trim());
           intent.putExtras(bundle);
            LoginActivity.this.setResult(1,intent);
            finish();
        }
    }
}
