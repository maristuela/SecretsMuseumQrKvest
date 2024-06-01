package space.samsung_innovation_campus.secretsmuseumqrkvest;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level1 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogend;

    public int count_true = 0;// Счетчик правильных ответов
    public  int count = 0;// Счетчик ответов
    Array array = new Array();

//    final int[] progress = {
//            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.universal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        TextView text_levels = findViewById(R.id.text_levets);
//        text_levels.setText(R.string.level1);
        TextView text_ball = findViewById(R.id.text_ball);
        text_ball.setText(count_true + "");

        final ImageView img_left = (ImageView)findViewById(R.id.img_left) ;
        final ImageView img_right = (ImageView)findViewById(R.id.img_right) ;
        //выбираем вопрос
        TextView questio = findViewById(R.id.text_ex);
        questio.setText(array.level1_questions[count]);

        //Скругляем углы у картинки
        img_left.setClipToOutline(true);

        img_right.setClipToOutline(true);

        // полноэкранный режим без верхней панели
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к диалоговому окну
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой Назад

        //Кнопка которая закрывает диалогое окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex){

                }
                dialog.dismiss(); //закрываем диалоговое окно
            }
        });

        // кнопка продолжить
        Button btncontinue = (Button) dialog.findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();



        //_____________________________________________________________________
        //вызов диалогового окна в конце игры
        dialogend = new Dialog(this);
        dialogend.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialogend.setContentView(R.layout.dialogend); //путь к диалоговому окну
        dialogend.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialogend.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialogend.setCancelable(false); //окно нельзя закрыть кнопкой Назад

        //Кнопка которая закрывает диалогое окно
        TextView btnclose2 = (TextView)dialogend.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex){

                }
                dialogend.dismiss(); //закрываем диалоговое окно
            }
        });

        // кнопка продолжить
        Button btncontinue2 = (Button) dialogend.findViewById(R.id.btncontinue);
        btncontinue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Level1.this, Level2.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex){

                }
                dialog.dismiss();
            }
        });

        //_____________________________________________________________________











        //Кнопка назад
        Button button_back = (Button)findViewById(R.id.button_back) ;
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    // создаём намеренье
                    Intent intent = new Intent(Level1.this, GameLevels.class);
                    startActivity(intent);
                    //закрываем старую активность
                    finish();
                }
                catch (Exception e){

                }
            }
        });

        //выбираем картинки
//        img_left.setImageResource(R.drawable.true_levelone);
//        img_right.setImageResource(R.drawable.false_levelone);

        //Подключаем анимацию
        final Animation a = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha);
        final Animation a_true = AnimationUtils.loadAnimation(Level1.this, R.anim.alpha_tru);


        //Обрабатываем нажатие на левую картинку
        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //коснулся экрана
                    img_right.setEnabled(false);// блокируем правую картинку
                    if (1 == array.level1_answers[count]){
                       // Toast.makeText(getApplicationContext(),"Верно", Toast.LENGTH_SHORT).show();
                        img_left.startAnimation(a_true);
                        img_left.setImageResource(R.drawable.ansver_true1);
                        count ++;
                        count_true ++;
                        text_ball.setText(count_true + "");
                     //   text_levels.setText(R.string.ball + count_true);
                    }
                    else {
                       // Toast.makeText(getApplicationContext()," Не Верно", Toast.LENGTH_SHORT).show();
                        img_left.startAnimation(a_true);
                        img_left.setImageResource(R.drawable.ansver_false);
                        count ++;
                    }

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                //    TextView tv = findViewById(progress[count-1]);
                    //убрал палец от экрана
                    if (1 == array.level1_answers[count-1]){
                     //   tv.setBackgroundResource(R.drawable.style_points_green);
                    }else{
                     //   tv.setBackgroundResource(R.drawable.style_points_red);
                    }
                    if (count == 10){
                        //ВЫХОД ИЗ УРОВНЯ

                        //сохранение програсса
                        if (count_true >= 7){
                            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                            final int level = save.getInt("Level1", 1);
                            if (level>1){
                                //пусто
                            }else {
                                SharedPreferences.Editor editor = save.edit();
                                editor.putInt("Level1", 2);
                                editor.commit();
                            }
                        }
                        else{
                            TextView textView = (TextView) dialogend.findViewById(R.id.textdescription);
                            textView.setText(R.string.theend);
                            Button btn = dialogend.findViewById(R.id.btncontinue);
                            btn.setText(R.string.startend);
                        }


                        TextView procent = (TextView) dialogend.findViewById(R.id.procent);
                        procent.setText(count_true * 10 + " %");
                        dialogend.show();
                    }
                    else{
                        //выбираем картинки
                        img_left.setImageResource(R.drawable.truth);
                        img_left.startAnimation(a);
                        questio.setText(array.level1_questions[count]);
                        img_right.setEnabled(true);
                    }
                }
                //возвращает правду, когда обрабатывается событие  поднял палец
                return true;
            }
        });

        //обрабатываем нажатие на правую картинку
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //коснулся экрана
                    img_left.setEnabled(false);// блокируем правую картинку
                    if (0 == array.level1_answers[count]){
                     //   Toast.makeText(getApplicationContext(),"Верно", Toast.LENGTH_SHORT).show();
                        img_right.startAnimation(a_true);
                        img_right.setImageResource(R.drawable.ansver_true1);
                        count ++;
                        count_true ++;
                        text_ball.setText(count_true + "");
                    //    text_levels.setText(R.string.ball + count_true);
                    }
                    else {
                      //  Toast.makeText(getApplicationContext()," Не Верно", Toast.LENGTH_SHORT).show();
                        img_right.startAnimation(a_true);
                        img_right.setImageResource(R.drawable.ansver_false);
                        count ++;
                    }

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                //    TextView tv = findViewById(progress[count-1]);
                    //убрал палец от экрана
                    if (0 == array.level1_answers[count-1]){
                     //   tv.setBackgroundResource(R.drawable.style_points_green);
                    }else{
                    //    tv.setBackgroundResource(R.drawable.style_points_red);
                    }
                    if (count == 10){
                        //ВЫХОД ИЗ УРОВНЯ

                        //сохранение програсса
                        if (count_true >= 7){
                            SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                            final int level = save.getInt("Level1", 1);
                            if (level>1){
                                //пусто
                            }else {
                                SharedPreferences.Editor editor = save.edit();
                                editor.putInt("Level1", 2);
                                editor.commit();
                            }
                        }
                        else{
                            TextView textView = (TextView) dialogend.findViewById(R.id.textdescription);
                            textView.setText(R.string.theend);
                            Button btn = dialogend.findViewById(R.id.btncontinue);
                            btn.setText(R.string.startend);
                        }


                        TextView procent = (TextView) dialogend.findViewById(R.id.procent);
                        procent.setText(count_true * 10 + " %");
                        dialogend.show();
                    }
                    else{
                        //выбираем картинки
                        img_right.setImageResource(R.drawable.lie);
                        img_right.startAnimation(a);
                        questio.setText(array.level1_questions[count]);
                        img_left.setEnabled(true);
                    }
                }
                //возвращает правду, когда обрабатывается событие  поднял палец
                return true;
            }
        });












    }

    @Override
    public void onBackPressed() {
        try{
            super.onBackPressed();
            // создаём намеренье
            Intent intent = new Intent(Level1.this, GameLevels.class);
            startActivity(intent);
            //закрываем старую активность
            finish();
        }
        catch (Exception e){

        }
    }
}