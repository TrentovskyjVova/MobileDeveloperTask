package com.vova.mobiledevelopertask;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.vova.mobiledevelopertask.list.ImagesFragment;
import com.vova.mobiledevelopertask.main.MainFragment;
import com.vova.mobiledevelopertask.utils.MarshMallowPermissions;

public class MainActivity extends AppCompatActivity {

    private MainFragment mainFragment;
    private ImagesFragment imagesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }

    private void initUI() {
        setContentView(R.layout.activity_main);
        initToolbar();

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            MarshMallowPermissions.requestPermissionForExternalStorage(this);
        } else {
            showMainFragment(0);
        }
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton imageButton = (ImageButton) findViewById(R.id.imageBtn);
        imageButton.setOnClickListener(view -> new CustomAlertDialog(MainActivity.this).show());
    }

    public void showMainFragment(int imageIndex) {
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
        }
        mainFragment.setIndexArgument(imageIndex);
        replaceFragment(mainFragment);
    }

    public void showImagesFragment() {
        if (imagesFragment == null) {
            imagesFragment = new ImagesFragment();
        }
        replaceFragment(imagesFragment);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case MarshMallowPermissions.EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    showMainFragment(0);
                }
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

}
