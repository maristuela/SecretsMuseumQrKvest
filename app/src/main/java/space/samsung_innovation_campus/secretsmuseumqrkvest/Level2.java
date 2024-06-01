package space.samsung_innovation_campus.secretsmuseumqrkvest;

import android.annotation.SuppressLint;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Level2 extends AppCompatActivity {

    Dialog dialog;
    Dialog dialogend;
    public int count_true = 0;// Счетчик правильных ответов
    public  int count = 0;// Счетчик ответов
    Array array = new Array();

//    final int[] progress = {
//            R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5, R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10
//    };


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.quiz_level_four_answers);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // полноэкранный режим без верхней панели
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

     //   TextView text_levels = findViewById(R.id.text_levets);
   //     text_levels.setText(R.string.ball + count_true);

        TextView text_ball = (TextView)findViewById(R.id.text_bal);
        text_ball.setText(count_true + "");

          //вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); //скрываем заголовок
        dialog.setContentView(R.layout.previewdialog); //путь к диалоговому окну
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); //прозрачный фон диалогового окна
        dialog.setCancelable(false); //окно нельзя закрыть кнопкой Назад
        //устанавливаем картинку в диалоговое окно
        ImageView previewimg = (ImageView) dialog.findViewById(R.id.previewimg);
        previewimg.setImageResource(R.drawable.previeimglevel2);
        //устанавливаем описание задания
        TextView textdescription = dialog.findViewById(R.id.textdescription);
        textdescription.setText(R.string.leveltwo);
        dialog.show();



        //Кнопка которая закрывает диалогое окно
        TextView btnclose = (TextView)dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent intent = new Intent(Level2.this, GameLevels.class);
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
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                }
                catch (Exception ex){

                }
                dialogend.dismiss(); //закрываем диалоговое окно
            }
        });



        //Кнопка назад
        Button button_back = (Button)findViewById(R.id.button_back) ;
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    // создаём намеренье
                    Intent intent = new Intent(Level2.this, GameLevels.class);
                    startActivity(intent);
                    //закрываем старую активность
                    finish();
                }
                catch (Exception e){

                }
            }
        });

        TextView text_ans1 = findViewById(R.id.text_ans1);
        TextView text_ans2 = findViewById(R.id.text_ans2);
        TextView text_ans3 = findViewById(R.id.text_ans3);
        TextView text_ans4 = findViewById(R.id.text_ans4);
        //выбираем текст
        text_ans1.setText(array.level2_answers[0][0]);
        text_ans2.setText(array.level2_answers[0][1]);
        text_ans3.setText(array.level2_answers[0][2]);
        text_ans4.setText(array.level2_answers[0][3]);
         //   .setImageResource(R.drawable.true_levelone);
         //   img_right.setImageResource(R.drawable.false_levelone);
        //выбираем вопрос
        TextView questio = findViewById(R.id.text_ex);
        questio.setText(array.level2_questions[0]);
//        //Подключаем анимацию
//        final Animation a = AnimationUtils.loadAnimation(Level2.this, R.anim.alpha);
//

            //обрабатыем нажание на кнопки
        //обрабатываем нажатие на правую картинку
        text_ans1.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //коснулся экрана
                    text_ans3.setEnabled(false);// блокируем правую картинку
                    text_ans4.setEnabled(false);// блокируем правую картинку
                    text_ans2.setEnabled(false);// блокируем правую картинку
                    if (0 == array.level2_answers_int[count]){
                       // Toast.makeText(getApplicationContext(),"Верно", Toast.LENGTH_SHORT).show();
                        text_ans1.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_green);
                        count ++;
                        count_true ++;
                        text_ball.setText(count_true + "");
                    }
                    else {
                      //  Toast.makeText(getApplicationContext()," Не Верно", Toast.LENGTH_SHORT).show();
                        text_ans1.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_red);
                        count ++;
                    }

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if (count == 10){
                        //выход из уровня
                        //сохранение програсса
                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level1", 2);
                        if (level < 3) {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level1", 3);
                            editor.apply();
                        }
                        TextView procent = (TextView) dialogend.findViewById(R.id.procent);
                        procent.setText(count_true * 10 + " %");
                        dialogend.show();
                    }
                    else{
                        text_ans1.setBackgroundResource(R.drawable.style_text_ansver_stroke_main);
                        questio.setText(array.level2_questions[count]);
                        text_ans1.setText(array.level2_answers[count][0]);
                        text_ans2.setText(array.level2_answers[count][1]);
                        text_ans3.setText(array.level2_answers[count][2]);
                        text_ans4.setText(array.level2_answers[count][3]);
                        text_ans2.setEnabled(true);
                        text_ans3.setEnabled(true);
                        text_ans4.setEnabled(true);
                    }
                }
                //возвращает правду, когда обрабатывается событие  поднял палец
                return true;
            }
        });
      //  TextView tv = findViewById(progress[count-1]);
                    //убрал палец от экрана
                //    if (1 == array.level1_answers[count-1]){
               //         tv.setBackgroundResource(R.drawable.style_points_green);
               //     }else{
                //        tv.setBackgroundResource(R.drawable.style_points_red);
               //     }

        //обрабатываем нажатие на правую картинку
        text_ans2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //коснулся экрана
                    text_ans3.setEnabled(false);// блокируем правую картинку
                    text_ans4.setEnabled(false);// блокируем правую картинку
                    text_ans1.setEnabled(false);// блокируем правую картинку
                    if (1 == array.level2_answers_int[count]){
                       // Toast.makeText(getApplicationContext(),"Верно", Toast.LENGTH_SHORT).show();
                        text_ans2.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_green);
                        count ++;
                        count_true ++;
                        text_ball.setText(count_true + "");
                    }
                    else {
                       // Toast.makeText(getApplicationContext()," Не Верно", Toast.LENGTH_SHORT).show();
                        text_ans2.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_red);
                        count ++;
                    }

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                  //  TextView tv = findViewById(progress[count-1]);
                    //убрал палец от экрана
//                    if (0 == array.level1_answers[count-1]){
//                        tv.setBackgroundResource(R.drawable.style_points_green);
//                    }else{
//                        tv.setBackgroundResource(R.drawable.style_points_red);
//                    }
                    if (count == 10){
                        //ВЫХОД ИЗ УРОВНЯ
                        //сохранение програсса

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level1", 2);
                        if (level>2){
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level1", 3);
                            editor.commit();
                        }


                        TextView procent = (TextView) dialogend.findViewById(R.id.procent);
                        procent.setText(count_true * 10 + " %");
                        dialogend.show();
                    }
                    else{
                        //выбираем картинки
//                        img_right.setImageResource(R.drawable.false_levelone);
//                        img_right.startAnimation(a);
                        text_ans2.setBackgroundResource(R.drawable.style_text_ansver_stroke_main);
                        questio.setText(array.level2_questions[count]);
                        text_ans1.setText(array.level2_answers[count][0]);
                        text_ans2.setText(array.level2_answers[count][1]);
                        text_ans3.setText(array.level2_answers[count][2]);
                        text_ans4.setText(array.level2_answers[count][3]);
                        text_ans1.setEnabled(true);
                        text_ans3.setEnabled(true);
                        text_ans4.setEnabled(true);
                    }
                }
                //возвращает правду, когда обрабатывается событие  поднял палец
                return true;
            }
        });





        //обрабатываем нажатие на правую картинку
        text_ans3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //коснулся экрана
                    text_ans1.setEnabled(false);// блокируем правую картинку
                    text_ans4.setEnabled(false);// блокируем правую картинку
                    text_ans2.setEnabled(false);// блокируем правую картинку
                    if (2 == array.level2_answers_int[count]){
                        // Toast.makeText(getApplicationContext(),"Верно", Toast.LENGTH_SHORT).show();
                        text_ans3.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_green);
                        count ++;
                        count_true ++;
                        text_ball.setText(count_true + "");
                    }
                    else {
                        //  Toast.makeText(getApplicationContext()," Не Верно", Toast.LENGTH_SHORT).show();
                        text_ans3.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_red);
                        count ++;
                    }

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //  TextView tv = findViewById(progress[count-1]);
                    //убрал палец от экрана
//                    if (0 == array.level1_answers[count-1]){
//                        tv.setBackgroundResource(R.drawable.style_points_green);
//                    }else{
//                        tv.setBackgroundResource(R.drawable.style_points_red);
//                    }
                    if (count == 10){
                        //ВЫХОД ИЗ УРОВНЯ
                        //сохранение програсса

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level1", 2);
                        if (level>2){
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level1", 3);
                            editor.commit();
                        }


                        TextView procent = (TextView) dialogend.findViewById(R.id.procent);
                        procent.setText(count_true * 10 + " %");
                        dialogend.show();
                    }
                    else{
                        //выбираем картинки
//                        img_right.setImageResource(R.drawable.false_levelone);
//                        img_right.startAnimation(a);
                        text_ans3.setBackgroundResource(R.drawable.style_text_ansver_stroke_main);
                        questio.setText(array.level2_questions[count]);
                        text_ans1.setText(array.level2_answers[count][0]);
                        text_ans2.setText(array.level2_answers[count][1]);
                        text_ans3.setText(array.level2_answers[count][2]);
                        text_ans4.setText(array.level2_answers[count][3]);
                        text_ans2.setEnabled(true);
                        text_ans1.setEnabled(true);
                        text_ans4.setEnabled(true);
                    }
                }
                //возвращает правду, когда обрабатывается событие  поднял палец
                return true;
            }
        });
        //  TextView tv = findViewById(progress[count-1]);
        //убрал палец от экрана
        //    if (1 == array.level1_answers[count-1]){
        //         tv.setBackgroundResource(R.drawable.style_points_green);
        //     }else{
        //        tv.setBackgroundResource(R.drawable.style_points_red);
        //     }

        //обрабатываем нажатие на правую картинку
        text_ans4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //Условие касания картинки
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //коснулся экрана
                    text_ans3.setEnabled(false);// блокируем правую картинку
                    text_ans2.setEnabled(false);// блокируем правую картинку
                    text_ans1.setEnabled(false);// блокируем правую картинку
                    if (3 == array.level2_answers_int[count]){
                        Toast.makeText(getApplicationContext(),"Верно", Toast.LENGTH_SHORT).show();
                        text_ans4.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_green);
                        count ++;
                        count_true ++;
                        text_ball.setText(count_true + "");
                    }
                    else {
                        Toast.makeText(getApplicationContext()," Не Верно", Toast.LENGTH_SHORT).show();
                        text_ans4.setBackgroundResource(R.drawable.style_text_ansver_stroke_main_press_red);
                        count ++;
                    }

                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //  TextView tv = findViewById(progress[count-1]);
                    //убрал палец от экрана
//                    if (0 == array.level1_answers[count-1]){
//                        tv.setBackgroundResource(R.drawable.style_points_green);
//                    }else{
//                        tv.setBackgroundResource(R.drawable.style_points_red);
//                    }
                    if (count == 10){
                        //ВЫХОД ИЗ УРОВНЯ
                        //сохранение програсса

                        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
                        final int level = save.getInt("Level1", 2);
                        if (level>2){
                            //пусто
                        }else {
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level1", 3);
                            editor.commit();
                        }


                        TextView procent = (TextView) dialogend.findViewById(R.id.procent);
                        procent.setText(count_true * 10 + " %");
                        dialogend.show();
                    }
                    else{
                        //выбираем картинки
//                        img_right.setImageResource(R.drawable.false_levelone);
//                        img_right.startAnimation(a);
                        text_ans4.setBackgroundResource(R.drawable.style_text_ansver_stroke_main);
                        questio.setText(array.level2_questions[count]);
                        text_ans1.setText(array.level2_answers[count][0]);
                        text_ans2.setText(array.level2_answers[count][1]);
                        text_ans3.setText(array.level2_answers[count][2]);
                        text_ans4.setText(array.level2_answers[count][3]);
                        text_ans1.setEnabled(true);
                        text_ans3.setEnabled(true);
                        text_ans2.setEnabled(true);
                    }
                }
                //возвращает правду, когда обрабатывается событие  поднял палец
                return true;
            }
        });
//
//
//
//
//
//
//
//
//
//
//

    }

    @Override
    public void onBackPressed() {
        try{
            super.onBackPressed();
            // создаём намеренье
            Intent intent = new Intent(Level2.this, GameLevels.class);
            startActivity(intent);
            //закрываем старую активность
            finish();
        }
        catch (Exception e){

        }
    }
}