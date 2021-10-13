// IAdd.aidl
package com.example.ekaratrattagan.aidl;
import com.example.ekaratrattagan.aidl.IAsyncServiceCounter;

interface IAsyncService {

    void startCount(int to, IAsyncServiceCounter callback);

 }
