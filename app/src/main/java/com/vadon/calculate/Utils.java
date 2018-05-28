package com.vadon.calculate;

import java.text.DecimalFormat;

import static java.lang.String.format;

/**
 * 算法工具类
 * Created by vadon on 2018/1/20.
 */

class Utils {
    private static final DecimalFormat df = new DecimalFormat("#######.000");

    static Result getResult(Calculate calculate) {
        Result result1 = new Result();
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

        if (nx1 == null) {
            result1.setMsg("nx1不能为空");
            return result1;
        }
        if (nx2 == null) {
            result1.setMsg("nx2不能为空");
            return result1;
        }
        if (ny1 == null) {
            result1.setMsg("ny1不能为空");
            return result1;
        }
        if (ny2 == null) {
            result1.setMsg("ny2不能为空");
            return result1;
        }
        if (ny1 > ny2) {
            x1 = nx1;
            y1 = ny1;
            x2 = nx2;
            y2 = ny2;
        } else if (ny1 < ny2) {
            x1 = nx2;
            y1 = ny2;
            x2 = nx1;
            y2 = ny1;
        } else {
            result1.setMsg("平行");
            return result1;
        }
        x = getX(x1, x2, y1, y2);
        z = getΘ(x1, x2, y1, y2);
        result += format("x：%s\n", df.format(x));
        result += format("θ：%s\n", toDegreesStr(z));
        if (nx3 == null) {
            result1.setMsg("nx3不能为空");
            return result1;
        }
        if (nh == null) {
            result1.setMsg("边线偏距不能为空");
            return result1;
        }
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
                    z2 = 90 + Math.toDegrees(Math.atan((x3 - x21) / h));
                    z2Str = format("θ2：%s\n", toDegreesStr(z2));
                }
            } else if (z == 90) {
                z1 = Math.toDegrees(Math.atan(h / (x - x3)));
                z2 = z1;
            } else {
                x11 = x - hTanRa;
                if (x3 < x11) {
                    a1 = x - x3 - hTanRa;
                    a2 = x - x3 + hTanRa;
                } else {
                    z1 = 90 + Math.toDegrees(Math.atan((x3 - x11) / h));
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
                    z1 = 90 + Math.toDegrees(Math.atan((x11 - x3) / h));
                    z1Str = format("θ1：%s\n", toDegreesStr(z1));
                }
            } else if (z == 90) {
                z1 = Math.toDegrees(Math.atan(h / (x3 - x)));
                z2 = z1;
            } else {
                x21 = x + hTanRa;
                if (x3 > x21) {
                    a1 = x3 - x + hTanRa;
                    a2 = x3 - x - hTanRa;
                } else {
                    z2 = 90 + Math.toDegrees(Math.atan((x21 - x3) / h));
                    a1 = x3 - x + hTanRa;
                    z2Str = format("θ2：%s\n", toDegreesStr(z2));
                }
            }
        } else {
            result1.setMsg("x = x3：error");
            return result1;
        }
        if (a1 != null) {
            z1 = Math.toDegrees(Math.atan(h / a1));
            z1Str = format("θ1：%s\n", toDegreesStr(z1));
        }
        if (a2 != null) {
            z2 = Math.toDegrees(Math.atan(h / a2));
            z2Str = format("θ2：%s\n", toDegreesStr(z2));
        }
        result += z1Str;
        result += z2Str;
        s3 = Math.abs(x - x3);
        s2 = h / Math.sin(Math.toRadians(z2));
        s1 = h / Math.sin(Math.toRadians(z1));
        result += format("s1：%.4f\n", s1);
        result += format("s2：%.4f\n", s2);
        result += format("s3：%.4f\n", s3);
        String leftR = null;
        String rightR = null;
        if (x3 > x) {
            leftR = toDegreesStr(180 + z2);
            rightR = toDegreesStr(180 - z1);
            result += format("转到左边线角度：%s\n", toDegreesStr(180 + z1));
            result += format("转到右边线角度：%s\n", toDegreesStr(180 - z2));
        } else {
            leftR = toDegreesStr(360 - z2);
            rightR = toDegreesStr(z1);
            result += format("转到左边线角度：%s\n", toDegreesStr(360 - z1));
            result += format("转到右边线角度：%s\n", toDegreesStr(z2));
        }
        result1.setLeftR(leftR);
        result1.setRightR(rightR);
        if (nh3 == null) {
            result1.setMsg("nh3不能为空");
            return result1;
        }
        if (nhg == null) {
            //result1.setMsg("仪器高不能为空");
            return result1;
        }
        if (nz11 == null) {
            //result1.setMsg("左边线天顶角不能为空");
            return result1;
        }
        h3 = nh3;
        hg = nhg;
        z11 = nz11;
        double tempH = h3 + hg;
        leftH = s1 * Math.tan(Math.toRadians(90 - z11)) + tempH;
        result += format("左边线点高程：%.4f\n", leftH);
        result1.setRightH(format("%.4f", leftH));//20180528左右交换
        if (nz21 == null) {
            //result1.setMsg("右边线天顶角不能为空");
            return result1;
        }
        z21 = nz21;
        rightH = s2 * Math.tan(Math.toRadians(90 - z21)) + tempH;
        result += format("右边线点高程：%.4f\n", rightH);
        result1.setLeftH(format("%.4f", rightH));//20180528左右交换
        if (nz31 == null) {
            //result1.setMsg("中间线天顶角不能为空");
            return result1;
        }
        z31 = nz31;
        centerH = s3 * Math.tan(Math.toRadians(90 - z31)) + tempH;
        result += format("中间线点高程：%.4f\n", centerH);
        result1.setCenterH(format("%.4f", centerH));
        return result1;
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
        return x1 - y1 * (x1 - x2) / (y1 - y2);
    }

    private static Double getΘ(Double x1, Double x2, Double y1, Double y2) {
        return Math.toDegrees(Math.atan(Math.abs((y1 - y2) / (x1 - x2))));
    }

    static Double toDegrees(Double r, Double m, Double s) {
        if (r == null && m == null && s == null) return null;
        if (r == null) r = 0d;
        if (m == null) m = 0d;
        if (s == null) s = 0d;
        return r + m * (1 / 60d) + s * (1 / 3600d);
    }
}
