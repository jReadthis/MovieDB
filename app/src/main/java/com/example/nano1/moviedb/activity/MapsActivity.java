package com.example.nano1.moviedb.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.nano1.moviedb.BuildConfig;
import com.example.nano1.moviedb.GetNearbyPlacesData;
import com.example.nano1.moviedb.PermissionUtils;
import com.example.nano1.moviedb.R;
import com.example.nano1.moviedb.fragment.MapDataFragment;
import com.example.nano1.moviedb.pojos.Result;
import com.example.nano1.moviedb.pojos.Theater;
import com.example.nano1.moviedb.service.PlacesService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapsActivity extends FragmentActivity
        implements OnMapReadyCallback,
        GoogleMap.OnMyLocationButtonClickListener,
        GoogleMap.OnMyLocationClickListener,
        ActivityCompat.OnRequestPermissionsResultCallback,
        MapDataFragment.OnFragmentInteractionListener {

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap;
    String KEY = BuildConfig.PLACES_API_KEY;
    private CameraPosition mCameraPosition;
    private SupportMapFragment mapFragment;
    private MapDataFragment dataFragment;
    private int PROXIMITY_RADIUS = 5000;
    private static final String Theaters = "movie_theater";
    double latitude;
    double longitude;

    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    public Theater theater;


    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        dataFragment = new MapDataFragment();
    }

    private void loadData() {
        Log.d(TAG + ":loadData() ", "Enter method");
        if (theater != null) {
            Bundle args = new Bundle();
            args.putParcelable("place", theater);
            dataFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.theaters, dataFragment);
            transaction.commit();
        }
        Log.d(TAG + ":loadData() ", "Exit method");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
        enableMyLocation();
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);
            getDeviceLocation();
            //showCurrentPlace();

        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        Toast.makeText(this, "MyLocation button clicked", Toast.LENGTH_SHORT).show();
        // Return false so that we don't consume the event and the default behavior still occurs
        // (the camera animates to the user's current position).
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "Current location:\n" + location, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        if (mPermissionDenied) {
            // Permission was not granted, display error dialog.
            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }

    /**
     *
     * Displays a dialog with error message explaining that the location permission is missing.
     *
     * */
    private void showMissingPermissionError() {
        PermissionUtils.PermissionDeniedDialog
                .newInstance(true).show(getSupportFragmentManager(), "dialog");
    }

    /**
     *
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     *
     **/
    private void getDeviceLocation() {
        try {
            if (!mPermissionDenied) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(mLastKnownLocation.getLatitude(),
                                            mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            Log.d(TAG, "Latitude: " + mLastKnownLocation.getLatitude() + " Longitude: " + mLastKnownLocation.getLongitude());
                            showTheaters();
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void showTheaters() {
        Log.d(TAG + ":showTheaters() ", "Enter method");
        if (mMap == null) {
            return;
        }

        if (!mPermissionDenied) {
            mMap.clear();
            if ( mLastKnownLocation != null){
                latitude = mLastKnownLocation.getLatitude();
                longitude = mLastKnownLocation.getLongitude();
            }
            Log.d(TAG, "Latitude: " + latitude + " Longitude: " + latitude);
//            String url = getUrl(latitude, longitude, Theaters);

            Call<Theater> call =  PlacesService.Implementation.get().getNearBy(latitude+","+longitude,PROXIMITY_RADIUS,Theaters, KEY);
            call.enqueue(new Callback<Theater>() {
                @Override
                public void onResponse(Call<Theater> call, Response<Theater> response) {
                    theater = response.body();
                    List<Result> nearbyPlacesList = theater.getResults();
                    showNearbyPlaces(nearbyPlacesList);
                    loadData();
                }

                @Override
                public void onFailure(Call<Theater> call, Throwable t) {
                    Toast.makeText(MapsActivity.this, "No Nearby Movie Theaters", Toast.LENGTH_SHORT).show();
                }
            });
            Log.d(TAG + ":showTheaters() ", "Exit method");
        }
    }

    private void showNearbyPlaces(List<Result> nearbyPlacesList) {
        Log.d(TAG + ":showNearbyPlaces()", "Enter method");
        for (Result place : nearbyPlacesList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng latLng = new LatLng(place.getGeometry().getLocation().getLat(),
                    place.getGeometry().getLocation().getLng());
            markerOptions.position(latLng);
            markerOptions.title(place.getName());
            mMap.addMarker(markerOptions);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            //Move Map Camera
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(12));
        }
        Log.d(TAG + ":showNearbyPlaces()", "Exit method");
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
