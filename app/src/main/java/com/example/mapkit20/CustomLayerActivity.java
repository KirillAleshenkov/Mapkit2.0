package com.example.mapkit20;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.geo.Projection;
import com.yandex.mapkit.geometry.geo.Projections;
import com.yandex.mapkit.images.DefaultImageUrlProvider;
import com.yandex.mapkit.layers.Layer;
import com.yandex.mapkit.layers.LayerOptions;
import com.yandex.mapkit.map.MapType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.tiles.UrlProvider;

public class CustomLayerActivity extends AppCompatActivity {
    private final String MAPKIT_API_KEY = "6953bbfa-0280-46af-a53b-56ece8baa13a";
    private UrlProvider urlProvider;
    private DefaultImageUrlProvider imageUrlProvider;
    private Projection projection;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_custom_layer);
        super.onCreate(savedInstanceState);

        urlProvider = (tileId, version) -> "https://maps-ios-pods-public.s3.yandex.net/mapkit_logo.png";
        imageUrlProvider = new DefaultImageUrlProvider();
        projection = Projections.getWgs84Mercator();

        mapView = (MapView)findViewById(R.id.mapview);
        mapView.getMap().setMapType(MapType.NONE);
        Layer l = mapView.getMap().addLayer(
                "mapkit_logo",
                "image/png",
                new LayerOptions(),
                urlProvider,
                imageUrlProvider,
                projection);
        l.invalidate("0.0.0");
    }

    @Override
    protected void onStop() {
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}