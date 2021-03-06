package com.example.jdk.restapp.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jdk.restapp.HttpUtils.RequestData;
import com.example.jdk.restapp.HttpUtils.ReturnRetrofit;
import com.example.jdk.restapp.R;
import com.example.jdk.restapp.Utils.SnackBarUtils;

/**
 * Created by JDK on 2016/8/4.
 */
public class ShakeMeiziFragment extends BaseFragment {
    private static Context mContext;
    public ShakeMeiziFragment() {
        super(R.layout.fragment_shake_meizi);
    }
    public static ShakeMeiziFragment newInstance(Context context){
        ShakeMeiziFragment meiziFragment=new ShakeMeiziFragment();
        mContext=context;
        return meiziFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void getData(final int page) {
        setSubscriber(page);
    }

    /**
     * 这里的page的作用是用来判断snackbar的显示与否
     * @param page
     */
    public void setSubscriber(final int page){
        RequestData.getInstance(mContext).requestMeiziData(ReturnRetrofit.getInstance().getMyGankApiRetrofit().getShakeMeiziData(),
                ReturnRetrofit.getInstance().getMyGankApiRetrofit().getShakeRestVideoData(),getMyRecyclerView(),page,null,isFirst(),true);
        if(page>1) {
            SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.shake_loadmore_footer)).danger();
        }else if(page==1){
            SnackBarUtils.makeLong(getActivity().getWindow().getDecorView(), getResources().getString(R.string.shake_refresh_header)).danger();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        staggeredGridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        getMyRecyclerView().setLayoutManager(new LinearLayoutManager(getActivity()));
        getData(0);
        InitListener();
    }

}
