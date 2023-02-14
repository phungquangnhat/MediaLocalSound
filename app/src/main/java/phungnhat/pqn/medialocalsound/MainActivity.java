package phungnhat.pqn.medialocalsound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {

    ImageButton imgPlayMusic, imgPre, imgNext;
    TextView txtMusic, txtTime;
    Button btnCong5, btnTru5;
    ProgressBar proMusic;
    ArrayList<String> arrayMusic, arrayGif;
    MediaPlayer mediaPlayer;
    GifImageView imgGif;
    int music, gif;
    int i = 0;
    int pauseResumeFlag, pauseLength;
    int timeSe = 0;
    int timeMi = 0;
    int tg1 = 0;
    int tg2, tg3 = 0;
    CountDownTimer countDownTimer;
    int timeMS = 0;
    int clickBtnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();

        String[] mangTen =getResources().getStringArray(R.array.ds_music);
        String[] mangGif =getResources().getStringArray(R.array.ds_gif);

        arrayMusic = new ArrayList<>(Arrays.asList(mangTen));
        arrayGif = new ArrayList<>(Arrays.asList(mangGif));


        music =getResources().getIdentifier(arrayMusic.get(i), "raw", getPackageName());
        txtMusic.setText(arrayMusic.get(i));
        mediaPlayer = MediaPlayer.create(MainActivity.this, music);

        Collections.shuffle(arrayGif);
        gif =getResources().getIdentifier(arrayGif.get(0), "drawable", getPackageName());
        imgGif.setImageResource(gif);


        imgPlayMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //imgPlayMusic.setEnabled(false);

                if (pauseResumeFlag == 1){
                    imgPlayMusic.setImageResource(R.drawable.pause);
                    mediaPlayer.pause();
                    pauseLength = mediaPlayer.getCurrentPosition();

                    pauseResumeFlag = 2;
                    txtMusic.setText("Đang tạm dừng!");
                }
                else if (pauseResumeFlag == 0){
                    imgPlayMusic.setImageResource(R.drawable.play);

                    mediaPlayer.start();
                    txtMusic.setText(arrayMusic.get(i));

                    Collections.shuffle(arrayGif);
                    gif =getResources().getIdentifier(arrayGif.get(0), "drawable", getPackageName());
                    imgGif.setImageResource(gif);
                    pauseResumeFlag = 1;
                }
                else
                {
                    imgPlayMusic.setImageResource(R.drawable.play);
                    mediaPlayer.seekTo(pauseLength);
                    mediaPlayer.start();
                    if (mediaPlayer.isPlaying() == false)
                        pauseResumeFlag = 0;
                    else {
                        pauseResumeFlag = 1;
                    }
                    txtMusic.setText(arrayMusic.get(i));
                }
            }
        });
        imgPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //pause cũ
//                if (pauseResumeFlag == 0){
//                    mediaPlayer.pause();
//                    pauseLength = mediaPlayer.getCurrentPosition();
//
//                    pauseResumeFlag = 1;
//                    txtMusic.setText("Đang tạm dừng!");
//                }
//                else
//                {
//                    mediaPlayer.seekTo(pauseLength);
//                    mediaPlayer.start();
//
//                    pauseResumeFlag = 0;
//                    txtMusic.setText(arrayMusic.get(i - 1));
//                }

                //previus

                Collections.shuffle(arrayGif);
                gif =getResources().getIdentifier(arrayGif.get(0), "drawable", getPackageName());
                imgGif.setImageResource(gif);

                imgPlayMusic.setImageResource(R.drawable.play);
                mediaPlayer.release();
                int index = arrayMusic.size();

                i -= 1;
                if (i < 0)
                    i = index - 1;
                music =getResources().getIdentifier(arrayMusic.get(i), "raw", getPackageName());
                txtMusic.setText(arrayMusic.get(i));
                mediaPlayer = MediaPlayer.create(MainActivity.this, music);
                mediaPlayer.start();
                pauseResumeFlag = 1;
            }
        });
        imgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Collections.shuffle(arrayGif);
                gif =getResources().getIdentifier(arrayGif.get(0), "drawable", getPackageName());
                imgGif.setImageResource(gif);
                timeSe = 0;
                timeMi = 0;
                txtTime.setText("00:00");
                imgPlayMusic.setImageResource(R.drawable.play);
                mediaPlayer.release();
                int index = arrayMusic.size();

                i += 1;
                if (i >= index)
                        i = 0;
                music =getResources().getIdentifier(arrayMusic.get(i), "raw", getPackageName());
                txtMusic.setText(arrayMusic.get(i));
                mediaPlayer = MediaPlayer.create(MainActivity.this, music);
                mediaPlayer.start();
                timeMS = mediaPlayer.getDuration();
                pauseResumeFlag = 1;
                //timeMusic(mediaPlayer.getDuration(), timeSe, timeMi);

                //progressMusic();
                //autoChuyenBai();


            }
        });
        btnCong5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int finalTime = mediaPlayer.getDuration();
                int startTime = mediaPlayer.getCurrentPosition();
                int cong5 = 5000;

                if((startTime+cong5)<=finalTime){
                    startTime = startTime + cong5;
                    mediaPlayer.seekTo(startTime);
                    Toast.makeText(getApplicationContext(),"Tua nhanh 5s",Toast.LENGTH_SHORT).show();
                }
                else{
                    imgPlayMusic.setImageResource(R.drawable.play);
                    mediaPlayer.release();
                    int index = arrayMusic.size();

                    i += 1;
                    if (i >= index)
                        i = 0;
                    music =getResources().getIdentifier(arrayMusic.get(i), "raw", getPackageName());
                    txtMusic.setText(arrayMusic.get(i));
                    mediaPlayer = MediaPlayer.create(MainActivity.this, music);
                    mediaPlayer.start();
                    pauseResumeFlag = 1;
                }

            }
        });

        btnTru5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int finalTime = mediaPlayer.getDuration();
                int startTime = mediaPlayer.getCurrentPosition();
                int cong5 = 5000;

                if((startTime-cong5) > 0){
                    startTime = startTime - cong5;
                    mediaPlayer.seekTo(startTime);
                    Toast.makeText(getApplicationContext(),"Tua lại 5s",Toast.LENGTH_SHORT).show();
                }
                else{
                    imgPlayMusic.setImageResource(R.drawable.play);
                    mediaPlayer.release();
                    int index = arrayMusic.size();

                    i -= 1;
                    if (i < 0)
                        i = index - 1;
                    music =getResources().getIdentifier(arrayMusic.get(i), "raw", getPackageName());
                    txtMusic.setText(arrayMusic.get(i));
                    mediaPlayer = MediaPlayer.create(MainActivity.this, music);
                    mediaPlayer.start();
                    pauseResumeFlag = 1;
                }
            }
        });
    }

    private void timeMusic(int max, int timeSe1, int timeMi1){
        countDownTimer = new CountDownTimer(max, 1000) {
            @Override
            public void onTick(long l) {
                if (timeMS != mediaPlayer.getDuration()){
                    countDownTimer.cancel();
                }
                if (timeSe < max/1000){
                    timeSe += 1;
                    if (timeSe == 60){
                        timeSe = 0;
                        timeMi += 1;
                    }
                    if (timeMi == 0)
                    {
                        if (timeSe < 10)
                            txtTime.setText("0" + timeMi + ":0" + timeSe);
                        else
                            txtTime.setText("0" + timeMi + ":" + timeSe);
                    }
                    else{
                        if (timeSe < 10)
                            txtTime.setText("0" + timeMi + ":0" + timeSe);
                        else
                            txtTime.setText("0" + timeMi + ":" + timeSe);
                    }
                }
                else {
                    this.cancel();
                }


            }
            @Override
            public void onFinish() {
                    this.cancel();
            }
        };
        countDownTimer.start();
    }

    private void autoChuyenBai(){
        Log.d("AAA", mediaPlayer.getDuration() + "");
        tg1 = 0;
        countDownTimer = new CountDownTimer(mediaPlayer.getDuration(), 1000) {
            @Override
            public void onTick(long l) {
                tg1 += 1000;
                Log.d("BBB", tg1 + "");
                float tg = mediaPlayer.getDuration()%1000;
                if (tg1 + tg >= mediaPlayer.getDuration())
                {
                    Log.d("CCC", (tg1 + tg) + "");
                    this.cancel();
                    timeSe = 0;
                    timeMi = 0;
                    txtTime.setText("00:00");
                    imgPlayMusic.setImageResource(R.drawable.play);
                    mediaPlayer.release();
                    int index = arrayMusic.size();

                    i += 1;
                    if (i >= index)
                        i = 0;
                    music =getResources().getIdentifier(arrayMusic.get(i), "raw", getPackageName());
                    txtMusic.setText(arrayMusic.get(i));
                    mediaPlayer = MediaPlayer.create(MainActivity.this, music);
                    mediaPlayer.start();
                    pauseResumeFlag = 1;
                    timeMusic(mediaPlayer.getDuration(), timeSe, timeMi);
                    progressMusic();
                    autoChuyenBai();
                }
            }

            @Override
            public void onFinish() {
                this.cancel();
            }
        };
        countDownTimer.start();
    }

    private void progressMusic(){
        tg2 = 0;
        tg3 = 0;
        proMusic.setMax(mediaPlayer.getDuration()/1000);
        countDownTimer = new CountDownTimer(mediaPlayer.getDuration(), 1000) {
            @Override
            public void onTick(long l) {
                tg2 += 1000;
                tg3 += 1;
                float tg = mediaPlayer.getDuration()%1000;
                proMusic.setProgress(tg1);
                if (tg2 + tg <= mediaPlayer.getDuration()){
                    proMusic.setProgress(tg3);
                }
                else{
                    this.cancel();
                }
            }
            @Override
            public void onFinish() {
                this.cancel();
            }
        };
        countDownTimer.start();
    }
    private void AnhXa() {
        imgPlayMusic = findViewById(R.id.imgPlayMusic);
        imgPre = findViewById(R.id.imgPre);
        imgNext = findViewById(R.id.imgNext);
        txtMusic = findViewById(R.id.txtMusic);
        btnCong5 = findViewById(R.id.btnCong5);
        btnTru5 = findViewById(R.id.btnTru5);
        txtTime = findViewById(R.id.txtTime);
        proMusic = findViewById(R.id.proMusic);
        imgGif = findViewById(R.id.imgGif);
    }
}