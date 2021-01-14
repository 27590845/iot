package com.xidian.iot.common.util.compress.sdt;

import com.xidian.iot.common.util.compress.Point;
import com.xidian.iot.common.util.compress.Proc;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: mrl
 * @date: 2021/1/7 上午12:35
 */
public class SdtProc extends Proc {

    class SdtPoints{
        private Point beginPoint;
        private Point lastPoint;

        public Point getBeginPoint() {
            return beginPoint;
        }

        public void setBeginPoint(Point beginPoint) {
            this.beginPoint = beginPoint;
        }

        public Point getLastPoint() {
            return lastPoint;
        }

        public void setLastPoint(Point lastPoint) {
            this.lastPoint = lastPoint;
        }
    }

    /**
     * SDT algorithm compress function
     *
     * @param originPoints 待压缩的点集
     * @param accuracyE    旋转门大小
     * @return
     */
    @Override
    public List<Point> compress(List<Point> originPoints, double accuracyE) {
        List<Point> compressPoints = new ArrayList<>();

        if (null != originPoints && originPoints.size() > 0) {
            SdtPoints sdtPoints = new SdtPoints();

            double upGate = -Double.MAX_VALUE;
            double downGate = Double.MAX_VALUE;
            for (int i = 0, size = originPoints.size(); i < size; i++) {
                Point currentPoint = originPoints.get(i);

                if (i == 0) {
                    sdtPoints.setLastPoint(currentPoint);
                    sdtPoints.setBeginPoint(currentPoint);
                } else {

                    double nowUpGate = (currentPoint.getY() - sdtPoints.getBeginPoint().getY() - accuracyE) /
                            (currentPoint.getX() - sdtPoints.getBeginPoint().getX());

                    if (nowUpGate > upGate) {
                        upGate = nowUpGate;
                    }

                    double nowDownGate = (currentPoint.getY() - sdtPoints.getBeginPoint().getY() + accuracyE) /
                            (currentPoint.getX() - sdtPoints.getBeginPoint().getX());

                    if (nowDownGate < downGate) {
                        downGate = nowDownGate;
                    }

                    if (upGate > downGate) {
                        // create new SdtPeriod(one)
                        compressPoints.add(sdtPoints.getLastPoint());

                        // update gates
                        upGate = (currentPoint.getY() - sdtPoints.getLastPoint().getY() - accuracyE) /
                                (currentPoint.getX() - sdtPoints.getLastPoint().getX());
                        downGate = (currentPoint.getY() - sdtPoints.getLastPoint().getY() + accuracyE) /
                                (currentPoint.getX() - sdtPoints.getLastPoint().getX());

                        sdtPoints.setBeginPoint(sdtPoints.getLastPoint());

                    }

                    sdtPoints.setLastPoint(currentPoint);

                }

                if (i==0 || i==size-1) {
                    compressPoints.add(currentPoint);
                }

            }
        }

        return compressPoints;
    }
}
