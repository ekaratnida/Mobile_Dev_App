package com.example.ekaratrattagan.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class AsyncService extends Service {

    private static final String TAG = "AsyncService";

    @Override
    public void onCreate()
    {
        Log.d(TAG, "onCreate");
    }

    @Override
    public void onDestroy()
    {
        /* TODO: Of course we would need to clean up. */
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IAsyncService.Stub mBinder = new IAsyncService.Stub() {

        public void startCount(final int to, final IAsyncServiceCounter callback){

            Thread t = new Thread(){

                public void preciseSleep(long millis){

                    long endTime = System.currentTimeMillis()+millis;
                    do {
                        try{
                            Thread.sleep(endTime-System.currentTimeMillis());
                        }catch(InterruptedException e)
                        {}
                    }while(System.currentTimeMillis() < endTime);

                }

                public void run(){
                    for(int i=1; i<=to; i++){
                        preciseSleep(1000);

                        try{
                            callback.handleCount(i);
                        }catch(DeadObjectException e){
                            Log.d(TAG, "Dead peer, aborting...",e);
                            break;
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };

            t.start();
        }
    };

}
