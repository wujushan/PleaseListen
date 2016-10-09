package com.paxsz.pleaselisten.MusicLibrary.View.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.paxsz.pleaselisten.MusicLibrary.Adapter.MusicListAdapter;
import com.paxsz.pleaselisten.MusicLibrary.Component.DaggerMusicFragmentComponent;
import com.paxsz.pleaselisten.MusicLibrary.Mode.MusicInfo;
import com.paxsz.pleaselisten.MusicLibrary.Module.MusicFragmentModule;
import com.paxsz.pleaselisten.MusicLibrary.Presenter.MusicFragmentPresenterCompl;
import com.paxsz.pleaselisten.MusicLibrary.View.IMusicFragment;
import com.paxsz.pleaselisten.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MusicFragment extends Fragment implements IMusicFragment {

    private static final String TAG = MusicFragment.class.getSimpleName();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.music_recyclerview)
    RecyclerView mRecyclerview;
    private MusicListAdapter mAdapter;
    private String mParam1;
    private String mParam2;

    @Inject
    MusicFragmentPresenterCompl mFragmentPresenterCompl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        DaggerMusicFragmentComponent.builder().musicFragmentModule(new MusicFragmentModule(this)).build().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPresenterCompl.getMusicInfos();
    }

    private void initView() {
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MusicListAdapter();
        mRecyclerview.setAdapter(mAdapter);
    }

    public MusicFragment() {
        // Required empty public constructor
    }

    public static MusicFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void updateMusicList(List<MusicInfo> musicInfos) {
        mAdapter.setData(musicInfos);
        mAdapter.notifyDataSetChanged();
    }
}
