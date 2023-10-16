package by.bsuir.wtl1.io;

import by.bsuir.wtl1.datastore.Coordinates2D;

import java.io.*;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class InputOutputProcess {

    private static Scanner in = new Scanner(System.in);

    private static PrintStream out = new PrintStream(System.out);

    public static void setInputStream(InputStream stream) {
        in = new Scanner(stream);
    }
    public static void setOutputStream(OutputStream stream) {
        out = new PrintStream(stream);
    }

    public static double readDouble() {
        double result;
        try {
            String bufferString = in.nextLine();
            result = Double.parseDouble(bufferString);
            return result;
        } catch (InputMismatchException |
                 NumberFormatException wrongInput) {
            InputOutputProcess.print("Error: wrong input");
        }
        return 0;
    }

    public static int readInteger(){
        try {
            return in.nextInt();
        } catch (InputMismatchException wrongInput) {
           InputOutputProcess.print("Error: wrong input");
        }
        return 0;
    }

    public static long readLong(){
        try {
            return in.nextLong();
        } catch (InputMismatchException wrongInput) {
            InputOutputProcess.print("Error: wrong input");
        }
        return 0;
    }

    public static byte readByte() {
        try {
            return in.nextByte();
        } catch (InputMismatchException wrongInput) {
            InputOutputProcess.print("Error: wrong input");
        }
        return 0;
    }

    public static short readShort() {
        try {
            return in.nextShort();
        } catch (InputMismatchException wrongInput) {
            InputOutputProcess.print("Error: wrong input");
        }
        return 0;
    }

    public static float readFloat() {
        float result;
        try {
            String bufferString = in.nextLine();
            result = Float.parseFloat(bufferString);
            return result;
        } catch (InputMismatchException |
                 NumberFormatException wrongInput) {
            InputOutputProcess.print("Error: wrong input");
        }
        return 0;
    }

    public static long[] readNumberArray() {
        long[] array;
        int length;
        InputOutputProcess.print("Enter number of array elements: ");
        length = InputOutputProcess.readInteger();
        array = new long[length];
        for (int i = 0; i < length; i++) {
            array[i] = InputOutputProcess.readLong();
        }
        return array;
    }
    public static void print(String text)  {
        try {
            out.write((text+"\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            out = new PrintStream(System.out);
        }
    }

    public static void printTable(List<Coordinates2D> list) {
        InputOutputProcess.print("==================");
        InputOutputProcess.print("|   x   |    y   |");
        InputOutputProcess.print("==================");
        DecimalFormat format = new DecimalFormat("###.#####");
        for (Coordinates2D point : list) {
            InputOutputProcess.print("|  " + format.format(point.getX())
                                + "  |  " + format.format(point.getY())
                                + "  |");
            InputOutputProcess.print("==================");
        }
    }

    public static void printArray(long[] array) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (long number : array) {
            sb.append(number + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        InputOutputProcess.print(sb.toString());
    }

    public static void printList(List<Integer> arrayList){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int number : arrayList) {
            sb.append(number + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        InputOutputProcess.print(sb.toString());
    }

    public static void printMatrix(long[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[i][j] + ", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            InputOutputProcess.print(sb.toString());
            sb.delete(0, sb.length());
        }
    }
}
