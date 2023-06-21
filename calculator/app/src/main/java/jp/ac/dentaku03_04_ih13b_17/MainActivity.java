package jp.ac.dentaku03_04_ih13b_17;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Button> buttonList = new ArrayList<>();
        // 数字ボタンを取得
        buttonList.add((Button) findViewById(R.id.zero));
        buttonList.add((Button) findViewById(R.id.one));
        buttonList.add((Button) findViewById(R.id.two));
        buttonList.add((Button) findViewById(R.id.three));
        buttonList.add((Button) findViewById(R.id.four));
        buttonList.add((Button) findViewById(R.id.five));
        buttonList.add((Button) findViewById(R.id.six));
        buttonList.add((Button) findViewById(R.id.seven));
        buttonList.add((Button) findViewById(R.id.eight));
        buttonList.add((Button) findViewById(R.id.nine));
        // 記号ボタンを取得
        buttonList.add((Button) findViewById(R.id.plus));
        buttonList.add((Button) findViewById(R.id.minus));
        buttonList.add((Button) findViewById(R.id.multiplied));
        buttonList.add((Button) findViewById(R.id.divided));
        buttonList.add((Button) findViewById(R.id.equal));
        // その他ボタンを取得
        buttonList.add((Button) findViewById(R.id.clear));
        buttonList.add((Button) findViewById(R.id.backspace));
        buttonList.add((Button) findViewById(R.id.dot));
        buttonList.add((Button) findViewById(R.id.plusminus));

        ButtonListener listener = new ButtonListener();

        for(Button button : buttonList){
            button.setOnClickListener(listener);
        }
    }

    private class ButtonListener implements View.OnClickListener {

        private List<BigDecimal> _numList = new ArrayList<>();
        private List<Character> _opeList = new ArrayList<>();
        private String _inputValue = "";

        @Override
        public void onClick(View view) {
            TextView tvFormula = findViewById(R.id.formula);
            TextView tvResult = findViewById(R.id.result);


            // ボタン毎の処理を定義
            int btId = view.getId();
            char inputChar;
            switch (btId) {
                // 数字ボタンの場合
                case R.id.zero:
                    inputChar = '0';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.one:
                    inputChar = '1';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.two:
                    inputChar = '2';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.three:
                    inputChar = '3';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.four:
                    inputChar = '4';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.five:
                    inputChar = '5';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.six:
                    inputChar = '6';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.seven:
                    inputChar = '7';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.eight:
                    inputChar = '8';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.nine:
                    inputChar = '9';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.plus:
                    inputChar = '+';
                    if(!(_inputValue.equals(""))) {
                        addList(tvFormula, _inputValue, inputChar);
                    }
                    tvResult.setText("");
                    break;
                case R.id.minus:
                    inputChar = '-';
                    if(!(_inputValue.equals(""))) {
                        addList(tvFormula, _inputValue, inputChar);
                    }
                    tvResult.setText("");
                    break;
                case R.id.multiplied:
                    inputChar = '×';
                    if(!(_inputValue.equals(""))) {
                        addList(tvFormula, _inputValue, inputChar);
                    }
                    tvResult.setText("");
                    break;
                case R.id.divided:
                    inputChar = '÷';
                    if(!(_inputValue.equals(""))) {
                        addList(tvFormula, _inputValue, inputChar);
                    }
                    tvResult.setText("");
                    break;
                case R.id.equal:
                    inputChar = '=';
                    if(!(_inputValue.equals(""))) {
                        addList(tvFormula, _inputValue, inputChar);
                    }
                    String result = calculate().toString();
                    tvFormula.setText(result);
                    tvResult.setText(result);
                    _inputValue = result;
                    _numList.clear();
                    _opeList.clear();
                    break;
                case R.id.clear:
                    tvFormula.setText("");
                    tvResult.setText("");
                    _numList.clear();
                    _opeList.clear();
                    _inputValue= "";
                    break;
                case R.id.backspace:
                    String formulaStr = tvFormula.getText().toString();
                    String resultStr = tvResult.getText().toString();
                    char formulaStrLastChar = formulaStr.charAt(formulaStr.length() - 1);

                    if (isFourArithmeticOpe(formulaStrLastChar)) {
                        _opeList.remove(_opeList.size() - 1);
                    }
                    if (!formulaStr.isEmpty()) {
                        tvFormula.setText(formulaStr.subSequence(0, formulaStr.length() - 1));
                        tvResult.setText(resultStr.subSequence(0, resultStr.length() - 1));
                    }
                    if (!_inputValue.isEmpty()) {
                        _inputValue = _inputValue.substring(0, _inputValue.length() - 1);
                    }
                    break;
                case R.id.dot:
                    inputChar = '.';
                    addTextView(tvFormula, inputChar);
                    addTextView(tvResult, inputChar);
                    _inputValue += inputChar;
                    break;
                case R.id.plusminus:
                    String formulaStrCon = tvFormula.getText().toString();
                    if(!formulaStrCon.equals("")){
                        int formulaInt = Integer.parseInt(formulaStrCon);
                        formulaInt *= -1;
                        _inputValue = "";
                        _inputValue += formulaInt;
                        String formulaResult = String.valueOf(formulaInt);
                        tvFormula.setText(formulaResult);
                        tvResult.setText(formulaResult);
                    }
                    break;
            }
        }

        private void addList(TextView tvFormula, String inputValue, char ope) {
            addTextView(tvFormula, ope);
            _numList.add(new BigDecimal(inputValue));
            _opeList.add(ope);
            _inputValue = "";
        }

        private void addTextView(TextView textView, char addStr) {
            textView.setText(textView.getText().toString() + addStr);
        }

        private BigDecimal calculate() {
            int i = 0;

            while(i < _opeList.size()) {
                if(_opeList.get(i) == '×' | _opeList.get(i) == '÷') {
                    BigDecimal resultMultiDiv = _opeList.get(i) == '×' ? _numList.get(i).multiply(_numList.get(i+1)) : _numList.get(i).divide(_numList.get(i+1));

                    _numList.set(i, resultMultiDiv);
                    _numList.remove(i+1);
                    _opeList.remove(i);
                    i--;
                }
                else if(_opeList.get(i) == '-') {
                    _opeList.set(i, '+');
                    _numList.set(i+1, _numList.get(i+1).negate());
                }
                i++;
            }

            BigDecimal result = new BigDecimal("0");
            for(BigDecimal j : _numList) {
                result = result.add(j);
            }

            return result;
        }

        private boolean isFourArithmeticOpe(char c) {
            if(c == '+' | c == '-' | c == '*' | c == '/') return true;
            return false;
        }
    }
}