package com.xidian.iot.common.util.compress;

/**
 * @description:
 * @author: mrl
 * @date: 2021/1/7 上午12:30
 */
public class Point implements Comparable<Point> {

    private long x;     // 时间戳
    private double y; // 测点值

    public Point(){}

    public Point(long x, double y){
        this.x = x;
        this.y = y;
    }

    public long getX() {
        return x;
    }

    public void setX(long x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        return (int) (this.y - o.y);
    }
}
