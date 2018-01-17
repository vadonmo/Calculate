package com.vadon.calculate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity implements View.OnClickListener {

    private static DecimalFormat df = new DecimalFormat("#######.000");
    private TextView txtNx1;
    private TextView txtNy1;
    private TextView txtNx2;
    private TextView txtNy2;
    private TextView txtNx3;
    private TextView txtNh3;
    private TextView txtNh;
    private TextView txtNhg;
    private TextView txtNh11;
    private TextView txtNh21;
    private TextView txtNh31;
    private TextView txtnθ11;
    private TextView txtnθ21;
    private TextView txtnθ31;
    private TextView txtResult;
    private Button btnCalculate;
    private Button btnClear;

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
        txtNhg = findViewById(R.id.txt_nhg);
        txtNh11 = findViewById(R.id.txt_nh11);
        txtNh21 = findViewById(R.id.txt_nh21);
        txtNh31 = findViewById(R.id.txt_nh31);
        txtnθ11 = findViewById(R.id.txt_nθ11);
        txtnθ21 = findViewById(R.id.txt_nθ21);
        txtnθ31 = findViewById(R.id.txt_nθ31);
        txtResult = findViewById(R.id.txt_result);
        btnCalculate = findViewById(R.id.btnCal);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnCalculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCal:
                String nx1Str = txtNx1.getText().toString().trim();
                String ny1Str = txtNy1.getText().toString().trim();
                String nx2Str = txtNx2.getText().toString().trim();
                String ny2Str = txtNy2.getText().toString().trim();
                String nx3Str = txtNx3.getText().toString().trim();
                String nhStr = txtNh.getText().toString().trim();
                String nhgStr = txtNhg.getText().toString().trim();
                String nh11Str = txtNh11.getText().toString().trim();
                String nh21Str = txtNh21.getText().toString().trim();
                String nh31Str = txtNh31.getText().toString().trim();
                String θ11Str = txtnθ11.getText().toString().trim();
                String θ21Str = txtnθ21.getText().toString().trim();
                String θ31Str = txtnθ31.getText().toString().trim();
                Double nx1 = Str2Double(nx1Str);
                Double ny1 = Str2Double(ny1Str);
                Double nx2 = Str2Double(nx2Str);
                Double ny2 = Str2Double(ny2Str);
                Double nx3 = Str2Double(nx3Str);
                Double nh = Str2Double(nhStr);
                Double nhg = Str2Double(nhgStr);
                Double nh11 = Str2Double(nh11Str);
                Double nh21 = Str2Double(nh21Str);
                Double nh31 = Str2Double(nh31Str);
                Double θ11 = Str2Double(θ11Str);
                Double θ21 = Str2Double(θ21Str);
                Double θ31 = Str2Double(θ31Str);

                Calculate calculate = new Calculate();
                calculate.setNx1(nx1);
                calculate.setNy1(ny1);
                calculate.setNx2(nx2);
                calculate.setNy2(ny2);
                calculate.setNx3(nx3);
                calculate.setNh(nh);
                calculate.setNhg(nhg);
                calculate.setNh11(nh11);
                calculate.setNh21(nh21);
                calculate.setNh31(nh31);
                calculate.setNθ11(θ11);
                calculate.setNθ21(θ21);
                calculate.setNθ31(θ31);
                String result = calculate(calculate);
                txtResult.setText(result);
                break;
            case R.id.btnClear:
                txtResult.setText("");
        }
    }

    private Double Str2Double(String s) {
        if (s.trim().length() == 0) return null;
        return Double.parseDouble(s);
    }

    private static String calculate(Calculate calculate) {
        Double nx1 = calculate.getNx1();
        Double ny1 = calculate.getNy1();
        Double nh11 = calculate.getNh11();
        Double nx2 = calculate.getNx2();
        Double ny2 = calculate.getNy2();
        Double nh21 = calculate.getNh21();
        Double nx3 = calculate.getNx3();
        Double nh = calculate.getNh();
        Double nhg = calculate.getNhg();
        Double nh31 = calculate.getNh31();
        Double nθ11 = calculate.getNθ11();
        Double nθ21 = calculate.getNθ21();
        Double nθ31 = calculate.getNθ31();
        String result = "";
        Double x1, x2, x3, y1, y2, h, x, θ;
        Double a1, a2, x11, x21, θ1, θ2;
        Double s1, s2, s3, y11, y21;
        Double h11, h21, h31, hg, θ21, θ31, θ11;
        Double h111, h211, h311;
        h = nh;
        x3 = nx3;
        if (ny1 > 0) {
            x1 = nx1;
            y1 = ny1;
        } else {
            x1 = nx2;
            y1 = ny2;
        }
        h11 = nh11;
        x2 = nx2;
        y2 = ny2;
        h21 = nh21;
        x3 = nx3;
        h31 = nh31;
        h = nh;
        hg = nhg;
        θ11 = nθ11;
        θ21 = nθ21;
        θ31 = nθ31;

        x = getX(x1, x2, y1, y2);
        //result = "x:" + df.format(x) + "\n";
        θ = getΘ(x1, x2, y1, y2);
        //result += "θ:" + θ + "\n";


        Double ra = Math.toRadians(90 - θ);
        if (x == x3) return "x==x3，不参与计算";
        else if (x > x3) {
            a1 = x - x3 - h * Math.tan(ra);
            a2 = x - x3 + h * Math.tan(ra);
        } else {//(x<x3)
            a1 = x3 - x - h * Math.tan(ra);
            a2 = x3 - x + h * Math.tan(ra);
        }
        x11 = x + h * Math.tan(ra);
        x21 = x - h * Math.tan(ra);


        θ1 = Math.toDegrees(Math.atan(h / a1));
        θ2 = Math.toDegrees(Math.atan(h / a2));
        s2 = h / Math.sin(Math.toRadians(θ1));
        s3 = h / Math.sin(Math.toRadians(θ2));
        String d1 = toDegreesStr(θ1);
        String d2 = toDegreesStr(θ2);
        s1 = Math.abs(x - x3);
        y11 = y21 = h;
        Double rad1 = null, rad2 = null;
        if (x3 > x) {
            rad1 = 180 - θ1;
            rad2 = 180 + θ2;
        } else if (x3 < x) {
            rad1 = θ1;
            rad2 = θ2;
        }
        String d11 = toDegreesStr(rad1);
        String d21 = toDegreesStr(rad2);
        String temp = "";
        if (θ11 != null && θ21 != null && θ31 != null) {
            Double ra11 = Math.toRadians(90 - θ11);
            Double ra21 = Math.toRadians(90 - θ21);
            Double ra31 = Math.toRadians(90 - θ31);
            h111 = s2 * Math.tan(ra11) + h11 + hg;
            h211 = s3 * Math.tan(ra21) + h21 + hg;
            h311 = s1 * Math.tan(ra31) + h31 + hg;

            temp += "h111:" + df.format(h111) + "\n";
            temp += "h211:" + df.format(h211) + "\n";
            temp += "h311:" + df.format(h311) + "\n";
        }

//        result += "a1:" + df.format(a1) + "\n";
//        result += "a2:" + df.format(a2) + "\n";
//
        result += "θ1:" + d1 + "\n";
        result += "转到θ1时的转角:" + d11 + "\n";
        result += "θ2:" + d2 + "\n";
        result += "转到θ2时的转角:" + d21 + "\n";
        result += "x11:" + df.format(x11) + "\n";
        result += "y11:" + df.format(y11) + "\n";
        result += "x21:" + df.format(x21) + "\n";
        result += "y21:" + df.format(y21) + "\n";
        result += temp;
        return result;
    }

    private static String toDegreesStr(double value) {
        int d = (int) value;// 度
        int m = (int) ((value - d) * 60);
        int s = (int) ((value - d) * 3600 % 60);
        return "" + d + "°" + m + "′" + s + "″";
    }

    private static Double getX(Double x1, Double x2, Double y1, Double y2) {
        Double x = 0d;
        x = (x1 - y1) * (x1 - x2) / (y1 - y2);
        return x;
    }

    private static Double getΘ(Double x1, Double x2, Double y1, Double y2) {
        Double x = 0d;
        x = Math.toDegrees(Math.atan(Math.abs((y1 - y2) / (x1 - x2))));
        return x;
    }

    class Calculate {
        Double nx1;
        Double ny1;
        Double nx2;
        Double ny2;
        Double nx3;
        Double nh;
        Double nhg;
        Double nh11;
        Double nh21;
        Double nh31;
        Double nθ11;
        Double nθ21;
        Double nθ31;

        public Double getNx1() {
            return nx1;
        }

        public void setNx1(Double nx1) {
            this.nx1 = nx1;
        }

        public Double getNy1() {
            return ny1;
        }

        public void setNy1(Double ny1) {
            this.ny1 = ny1;
        }

        public Double getNx2() {
            return nx2;
        }

        public void setNx2(Double nx2) {
            this.nx2 = nx2;
        }

        public Double getNy2() {
            return ny2;
        }

        public void setNy2(Double ny2) {
            this.ny2 = ny2;
        }


        public Double getNx3() {
            return nx3;
        }

        public void setNx3(Double nx3) {
            this.nx3 = nx3;
        }

        public Double getNh() {
            return nh;
        }

        public void setNh(Double nh) {
            this.nh = nh;
        }

        public Double getNhg() {
            return nhg;
        }

        public void setNhg(Double nhg) {
            this.nhg = nhg;
        }

        public Double getNθ11() {
            return nθ11;
        }

        public void setNθ11(Double nθ11) {
            this.nθ11 = nθ11;
        }

        public Double getNθ21() {
            return nθ21;
        }

        public void setNθ21(Double nθ21) {
            this.nθ21 = nθ21;
        }

        public Double getNθ31() {
            return nθ31;
        }

        public void setNθ31(Double nθ31) {
            this.nθ31 = nθ31;
        }

        public Double getNh11() {
            return nh11;
        }

        public void setNh11(Double nh11) {
            this.nh11 = nh11;
        }

        public Double getNh21() {
            return nh21;
        }

        public void setNh21(Double nh21) {
            this.nh21 = nh21;
        }

        public Double getNh31() {
            return nh31;
        }

        public void setNh31(Double nh31) {
            this.nh31 = nh31;
        }
    }

}
