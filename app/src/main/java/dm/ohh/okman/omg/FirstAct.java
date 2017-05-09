package dm.ohh.okman.omg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import util.HttpUtil;

public class FirstAct extends AppCompatActivity {

    private String CAR_BANLANCE = "http://192.168.252.8:8080/transportservice/type/jason/action/GetCarAccountBalance.do";
    private String CAR_RECHANGE = "http://192.168.252.8:8080/transportservice/type/jason/action/SetCarAccountRecharge.do";
    private Spinner sp1;
    private Button bt_chaxun;
    private Spinner sp2;
    private EditText et_jine;
    private Button bt_chongzhi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        initView();
    }
    private void initView(){
        sp1 = (Spinner) findViewById(R.id.sp_1);
        sp2 = (Spinner) findViewById(R.id.sp_2);
        bt_chaxun = (Button) findViewById(R.id.btn_chaxun);
        bt_chongzhi = (Button) findViewById(R.id.btn_chongzhi);
        et_jine = (EditText) findViewById(R.id.et_jine);
        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bt_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpUtil.getPost(CAR_RECHANGE,"{\"CarId\":1,\"Money\":200}");
                    }
                }).start();
            }
        });
        bt_chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                            HttpUtil.getPost(CAR_BANLANCE,"{\"CarId\":1}");
                    }
                }).start();
            }
        });
    }
}
