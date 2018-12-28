package com.wit.dylan.fragment;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wit.dylan.activity.R;
import com.wit.dylan.entity.Users;


public class PersonDataFragment extends Fragment {
    private TextView text_name;
    private TextView text_userNUm;
    private TextView text_school;

    private Users users = new Users();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view  = inflater.inflate(R.layout.fragment_person_data,container,false);
        text_name = (TextView) view.findViewById(R.id.text_name);
        text_userNUm = (TextView) view.findViewById(R.id.text_userNum);
        text_school = (TextView) view.findViewById(R.id.text_school);

        users = (Users) getArguments().getSerializable("user");

        text_name.setText(users.getUserName());
        text_userNUm.setText(users.getUserNum());
        text_school.setText(users.getSchoolName());
        return view;
    }


}



