package com.wit.dylan.fragment;


import android.app.Fragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.wit.dylan.activity.R;
import com.wit.dylan.activity.SelectSeat_SelfRoomSeatOrder;
import com.wit.dylan.entity.OrderDetails;
import com.wit.dylan.entity.Times;


public class SelfRoomSeatOrder extends Fragment {
    private EditText edt_startTime;
    private EditText edt_endTime;
    private Button btn_Date_Today;
    private Button btn_Date_Tomorrow;
    private Button btn_ToSelectSeat;
    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private String[] m={"全部自习室","附一楼自习室","主一楼自习室","附四楼小自习室","附四楼大自习室"};

    private Times time;
    private OrderDetails orders = new OrderDetails();

    private String selfRoomName="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_self_room_seat_order, container, false);

        initView(view);

        edt_startTime.setOnFocusChangeListener(new EditOnClickListerner());
        edt_endTime.setOnFocusChangeListener(new EditOnClickListerner());

        btn_Date_Today.setOnClickListener(new ButtonOnClickListerner(time.getToday()));
        btn_Date_Tomorrow.setOnClickListener(new ButtonOnClickListerner(time.getTomorrow()));
        btn_ToSelectSeat.setOnClickListener(new ButtonOnClickListerner(null));

        adapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,m);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    selfRoomName = "当前所选自习室："+spinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //设置默认值
        spinner.setVisibility(View.VISIBLE);



        return view;
    }


    public void initView(View view) {
        btn_Date_Today = (Button) view.findViewById(R.id.btn_date_today);
        btn_Date_Tomorrow = (Button) view.findViewById(R.id.btn_date_tomorrow);
        btn_ToSelectSeat = (Button) view.findViewById(R.id.btn_toSelectSeat);
        edt_startTime = (EditText) view.findViewById(R.id.edt_startTime);
        edt_endTime = (EditText) view.findViewById(R.id.edt_EndTime);
        spinner = (Spinner) view.findViewById(R.id.spinner_selfroom);
        time = new Times();


        btn_Date_Today.setText(time.getTodayMonth_Day());
        btn_Date_Tomorrow.setText(time.getTomorrowMonth_Day());

        edt_startTime.setText(time.getToday() + " 7:00");
        edt_endTime.setText(time.getTomorrow() + " 22:00");
    }


    //EditText点击监听
    class EditOnClickListerner implements View.OnFocusChangeListener {

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(v.getContext(),
                        timeSet, time.getmHour(),
                        time.getmMinute(), true);
                timePickerDialog.show();
            }
        }

        TimePickerDialog.OnTimeSetListener timeSet = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                String str = ((hourOfDay < 10) ? "0" + hourOfDay : hourOfDay) + ":" + ((minute < 10) ? "0" + minute : minute);

                if (edt_startTime.isFocused()) {
                    edt_startTime.setText(time.getToday() + " " + str);
                }
                if (edt_endTime.isFocused()) {
                    edt_endTime.setText(time.getToday() + " " + str);
                }

            }
        };
    }


    //Button点击监听
    class ButtonOnClickListerner implements View.OnClickListener {

        private String date;

        public ButtonOnClickListerner(String date) {
            this.date = date;
        }

        @Override
        public void onClick(View v) {

            orders.setStartDate(edt_startTime.getText().toString());
            orders.setEndDate(edt_endTime.getText().toString());
            switch (v.getId()) {
                case R.id.btn_toSelectSeat:
                    Intent intent = new Intent(getActivity(), SelectSeat_SelfRoomSeatOrder.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("selfroomname",selfRoomName);
                    bundle.putSerializable("order",orders);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
                default:
                    edt_startTime.setText(date + " 07:00");
                    edt_endTime.setText(date + " 22:00");
                    break;
            }
        }
    }





}

