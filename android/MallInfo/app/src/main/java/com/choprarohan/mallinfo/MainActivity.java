package com.choprarohan.mallinfo;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    @BindView(R.id.info_recycler)
    RecyclerView infoRecyclerView;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    InfoAdapter infoAdapter;
    LinearLayoutManager layoutManager;
    ArrayList<String>  alreadyFound = new ArrayList<>();
    ArrayList<Details> items = new ArrayList<>();
    private BluetoothAdapter mBluetoothAdapter;
    private int REQUEST_ENABLE_BT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        /**
         * Checks if Bluetooth is enabled on device
         * Use this within and Activity
         */
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, REQUEST_ENABLE_BT);
        }

        mBluetoothAdapter.startLeScan(mLeScanCallback);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mBluetoothAdapter.stopLeScan(mLeScanCallback);
            }
        }, 5000);


    }

    @Override
    protected void onStart() {
        super.onStart();



        infoRecyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);



        infoAdapter = new InfoAdapter(items);
        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setMoveDuration(1000);
        infoRecyclerView.setItemAnimator(animator);
        infoRecyclerView.setAdapter(infoAdapter);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        infoRecyclerView.setLayoutManager(layoutManager);


        if(items.size()!=0) {
            infoRecyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }



    }


    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            int startByte = 2;
            boolean patternFound = false;
            byte[] bytes = new byte[16];
            System.arraycopy(scanRecord, startByte+4, bytes, 0, 16);
            String hString = bytesToHex(bytes);
            while (startByte <= 5) {
                if ((    ((int) scanRecord[startByte + 2] & 0xff) == 0x02 && //Identifies an iBeacon
                        ((int) scanRecord[startByte + 3] & 0xff) == 0x15) &&  //Identifies correct data length
                        alreadyFound.contains(hString.substring(20,32)) == false) {

                    Log.d("Array",alreadyFound+"");
                    alreadyFound.add(hString.substring(20,32));
                    patternFound = true;
                    break;
                }
                startByte++;
            }

            if (patternFound) {
                //Convert to hex String
                byte[] uuidBytes = new byte[16];
                System.arraycopy(scanRecord, startByte+4, uuidBytes, 0, 16);
                String hexString = bytesToHex(uuidBytes);

                //Here is your UUID
                String uuid =  hexString.substring(0,8) + "-" +
                        hexString.substring(8,12) + "-" +
                        hexString.substring(12,16) + "-" +
                        hexString.substring(16,20) + "-" +
                        hexString.substring(20,32);

                //Here is your Major value
                int major = (scanRecord[startByte+20] & 0xff) * 0x100 + (scanRecord[startByte+21] & 0xff);

                //Here is your Minor value
                int minor = (scanRecord[startByte+22] & 0xff) * 0x100 + (scanRecord[startByte+23] & 0xff);

                Log.v("Major: ", ""+major);
                Log.v("Minor: ", ""+minor);

                ArrayList<Details> newItems = new ArrayList<>();
                newItems.addAll(items);

                newItems.add(new Details(120,301,1));

                infoAdapter.update(newItems);


                infoRecyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);




            }
        }

        /**
         * bytesToHex method
         * Found on the internet
         * http://stackoverflow.com/a/9855338
         */
        final char[] hexArray = "0123456789ABCDEF".toCharArray();
        private String bytesToHex(byte[] bytes) {
            char[] hexChars = new char[bytes.length * 2];
            for ( int j = 0; j < bytes.length; j++ ) {
                int v = bytes[j] & 0xFF;
                hexChars[j * 2] = hexArray[v >>> 4];
                hexChars[j * 2 + 1] = hexArray[v & 0x0F];
            }
            return new String(hexChars);
        }
    };
}
