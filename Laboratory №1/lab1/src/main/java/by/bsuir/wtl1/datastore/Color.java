package by.bsuir.wtl1.datastore;

public class Color {
    private int colorCode;

   protected Color() {
        colorCode = 0;
    }

    Color(int codeOfColor) {
        colorCode = codeOfColor;
    }

    public int getColorCode() {
        return colorCode;
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
            Color comparableColor = (Color) object;
            return this.colorCode == comparableColor.colorCode;
        }
    }
    @Override
    public int hashCode(){
        int hash = 13;
        hash *= (colorCode != 0 ? colorCode : 1);
        return hash;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass())
                .append("{")
                .append("Color: ")
                .append("default")
                .append("}");
        return stringBuilder.toString();
    }

}
