package space.samsung_innovation_campus.secretsmuseumqrkvest;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity2 extends AppCompatActivity {

    private long backPresedTime;
    Button scanBtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });













        Button buttonStart = (Button) findViewById(R.id.buttonStart);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(MainActivity2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex){
                  //  Toast toast = new Toast("aaaaaa").show();
                }
            }
        });


        Button buttonQR = (Button) findViewById(R.id.buttonQR);

        buttonQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                    startActivity(intent);
                    // finish();
                }
                catch (Exception ex){
                    //  Toast toast = new Toast("aaaaaa").show();
                }
            }
        });




// полноэкранный режим без верхней панели
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //скрывает панель ситемную управления
     //   w.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }


    //Системная кнопка назад Cntrl+O

    @Override
    public void onBackPressed() {
        if (backPresedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            backPresedTime = System.currentTimeMillis();
            Toast.makeText(getBaseContext(), "Нажмите ещё раз, чтобы выйти", Toast.LENGTH_SHORT).show();
        }
    }



   /* public void onBackPressed() {

    }*/
}