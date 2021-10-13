package com.example.pok.mythread;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button runBtn;
    TextView tv1;
    Button runBtn2;
    TextView tv2;
    ProgressBar pb, pb2;
    ImageView imv, imv2;
    SeekBar seekBar;

    int i = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        runBtn = findViewById(R.id.button1);
        tv1 = findViewById(R.id.tv1);
        pb = findViewById(R.id.progressBar);
        imv = findViewById(R.id.imageView);
        pb2 = findViewById(R.id.progressBar2);
        imv2 = findViewById(R.id.imageView2);
        seekBar = findViewById(R.id.seekBar2);

        tv1.setEnabled(false);
        //imv.setImageResource(R.drawable.mario);
        //Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").placeholder(R.drawable.mario).into(imv);
        //Picasso.with(this).setLoggingEnabled(true);

        runBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outputStr += "\n";
                example6();
            }
        });

        /*final MediaPlayer mPlayer = MediaPlayer.create(MainActivity.this, R.raw.mysoundfile);
        mPlayer.start();
        mPlayer.stop(); mPlayer.prepare();
        mPlayer.pause();
        mPlayer.setVolume(0.5f, 0.5f);*/ //0~1

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float v = progress/100.0f;
                //mPlayer.setVolume(v,v);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    String outputStr = "";
    //1. Create 1 Button -> setOnClickListener -> example1()
    //1. Create 1 Textview
    private void example1() {
        //1 show sequential
        for (int i = 0; i < 5; i++) {
            outputStr += "A";
        }
        for (int i = 0; i < 5; i++) {
            outputStr += "B";
        }

        for (int i = 0; i < 5; i++) {
            outputStr += "C";
        }

        tv1.setText(outputStr);
    }

    private void example2_() {
        //1 show sequential
        for (int i = 0; i < 5; i++) {
            outputStr += "A";
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    outputStr += "B";
                }
            }
        }).start();

        for (int i = 0; i < 50; i++) {
            outputStr += "C";
        }

        tv1.setText(outputStr);
    }


    private void example2() {

        //Rule 1: Not block main thread
        //Generated App Not Responding (ANR)
       /* try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    outputStr += "A";
                }
                //Rule2: Not access View(UI) inside a worker thread
                //Generated error
                //tv1.setText("hello");
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 5; i++) {
                    outputStr += "B";
                }
            }
        }).start();

        tv1.setText(outputStr);

    }

    private void example3_() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Count 1...100 delay 10 sec
                for(int i=1; i<=100; i++){
                    final int x = i;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText(String.valueOf(x));
                        }
                    });
                  /*  tv1.post(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText(String.valueOf(x));
                        }
                    });*/
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }


    private void example7() {

        String imgUrl = "https://static.pexels.com/photos/103567/pexels-photo-103567.jpeg";
        //new LoadImageTask(imv,pb).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,img2);
         new LoadImageTask(imv,pb).execute(imgUrl);

    }

    private void example6() {

        //new MyTask(tv1).execute("Hello");

        MyTask m1 = new MyTask(tv1);
        m1.execute("Hello1","Hello2","Hello3");

        //new MyTask(tv1).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"test");

        //MyTask2 mt2 = new MyTask2();
        //mt2.tv = tv1;
        //mt2.execute();


        //mt2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"test");


    }

    private void example5() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    URL newurl = new URL("https://api.learn2crack.com/android/images/donut.png");
                    HttpURLConnection con = (HttpURLConnection)newurl.openConnection();
                    InputStream is = con.getInputStream();
                    int imgSize = con.getContentLength();

                    byte[] buffer = new byte[1024];
                    int len = 0; int sum = 0;
                    final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
                    while ((len = is.read(buffer)) > 0)  {
                        byteBuffer.write(buffer,0,len);
                        sum += len;
                        final float percent = (sum*100.0f)/imgSize;
                        pb.post(new Runnable() {
                            @Override
                            public void run() {
                                pb.setProgress((int)percent);
                                tv1.setText( Integer.toString((int)percent));
                            }
                        });
                    }
                     //runOnUiThread(), post()  ปฎิบัตตามกฏข้อ 2;
                    imv.post(new Runnable() {
                        public void run() {
                            Bitmap bmp = BitmapFactory.decodeByteArray(
                                    byteBuffer.toByteArray(),
                                    0,
                                    byteBuffer.size());
                            imv.setImageBitmap(bmp);
                        }
                    });
                }
                catch (Exception e) {
                    Log.d("MyThread",e.toString());
                }
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    URL newurl = new URL("https://api.learn2crack.com/android/images/donut.png");
                    HttpURLConnection con = (HttpURLConnection)newurl.openConnection();
                    InputStream is = con.getInputStream();
                    int imgSize = con.getContentLength();

                    byte[] buffer = new byte[1024];
                    int len = 0; int sum = 0;
                    final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
                    while ((len = is.read(buffer)) > 0)  {
                        byteBuffer.write(buffer,0,len);
                        sum += len;
                        final float percent = (sum*100.0f)/imgSize;
                        pb2.post(new Runnable() {
                            @Override
                            public void run() {
                                pb2.setProgress((int)percent);
                                //tv1.setText( Integer.toString((int)percent));
                            }
                        });
                    }
                    //runOnUiThread(), post()  ปฎิบัตตามกฏข้อ 2;
                    imv2.post(new Runnable() {
                        public void run() {
                            Bitmap bmp = BitmapFactory.decodeByteArray(
                                    byteBuffer.toByteArray(),
                                    0,
                                    byteBuffer.size());
                            imv2.setImageBitmap(bmp);
                        }
                    });
                }
                catch (Exception e) {
                    Log.d("MyThread",e.toString());
                }
            }
        }).start();
    }

    //4. Add progressbar
    private void example4() {

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    final int value = i;
                    //runOnUiThread
                    pb.post(new Runnable() {
                        @Override
                        public void run() {
                            int numDot = value % 4;
                            String dot = "";
                            for(int j=0; j<numDot; j++) dot += ".";
                            tv1.setText("Updating"+dot);
                            pb.setProgress(value);
                        }
                    });
                    setDelay(1000);
                }
            }
        }).start();

    }

    private void setDelay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private  void example3(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i =100; i>=1 ; i-- ){

                    final  int value = i ;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv1.setText(""+value);
                        }
                    });

                    setDelay(1000);
                }
            }
        }).start();
    }

    //3

}

