package com.paxsz.pleaselisten.MusicLibrary.View.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.paxsz.pleaselisten.MusicLibrary.Adapter.MusicLibraryPagerAdapter;
import com.paxsz.pleaselisten.MusicLibrary.Adapter.MusicListAdapter;
import com.paxsz.pleaselisten.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicLibraryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.music_library_viewpager)
    ViewPager mViewpager;

    private MusicListAdapter mAdapter;
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;

    public MusicLibraryFragment() {
    }

    public static MusicLibraryFragment newInstance() {
        MusicLibraryFragment fragment = new MusicLibraryFragment();
        return fragment;
    }

    public static MusicLibraryFragment newInstance(String param1, String param2) {
        MusicLibraryFragment fragment = new MusicLibraryFragment();
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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_library, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("音乐库");
        MusicLibraryPagerAdapter adaper = new MusicLibraryPagerAdapter(getActivity().getSupportFragmentManager());
        mViewpager.setAdapter(adaper);
        mTablayout.setupWithViewPager(mViewpager);
   /*     mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));*/
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      /*  if (context instanceof OnFragmentInteractionmmTfffffftListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

 /*   @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
