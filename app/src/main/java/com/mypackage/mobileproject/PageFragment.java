package com.mypackage.mobileproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PageFragment extends Fragment {

    private static final String ARG_POS = "pos";

    public static PageFragment newInstance(int pos) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POS, pos);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        int pos = requireArguments().getInt(ARG_POS);
        int[] pics = {R.drawable.tab1, R.drawable.tab2, R.drawable.tab3};

        ((TextView) view.findViewById(R.id.tvNumber)).setText(String.valueOf(pos + 1));
        ((ImageView) view.findViewById(R.id.ivPage)).setImageResource(pics[pos]);
        return view;
    }
}