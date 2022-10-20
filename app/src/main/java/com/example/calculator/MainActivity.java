package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

//Imported from library
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView solution_tv,Result_tv;
    MaterialButton button_c,button_openbra,button_closebra;
    MaterialButton button_divide,button_mul,button_plus,button_sub,button_eql,button_dot,button_clear;
    MaterialButton button_0,button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result_tv = findViewById(R.id.Result_tv);
        solution_tv = findViewById(R.id.solution_tv);

        button_c = findViewById(R.id.button_c);
        button_openbra = findViewById(R.id.button_openbra);
        button_closebra = findViewById(R.id.button_closebra);

        button_divide = findViewById(R.id.button_divide);
        button_mul = findViewById(R.id.button_mul);
        button_plus = findViewById(R.id.button_plus);
        button_sub = findViewById(R.id.button_sub);
        button_eql = findViewById(R.id.button_eql);
        button_dot = findViewById(R.id.button_dot);
        button_clear = findViewById(R.id.button_clear);

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
    }

    public void onClick(View view)
    {
        MaterialButton button = (MaterialButton) view;
        String buttontext = button.getText().toString();
        String datacalc= solution_tv.getText().toString();


        if(buttontext.equals("Clr"))
        {
            datacalc = "";
            solution_tv.setText("");
            Result_tv.setText("0");
            //return;
        }
        else if(buttontext.equals("C"))
        {
            if(datacalc.length()>0)
            {
                datacalc = datacalc.substring(0, datacalc.length() - 1);
                solution_tv.setText(datacalc);
            }
            if(datacalc.length()==0)
                Result_tv.setText("0");
        }
        else if(buttontext.equals("="))
        {
            solution_tv.setText(Result_tv.getText());
            return;
        }
        else
        {
            datacalc+=buttontext;
        }
        solution_tv.setText(datacalc);
        if(datacalc.length()>0)
        {
            String result = CalcResult(datacalc);

            if (result.endsWith(".0"))
                result = result.substring(0, result.length() - 2);
            if (!result.equals("Error")) {
                Result_tv.setText(result.toString());
            }
        }
    }

    String CalcResult(String data)
    {
        try
        {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            return result;
        }
        catch(Exception e)
        {
            return "Error";
        }
    }
}