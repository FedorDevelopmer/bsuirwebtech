package by.bsuir.wtl1.datastore;

public class RedColor extends Color {
    public RedColor() {
        super(2);
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
                .append("red")
                .append("}");
        return stringBuilder.toString();
    }
}
