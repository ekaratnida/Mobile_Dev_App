package com.example.ekaratrattagan.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AsyncClient extends AppCompatActivity {

    private static final String TAG = "AsyncClient";
    private Handler mHandler = new Handler();
    private IAsyncService mService;

    TextView mCounterText;
    Button button;
    boolean mCounting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCounterText = findViewById(R.id.counter);
        button = findViewById(R.id.start);
        button.setOnClickListener(mFire);

        bindService(
                new Intent(this, AsyncService.class),
                mConnection,
                Context.BIND_AUTO_CREATE);
    }

    protected View.OnClickListener mFire = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(mService == null){
                Log.d(TAG,"Nothing to do.");
                return;
            }

            if(mCounting == true){
                Log.d(TAG, "mCounter is implemented globally and cannot be reused while counting is in progress.");
                return;
            }

            try
            {
                mCounting = true;
                mService.startCount(5, mCounter);
                Log.d(TAG, "Counting has begun...");
            }
            catch (RemoteException e)
            {
                mCounting = false;
                Log.d(TAG, Log.getStackTraceString(e));
            }
        }
    };

    private ServiceConnection mConnection = new ServiceConnection()
    {
        public void onServiceConnected(ComponentName className, IBinder service)
        {
            Log.d(TAG, "onServiceConnected");
            mService = IAsyncService.Stub.asInterface((IBinder)service);
        }

        public void onServiceDisconnected(ComponentName className)
        {
            Log.d(TAG, "onServiceDisconnected");
            mService = null;
        }
    };

    private IAsyncServiceCounter.Stub mCounter = new IAsyncServiceCounter.Stub() {
        @Override
        public void handleCount(final int n) throws RemoteException {

            Log.d(TAG, "handleCount(" + n + ")");

            mHandler.post(new Runnable()
            {
                public void run()
                {
                    if (n == 10)
                    {
                        mCounterText.setText("Done!");
                        mCounting = false;
                    }
                    else {
                        mCounterText.setText(String.valueOf(n));
                    }
                }
            });
        }
    };
}
