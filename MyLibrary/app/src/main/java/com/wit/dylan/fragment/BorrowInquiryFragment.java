package com.wit.dylan.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.wit.dylan.activity.R;
import com.wit.dylan.entity.Users;
import com.wit.dylan.utils.HttpConnection;
import com.wit.dylan.utils.ListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BorrowInquiryFragment extends Fragment {
    private TextView text_bookLendNum;
    private ListView booklist;
    private ArrayList<HashMap<String,String>> bList;

    private String url = null;
    private String param = null;

    private  Users student = new Users();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_borrow_inquiry, container, false);

        initView(view);

        bList = new ArrayList<HashMap<String,String>>();
        BookLendHandler handler = new BookLendHandler();
        student = (Users) getArguments().getSerializable("user");
        url = "http://console.wit.edu.cn/schoollib/getHomePage";
        param = "idsNo="+student.getUserNum();
        new HttpConnection(url,param,handler).start();
        return view;
    }


    void initView(View v){
        text_bookLendNum = (TextView) v.findViewById(R.id.text_bookLendNum);
        booklist = (ListView) v.findViewById(R.id.booklist_borrow);

    }

    class BookLendHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            String json = (String) msg.obj;

            if (json.equals("null")){
                text_bookLendNum.setText("当前借阅：0" );
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("多借点书吧！");
                builder.setTitle("嘿~兄台");
                builder.setPositiveButton("哦！",null);
                builder.show();
            }
            else {
                try {
                    JSONArray jsonArray = new JSONArray(json);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        map.put("bookname", jsonObject.getString("title"));
                        map.put("starttime", jsonObject.getString("rentday"));
                        map.put("endtime", jsonObject.getString("returnday"));
                        map.put("status", jsonObject.getString("status"));
                        bList.add(map);
                    }
                    text_bookLendNum.setText("当前借阅：" + bList.size());
                    ListAdapter listAdapter = new ListAdapter(getActivity(), bList);
                    booklist.setAdapter(listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
