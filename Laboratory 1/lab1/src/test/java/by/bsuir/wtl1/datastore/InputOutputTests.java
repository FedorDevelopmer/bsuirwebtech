package by.bsuir.wtl1.datastore;

import by.bsuir.wtl1.io.InputOutputProcess;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputOutputTests {
    @Test
    public void printTest() throws IOException {
        StringBuilder builder;
        FileInputStream getFileData;
        PrintStream testOutputStream;
        Path outputPath;
        String fileContent;
        List<Coordinates2D> testCoordinatesList;
        List<Integer> testList;
        long[] testArray;
        long[][] testMatrix;

        outputPath = Path.of("out.txt");
        if(!Files.exists(outputPath)) {
            Files.createFile(outputPath);
        }
        testOutputStream = new PrintStream(outputPath.toString());
        InputOutputProcess.setOutputStream(testOutputStream);
        getFileData = new FileInputStream(outputPath.toString());

        // Integer read test
        writeInput("4");
        assertEquals(4,InputOutputProcess.readInteger());
        writeInput("four");
        InputOutputProcess.readInteger();
        fileContent = new String(getFileData.readAllBytes());
        assertEquals("Error: wrong input\n",fileContent);
        clearOutput(outputPath,testOutputStream);

        // Long read test
        writeInput("10");
        assertEquals(10,InputOutputProcess.readLong());
        writeInput("ten");
        InputOutputProcess.readLong();
        fileContent = new String(getFileData.readAllBytes());
        assertEquals("Error: wrong input\n",fileContent);
        clearOutput(outputPath,testOutputStream);

        //Byte read test
        writeInput("65");
        assertEquals(65,InputOutputProcess.readByte());
        writeInput("sixty five");
        InputOutputProcess.readByte();
        fileContent = new String(getFileData.readAllBytes());
        assertEquals("Error: wrong input\n",fileContent);
        clearOutput(outputPath,testOutputStream);

        // Short read test
        writeInput("-670");
        assertEquals(-670,InputOutputProcess.readShort());
        writeInput("minus six hundred seventy");
        InputOutputProcess.readShort();
        fileContent = new String(getFileData.readAllBytes());
        assertEquals("Error: wrong input\n",fileContent);
        clearOutput(outputPath,testOutputStream);

        //Float read test
        writeInput("100.65");
        assertEquals(100.65,InputOutputProcess.readFloat(),0.0001);
        writeInput("one hundred and sixty five hundredths");
        InputOutputProcess.readFloat();
        fileContent = new String(getFileData.readAllBytes());
        assertEquals("Error: wrong input\n",fileContent);
        clearOutput(outputPath,testOutputStream);

        //Double read test
        writeInput("1000.6544");
        assertEquals(1000.6544,InputOutputProcess.readDouble(),0.0001);
        writeInput("long name double");
        InputOutputProcess.readDouble();
        fileContent = new String(getFileData.readAllBytes());
        assertEquals("Error: wrong input\n",fileContent);
        clearOutput(outputPath,testOutputStream);

        //Table print test
        testCoordinatesList = new ArrayList<>();
        builder = new StringBuilder();
        testCoordinatesList.add(new Coordinates2D(1.5,10.5));
        testCoordinatesList.add(new Coordinates2D(2.5,20.5));
        builder.append("==================\n")
               .append("|   x   |    y   |\n")
               .append("==================\n")
               .append("|  1,5  |  10,5  |\n")
               .append("==================\n")
               .append("|  2,5  |  20,5  |\n")
               .append("==================\n");
        InputOutputProcess.printTable(testCoordinatesList);
        fileContent = new String(getFileData.readAllBytes());
        assertEquals(builder.toString(),fileContent);
        clearOutput(outputPath,testOutputStream);
        builder.delete(0,builder.length());

        //Array print test
        testArray = new long[5];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i + 1;
        }
        InputOutputProcess.printArray(testArray);
        builder.append("[1, 2, 3, 4, 5]\n");
        fileContent = new String(getFileData.readAllBytes());
        assertEquals(builder.toString(),fileContent);
        clearOutput(outputPath,testOutputStream);
        builder.delete(0,builder.length());

        //List print test
        testList = new ArrayList<>();
        testList.add(1);
        testList.add(3);
        testList.add(10605);
        InputOutputProcess.printList(testList);
        builder.append("[1, 3, 10605]\n");
        fileContent = new String(getFileData.readAllBytes());
        assertEquals(builder.toString(),fileContent);
        clearOutput(outputPath,testOutputStream);
        builder.delete(0,builder.length());

        //Matrix print test
        testMatrix = new long[2][2];
        for (int i = 0; i < testMatrix.length; i++) {
            for (int j = 0; j < testMatrix[i].length; j++) {
                testMatrix[i][j] = i;
            }
        }
        InputOutputProcess.printMatrix(testMatrix);
        builder.append("0, 0\n")
               .append("1, 1\n");
        fileContent = new String(getFileData.readAllBytes());
        assertEquals(builder.toString(),fileContent);
        clearOutput(outputPath,testOutputStream);
        builder.delete(0,builder.length());
        Files.delete(outputPath);
        InputOutputProcess.setInputStream(System.in);
        InputOutputProcess.setOutputStream(System.out);
    }
    private void writeInput(String text) {
        byte[] dataInBytes;
        InputStream testStream;
        dataInBytes = text.getBytes();
        testStream = new ByteArrayInputStream(dataInBytes);
        InputOutputProcess.setInputStream(testStream);
    }

    private void clearOutput(Path outputPath,PrintStream testOutputStream) throws IOException {
        new FileOutputStream(String.valueOf(outputPath)).close();
        InputOutputProcess.setOutputStream(testOutputStream);
    }





}
