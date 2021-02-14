package Converter;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.HashMap;

public class Converter extends Application {
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static void main(String[] args) {
        int i =643908;
        System.out.println(dec2Hex(i));
    }

    private String dec2Bin(int dec) {
        String bin = "";
        if (dec / 2 == 0) {
            bin = Integer.toString(dec % 2);
        } else {
            bin = bin + dec2Bin(dec / 2) + dec % 2;
        }

        return bin;
    }

    static String dec2Hex(int dec) {
        String result = "";
        HashMap<Integer, String> remainder = new HashMap<>() {{
            put(10, "A");
            put(11, "B");
            put(12, "C");
            put(13, "D");
            put(14, "E");
            put(15, "F");
        }};
        String rem = String.valueOf(dec % 16);

        if (dec % 16 > 9) {
            rem = remainder.get(dec % 16);
        }
        if ((dec / 16) == 0) {
            result += rem;
        } else {
            result = result + dec2Hex(dec / 16) + rem;
        }
        return result;
    }

    private int bin2Dec(String string) { // Hjelpemetode
        // TODO
        return 0;
    }

    private int bin2Dec(String string, int low, int high) { // Hovedmetode
        // TODO
        return 0;
    }

    private int hex2Dec(String string) { // Hjelpmetode
        // TODO
        return 0;
    }

    private int hex2Dec(String string, int low, int high) { // Hovedmetode
        // TODO
        return 0;
    }
}
