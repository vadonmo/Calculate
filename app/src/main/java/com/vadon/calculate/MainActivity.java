package com.vadon.calculate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText txtNx1;
    private EditText txtNy1;
    private EditText txtNx2;
    private EditText txtNy2;
    private EditText txtNx3;
    private EditText txtNh;
    private EditText txtNh3;
    private EditText txtNhg;
    private EditText txtnθ11;
    private EditText txtN11Min;
    private EditText txtN11Sec;
    private EditText txtnθ21;
    private EditText txtN21Min;
    private EditText txtN21Sec;
    private EditText txtnθ31;
    private EditText txtN31Min;
    private EditText txtN31Sec;
    private TextView txtLeftR;
    private TextView txtRightR;
    private TextView txtLeftH;
    private TextView txtRightH;
    private TextView txtCenterH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtNx1 = findViewById(R.id.edit_nx1);
        txtNy1 = findViewById(R.id.edit_ny1);
        txtNx2 = findViewById(R.id.edit_nx2);
        txtNy2 = findViewById(R.id.edit_ny2);
        txtNx3 = findViewById(R.id.edit_nx3);
        txtNh = findViewById(R.id.edit_nh);
        txtNh3 = findViewById(R.id.edit_nh3);
        txtNhg = findViewById(R.id.edit_nhg);
        txtnθ11 = findViewById(R.id.edit_nθ11);
        txtN11Min = findViewById(R.id.edit_nθ11_min);
        txtN11Sec = findViewById(R.id.edit_nθ11_sec);
        txtnθ21 = findViewById(R.id.edit_nθ21);
        txtN21Min = findViewById(R.id.edit_nθ21_min);
        txtN21Sec = findViewById(R.id.edit_nθ21_sec);
        txtN31Min = findViewById(R.id.edit_nθ31_min);
        txtN31Sec = findViewById(R.id.edit_nθ31_sec);
        txtnθ31 = findViewById(R.id.edit_nθ31);
        txtLeftR = findViewById(R.id.leftR);
        txtRightR = findViewById(R.id.rightR);
        txtLeftH = findViewById(R.id.leftH);
        txtRightH = findViewById(R.id.rightH);
        txtCenterH = findViewById(R.id.centerH);
        Button btnCalculate = findViewById(R.id.btnCal);
        Button btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
    }

    //隐藏虚拟键盘
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCal:
                HideKeyboard(view);
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
                String min11Str = txtN11Min.getText().toString().trim();
                String sec11Str = txtN11Sec.getText().toString().trim();
                String min21Str = txtN21Min.getText().toString().trim();
                String sec21Str = txtN21Sec.getText().toString().trim();
                String min31Str = txtN31Min.getText().toString().trim();
                String sec31Str = txtN31Sec.getText().toString().trim();
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
                Double min11 = Utils.Str2Double(min11Str);
                Double min21 = Utils.Str2Double(min21Str);
                Double min31 = Utils.Str2Double(min31Str);
                Double sec11 = Utils.Str2Double(sec11Str);
                Double sec21 = Utils.Str2Double(sec21Str);
                Double sec31 = Utils.Str2Double(sec31Str);
                Calculate calculate = new Calculate();
                calculate.setNx1(nx1);
                calculate.setNy1(ny1);
                calculate.setNx2(nx2);
                calculate.setNy2(ny2);
                calculate.setNx3(nx3);
                calculate.setNh(nh);
                calculate.setNhg(nhg);
                calculate.setNh3(nh3);
                calculate.setNθ11(Utils.toDegrees(θ11, min11, sec11));
                calculate.setNθ21(Utils.toDegrees(θ21, min21, sec21));
                calculate.setNθ31(Utils.toDegrees(θ31, min31, sec31));
                Result result = Utils.getResult(calculate);
                String leftR = result.getLeftR();
                String rightR = result.getRightR();
                String leftH = result.getLeftH();
                String rightH = result.getRightH();
                String centerH = result.getCenterH();
                if (result.getMsg() != null) {
                    Toast.makeText(this, result.getMsg(), Toast.LENGTH_LONG).show();
                    return;
                }
                txtLeftR.setText(leftR == null ? "" : leftR);
                txtLeftH.setText(leftH == null ? "" : leftH);
                txtRightR.setText(rightR == null ? "" : rightR);
                txtRightH.setText(rightH == null ? "" : rightH);
                txtCenterH.setText(centerH == null ? "" : centerH);
                break;
            case R.id.btnClear:
                txtLeftR.setText("");
                txtLeftH.setText("");
                txtRightR.setText("");
                txtRightH.setText("");
                txtCenterH.setText("");
                txtNx1.setText("");
                txtNy1.setText("");
                txtNx2.setText("");
                txtNy2.setText("");
                txtNx3.setText("");
                txtNh.setText("");
                txtNh3.setText("");
                txtNhg.setText("");
                txtnθ11.setText("");
                txtN11Sec.setText("");
                txtN21Sec.setText("");
                txtN31Sec.setText("");
                txtN11Min.setText("");
                txtN21Min.setText("");
                txtN31Min.setText("");
                txtnθ21.setText("");
                txtnθ31.setText("");
        }
    }


}
