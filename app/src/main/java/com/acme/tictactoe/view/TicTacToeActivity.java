package com.acme.tictactoe.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import com.acme.tictactoe.R;
import com.acme.tictactoe.presenter.TicTacToePresenter;

public class TicTacToeActivity extends AppCompatActivity implements TicTacToeView {

    private static String TAG = TicTacToeActivity.class.getName();

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9
            , buttonCLR, buttonDELETE, buttonSIGN, buttonDIVISION, buttonMULTIPLICATION, buttonMINUS, buttonADD, buttonDOT, buttonEQUAL;
    EditText editText;

    TicTacToePresenter presenter = new TicTacToePresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe);

        editText = (EditText) findViewById(R.id.editText);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        buttonCLR = (Button) findViewById(R.id.buttonCLR);
        buttonDELETE = (Button) findViewById(R.id.buttonDELETE);
        buttonSIGN = (Button) findViewById(R.id.buttonSIGN);
        buttonDIVISION = (Button) findViewById(R.id.buttonDIVISION);
        buttonMULTIPLICATION= (Button) findViewById(R.id.buttonMULTIPLICATION);
        buttonMINUS = (Button) findViewById(R.id.buttonMINUS);
        buttonADD = (Button) findViewById(R.id.buttonADD);
        buttonDOT = (Button) findViewById(R.id.buttonDOT);
        buttonEQUAL = (Button) findViewById(R.id.buttonEQUAL);




        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "0");
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "2");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "3");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "4");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "5");
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "6");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "7");
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "8");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText(editText.getText() + "9");
            }
        });
        buttonCLR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
        buttonDELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentString = editText.getText().toString();
                if(currentString.length() != 0) {
                    currentString = currentString.substring(0, currentString.length() - 1);
                    editText.setText(currentString);
                }
            }
        });
        buttonSIGN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lastIndex1 = -1, lastIndex2 = -1;
                String currentString = editText.getText().toString();
                lastIndex1 = currentString.lastIndexOf("*-");
                lastIndex2 = currentString.lastIndexOf("/-");
                lastIndex1 = Math.max(lastIndex1,lastIndex2);
                lastIndex2 = currentString.lastIndexOf("+-");
                lastIndex1 = Math.max(lastIndex1,lastIndex2);
                lastIndex2 = currentString.lastIndexOf("*-");
                lastIndex1 = Math.max(lastIndex1,lastIndex2);

                int lastIndex11 = -1, lastIndex12 = -1;
                lastIndex11 = currentString.lastIndexOf("*");
                lastIndex12 = currentString.lastIndexOf("/");
                lastIndex11 = Math.max(lastIndex11,lastIndex12);
                lastIndex12 = currentString.lastIndexOf("+");
                lastIndex11 = Math.max(lastIndex11,lastIndex12);
                lastIndex12 = currentString.lastIndexOf("*");
                lastIndex11 = Math.max(lastIndex11,lastIndex12);

                if(currentString.length() == 0)
                {
                    editText.setText("-");
                }
                else if(currentString.length() == 1 && currentString.contains("-"))
                {
                    editText.setText("");
                }
                else if(lastIndex1 != -1)
                {
                    editText.setText(currentString.substring(0, lastIndex1 + 1) + currentString.substring(lastIndex1 + 2, currentString.length()));
                }
                else if(lastIndex11 != -1)
                {
                    editText.setText(currentString.substring(0, lastIndex11 + 1) + "-" + currentString.substring(lastIndex11 + 1, currentString.length()));
                }
                else if(currentString.substring(0, 1).contains("-"))
                {
                    editText.setText(currentString.substring(1, currentString.length()));
                }
                else
                {
                    editText.setText("-" + currentString);
                }

            }
        });
        buttonDIVISION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentString = editText.getText().toString();
                if(currentString.length() > 0){
                String lastCharacter = currentString.substring(currentString.length() - 1);
                if (!(lastCharacter.contains("/") || lastCharacter.contains("*") || lastCharacter.contains("-") || lastCharacter.contains("+") || lastCharacter.contains("."))) {
                    editText.setText(editText.getText() + "/");}
                }
            }
        });
        buttonMULTIPLICATION.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentString = editText.getText().toString();
                if(currentString.length() > 0){
                String lastCharacter = currentString.substring(currentString.length() - 1);
                if (!(lastCharacter.contains("/") || lastCharacter.contains("*") || lastCharacter.contains("-") || lastCharacter.contains("+") || lastCharacter.contains("."))) {
                    editText.setText(editText.getText() + "*");}}
            }
        });
        buttonMINUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentString = editText.getText().toString();
                if(currentString.length() > 0){
                String lastCharacter = currentString.substring(currentString.length() - 1);
                if (!(lastCharacter.contains("/") || lastCharacter.contains("*") || lastCharacter.contains("-") || lastCharacter.contains("+") || lastCharacter.contains("."))) {
                editText.setText(editText.getText() + "-");}}
            }
        });
        buttonADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentString = editText.getText().toString();
                if(currentString.length() > 0){
                String lastCharacter = currentString.substring(currentString.length() - 1);
                if (!(lastCharacter.contains("/") || lastCharacter.contains("*") || lastCharacter.contains("-") || lastCharacter.contains("+") || lastCharacter.contains("."))) {
                editText.setText(editText.getText() + "+");}}
            }
        });
        buttonDOT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentString = editText.getText().toString();
                if(currentString.length() > 0){
                String lastCharacter = currentString.substring(currentString.length() - 1);
                if (!(lastCharacter.contains("/") || lastCharacter.contains("*") || lastCharacter.contains("-") || lastCharacter.contains("+") || lastCharacter.contains("."))) {
                editText.setText(editText.getText() + ".");}}
            }
        });
        buttonEQUAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Expression expression;
                double result;
                try
                {
                    expression = new ExpressionBuilder(editText.getText().toString()).build();
                    result = expression.evaluate();
                    editText.setText(Double.toString(result));
                }
                catch(Exception e)
                {
                    editText.setText("Error");
                }
            }
        });
//        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
//        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
//        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);
//        presenter.onCreate();
    }

    protected boolean detectDelimiter(String givenString, char Delimiter ){
        int IsFound = -1;
        IsFound = givenString.indexOf(Delimiter);

        if(IsFound == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tictactoe, menu);
        return true;
    }


    public void onCellClicked(View v) {

        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        presenter.onButtonSelected(row, col);

    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    public void clearButtons() {
        for( int i = 0; i < buttonGrid.getChildCount(); i++ ) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

    public void showWinner(String winningPlayerDisplayLabel) {
        winnerPlayerLabel.setText(winningPlayerDisplayLabel);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    public void clearWinnerDisplay() {
        winnerPlayerViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");
    }
}
