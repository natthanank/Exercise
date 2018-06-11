package com.example.admin.exercise.exercise11;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import com.esri.arcgisruntime.concurrent.ListenableFuture;
import com.esri.arcgisruntime.data.Feature;
import com.esri.arcgisruntime.data.ServiceFeatureTable;
import com.esri.arcgisruntime.geometry.GeometryEngine;
import com.esri.arcgisruntime.geometry.SpatialReference;
import com.esri.arcgisruntime.geometry.SpatialReferences;
import com.esri.arcgisruntime.layers.ArcGISMapImageLayer;
import com.esri.arcgisruntime.layers.ArcGISTiledLayer;
import com.esri.arcgisruntime.layers.FeatureLayer;
import com.esri.arcgisruntime.layers.LayerContent;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.GeoElement;
import com.esri.arcgisruntime.mapping.view.Callout;
import com.esri.arcgisruntime.mapping.view.DefaultMapViewOnTouchListener;
import com.esri.arcgisruntime.mapping.view.IdentifyLayerResult;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.example.admin.exercise.R;


import java.io.Console;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ElevenActivity extends AppCompatActivity {

    private MapView mMapView;
    private ArcGISMap map;
    private Point screenPoint;

    private ServiceFeatureTable mServiceFeatureTable;
    private FeatureLayer mFeaturelayer;

    private Callout mCallout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleven);

        mMapView = findViewById(R.id.mapView);
        map = new ArcGISMap();
        mMapView.setMap(map);

        ArcGISTiledLayer tiledLayerBaseMap = new ArcGISTiledLayer(getResources().getString(R.string.world_topo_service));
        // set tiled layer as basemap
        Basemap basemap = new Basemap(tiledLayerBaseMap);
        // create an empty map instance
        ArcGISMap map = new ArcGISMap(basemap);

        mServiceFeatureTable = new ServiceFeatureTable(getString(R.string.montogomery));
        mFeaturelayer = new FeatureLayer(mServiceFeatureTable);
        map.getOperationalLayers().add(mFeaturelayer);


        mMapView.setMap(map);

        screenPoint = new Point(0, 0);

        // call identifyLayersAsync, passing in the screen point, tolerance, return types, and maximum results, but no layer
        final ListenableFuture<List<IdentifyLayerResult>> identifyFuture = mMapView.identifyLayersAsync(
                screenPoint, 20, false, 25);

        mMapView.setViewpointCenterAsync(new com.esri.arcgisruntime.geometry.Point(41.1197487, -80.0094202,  SpatialReference.create(26729)), 16);

        // add a listener to the future
        identifyFuture.addDoneListener(new Runnable() {
            @Override
            public void run() {
                try {
                    // get the identify results from the future - returns when the operation is complete
                    List<IdentifyLayerResult> identifyLayersResults = identifyFuture.get();

                    // iterate all the layers in the identify result
                    for (IdentifyLayerResult identifyLayerResult : identifyLayersResults) {

                        // iterate each result in each identified layer, and check for Feature results
                        for (GeoElement identifiedElement : identifyLayerResult.getElements()) {
                            if (identifiedElement instanceof Feature) {
                                Feature identifiedFeature = (Feature) identifiedElement;

                                // Use feature as required, for example access attributes or geometry, select, build a table, etc...
                                processIdentifyFeatureResult(identifiedFeature, identifyLayerResult.getLayerContent());
                            }
                        }
                    }
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        });


        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(this, mMapView) {

            @Override
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                Log.d("MotionEvent", "onSingleTapConfirmed: " + motionEvent.toString());

                // get the point that was clicked and convert it to a point in map coordinates
                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(motionEvent.getX()),
                        Math.round(motionEvent.getY()));
                // create a map point from screen point
                com.esri.arcgisruntime.geometry.Point mapPoint = mMapView.screenToLocation(screenPoint);
                // convert to WGS84 for lat/lon format
                com.esri.arcgisruntime.geometry.Point wgs84Point = (com.esri.arcgisruntime.geometry.Point) GeometryEngine.project(mapPoint, SpatialReferences.getWgs84());
                // create a textview for the callout
                TextView calloutContent = new TextView(getApplicationContext());
                calloutContent.setTextColor(Color.BLACK);
                calloutContent.setSingleLine();
                // format coordinates to 4 decimal places
                calloutContent.setText("Lat: " +  String.format("%.4f", wgs84Point.getY()) +
                        ", Lon: " + String.format("%.4f", wgs84Point.getX()));

                // get callout, set content and show
                mCallout = mMapView.getCallout();
                mCallout.setLocation(mapPoint);
                mCallout.setContent(calloutContent);
                mCallout.show();

                // center on tapped point
                mMapView.setViewpointCenterAsync(mapPoint);

                return true;
            }
        });

    }

    private void processIdentifyFeatureResult(Feature identifiedFeature, LayerContent layerContent) {
        Log.i("identifiedFeature", identifiedFeature.toString());
        Log.i("layerContent", layerContent.getName());
    }

    @Override
    protected void onPause(){
        mMapView.pause();
        super.onPause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mMapView.resume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.dispose();
    }

    private class IdentifyFeatureLayerTouchListener extends DefaultMapViewOnTouchListener {

        private FeatureLayer layer = null; // reference to the layer to identify features in

        // provide a default constructor
        public IdentifyFeatureLayerTouchListener(Context context, MapView mapView, FeatureLayer layerToIdentify) {
            super(context, mapView);
            layer = layerToIdentify;
        }

        // override the onSingleTapConfirmed gesture to handle a single tap on the MapView
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            // get the screen point where user tapped
            android.graphics.Point screenPoint = new android.graphics.Point((int) e.getX(), (int) e.getY());
            // ...

            return true;
        }
    }
}
