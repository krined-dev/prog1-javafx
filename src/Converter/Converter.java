package Converter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Converter extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Converter");
        Pane pane = new Pane();

        Button dec2BinConv = new Button("Decimal to Binary");
        Button dec2HexConv = new Button("Decimal to Hex");
        Button bin2DecConv = new Button("Binary to Decimal");
        Button hex2DecConv = new Button("Hex to Decimal");

        // TextInputDialog num2Conv = new TextInputDialog("Enter number to convert");

        TextField numConversion = new TextField("Enter number to convert");
        TextField convertedNumber = new TextField("Conversion result");

        HBox buttonBox = new HBox(5);
        buttonBox.getChildren().addAll(dec2BinConv, dec2HexConv, bin2DecConv, hex2DecConv);

        VBox allFields = new VBox(10);

        // Button events

        dec2BinConv.setOnAction(event -> {
            try {
                int decimal = Integer.parseInt(numConversion.getText());
                String bin = dec2Bin(decimal);
                convertedNumber.setText(bin);
            } catch (Exception e) {
                // e.printStackTrace();
                convertedNumber.setText("Invalid input. Only valid input is 32 bit integers");
            }
        });

        dec2HexConv.setOnAction(event -> {
            try {
                int decimal = Integer.parseInt(numConversion.getText());
                String hex = dec2Hex(decimal);
                convertedNumber.setText(hex);
            } catch (Exception e) {
                // e.printStackTrace();
                convertedNumber.setText("Invalid input. Only valid input is 32 bit integers");
            }
        });

        bin2DecConv.setOnAction(event -> {
            try {
                if (isBinary(numConversion.getText())) {
                    convertedNumber.setText(String.valueOf(bin2Dec(numConversion.getText())));
                } else {
                    convertedNumber.setText("Invalid input");
                }
            } catch (Exception ignore) {
                convertedNumber.setText("Invalid input");
            }
        });

        hex2DecConv.setOnAction(event -> {
            try {
                if (validateHex(numConversion.getText())) {
                    convertedNumber.setText(String.valueOf(hex2Dec(numConversion.getText())));
                } else {
                    convertedNumber.setText("Invalid input");
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        });

        // Setting pane scene and stage.

        pane.getChildren().addAll(allFields);

        allFields.getChildren().addAll(buttonBox, numConversion, convertedNumber);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

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




    private String dec2Hex(int dec) {

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
        int low = 0;
        int high = string.length() - 1;
        int dec = bin2Dec(string, low, high);
        return dec;
    }

    private int bin2Dec(String string, int low, int high) { // Hovedmetode
         double dec = 0;

        if (high == low) {
            dec += Character.getNumericValue(string.charAt(low)) * Math.pow(2, 0);
        } else {
            int flag = Character.getNumericValue(string.charAt(low));
            dec += (flag * Math.pow(2, high - low)) + bin2Dec(string, low + 1, high);

        }

        return (int) dec;
    }

    private int hex2Dec(String string) { // Hjelpmetode
        // TODO
        int low = 0;
        int high = string.length() - 1;
        return hex2Dec(string, low, high);
    }

    private int hex2Dec(String string, int low, int high) { // Hovedmetode
        int dec = 0;

        if (high == low) {
            dec += hexAsNumerical(string.substring(low, low +1));
        } else {
            dec += (hexAsNumerical(string.substring(low, low + 1)) * Math.pow(16, high - low)) +
                    hex2Dec(string, low + 1, high);
        }
        return dec;
    }

    private boolean isBinary(String s) {
        for (int i = 0; i < s.length(); i++) {
            int c = Character.getNumericValue(s.charAt(i));
            if (c != 0 && c != 1) {
                return false;
            }
        }
        return true;
    }

    private int hexAsNumerical(String s) {
        HashMap<String, Integer> hex = new HashMap<>() {{
            put("A", 10);
            put("B", 11);
            put("C", 12);
            put("D", 13);
            put("E", 14);
            put("F", 15);
        }};

        if (isNumerical(s)) {
            return Integer.parseInt(s);
        } else {
            return hex.get(s);
        }
    }

    private boolean isNumerical(String num) {
        if (num == null) {
            return false;
        }

        try {
            Integer.parseInt(num);
        } catch (Exception ignore) {
            return false;
        }

        return true;
    }

    private boolean validateHex(String s) {
        List<String> letters = Arrays.asList("A", "B", "C", "D", "E", "F");
        for (int i = 0; i < s.length(); i++) {
            if (!(isNumerical(s.substring(i, i +1)))) {
                if (!(letters.contains(s.substring(i, i +1)))) {
                    return false;
                }
            }
        }
        return true;
    }
}
