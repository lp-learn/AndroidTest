package adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bean.Lukou;
import dm.ohh.okman.omg.R;

/**
 * Created by OKMAN on 2017/5/9.
 */

public class LukouAdaper extends BaseAdapter {

    private ArrayList<Lukou> mData;
    private Context context;
    public LukouAdaper(ArrayList<Lukou> data , Context context) {
        this.mData = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        systemService.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        TextView tv_hone = (TextView) convertView.findViewById(R.id.tv_hong);
        TextView tv_lv = (TextView) convertView.findViewById(R.id.tv_lv);
        TextView tv_huang = (TextView) convertView.findViewById(R.id.tv_huang);
        TextView tv_lukou = (TextView) convertView.findViewById(R.id.tv_lukou);
        if (position == 0){
            tv_hone.setText("红灯时长/s");
            tv_lv.setText("绿灯时长/s");
            tv_huang.setText("黄灯时长/s");
            tv_lukou.setText("路口");
        }else{
            tv_hone.setText(mData.get(position-1).getRedTime());
            tv_lv.setText(mData.get(position-1).getGreenTime());
            tv_huang.setText(mData.get(position-1).getYellowTime());
            tv_lukou.setText(mData.get(position-1).getLukouID());
        }
        tv_hone.setWidth((int) (width*0.2));
        tv_lv.setWidth((int) (width*0.2));
        tv_huang.setWidth((int) (width*0.2));
        tv_lukou.setWidth((int) (width*0.2));
        return convertView;
    }
}
