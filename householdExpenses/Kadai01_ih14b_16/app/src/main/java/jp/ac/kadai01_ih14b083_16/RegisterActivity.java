package jp.ac.kadai01_ih14b083_16;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    EditText editText;

    private final String[] spinnerItems = {"給料", "食費", "交通費", "通信費", "水道光熱費", "課金"};

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editText = findViewById(R.id.edit_text);
        Button dateBtn = findViewById(R.id.dateButton);
        Button registerBtn = findViewById(R.id.register);
        Button backBtn =findViewById(R.id.back);

        button();

        test();

        dateBtn.setOnClickListener(v -> button());

        registerBtn.setOnClickListener(v -> register());

        backBtn.setOnClickListener(v -> intentBack());

        //Spinner用のアダプタとしてインスタンス化
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerItems);
        //spinnerの見せ方
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner spinner = findViewById(R.id.category_spinner);
        spinner.setAdapter(adapter);

        // リスナーを登録
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            //　アイテムが選択された時
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Spinner spinner = (Spinner)parent;
//                String item = (String)spinner.getSelectedItem();
//                textView.setText(item);
//            }
//
//            //　アイテムが選択されなかった
//            public void onNothingSelected(AdapterView<?> parent) {
//                //
//            }
//        });

    }

    public void button() {

        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editText.setText(String.format(Locale.JAPAN, "%02d / %02d / %02d", year, month + 1, dayOfMonth));
            }
        },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void register() {
        EditText etDate = findViewById(R.id.edit_text);
        Spinner spCategory = findViewById(R.id.category_spinner);
        RadioGroup rgInout =(RadioGroup) findViewById(R.id.inout);
        EditText etMoney = findViewById(R.id.editTextNumber);
        String inout = "";
        int money = -1;
        String message = "";

        String date = etDate.getText().toString();
        String category = (String) spCategory.getSelectedItem();
        int intInout = rgInout.getCheckedRadioButtonId();
        if (intInout != -1) {
            RadioButton radioButton = (RadioButton) findViewById(intInout);
            inout = radioButton.getText().toString();
        }
        String strMoney = etMoney.getText().toString();
        if (!strMoney.isEmpty()) {
            money = Integer.parseInt(etMoney.getText().toString());
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("date",date);
        contentValues.put("category",category);
        contentValues.put("inout",inout);
        contentValues.put("money",money);

        if (!date.isEmpty() && money != -1 && money != 0) {
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

            db.insert("budget", null, contentValues);
            contentValues.clear();

            message = "登録完了";

            etMoney.setText("");
        } else {
            message = "入力されていない項目があります";

            etMoney.setText("");
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void intentBack() {
        Intent intent = new Intent(this, MainActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            finish();
        }
    }

    private void test() {
        EditText etMoney = findViewById(R.id.editTextNumber);
        String money = etMoney.getText().toString();
        Log.d("テスト", money);
    }
}
