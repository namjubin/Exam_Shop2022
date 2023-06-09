package com.example.exam_shop2022;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_product_main;
    EditText edit_count;
    TextView txt_price, txt_delivery, txt_pay;
    CheckBox chk_agree;

    int val_price;                  // 제품의 총 가격(배송비 미포함)
    int val_delivery;               // 배송비 ( 제품 총 가격이 10000원 이상이면 0원, 미만이면 2500원)
    int val_pay;                    // 총 결제 금액
    int selected_product=2000;      // 선택한 제품 한개의 가격
    int selected_count=1;             // 선택한 수량

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img_product_main = findViewById(R.id.img_product_main);
        edit_count = findViewById(R.id.edit_count);
        txt_price = findViewById(R.id.txt_price);
        txt_delivery = findViewById(R.id.txt_delivery);
        txt_pay = findViewById(R.id.txt_pay);
        chk_agree = findViewById(R.id.chk_agree);

        findViewById(R.id.radio1).setOnClickListener(this);
        findViewById(R.id.radio2).setOnClickListener(this);
        findViewById(R.id.radio3).setOnClickListener(this);
        findViewById(R.id.radio4).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_pay).setOnClickListener(this);

        findViewById(R.id.radio1).setSelected(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.radio1:
                selected_product = 2000;
                img_product_main.setImageResource(R.drawable.coffee1);
                sum_total();
                break;
            case R.id.radio2:
                selected_product = 3000;
                img_product_main.setImageResource(R.drawable.coffee2);
                sum_total();
                break;
            case R.id.radio3:
                selected_product = 4000;
                img_product_main.setImageResource(R.drawable.coffee3);
                sum_total();
                break;
            case R.id.radio4:
                selected_product = 5000;
                img_product_main.setImageResource(R.drawable.coffee4);
                sum_total();
                break;
            case R.id.btn_minus:
                if(selected_count<=1){
                    Toast.makeText(this, "최소 수량입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    --selected_count;
                    edit_count.setText(String.valueOf(selected_count));
                    sum_total();
                }
                break;
            case R.id.btn_plus:
                if(selected_count>=5){
                    Toast.makeText(this, "최대 수량입니다.", Toast.LENGTH_SHORT).show();
                }
                else{
                    ++selected_count;
                    edit_count.setText(String.valueOf(selected_count));
                    sum_total();
                }
                break;
            case R.id.btn_pay:
                if(chk_agree.isChecked()){
                    Toast.makeText(this, String.valueOf(val_pay)+"원을 결제하겠습니다.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, SubActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "결제 동의 버튼을 체크해주세요", Toast.LENGTH_SHORT).show();
                }
        }
    }

    private void sum_total() {
        val_price = selected_product * selected_count;
        txt_price.setText(String.valueOf(val_price)+"원");

        if(val_price<10000){
            val_delivery = 2500;
            txt_delivery.setText(String.valueOf(val_delivery)+"원");
        }
        else{
            val_delivery = 0;
            txt_delivery.setText("무료");
        }

        val_pay = val_price + val_delivery;
        txt_pay.setText(String.valueOf(val_pay)+"원");
    }

}