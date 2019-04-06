package com.example.myapplicationtic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
  GridLayout gridLayout;

    int j = 0;
    int[] a = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean gameIsActive = true;
    int[][] winpos = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        int strike = Integer.parseInt(counter.getTag().toString());
        if (a[strike] == 2 && gameIsActive) {
            counter.setTranslationY(-1000f);
            a[strike] = j;
            if (j == 0) {
                counter.setImageResource(R.drawable.redim);
                j = 1;
            } else {
                counter.setImageResource(R.drawable.yel);
                j = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int i = 0; i < 8; i++) {
                if (a[winpos[i][0]] == a[winpos[i][1]] && a[winpos[i][1]] == a[winpos[i][2]] && a[winpos[i][0]] != 2) {
                    System.out.println(winpos[i][0] + " " + winpos[i][1] + " " + winpos[i][2]);
                    System.out.println(a[winpos[i][0]] + " " + a[winpos[i][1]] + " " + a[winpos[i][2]]);
                    String str;
                    gameIsActive = false;
                    if (a[winpos[i][0]] == 1)
                        str = "yellow";
                    else
                        str = "Orange";
                    TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                    winnerMessage.setText(str + " has won");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    System.out.println("************************");
                    break;
                } else {
                    boolean gameIsOver = true;
                    for (int k = 0; k < a.length; k++) {
                        if (a[k] == 2)
                            gameIsOver = false;
                    }
                    if (gameIsOver) {
                        TextView winnerMessage = (TextView) findViewById(R.id.winnerMessage);
                        winnerMessage.setText("     it is draw");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }


            }
        }
    }

    public void playAgain(View view) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        gameIsActive = true;
        j = 0;
        for (int i = 0; i < a.length; i++)
            a[i] = 2;
//        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        //  GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < 9; i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLayout);
    }
}
