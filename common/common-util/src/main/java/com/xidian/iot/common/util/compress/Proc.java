package com.xidian.iot.common.util.compress;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: mrl
 * @date: 2021/1/9 下午12:20
 */
public abstract class Proc {

    /**
     * compress function
     *
     * @param originPoints 待压缩的点集
     * @param accuracyE    模糊参数
     * @return
     */
    public abstract List<Point> compress(List<Point> originPoints, double accuracyE);

    /**
     * uncompress function
     *
     * @param compressPoints 压缩点集合
     * @param bgTime        返回点集开始时间
     * @param endTime       返回点集结束时间
     * @param interval      返回点集2点间隔
     * @return
     */
    public List<Point> unCompress(List<Point> compressPoints, long bgTime, long endTime, long interval) {

        List<Point> pointList = new ArrayList<>();

        if (null != compressPoints && compressPoints.size() > 0) {

            // recalculate bgTime and endTime
            long globalBgTime = compressPoints.get(0).getX();
            long globalEndTime = compressPoints.get(compressPoints.size() - 1).getX();

            if (bgTime > globalEndTime || endTime < globalBgTime) {
                return pointList;
            }

            if (bgTime < globalBgTime && endTime < globalEndTime) {
                while (bgTime < globalBgTime) {
                    bgTime += interval;
                }
            }

            if (globalBgTime < bgTime && globalEndTime < endTime) {
                long tempEndTime = bgTime;
                while (tempEndTime < globalEndTime) {
                    tempEndTime += interval;
                }
                endTime = tempEndTime - interval;
            }

            if (bgTime < globalBgTime && endTime > globalEndTime) {
                while (bgTime < globalBgTime) {
                    bgTime += interval;
                }
                long tempEndTime = bgTime;
                while (tempEndTime < globalEndTime) {
                    tempEndTime += interval;
                }
                endTime = tempEndTime - interval;
            }

            // final check
            if (bgTime > globalEndTime || endTime < globalBgTime) {
                return pointList;
            }

            long interpolatingTime = bgTime;
            for(int i=1; i<compressPoints.size(); i++){
                long periodBgTime = compressPoints.get(i-1).getX();
                double periodBgValue = compressPoints.get(i-1).getY();
                long periodEndTime = compressPoints.get(i).getX();
                double periodEndValue = compressPoints.get(i).getY();
                double grad = (periodEndValue-periodBgValue)/(periodEndTime-periodBgTime);

                if (interpolatingTime >= periodBgTime && interpolatingTime <= periodEndTime) {
                    while (interpolatingTime <= periodEndTime && interpolatingTime <= endTime) {
                        Point point = new Point();
                        point.setX(interpolatingTime);
                        point.setY(periodBgValue + (interpolatingTime - periodBgTime) * grad);
                        pointList.add(point);
                        interpolatingTime += interval;
                    }
                }

                if (interpolatingTime > endTime) {
                    break;
                }
            }

        }

        return pointList;
    }
}
