package com.alvaroartieda.foodies.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alvaroartieda.foodies.R;
import com.alvaroartieda.foodies.map.model.Chef;
import com.alvaroartieda.foodies.map.model.ChefItemizedOverlay;
import com.alvaroartieda.foodies.map.model.ChefOverlay;
import com.alvaroartieda.foodies.map.model.KitchenType;

import org.osmdroid.api.IMapController;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;
import android.app.Fragment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;


/**
 * A placeholder fragment containing a simple view.
 */
public class MapFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_SD = 1;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 2;
    MapView map = null;
    private MyLocationNewOverlay myLocationoverlay;
    private ChefItemizedOverlay poiOverlay;
    private volatile CountDownLatch permissionsBarier = new CountDownLatch(2);

    public MapFragment() {
    }

    public static Fragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
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

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        permissionsBarier = new CountDownLatch(2);
        askForPermissionToExternalSD(Manifest.permission.WRITE_EXTERNAL_STORAGE, MY_PERMISSIONS_REQUEST_WRITE_SD);
        askForPermissionToExternalSD(Manifest.permission.ACCESS_FINE_LOCATION, MY_PERMISSIONS_REQUEST_FINE_LOCATION);
        if (permissionsBarier.getCount() == 0) {
            map.onResume();
            setLocation();
            createPoiOverlay();
        }
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
            permissionsBarier.countDown();
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
                    permissionsBarier.countDown();
                } else {
                    // show error
                }
                break;
            }
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION:{
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionsBarier.countDown();
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

    private void createPoiOverlay() {
        /* Itemized Overlay */
        {
			/* Create a static ItemizedOverlay showing a some Markers on some cities. */
            final ArrayList<Chef> chefList = new ArrayList<>();
            chefList.add(new Chef("Hannover", KitchenType.AMERICAN, new GeoPoint(46.525, 6.512612)));
            chefList.add(new Chef("Berlin", KitchenType.INDIAN, new GeoPoint(46.524, 6.55231)));
            chefList.add(new Chef("Washington", KitchenType.ITALIEN, new GeoPoint(46.517, 6.5925)));
            chefList.add(new Chef("San Francisco", KitchenType.JAPAN, new GeoPoint(46.514, 6.5827)));
            chefList.add(new Chef("Tolaga Bay", KitchenType.SUISSE, new GeoPoint(46.534, 6.542)));

            final List<ChefOverlay> items = ChefOverlay.from(chefList,getActivity());

			/* OnTapListener for the Markers, shows a simple Toast. */
            this.poiOverlay = new ChefItemizedOverlay(items,
                    new ItemizedIconOverlay.OnItemGestureListener<ChefOverlay>() {
                        @Override
                        public boolean onItemSingleTapUp(final int index, final ChefOverlay item) {
                            map.getController().animateTo(item.getPoint());
                            return false;
                        }

                        @Override
                        public boolean onItemLongPress(final int index, final ChefOverlay item) {
                            return false;
                        }
                    }, getActivity().getApplicationContext(),getActivity());
            map.getOverlays().add(this.poiOverlay);
        }

		/* MiniMap */
        {
            final MinimapOverlay miniMapOverlay = new MinimapOverlay(getActivity(),
                    map.getTileRequestCompleteHandler());
            this.map.getOverlays().add(miniMapOverlay);
        }

		/* list of items currently displayed */
        {
            final MapEventsReceiver mReceive = new MapEventsReceiver() {
                @Override
                public boolean singleTapConfirmedHelper(GeoPoint p) {
                    return false;
                }

                @Override
                public boolean longPressHelper(final GeoPoint p) {
                    final List<ChefOverlay> displayed = poiOverlay.getDisplayedItems();
                    return true;
                }
            };
            map.getOverlays().add(new MapEventsOverlay(mReceive));
        }
    }
}
