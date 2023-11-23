package by.bsuir.wtl1.processing;

import by.bsuir.wtl1.datastore.Coordinates2D;
import java.util.ArrayList;


public class TangentRangeProcess {

    public static ArrayList<Coordinates2D> countTangentInRange(double a,
                                                               double b,
                                                               double h){
        ArrayList<Coordinates2D> result = new ArrayList<>();
        if (a < b) {
            if (h > 0) {
                while((a + h) < b){
                    result.add(new Coordinates2D(a, Math.tan(a)));
                    a = a + h;
                }
            } else {
                return result;
            }
        } else {
            if (h < 0) {
                while ((a + h) > b) {
                    result.add(new Coordinates2D(a, Math.tan(a)));
                    a = a + h;
                }
            } else {
                return result;
            }
        }

        return result;
    }
}
