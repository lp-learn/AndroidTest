package dm.ohh.okman.omg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.IOException;
import java.util.ArrayList;

import adapter.LukouAdaper;
import bean.Lukou;
import util.HttpListener;
import util.HttpUtil;

public class SecondAct extends AppCompatActivity {
    private static final String LUKOU_DENG = "http://192.168.252.8:8080/transportservice/type/jason/action/GetTrafficLightConfigAciton.do";
    private ListView lv;
    private Button btn_chaxun;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
        initEvent();
        ArrayList<Lukou> data = new ArrayList<>();
        data.add(new Lukou("5","1","10","3"));
        data.add(new Lukou("5","2","12","4"));
        data.add(new Lukou("5","3","6","5"));
        data.add(new Lukou("5","4","7","6"));
        data.add(new Lukou("5","5","8","55"));
        LukouAdaper la = new LukouAdaper(data,SecondAct.this);
        lv.setAdapter(la);
    }
    private void initView(){
        lv = (ListView) findViewById(R.id.lv);
        spinner = (Spinner) findViewById(R.id.spinner);
        btn_chaxun = (Button) findViewById(R.id.btn_chaxun);
    }

    private void initEvent() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findLukouDeng();
            }
        });
    }

    private void findLukouDeng() {
        System.out.print("123");
        HttpUtil.getPost(LUKOU_DENG, "{\"TrafficLightID\":2}", new HttpListener() {
            @Override
            public void onSuccess(String response) {
                Log.e("test", response);
            }

            @Override
            public void onFail(IOException e) {
                Log.e("test", e.toString());
            }
        });
    }
}
