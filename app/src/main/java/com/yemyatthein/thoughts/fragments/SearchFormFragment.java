package com.yemyatthein.thoughts.fragments;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yemyatthein.thoughts.R;
import com.yemyatthein.thoughts.activities.WriteActivity;
import java.lang.reflect.Field;

public class SearchFormFragment extends Fragment {

    private GoogleMap map;
    private MapFragment fragment;
    private SearchFormFragmentInteractionListener mListener;

    public interface SearchFormFragmentInteractionListener {
        public void keywordSubmitted();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (SearchFormFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement SearchFormFragmentInteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_form, container, false);

        Button btnSearchNearby = (Button) view.findViewById(R.id.btn_search_nearby_search_form);
        btnSearchNearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.keywordSubmitted();
            }
        });

        Button btnLeaveThought = (Button) view.findViewById(R.id.btn_leave_thought_search_form);
        btnLeaveThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), WriteActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        fragment = (MapFragment) fm.findFragmentById(R.id.map_container_search_form);
        if (fragment == null) {
            fragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container_search_form, fragment).commit();
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

            CameraUpdate center = CameraUpdateFactory.newLatLng(
                    latLng);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

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
