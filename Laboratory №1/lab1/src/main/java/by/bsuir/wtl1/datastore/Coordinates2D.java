package by.bsuir.wtl1.datastore;

public class Coordinates2D{

    private double x;
    private double y;
    public Coordinates2D(){
        x = 0;
        y = 0;
    }

    public Coordinates2D(double xValue,double yValue){
        x = xValue;
        y = yValue;
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    @Override
    public boolean equals(Object object){
        if (object == this) {
            return true;
        } else if (object == null) {
            return false;
        } else if (getClass() != object.getClass()) {
            return false;
        } else {
            Coordinates2D comparablePoint = (Coordinates2D) object;
            return this.x == comparablePoint.x
                   && this.y == comparablePoint.y;
        }
    }
    @Override
    public int hashCode(){
        int hash = 17;
        hash += Double.hashCode(x != 0 ? x : 1);
        hash += Double.hashCode(y != 0 ? y : 1);
        return hash;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass())
                .append("{")
                .append("x: ")
                .append(x)
                .append(", y: ")
                .append(y)
                .append("}");
        return stringBuilder.toString();
    }

}
