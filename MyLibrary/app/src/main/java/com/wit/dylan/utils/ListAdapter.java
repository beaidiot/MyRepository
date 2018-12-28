package com.wit.dylan.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wit.dylan.activity.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dylan on 2017/5/28.
 */
public class ListAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, String>> list;
    private LayoutInflater inflater;


    public ListAdapter(Context context, ArrayList<HashMap<String, String>> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();
        if (convertView ==null) {
            convertView = inflater.inflate(R.layout.fragment_borrow_item, null);
            holder.text_bookimg = (TextView) convertView.findViewById(R.id.img_text);
            holder.text_bookName = (TextView) convertView.findViewById(R.id.text_imgbookName);
            holder.text_starttime = (TextView) convertView.findViewById(R.id.text_bookStartTime);
            holder.text_endtime = (TextView) convertView.findViewById(R.id.text_bookEndTime);
            holder.text_isReturn = (TextView) convertView.findViewById(R.id.text_bookIsReturn);
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }
        HashMap<String,String> map = list.get(position);

        holder.text_bookimg.setText(map.get("bookname"));
        holder.text_bookName.setText(map.get("bookname"));
        holder.text_starttime.setText("借阅时间："+map.get("starttime"));
        holder.text_endtime.setText("归还时间："+map.get("endtime"));
        if (map.get("status").equals("1"))
        {
            holder.text_isReturn.setText("是否归还：否");
        }else {
            holder.text_isReturn.setText("是否归还：是");

        }

        return convertView;
    }

    public class Holder {
        TextView text_bookimg;
        TextView text_bookName;
        TextView text_starttime;
        TextView text_endtime;
        TextView text_isReturn;
    }
}
