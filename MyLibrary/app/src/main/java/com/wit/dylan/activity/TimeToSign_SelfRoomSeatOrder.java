package com.wit.dylan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.wit.dylan.entity.OrderDetails;

public class TimeToSign_SelfRoomSeatOrder extends AppCompatActivity {

    private TextView txt_seatId;
    private TextView txt_startTime;
    private TextView txt_endTime;
    private Toolbar toolbar;
    private OrderDetails orders = new OrderDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_to_sign__self_room_seat_order);

        //初始化界面
        initView();

        orders = (OrderDetails) getIntent().getSerializableExtra("order");

        txt_seatId.setText("座位号：" + orders.getSeatId());
        txt_startTime.setText(orders.getStartDate());
        txt_endTime.setText(orders.getEndDate());

    }

    public void initView() {
        txt_seatId = (TextView) findViewById(R.id.text_ordered_roomSeat_Id);
        txt_startTime = (TextView) findViewById(R.id.txt_starttime);
        txt_endTime = (TextView) findViewById(R.id.txt_endtime);
        toolbar = (Toolbar) findViewById(R.id.SelfRoom_timeToSign_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setTitle("预约记录");
    }
}
