package com.lecheng.hello.thirdapp.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lecheng.hello.thirdapp.ActivityListItem.Aty014Fragment_Navi;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.ActivityListItem.Aty014Fragment_Tabed;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment2Fragment extends Fragment {

    public Fragment2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View fragment2 = inflater.inflate(R.layout.fragment014, container, false);

        fragment2.findViewById(R.id.btn2anothersFAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                .add(R.id.fragment, new FragmentAnothers()).commit();
            }
        });

        fragment2.findViewById(R.id.btn2navigationAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Aty014Fragment_Navi.class));

            }
        });
        fragment2.findViewById(R.id.btn2tabAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Aty014Fragment_Tabed.class));

            }
        });

        return fragment2;

    }
}
