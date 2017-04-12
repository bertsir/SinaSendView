package bertsir.cn.sinadialognemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bt = (Button) findViewById(R.id.bt);

        bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt:
                showSina();
                break;
        }
    }

    private void showSina(){
        new SinaSendView(MainActivity.this,R.style.SinaSendDialog,false).setClick(new SinaSendDialog() {
            @Override
            public void onNormalClick() {
                Toast.makeText(getApplicationContext(),"一般内容",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTimeClick() {
                Toast.makeText(getApplicationContext(),"时间胶囊",Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onMapClick() {
                Toast.makeText(getApplicationContext(),"地点胶囊",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
