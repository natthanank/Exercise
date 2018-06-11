package com.example.admin.exercise.exercise11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ElevenActivity extends AppCompatActivity {

    private MapView mMapView;
    private ArcGISMap map;
    private ArcGISTiledLayer tiledLayerBaseMap;
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

        // create tile layer
        tiledLayerBaseMap = new ArcGISTiledLayer(getResources().getString(R.string.world_topo_service));
        // set tiled layer as basemap
        Basemap basemap = new Basemap(tiledLayerBaseMap);
        // create an map instance and pass basemap as argument
        ArcGISMap map = new ArcGISMap(basemap);

        // create service feature table
        mServiceFeatureTable = new ServiceFeatureTable(getString(R.string.sample_service_url));
        // create feature layer from service feature table
        mFeaturelayer = new FeatureLayer(mServiceFeatureTable);
        // add feature layer to operational layer
        map.getOperationalLayers().add(mFeaturelayer);

        // set map to mapView
        mMapView.setMap(map);

        mMapView.setViewpointCenterAsync(new com.esri.arcgisruntime.geometry.Point(-85.66, 38.19,  SpatialReferences.getWgs84()), 11);


        // identify feature
        // add a listener to detect taps on the map view
        mMapView.setOnTouchListener(new DefaultMapViewOnTouchListener(ElevenActivity.this, mMapView) {
            @Override public boolean onSingleTapConfirmed(MotionEvent e) {
                android.graphics.Point screenPoint = new android.graphics.Point(Math.round(e.getX()),
                        Math.round(e.getY()));
                identifyResult(screenPoint);
                return true;
            }
        });
    }

    private void identifyResult(android.graphics.Point screenPoint) {

        final ListenableFuture<List<IdentifyLayerResult>> identifyLayerResultsFuture = mMapView
                .identifyLayersAsync(screenPoint, 12, false, 10);

        identifyLayerResultsFuture.addDoneListener(new Runnable() {
            @Override public void run() {
                try {
                    List<IdentifyLayerResult> identifyLayerResults = identifyLayerResultsFuture.get();
                    handleIdentifyResults(identifyLayerResults);
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("error na ja", "Error identifying results: " + e.getMessage());
                }
            }
        });
    }

    private void handleIdentifyResults(List<IdentifyLayerResult> identifyLayerResults) {
        StringBuilder message = new StringBuilder();
        int totalCount = 0;
        for (IdentifyLayerResult identifyLayerResult : identifyLayerResults) {
            int count = geoElementsCountFromResult(identifyLayerResult);
            String layerName = identifyLayerResult.getLayerContent().getName();
            message.append(layerName).append(": ").append(count);

            // add new line character if not the final element in array
            if (!identifyLayerResult.equals(identifyLayerResults.get(identifyLayerResults.size() - 1))) {
                message.append("\n");
            }
            totalCount += count;
        }

        // if any elements were found show the results, else notify user that no elements were found
        if (totalCount > 0) {
            showAlertDialog(message);
        } else {
            Toast.makeText(this, "No element found", Toast.LENGTH_SHORT).show();
            Log.i("error na ja", "No element found.");
        }
    }

    private int geoElementsCountFromResult(IdentifyLayerResult result) {
        // create temp array
        List<IdentifyLayerResult> tempResults = new ArrayList<>();
        tempResults.add(result);

        // using Depth First Search approach to handle recursion
        int count = 0;
        int index = 0;

        while (index < tempResults.size()) {
            // get the result object from the array
            IdentifyLayerResult identifyResult = tempResults.get(index);

            // update count with geoElements from the result
            count += identifyResult.getElements().size();

            // if sublayer has any results, add result objects in the tempResults array after the current result
            if (identifyResult.getSublayerResults().size() > 0) {
                tempResults.add(identifyResult.getSublayerResults().get(index));
            }

            // update the count and repeat
            index += 1;
        }
        return count;
    }

    private void showAlertDialog(StringBuilder message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        // set title
        alertDialogBuilder.setTitle("Number of elements found");

        // set dialog message
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show the alert dialog
        alertDialog.show();
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



}
