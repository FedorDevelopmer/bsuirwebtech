package by.bsuir.wtl1.processing;

import by.bsuir.wtl1.datastore.Coordinates2D;
import java.lang.Math;


public class FormulaProcess {

    public static double countFormula(Coordinates2D point) {

        double numerator = 1 + Math.pow(Math.sin(point.getX() + point.getY()), 2);
        double denominator = 2 + Math.abs(point.getX() - ((2 * point.getX())
                             / (1 + (Math.pow(point.getX(), 2)
                             * Math.pow(point.getY(), 2)))));
        return numerator / denominator + point.getX();
    }
}
