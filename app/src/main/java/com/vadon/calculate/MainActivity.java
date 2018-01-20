package com.vadon.calculate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private TextView txtNx1;
    private TextView txtNy1;
    private TextView txtNx2;
    private TextView txtNy2;
    private TextView txtNx3;
    private TextView txtNh;
    private TextView txtNh3;
    private TextView txtNhg;
    private TextView txtnθ11;
    private TextView txtnθ21;
    private TextView txtnθ31;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNx1 = findViewById(R.id.txt_nx1);
        txtNy1 = findViewById(R.id.txt_ny1);
        txtNx2 = findViewById(R.id.txt_nx2);
        txtNy2 = findViewById(R.id.txt_ny2);
        txtNx3 = findViewById(R.id.txt_nx3);
        txtNh = findViewById(R.id.txt_nh);
        txtNh3 = findViewById(R.id.txt_nh3);
        txtNhg = findViewById(R.id.txt_nhg);
        txtnθ11 = findViewById(R.id.txt_nθ11);
        txtnθ21 = findViewById(R.id.txt_nθ21);
        txtnθ31 = findViewById(R.id.txt_nθ31);
        txtResult = findViewById(R.id.txt_result);
        Button btnCalculate = findViewById(R.id.btnCal);
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCal:
                InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                String nx1Str = txtNx1.getText().toString().trim();
                String ny1Str = txtNy1.getText().toString().trim();
                String nx2Str = txtNx2.getText().toString().trim();
                String ny2Str = txtNy2.getText().toString().trim();
                String nx3Str = txtNx3.getText().toString().trim();
                String nhStr = txtNh.getText().toString().trim();
                String nh3Str = txtNh3.getText().toString().trim();
                String nhgStr = txtNhg.getText().toString().trim();
                String θ11Str = txtnθ11.getText().toString().trim();
                String θ21Str = txtnθ21.getText().toString().trim();
                String θ31Str = txtnθ31.getText().toString().trim();
                Double nx1 = Utils.Str2Double(nx1Str);
                Double ny1 = Utils.Str2Double(ny1Str);
                Double nx2 = Utils.Str2Double(nx2Str);
                Double ny2 = Utils.Str2Double(ny2Str);
                Double nx3 = Utils.Str2Double(nx3Str);
                Double nh = Utils.Str2Double(nhStr);
                Double nh3 = Utils.Str2Double(nh3Str);
                Double nhg = Utils.Str2Double(nhgStr);
                Double θ11 = Utils.Str2Double(θ11Str);
                Double θ21 = Utils.Str2Double(θ21Str);
                Double θ31 = Utils.Str2Double(θ31Str);
                Calculate calculate = new Calculate();
                calculate.setNx1(nx1);
                calculate.setNy1(ny1);
                calculate.setNx2(nx2);
                calculate.setNy2(ny2);
                calculate.setNx3(nx3);
                calculate.setNh(nh);
                calculate.setNhg(nhg);
                calculate.setNh3(nh3);
                calculate.setNθ11(θ11);
                calculate.setNθ21(θ21);
                calculate.setNθ31(θ31);
                String result = Utils.getResult(calculate);
                txtResult.setText(result);
                break;
            case R.id.btnClear:
                txtResult.setText("");
                txtNx1.setText("");
                txtNy1.setText("");
                txtNx2.setText("");
                txtNy2.setText("");
                txtNx3.setText("");
                txtNh.setText("");
                txtNh3.setText("");
                txtNhg.setText("");
                txtnθ11.setText("");
                txtnθ21.setText("");
                txtnθ31.setText("");
        }
    }


}
