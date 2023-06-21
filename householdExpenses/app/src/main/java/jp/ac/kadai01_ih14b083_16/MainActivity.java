package jp.ac.kadai01_ih14b083_16;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView moveRegisterBtn1 = findViewById(R.id.register);
        ImageView moveRegisterBtn2 = findViewById(R.id.register_image);
        moveRegisterBtn1.setOnClickListener(v -> intentRegister());
        moveRegisterBtn2.setOnClickListener(v -> intentRegister());

        TextView moveReferBtn1 = findViewById(R.id.refer);
        ImageView moveReferBtn2 = findViewById(R.id.refer_image);
        moveReferBtn1.setOnClickListener(v -> intentRefer());
        moveReferBtn2.setOnClickListener(v -> intentRefer());
    }

    private void intentRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void intentRefer() {
        Intent intent = new Intent(this, ReferActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}