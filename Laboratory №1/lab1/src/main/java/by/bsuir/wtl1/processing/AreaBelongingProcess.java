package by.bsuir.wtl1.processing;

import by.bsuir.wtl1.datastore.Coordinates2D;


public class AreaBelongingProcess {

    //area is split on 2 parts: below and above zero
    private static final double xFixFirstTopArea = -4;
    private static final double yFixFirstTopArea = 5;
    private static final double xFixSecondTopArea = 4;
    private static final double yFixSecondTopArea = 0;
    private static final double xFixFirstBottomArea = -6;
    private static final double yFixFirstBottomArea = 0;
    private static final double xFixSecondBottomArea = 6;
    private static final double yFixSecondBottomArea = -3;

    public static boolean isBelongToArea(Coordinates2D point) {
        boolean belong = true;
        if (point.getX() < xFixFirstTopArea || point.getX() > xFixSecondTopArea
                                            || point.getY() > yFixFirstTopArea) {
            belong = false;
        }else if (point.getX() < xFixFirstBottomArea
                  || point.getX() > xFixSecondBottomArea
                  || point.getY() < yFixSecondBottomArea) {
            belong = false;
        }
        return belong;
    }


}
