package com.wit.dylan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wit.dylan.customview.SeatTable;
import com.wit.dylan.entity.OrderDetails;

public class SelectSeat_SelfRoomSeatOrder extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView textView;
    private SeatTable seatTableView;
    private Button confirmSelect;
    private OrderDetails orders = new OrderDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat__self_room_seat_order);


        //初始化界面
        initView();

    }


    //初始化
    public void initView() {

        toolbar = (Toolbar) findViewById(R.id.SelfRoom_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView = (TextView) findViewById(R.id.text_selfroomname);
        textView.setText(getIntent().getStringExtra("selfroomname"));

        orders = (OrderDetails) getIntent().getSerializableExtra("order");

        seatTableView = (SeatTable) findViewById(R.id.seatView);
        //设置最多选中
        seatTableView.setMaxSelected(1);
        //点击监听
        seatTableView.setSeatChecker(new SeatTable.SeatChecker() {

            @Override
            public boolean isValidSeat(int row, int column) {
                if (column == 2) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean isSold(int row, int column) {

                return false;
            }

            @Override
            public void checked(int row, int column) {
                orders.setSeatId((row + 1) + "-" + (column<2?column+1:column));
                Toast.makeText(SelectSeat_SelfRoomSeatOrder.this, "已选择" + (row + 1) + "-" + (column<2?column+1:column), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void unCheck(int row, int column) {
                Toast.makeText(SelectSeat_SelfRoomSeatOrder.this, "取消选择", Toast.LENGTH_SHORT).show();
            }

            @Override
            public String[] checkedSeatTxt(int row, int column) {
                return null;
            }

        });
        //设置行列数；
        seatTableView.setData(10, 15);

        confirmSelect = (Button) findViewById(R.id.confirm_selectseat);
        confirmSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectSeat_SelfRoomSeatOrder.this,TimeToSign_SelfRoomSeatOrder.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("order",orders);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }




    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toolbar.setTitle("选择座位");
    }


}
