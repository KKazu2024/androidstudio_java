package jp.ac.kadai06_ih13b_17;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private CustomImageView[][] civ = new CustomImageView[3][3];
    private boolean isMaru = true;
    private boolean judge = false;
    private int turnCount = 0;
    private int oWin = 0;
    private int oLose = 0;
    private int draw = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ID割り当て
        civ[0][0] = findViewById(R.id.civ00);
        civ[0][1] = findViewById(R.id.civ01);
        civ[0][2] = findViewById(R.id.civ02);

        civ[1][0] = findViewById(R.id.civ10);
        civ[1][1] = findViewById(R.id.civ11);
        civ[1][2] = findViewById(R.id.civ12);

        civ[2][0] = findViewById(R.id.civ20);
        civ[2][1] = findViewById(R.id.civ21);
        civ[2][2] = findViewById(R.id.civ22);

        Button reset = findViewById(R.id.reset);

        TextView oHistory = findViewById(R.id.oHistory);
        TextView xHistory = findViewById(R.id.xHistory);

        //盤面初期化
        init();

        //勝敗履歴
        oHistory.setText("〇：" + oWin + "勝" + oLose + "敗" + draw + "分");
        xHistory.setText("✕：" + oLose + "勝" + oWin + "敗" + draw + "分");

        //盤面変化
        for (int i = 0; i < civ.length; i++){
            for (int j = 0; j < civ.length; j++){
                civ[i][j].setOnClickListener(view -> civClicked(view));
            }
        }

        //リセット
        reset.setOnClickListener(View -> {
            init();
        });
    }

    //画面初期化
    private void init(){
        TextView result = findViewById(R.id.result);
        for (int i = 0; i < civ.length; i++){
            for (int j = 0; j < civ.length; j++){
                civ[i][j].setImageResource(R.drawable.blank);
            }
        }

        result.setText("");
        isMaru = true;
        judge = false;
        turnCount = 0;
    }

    //盤面変化
    private void civClicked(View view){
        CustomImageView clickedCiv = (CustomImageView) view;
        TextView oHistory = findViewById(R.id.oHistory);
        TextView xHistory = findViewById(R.id.xHistory);

        int resId = clickedCiv.getResId();
        if(!judge){
            if(resId == R.drawable.blank){
                int setImg = R.drawable.blank;
                if(isMaru){
                    setImg = R.drawable.maru;
                }else{
                    setImg = R.drawable.batu;
                }
                clickedCiv.setImageResource(setImg);

                turnCount++;
                doJudge(setImg);
                isMaru = !isMaru;
            }
        }
        oHistory.setText("〇：" + oWin + "勝" + oLose + "敗" + draw + "分");
        xHistory.setText("✕：" + oLose + "勝" + oWin + "敗" + draw + "分");
    }

    //判定
    private void doJudge(int player){
        //縦横チェック
        for(int i = 0; i < civ.length; i++){
            int row = 0;
            int column = 0;
            for(int j = 0; j < civ.length; j++){
                if(civ[i][j].getResId() == player){
                    row++;
                }
                if(civ[j][i].getResId() == player){
                    column++;
                }
            }
            if(row == civ.length || column == civ.length){
                judge = true;
            }
        }

        //斜めチェック
        int diagonalL = 0;
        int diagonalR = 0;
        for(int i = 0; i < civ.length; i++){
            if(civ[i][i].getResId() == player){
                diagonalL++;
            }
            if(civ[civ.length - i - 1][i].getResId() == player){
                diagonalR++;
            }
        }
        if(diagonalL == civ.length || diagonalR == civ.length){
            judge = true;
        }

        //結果
        TextView result = findViewById(R.id.result);
        String msg = "";
        if(judge){
            if(player == R.drawable.maru){
                msg = "〇の勝ち";
                oWin++;
            }else{
                msg = "×の勝ち";
                oLose++;
            }
        }
        if(!judge && turnCount == 9) {
            msg = "引き分け";
            draw++;
            judge = true;
        }
        if(judge){
            result.setText(msg);
        }
    }
}