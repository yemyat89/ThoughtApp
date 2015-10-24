package com.yemyatthein.thoughts.fragments;


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
import com.yemyatthein.thoughts.activities.ReadActivity;
import java.lang.reflect.Field;


public class CreateFormFragment extends Fragment {

    private GoogleMap map;
    private MapFragment fragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_form, container, false);

        Button btnSubmitThought = (Button) view.findViewById(R.id.btn_submit_throught_create_form);
        btnSubmitThought.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt(ReadActivity.FRAGMENT_TARGET_KEY,
                        ReadActivity.READ_FRAGMENT_TARGET_VIEW);

                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        FragmentManager fm = getChildFragmentManager();
        fragment = (MapFragment) fm.findFragmentById(R.id.map_container_create_form);

        if (fragment == null) {
            fragment = MapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container_create_form, fragment).commit();
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
