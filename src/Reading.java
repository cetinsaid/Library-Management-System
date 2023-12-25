import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Reading {
    private String inputFile;
    private String outputFile;

    // readFile method reads the content of given input file as an argument and converts it to an ArrayList.
    public String[][] readFile(String inputFile) throws IOException {
        ArrayList<String> inputOrder = new ArrayList<>();
        ArrayList<ArrayList<Arrays>> test = new ArrayList<>();
        this.inputFile = inputFile;
        BufferedReader input = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = input.readLine()) != null) {
            if(line.compareTo("")==0){
                continue;}
            inputOrder.add(line);
        }
        String[][] inputsAs2DArray = new String[inputOrder.size()][];
        for (int i = 0; i < inputOrder.size(); i++) {
            inputsAs2DArray[i] = inputOrder.get(i).split("\t");
        }
        input.close();
        return inputsAs2DArray;
    }
//  writeFile method takes output file and content of that will be written to that output file as a String.
    public void writeFile(String outputFile, String outputs) throws IOException {
        this.outputFile = outputFile;
        BufferedWriter output = new BufferedWriter(new FileWriter(outputFile));
        output.write(outputs);
        output.close();
    }
}
