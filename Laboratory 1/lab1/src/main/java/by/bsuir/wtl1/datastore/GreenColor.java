package by.bsuir.wtl1.datastore;

public class GreenColor extends Color {
    public GreenColor() {
        super(3);
    }

    @Override
    public boolean equals(Object object){
        if (object == this) {
            return true;
        } else if (object == null) {
            return false;
        } else {
            return getClass() == object.getClass();
        }
    }
    @Override
    public int hashCode(){
        return super.hashCode();
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass())
                .append("{")
                .append("Color: ")
                .append("green")
                .append("}");
        return stringBuilder.toString();
    }
}
