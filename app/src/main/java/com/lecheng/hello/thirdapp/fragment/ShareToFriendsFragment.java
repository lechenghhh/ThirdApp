package com.lecheng.hello.thirdapp.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.ShareToFriends;

public class ShareToFriendsFragment extends Fragment {

    public static ShareToFriendsFragment newInstance() {
        ShareToFriendsFragment fragment = new ShareToFriendsFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View v = inflater.inflate(R.layout.fragment_share_friends_layout, container, false);
        Button shareButton = (Button) v.findViewById(R.id.share_to_friends_button);
        shareButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                ShareToFriends.shareForFriend(getActivity(), v);
            }
        });

        return v;
    }
}
