package com.alvaroartieda.foodies.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alvaroartieda.foodies.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;


/**
 * A placeholder fragment containing a simple view.
 */
public class MapFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_SD = 1;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 2;
    MapView map = null;
    private MyLocationNewOverlay myLocationoverlay;

    public MapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //setting this before the layout is inflated is a good idea
        View layout = inflater.inflate(R.layout.fragment_map, container, false);
        map = (MapView) layout.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        IMapController mapController = map.getController();
        mapController.setZoom(16);
        GeoPoint startPoint = new GeoPoint(48.8583, 2.2944);
        mapController.setCenter(startPoint);
        askForPermissionToExternalSD(Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_WRITE_SD);
        askForPermissionToExternalSD(Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();

        map.onResume();
        setLocation();
    }

    @Override
    public void onPause() {
        super.onPause();
        map.onPause();
    }

    private void askForPermissionToExternalSD(String permission, Integer reqCode){
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                permission)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    permission)) {
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{permission},
                        reqCode);
            }
        } else {

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_SD: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // show map
                } else {
                    // show error
                }
                break;
            }
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // set location
                } else {
                    // leave it to the user
                }
            }


        }
    }

    private void setLocation(){
        // My Location Overlay
        myLocationoverlay = new MyLocationNewOverlay(map);
        myLocationoverlay.enableMyLocation();
        myLocationoverlay.setDrawAccuracyEnabled(true);
        map.getOverlayManager().add(myLocationoverlay);

        myLocationoverlay.runOnFirstFix(new Runnable() {
            public void run() {
                map.getController().animateTo(myLocationoverlay.getMyLocation());
            }
        });
    }
}
