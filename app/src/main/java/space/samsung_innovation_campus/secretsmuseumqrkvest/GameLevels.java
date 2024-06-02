package space.samsung_innovation_campus.secretsmuseumqrkvest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.game_levels);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level1 = save.getInt("Level1", 1);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Button button_back = (Button)findViewById(R.id.button_back);

        //Кнопка для перехода на 1 уровень
        TextView textView1 = (TextView)findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                   Intent intent = new Intent(GameLevels.this, Level1.class);
                   startActivity(intent);
                   finish();
                }
                catch (Exception ex){

                }
            }
        });


        //Кнопка для перехода на 2 уровень
        TextView textView2 = (TextView)findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if (level1 >=2) {
                        Intent intent = new Intent(GameLevels.this, Level2.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception ex){

                }
            }
        });


        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    // создаём намеренье
                    Intent intent = new Intent(GameLevels.this, MainActivity2.class);
                    startActivity(intent);
                    //закрываем старую активность
                    finish();
                }
                catch (Exception e){

                }
            }
        });
        final int[] x ={R.id.textView1, R.id.textView2,R.id.textView3, R.id.textView4,
                R.id.textView5, R.id.textView6, R.id.textView7, R.id.textView8, R.id.textView9,
                R.id.textView10, R.id.textView11, R.id.textView12, R.id.textView13, R.id.textView14,
                R.id.textView15, R.id.textView16, R.id.textView17, R.id.textView18, R.id.textView19,
                R.id.textView20, R.id.textView21, R.id.textView22, R.id.textView23, R.id.textView24,
                R.id.textView25, R.id.textView26, R.id.textView27, R.id.textView28, R.id.textView29,
                R.id.textView30};

        for (int i = 1 ; i< level1; i++){
            TextView tv = findViewById(x[i]);
            tv.setText(""+(i+1));
        }
    }
}