package com.wit.dylan.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wit.dylan.activity.R;
import com.wit.dylan.activity.ShowBookDetails;


public class BookCheckFragment extends Fragment {
    private EditText edt_book_name;
    private RadioGroup radioGroup;
    private Button btn_search;
    private String url = null;
    private String keyword = "keyword";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_check, container, false);

        //初始化控件
        initView(view);

        //设置监听
        setOnClick();

        return view;
    }

    //初始化控件
    public void initView(View view) {

        edt_book_name = (EditText) view.findViewById(R.id.edt_bookname);
        btn_search = (Button) view.findViewById(R.id.btn_book_search);
        radioGroup = (RadioGroup) view.findViewById(R.id.search_choice);
    }

    //设置监听
    public void setOnClick() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbtn_keyword:
                        keyword = "keyword";
                        break;
                    case R.id.rbtn_author:
                        keyword = "author";
                        break;
                    case R.id.rbtn_title:
                        keyword = "title";
                        break;
                    default:
                        break;
                }
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_book_name.getText()) || keyword.equals("")) {
                    Toast.makeText(getActivity(), "搜索内容及选项不能为空", Toast.LENGTH_SHORT).show();
                } else {


                    System.out.println("-------"+keyword);

                    url = "http://218.199.187.115:8080/search?kw=" + edt_book_name.getText().toString() + "&xc=4&searchtype=" + keyword;
                    Intent intent = new Intent(getActivity(), ShowBookDetails.class);
                    intent.putExtra("url", url);
                    startActivity(intent);
                }
            }
        });
    }
}
