package com.example.appcalculator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    double input1 = 0, input2=0, resultat=0;
    TextView edt1, edt2;
    boolean addition, subtract, multiplication, division, mRemainder;
    String display ="0",displayOp ="0", currentOp, operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1=findViewById(R.id.display);
        edt1.setText(display);
        edt2=findViewById(R.id.displayOp);
        edt2.setText(displayOp);
    }
    public void clickbuttonChiffre(View view ) {
        Button b = (Button) view;
        String buttonText = b.getText().toString();
        if (display.length()<=19){
            printInput(buttonText);
        } else {
            Toast.makeText(this, "Max 20 digits",Toast.LENGTH_LONG).show();
        }
    }

    public void printInput (String value){
        Log.d("display", "addition: "+ addition);
        Log.d("display", "subtract: "+ subtract);
        Log.d("display", "multiplication: "+ multiplication);
        Log.d("display", "division: "+ division);
        Log.d("display", "mRemainder: "+ mRemainder);

         if (!addition && !subtract && !multiplication && !division && !mRemainder){
             if (display == "0") {
                 display = value;
                 input1 = Double.parseDouble(display);
                 edt1.setText(display);
             } else {
                 display = display + value;
                 input1 = Double.parseDouble(display);
                 edt1.setText(display);
             }
         } else {
                 if (display == "0") {
                     display = value;
                     input2 = Double.parseDouble(display);
                     edt1.setText(display);
                     Log.d("display", value);
                     } else {
                             display = display + value;
                             edt1.setText(display);
                             input2 = Double.parseDouble(display);
                    }
            }
        Log.d("display", "Input1: "+ input1);
        Log.d("display", "Input2: "+ input2);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickbuttonCE(View view) {
        input1=0;
        input2=0;
        display ="0";
        displayOp ="0";
        edt1.setText(display);
        edt1.setText(displayOp);
    }

    public void clickbuttonC(View view) {
        display ="0";
        edt1.setText(display);
        //TODO: reset edt2
    }

    public void clickbuttonDEL(View view) {
    }

    public void clickbuttonOperation(View view) {
        if (!addition && !subtract && !multiplication && !division && !mRemainder){
            Button b = (Button) view;
            operation = b.getText().toString();
            switch (operation) {
                case "+":
                    addition=true;
                    currentOp=" + ";
                    break;
                case "-":
                    subtract=true;
                    currentOp=" - ";
                    break;
                case "x":
                    multiplication=true;
                    currentOp=" x ";
                    break;
                case "/":
                    division=true;
                    currentOp=" / ";
                break;
                case "%":
                    mRemainder=true;
                    currentOp=" % ";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + operation);
            }
            edt2.setText(input1 +" "+currentOp);
            display ="0";
            edt1.setText(display);

        } else {
            Toast.makeText(this, "Seulement une operation permis par calcul",Toast.LENGTH_LONG).show();
        }

    }

    public void clickbuttonEql(View view) {

        switch (operation) {
            case "+":
                resultat = input1 + input2;
                addition=false;
                break;
            case "-":
                resultat = input1 - input2;
                subtract=false;
                break;
            case "x":
                resultat = input1 * input2;
                multiplication=false;
                break;
            case "/":
                resultat = input1 / input2;
                division=false;
                break;
            case "%":
                resultat = input1 % input2;
                mRemainder=false;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        edt2.setText(String.format("%s %s %s %s", input1, currentOp, input2, "="));
        display = String.valueOf(resultat);
        edt1.setText(display);
    }

}
