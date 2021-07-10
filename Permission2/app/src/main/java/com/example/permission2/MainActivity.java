package com.example.permission2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mbtnPermission;
    private static final int REQ_STROGE_CODE = 101;
    private static final  int REQ_LOCATION_CODE = 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndListener();
    }

    private void initViewAndListener() {
        mbtnPermission = findViewById(R.id.btnPermission);
        mbtnPermission.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPermission:
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.ACCESS_FINE_LOCATION},REQ_STROGE_CODE);
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQ_STROGE_CODE:
                if(grantResults.length >=1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"Storage and Location Permission Granted",Toast.LENGTH_SHORT).show();
                } else if(grantResults.length >=1 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(MainActivity.this,"Storage  Permission Granted But Location Denied",Toast.LENGTH_SHORT).show();
            }else if(grantResults.length >=1 && grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this,"Location Permission Granted But Storage Permission Denied",Toast.LENGTH_SHORT).show();
                } else if(grantResults.length >=1 && grantResults[0] == PackageManager.PERMISSION_DENIED && grantResults[1] == PackageManager.PERMISSION_DENIED){
                Toast.makeText(MainActivity.this,"Storage and Location Permission Denied",Toast.LENGTH_SHORT).show();
            }
                break;

        }
    }
}