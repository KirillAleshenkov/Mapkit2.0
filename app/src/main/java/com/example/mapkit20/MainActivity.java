package com.example.mapkit20;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.widget.Toast;

import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Circle;
import com.yandex.mapkit.geometry.LinearRing;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polygon;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CircleMapObject;
import com.yandex.mapkit.map.CompositeIcon;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.map.PolygonMapObject;
import com.yandex.mapkit.map.RotationType;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements UserLocationObjectListener {
    private final String MAPKIT_API_KEY = "6953bbfa-0280-46af-a53b-56ece8baa13a";
    private final Point TARGET_LOCATION = new Point(51.2049, 58.5668);
    private final Point CIRCLE_CENTER = new Point(51.207970, 58.563277);
    private MapView mapView;
    private PlacemarkMapObject Mark;
    private UserLocationLayer userLocationLayer;
    private Context context;
    private ImageProvider iProvider;
    private MapObjectCollection mapObjectCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(this);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);
        mapView.getMap().setRotateGesturesEnabled(true);
        mapView.getMap().move(new CameraPosition(new Point(51.207970, 58.563277), 14, 0, 0));
        MapKit mapKit = MapKitFactory.getInstance();
        userLocationLayer = mapKit.createUserLocationLayer(mapView.getMapWindow());
        userLocationLayer.setVisible(true);
        userLocationLayer.setHeadingEnabled(true);
        userLocationLayer.setObjectListener((UserLocationObjectListener) this);
        mapView.getMap().getMapObjects().addPlacemark(new Point(51.207970, 58.563277)).setIcon(ImageProvider.fromResource(this, R.drawable.icon));
        List<Point> points = new ArrayList<>();
        points.add(new Point(55.207970, 56.563277));
        points.add(new Point(53.307970, 59.663277));
        points.add(new Point(52.407970, 60.763277));
        points.add(new Point(51.507970, 62.863277));
        mapObjectCollection = mapView.getMap().getMapObjects().addCollection();
        Polyline poly = new Polyline(points);
        //mapObjectCollection.addPolyline(poly);
        //PolygonMapObject rect = mapObjectCollection.addPolygon(new Polygon(new LinearRing(points), new ArrayList<LinearRing>()));
        //Polygon polygon = Polygon()
        CircleMapObject circle = mapObjectCollection.addCircle(new Circle(CIRCLE_CENTER, 100), Color.BLUE, 200, Color.RED);
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
    public void onObjectAdded(UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.5)),
                new PointF((float)(mapView.getWidth() * 0.5), (float)(mapView.getHeight() * 0.83)));
        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                this, R.drawable.user_arrow));
        CompositeIcon pinIcon = userLocationView.getPin().useCompositeIcon();
        pinIcon.setIcon(
                "icon",
                ImageProvider.fromResource(this, R.drawable.icon),
                new IconStyle().setAnchor(new PointF(0f, 0f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(0f)
                        .setScale(1f)
        );
        pinIcon.setIcon(
                "pin",
                ImageProvider.fromResource(this, R.drawable.search_result),
                new IconStyle().setAnchor(new PointF(0.5f, 0.5f))
                        .setRotationType(RotationType.ROTATE)
                        .setZIndex(1f)
                        .setScale(0.5f)
        );
        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x99ffffff);
    }
    public void onObjectRemoved(UserLocationView view) {
    }
    public void onObjectUpdated(UserLocationView view, ObjectEvent event) {
    }

}