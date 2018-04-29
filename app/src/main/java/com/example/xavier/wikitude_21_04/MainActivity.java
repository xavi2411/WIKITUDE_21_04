package com.example.xavier.wikitude_21_04;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;
import com.wikitude.common.camera.CameraSettings;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    public ArchitectView architectView;
    private GoogleApiClient mGoogleApiClient;
    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Toast.makeText(MainActivity.this, "Location changed", Toast.LENGTH_LONG).show();
            MainActivity.this.architectView.setLocation(location.getLatitude(), location.getLongitude(), location.getAltitude(), location.getAccuracy());
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.architectView = (ArchitectView) this.findViewById(R.id.architectView);
        final ArchitectStartupConfiguration config = new ArchitectStartupConfiguration();
        config.setLicenseKey("pXWSeyXQSMu21cKxbx9rCvOH77rTxLtJ0t9HMLBivboXG9yQ5/8TtFq6EXXgKX8wX6cadbr3kd2tF4Ds3ZQAhuwmew9PlpzDNMXGM4H5jUMlj033iFpHGmTMUT7QqA0YBY2/k1mUtfePIp/nxTI6r+XWKI1IZyGiNexCYphQ6OtTYWx0ZWRfX/Dy4LNXpBq4tRojOjhTFxdFe7ANEzz9C6TIRA59pXPDT5ng0KtGU4PTcq3p4RrE+L0Wn7WOkiYmBTed0iHLsiabFWNvxsMcB3/XiM7f+zInwj28Sux4VdIgWtcc3WNK6xsKddS1R7Jh+ZkgPs2e+l6jrEZ13DcGfkoD5CMzSyGnnCfNpyi1dkNO2bWuJoz6+2H2mNFZc0sdIOW4lcYGH0PpVNIu7W16VnSGCJms5q1mWbofMl97QeM4T1q+SI4qGT0jqZS1djZzRAzb7ybhqxoOuCLfbzHBrpFoyTk/Xnb3jadD7YT6ZW8e2RZCyKAE3cBbjIFSvZT6Rz7hOnmFAhj3boRsgWPjRKnQdFd/3KbAmblck5ZrIj1Zit99cCEbZyyC8Q4anGIN62XwzlcAQjHErWYdQ2Aq9tDB/fbcUZh7IuJVjIqS2IqyG73M29/S2qpn8HPRguGFNVfAPATrbQu1IaWCGpDDUxZkxVAAssuO+bPugK88qug=");
        config.setFeatures(ArchitectStartupConfiguration.Features.Geo);
        config.setCameraResolution(CameraSettings.CameraResolution.AUTO);
        this.architectView.onCreate(config);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    @Override

    protected void onPostCreate(final Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if (this.architectView != null) {
            this.architectView.onPostCreate();

            try {
                this.architectView.load("FreeGuideJS/index.html");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.architectView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.architectView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.architectView.onDestroy();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            this.architectView.setLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), mLastLocation.getAltitude(), mLastLocation.getAccuracy());
        }

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, locationListener);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
