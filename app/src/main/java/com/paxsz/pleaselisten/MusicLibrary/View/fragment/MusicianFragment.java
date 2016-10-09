package com.paxsz.pleaselisten.MusicLibrary.View.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paxsz.pleaselisten.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MusicianFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicianFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public MusicianFragment() {
        // Required empty public constructor
    }

    public static MusicianFragment newInstance(){
        MusicianFragment fragment = new MusicianFragment();
        return fragment;
    }
    public static MusicianFragment newInstance(String param1, String param2) {
        MusicianFragment fragment = new MusicianFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_musician, container, false);
    }

}
