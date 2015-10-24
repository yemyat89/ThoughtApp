package com.yemyatthein.thoughts.fragments;


import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yemyatthein.thoughts.R;
import com.yemyatthein.thoughts.customs.SearchResultAdapter;
import java.lang.reflect.Field;


public class SearchResultFragment extends Fragment {

    private GoogleMap map;
    private ListView lv;
    private MapFragment fragment;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_result, container, false);

        context=getActivity();

        lv=(ListView) view.findViewById(R.id.lv_search_results);
        lv.setAdapter(new SearchResultAdapter(context));

        FragmentManager fm = getFragmentManager();
        fragment = (MapFragment) fm.findFragmentById(R.id.map_container_search_result);
        if (fragment == null) {
            fragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container_search_result, fragment).commit();
        }

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        fragment = (MapFragment) fm.findFragmentById(R.id.map_container_search_result);
        if (fragment == null) {
            fragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container_search_result, fragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (map == null) {
            map = fragment.getMap();

            LatLng latLng = new LatLng(51.50073, -0.12463);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50121, -0.12491);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50151, -0.12421);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50111, -0.12461);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50201, -0.12500);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50287, -0.12530);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50267, -0.12560);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));

            latLng = new LatLng(51.50234, -0.12533);
            map.addMarker(new MarkerOptions().position(latLng)
                    .title("Marker"));


            latLng = new LatLng(51.50073, -0.12463);
            CameraUpdate center = CameraUpdateFactory.newLatLng(
                    latLng);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(13);

            map.moveCamera(center);
            map.animateCamera(zoom);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


}
