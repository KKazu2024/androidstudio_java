package jp.ac.kadai05_ih13b_17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private boolean flg = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //インスタンス化
        ImageView[] player = new ImageView[3];
        player[0] = findViewById(R.id.p_rock);
        player[1] = findViewById(R.id.p_scissors);
        player[2] = findViewById(R.id.p_paper);

        Button reset = findViewById(R.id.button);
        Button again = findViewById(R.id.button2);

        reset.setVisibility(View.GONE);
        again.setVisibility(View.GONE);

        Intent intent = getIntent();
        int beforeWin = intent.getIntExtra("win",0);
        int beforeLose = intent.getIntExtra("lose",0);
        int beforeAiko = intent.getIntExtra("aiko",0);

        for(int i = 0; i < player.length; i++){
            player[i].setOnClickListener(view -> {
                if(flg){
                    doJanken(view, beforeWin, beforeLose, beforeAiko);
                }
            });
        }

    }

    private void doJanken(View view, int beforeWin, int beforeLose, int beforeAiko){
        //player処理
        ImageView imageView = (ImageView) view;

        int player = 0;
        switch (imageView.getId()){
            case R.id.p_rock:
                findViewById(R.id.p_scissors).setVisibility(View.INVISIBLE);
                findViewById(R.id.p_paper).setVisibility(View.INVISIBLE);
                break;
            case R.id.p_scissors:
                player = 1;
                findViewById(R.id.p_rock).setVisibility(View.INVISIBLE);
                findViewById(R.id.p_paper).setVisibility(View.INVISIBLE);
                break;
            case R.id.p_paper:
                player = 2;
                findViewById(R.id.p_rock).setVisibility(View.INVISIBLE);
                findViewById(R.id.p_scissors).setVisibility(View.INVISIBLE);
                break;
        }

        //cpu処理
        ImageView img = findViewById(R.id.imageView);
        Random r = new Random();
        int cpu = r.nextInt(3);
        int resId = 0;

        if(cpu == 0){
            resId = R.drawable.rock_cpu;
        }else if(cpu == 1){
            resId = R.drawable.scissors_cpu;
        }else{
            resId = R.drawable.paper_cpu;
        }
        img.setImageResource(resId);

        //勝敗判定処理
        TextView countJudge = findViewById(R.id.textView3);
        TextView judge = findViewById(R.id.textView4);
        String judgeText = "";
        int countWin = 0;
        int countLose = 0;
        int countAiko = 0;
        int jValue = player - cpu;

        if(jValue == 0){
            judgeText = "あいこ";
            countAiko++;
        }else if(jValue == -1 || jValue == 2){
            judgeText = "勝ち";
            countWin++;
        }else{
            judgeText = "負け";
            countLose++;
        }
        countWin += beforeWin;
        countLose += beforeLose;
        countAiko += beforeAiko;
        countJudge.setText(countWin + "勝" + countLose + "敗" + countAiko + "分");
        judge.setText(judgeText);
        flg = false;

        //ボタン処理
        Button reset = findViewById(R.id.button);
        Button again = findViewById(R.id.button2);
        reset.setVisibility(View.VISIBLE);
        again.setVisibility(View.VISIBLE);
        Intent intent = new Intent(this, MainActivity.class);

        reset.setOnClickListener(View -> {
            startActivity(intent);
        });

        int finalCountWin = countWin;
        int finalCountLose = countLose;
        int finalCountAiko = countAiko;
        again.setOnClickListener(View -> {
            intent.putExtra("win", finalCountWin);
            intent.putExtra("lose", finalCountLose);
            intent.putExtra("aiko", finalCountAiko);
            startActivity(intent);
        });
    }

}