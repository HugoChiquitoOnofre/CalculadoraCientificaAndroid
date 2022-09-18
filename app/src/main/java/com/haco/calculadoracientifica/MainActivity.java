package com.haco.calculadoracientifica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.mariuszgromada.math.mxparser.*;

public class MainActivity extends AppCompatActivity {

    private TextView previousCalculation;
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previousCalculation = findViewById(R.id.previousCalculationView);
        display = findViewById(R.id.displayEditText);

        display.setShowSoftInputOnFocus(false);
    }


    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();

        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
        display.setSelection(cursorPos + strToAdd.length());
    }

    // Function default buttons (1,2,3,x,-, etc)
    public void defaultButtonPush(View view){
        Button button = (Button) view;
        updateText(button.getText().toString());
    }

    //Clear button
    public void clearButton(View view) {
        display.setText("");
        //previousCalculation.setText("");
    }

    public void backspaceButtonPush(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

    public void equalsButtonPush(View view) {
        String userExpr = display.getText().toString();

        userExpr = userExpr.replaceAll("x","*");
        userExpr = userExpr.replaceAll("÷", "/");

        Expression expression = new Expression(userExpr);
        String result = String.valueOf(expression.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    // scientific mode

    public void trigSinButtonPush(){
        updateText("sin(");
    }
    public void trigArcButtonPush(){
        updateText("cos(");
    }
    public void trigTanButtonPush(){
        updateText("tan(");
    }
    public void trigArcSinButtonPush(){
        updateText("arcsin(");
    }
    public void trigArcCosButtonPush(){
        updateText("arccos(");
    }
    public void trigArcTanButtonPush(){
        updateText("arctan(");
    }
    public void logButtonPush(){
        updateText("lg(");
    }
    public void naturalLogButtonPush(){
        updateText("ln(");
    }
    public void log2ButtonPush(){

    }
    public void eButtonPush(){

    }
    public void piButtonPush(){

    }
    public void absButtonPush(){

    }
    public void sqrtButtonPush(){

    }

    public void squaredButtonPush(){

    }
    public void xPoweredYButtonPush(){

    }
}