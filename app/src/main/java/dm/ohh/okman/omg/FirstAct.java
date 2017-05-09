package dm.ohh.okman.omg;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import util.HttpListener;
import util.HttpUtil;

public class FirstAct extends AppCompatActivity {

    private String CAR_BANLANCE = "http://192.168.252.8:8080/transportservice/type/jason/action/GetCarAccountBalance.do";
    private String CAR_RECHANGE = "http://192.168.252.8:8080/transportservice/type/jason/action/SetCarAccountRecharge.do";
    private Spinner sp1;
    private Button bt_chaxun;
    private Spinner sp2;
    private EditText et_jine;
    private Button bt_chongzhi;

    private String sp1_val = "1";
    private String sp2_val = "1";

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
                String s = adapterView.getSelectedItem().toString();
                Log.e("test", s);
                sp1_val = s;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s = adapterView.getSelectedItem().toString();
                Log.e("test", s);
                sp2_val = s;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        bt_chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int carid = Integer.valueOf(sp2_val);
                int jine = 0;
                if (!et_jine.getText().toString().equals("")){
                    jine = Integer.valueOf(et_jine.getText().toString());
                }else{
                    et_jine.setError("请输入金额");
                }
                HttpUtil.getPost(CAR_RECHANGE, "{\"CarId\":" + carid + ",\"Money\":" + jine + "}", new HttpListener() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            String resul = jo.getString("serverinfo");
                            JSONObject jo2 = new JSONObject(resul);
                            final String result = jo2.getString("result");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(FirstAct.this);
                                    builder.setTitle("充值结果");
                                    builder.setMessage("小车" + carid + ":充值" + result);
                                    builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    builder.show();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(IOException e) {

                    }
                });
            }
        });
        bt_chaxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int carid = Integer.valueOf(sp1_val);
                HttpUtil.getPost(CAR_BANLANCE, "{\"CarId\":+"+carid+"}", new HttpListener() {
                    @Override
                    public void onSuccess(String response) {
                        try {
                            JSONObject jo = new JSONObject(response);
                            String resul = jo.getString("serverinfo");
                            JSONObject jo2 = new JSONObject(resul);
                            final String result = jo2.getString("Balance");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    final AlertDialog.Builder builder = new AlertDialog.Builder(FirstAct.this);
                                    builder.setTitle("查询结果");
                                    builder.setMessage("小车"+carid+":"+result);
                                    builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                                    builder.show();
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFail(IOException e) {
                        Log.e("FirstAct", e.toString());
                    }
                });
            }
        });
    }
}
