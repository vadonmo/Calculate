package com.vadon.calculate;

import java.text.DecimalFormat;

import static java.lang.String.format;

/**
 * 算法工具类
 * Created by vadon on 2018/1/20.
 */

class Utils {
    private static final DecimalFormat df = new DecimalFormat("#######.000");

    static String getResult(Calculate calculate) {
        Double nx1 = calculate.getNx1();
        Double ny1 = calculate.getNy1();
        Double nx2 = calculate.getNx2();
        Double ny2 = calculate.getNy2();
        Double nx3 = calculate.getNx3();
        Double nh = calculate.getNh();
        Double nh3 = calculate.getNh3();
        Double nhg = calculate.getNhg();
        Double nz11 = calculate.getNθ11();
        Double nz21 = calculate.getNθ21();
        Double nz31 = calculate.getNθ31();
        String result = "";
        String z1Str = "", z2Str = "";
        Double x1, x2, x3, y1, y2, h, h3, x, z;
        Double a1 = null, a2 = null, x11, x21, z1 = null, z2 = null;
        Double s1, s2, s3;
        Double hg, z21, z31, z11;

        double leftH, rightH, centerH;

        if (nx1 == null) return result + "nx1不能为空";
        if (nx2 == null) return result + "nx2不能为空";
        if (ny1 == null) return result + "ny1不能为空";
        if (ny2 == null) return result + "ny2不能为空";
        if (ny1 > ny2) {
            x1 = nx1;
            y1 = ny1;
            x2 = nx2;
            y2 = ny2;
        } else if (ny1 > ny2) {
            x1 = nx2;
            y1 = ny2;
            x2 = nx1;
            y2 = ny1;
        } else {
            return "平行";
        }
        x = getX(x1, x2, y1, y2);
        z = getΘ(x1, x2, y1, y2);
        result += format("x：%s\n", df.format(x));
        result += format("θ：%s\n", toDegreesStr(z));
        if (nx3 == null) return result + "nx3不能为空";
        if (nh == null) return result +"nh不能为空";
        x3 = nx3;
        h = nh;

        double hTanRa = h * Math.tan(Math.toRadians(90 - z));

        if (x > x3) {
            if (z < 90) {
                x21 = x - hTanRa;
                a1 = x - x3 + hTanRa;
                if (x3 < x21) {
                    a2 = x - x3 - hTanRa;
                } else {
                    z2 = 90 + Math.atan((x3 - x21) / h);
                    z2Str = format("θ2：%s\n", toDegreesStr(z2));
                }
            } else if (z == 90) {
                z1 = Math.atan(h / (x - x3));
                z2 = z1;
            } else {
                x11 = x - hTanRa;
                if (x3 < x11) {
                    a1 = x - x3 - hTanRa;
                    a2 = x - x3 + hTanRa;
                } else {
                    z1 = 90 + Math.atan((x3 - x11) / h);
                    a2 = x - hTanRa;
                    z1Str = format("θ1：%s\n", toDegreesStr(z1));
                }
            }
        } else if (x < x3) {
            if (z < 90) {
                x11 = x + hTanRa;
                a2 = x3 - x + hTanRa;
                if (x3 > x11) {
                    a1 = x3 - x - hTanRa;
                } else {
                    z1 = 90 + Math.atan((x11 - x3) / h);
                    z1Str = format("θ1：%s\n", toDegreesStr(z1));
                }
            } else if (z == 90) {
                z1 = Math.atan(h / (x3 - x));
                z2 = z1;
            } else {
                x21 = x + hTanRa;
                if (x3 > x21) {
                    a1 = x3 - x + hTanRa;
                    a2 = x3 - x - hTanRa;
                } else {
                    z2 = 90 + Math.atan((x21 - x3) / h);
                    a1 = x3 - x + hTanRa;
                    z2Str = format("θ2：%s\n", toDegreesStr(z2));
                }
            }
        } else {
            return "x = x3：error\n";
        }
        if (a1 != null) {
            z1 = Math.atan(h / a1);
            z1Str = format("θ1：%s\n", toDegreesStr(z1));
        }
        if (a2 != null) {
            z2 = Math.atan(h / a2);
            z2Str = format("θ2：%s\n", toDegreesStr(z2));
        }
        result += z1Str;
        result += z2Str;
        s3 = Math.abs(x - x3);
        s2 = h / Math.sin(z2);
        s1 = h / Math.sin(z1);
        result += format("s1：%.3f\n", s1);
        result += format("s2：%.3f\n", s2);
        result += format("s3：%.3f\n", s3);
        if (x3 > x) {
            result += format("转到左边线角度：%s\n", toDegreesStr(180 + z1));
            result += format("转到右边线角度：%s\n", toDegreesStr(180 - z2));
        } else {
            result += format("转到左边线角度：%s\n", toDegreesStr(360 - z1));
            result += format("转到右边线角度：%s\n", toDegreesStr(z2));
        }
        if (nh3 == null) return result + "nh3不能为空";
        if (nhg == null) return result + "仪器高不能为空";
        if (nz11 == null) return result + "左边线天顶角不能为空";
        h3 = nh3;
        hg = nhg;
        z11 = nz11;
        double tempH = h3 + hg;
        leftH = s1 * Math.tan(Math.toRadians(90 - z11)) + tempH;
        result += format("左边线点高程：%.3f\n", leftH);
        if (nz21 == null) return result += "右边线天顶角不能为空";
        z21 = nz21;
        rightH = s2 * Math.tan(Math.toRadians(90 - z21)) + tempH;
        result += format("右边线点高程：%.3f\n", rightH);
        if (nz31 == null) return result += "中间线天顶角不能为空";
        z31 = nz31;
        centerH = s3 * Math.tan(Math.toRadians(90 - z31)) + tempH;
        result += format("中间线点高程：%.3f\n", centerH);


        return result;
    }


    static Double Str2Double(String s) {
        if (s.trim().length() == 0) return null;
        return Double.parseDouble(s);
    }

    private static String toDegreesStr(double value) {
        int d = (int) value;// 度
        int m = (int) ((value - d) * 60);
        int s = (int) ((value - d) * 3600 % 60);
        return "" + d + "°" + m + "′" + s + "″";
    }

    private static Double getX(Double x1, Double x2, Double y1, Double y2) {
        return (x1 - y1) * (x1 - x2) / (y1 - y2);
    }

    private static Double getΘ(Double x1, Double x2, Double y1, Double y2) {
        return Math.toDegrees(Math.atan(Math.abs((y1 - y2) / (x1 - x2))));
    }
}
