package com.goodjob.singing;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteOrder;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.TarsosDSPAudioFormat;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;
import be.tarsos.dsp.writer.WriterProcessor;

public class PartPracticePlane2 extends AppCompatActivity {
        AudioDispatcher dispatcher;
        TarsosDSPAudioFormat tarsosDSPAudioFormat;  //TarsosDSP Format 세팅

        File file;

        TextView pitchTextView;
        Button pitchButton, pitchbutton2, pitchbutton3, pitchbutton4, pitchbutton5, pitchbutton6, pitchbutton7, pitchbutton8; //recordButton -> pitchButton
        Button playVib;
        ImageButton next, last;
        TextView highPitch;
        TextView lowPitch;
        ImageView pitchline;
        String filename = "recorded_sound.wav";
        float note;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_part_practice_plane2);

            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            File sdCard = Environment.getExternalStorageDirectory();
            file = new File(sdCard, filename);

            tarsosDSPAudioFormat=new TarsosDSPAudioFormat(TarsosDSPAudioFormat.Encoding.PCM_SIGNED,
                    22050,
                    2 * 8,
                    1,
                    2 * 1,
                    22050,
                    ByteOrder.BIG_ENDIAN.equals(ByteOrder.nativeOrder()));

            pitchTextView = findViewById(R.id.pitchTextView);
            pitchButton = findViewById(R.id.pitchButton);
            pitchbutton2 = findViewById(R.id.pitchbutton2);
            pitchbutton3 = findViewById(R.id.pitchbutton3);
            pitchbutton4 = findViewById(R.id.pitchbutton4);
            pitchbutton5 = findViewById(R.id.pitchbutton5);
            pitchbutton6 = findViewById(R.id.pitchbutton6);
            pitchbutton7 = findViewById(R.id.pitchbutton7);
            pitchbutton8 = findViewById(R.id.pitchbutton8);
            highPitch = findViewById(R.id.highpitch);
            lowPitch = findViewById(R.id.lowpitch);
            pitchline = findViewById(R.id.pitchline);
            next = findViewById(R.id.next);
            last= findViewById(R.id.last);
            playVib = findViewById(R.id.playVib);

            playVib.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long[] pattern = {100, 100, 400, 100, 400, 100,900, 100, 400, 100, 400, 100,900};
                    //짝수 인덱스 : 대기시간 . 홀수 인덱스 : 진동시간

                    vibrator.vibrate(pattern, -1); // -1은 반복없음. 0은 무한반복
                }
            });


            next.setOnClickListener(new View.OnClickListener() { //다음마디
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (
                            getApplicationContext(), PartPracticePlane3.class);
                    startActivity(intent);
                }
            });

            last.setOnClickListener(new View.OnClickListener() { //이전마디
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent (
                            getApplicationContext(), PartPracticePlane1.class);
                    startActivity(intent);
                }
            });

            pitchButton.setOnClickListener(new View.OnClickListener() {
                //녹음 버튼을 누르면 녹음 실행

                @Override
                public void onClick(View v) {
                    buttonColored();
                    pitchButton.setSelected(true);
                    recordAudio2(294.000f);
                }
            });

            pitchbutton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonColored();
                    pitchbutton2.setSelected(true);
                    recordAudio2(294.000f);
                }
            });
            pitchbutton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonColored();
                    pitchbutton3.setSelected(true);
                    recordAudio2(294.000f); //레
                }
            });
            pitchbutton6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonColored();
                    pitchbutton5.setSelected(true);
                    recordAudio2(330.000f); //미
                }
            });
            pitchbutton7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonColored();
                    pitchbutton6.setSelected(true);
                    recordAudio2(392.000f); //솔
                }
            });
            pitchbutton8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonColored();
                    pitchbutton7.setSelected(true);
                    recordAudio2(392.000f);
                }
            });

        }



        public void recordAudio2(float note)
        {
            releaseDispatcher();
            dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);

            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file,"rw");
                AudioProcessor recordProcessor = new WriterProcessor(tarsosDSPAudioFormat, randomAccessFile);
                dispatcher.addAudioProcessor(recordProcessor);

                PitchDetectionHandler pitchDetectionHandler = new PitchDetectionHandler() {
                    @Override
                    public void handlePitch(PitchDetectionResult res, AudioEvent e){
                        final float pitchInHz = res.getPitch();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pitchTextView.setText(pitchInHz + "");
                                if(Float.compare(pitchInHz, note - 5.000f) < 0){
                                    pitchline.setColorFilter(null);
                                    lowPitch.setTextColor(Color.parseColor("#e65d5d"));
                                    highPitch.setTextColor(Color.parseColor("#0a0a0a"));
                                }else if(Float.compare(pitchInHz, note + 5.000f) > 0){
                                    pitchline.setColorFilter(null);
                                    lowPitch.setTextColor(Color.parseColor("#0a0a0a"));
                                    highPitch.setTextColor(Color.parseColor("#e65d5d"));
                                }else{
                                    pitchline.setColorFilter(Color.parseColor("#82fa46"), PorterDuff.Mode.SRC_IN);
                                    lowPitch.setTextColor(Color.parseColor("#0a0a0a"));
                                    highPitch.setTextColor(Color.parseColor("#0a0a0a"));

                                }
                            }
                        });
                    }
                };
                AudioProcessor pitchProcessor = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pitchDetectionHandler);
                dispatcher.addAudioProcessor(pitchProcessor);

                Thread audioThread = new Thread(dispatcher, "Audio Thread");
                audioThread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        public void buttonColored(){
            pitchButton.setSelected(false);
            pitchbutton2.setSelected(false);
            pitchbutton3.setSelected(false);
            pitchbutton4.setSelected(false);
            pitchbutton5.setSelected(false);
            pitchbutton6.setSelected(false);
            pitchbutton7.setSelected(false);
        }
        public void releaseDispatcher()
        {
            if(dispatcher != null)
            {
                if(!dispatcher.isStopped())
                    dispatcher.stop();
                dispatcher = null;
            }
        }

        @Override
        protected void onStop() {
            super.onStop();
            releaseDispatcher();
        }
    }
