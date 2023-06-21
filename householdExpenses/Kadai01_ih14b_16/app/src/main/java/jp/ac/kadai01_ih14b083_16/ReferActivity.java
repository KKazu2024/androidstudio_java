package jp.ac.kadai01_ih14b083_16;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ReferActivity extends AppCompatActivity {

    private final String[] dataset = new String[400];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refer);
        Button backBtn = findViewById(R.id.back);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        backBtn.setOnClickListener(v -> intentBack());

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(this);

        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();

        //抽出用配列作成
        String[] col = new String[]{"_id", "date", "category", "inout", "money"};

        //カーソルで位置管理
        Cursor cursor =  db.query("budget", col, null, null, null, null, null);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int i = 0;
        boolean flag = cursor.moveToFirst();

        while (flag) {
            dataset[i] = cursor.getString(1) + "   " + cursor.getString(2) + "   " + cursor.getString(3) + "   " + cursor.getInt(4) + "円";;
            flag = cursor.moveToNext();
            i++;
        }

        MyAdapter adapter = new MyAdapter(dataset);
        recyclerView.setAdapter(adapter);

    }

    private void intentBack() {
        Intent intent = new Intent(this, MainActivity.class);
        if (intent.resolveActivity(getPackageManager()) != null) {
            finish();
        }
    }
}
