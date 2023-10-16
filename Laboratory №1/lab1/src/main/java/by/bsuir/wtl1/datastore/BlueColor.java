package by.bsuir.wtl1.datastore;

public class BlueColor extends Color{

    public BlueColor() {
        super(1);
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
                .append("blue")
                .append("}");
        return stringBuilder.toString();
    }
}
