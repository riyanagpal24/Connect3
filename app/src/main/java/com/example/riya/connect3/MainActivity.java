package com.example.riya.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow, 1 = green
    int activePlayer =0;

    boolean gameIsActive = true;
    // 2 means unplayed
    int gameState[] = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8},{2,4,6}, {0,4,8} };
    LinearLayout ll;
    GridLayout gl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll = (LinearLayout) findViewById(R.id.linearLayout);
        gl = (GridLayout) findViewById(R.id.gridLayout);
    }

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        TextView msg =(TextView) findViewById(R.id.textView);

        int tapped = Integer.parseInt(counter.getTag().toString());



        if(gameState[tapped] == 2  && gameIsActive == true){

            gameState[tapped] = activePlayer;
            counter.setTranslationY(-1000f);

            if(activePlayer == 0){
                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            }else{

                counter.setImageResource(R.drawable.green);

                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(300);

            for(int[] winningPosition : winningPositions){

                if((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]])
                        &&  gameState[winningPosition[0]] != 2){

                        gameIsActive = false;
                        String winner = "Green";
                        if(gameState[winningPosition[0]] == 0)
                            winner = "Yellow";

                        msg.setText(winner + " has won.");

                        ll.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameIsOver = true;
                    for(int counterState : gameState){
                        if (counterState == 2)
                            gameIsOver = false;
                    }
                    /*
                    int i=0;
                    while(gameState[i] != 2 &&  i < gameState.length){
                        i++;
                    }
                    if(i == 8)
                        gameIsOver = false;
                    */
                        if(gameIsOver){
                        gameIsActive = false;
                        msg.setText("It is a draw");
                        ll.setVisibility(View.VISIBLE);
                    }

                }
            }
        }

        else{
            Toast.makeText(this,"Oops! Don't Touch Me..", Toast.LENGTH_LONG).show();
        }



    }

    public void playAgain(View view) {

        gameIsActive = true;
        ll.setVisibility(View.INVISIBLE);
        activePlayer =0;
        for(int i =0 ; i<gameState.length; i++){
            gameState[i] = 2;
        }

        for(int j =0; j<gl.getChildCount(); j++){
            ((ImageView)gl.getChildAt(j)).setImageResource(0);
        }
    }
}
