package com.dawn.basefragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by 90449 on 2017/7/24.
 */

public class ContentFragment02 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_content, container, false);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText("text 02");
        return view;
    }
}
