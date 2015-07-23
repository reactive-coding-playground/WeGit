package com.quinn.githubknife.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quinn.githubknife.presenter.FollowerPresenterImpl;
import com.quinn.githubknife.ui.view.ListFragmentView;
import com.quinn.githubknife.ui.activity.UsersAdapter;
import com.quinn.githubknife.utils.L;
import com.quinn.httpknife.github.GithubImpl;
import com.quinn.httpknife.github.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quinn on 7/15/15.
 */
public class FollowerFragment extends BaseFragment implements ListFragmentView{

    private UsersAdapter adapter;
    private List<User> follwers = new ArrayList<User>();

    public static FollowerFragment getInstance(String user){
        FollowerFragment followerFragment = new FollowerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("user", user);
        followerFragment.setArguments(bundle);
        return followerFragment;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        L.i("onResume FollowerFragment");
        if(follwers.isEmpty()) {
            presenter.onPageLoad(currPage,user);
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.i("onCreate FollowerFragment");
        presenter = new FollowerPresenterImpl(this.getActivity(),this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container,savedInstanceState);
        adapter = new UsersAdapter(follwers);
        recyclerView.setAdapter(adapter);
        L.i("onCreateView FollowerFragment");
        return view;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setItems(List items) {
        for(Object user:items){
            follwers.add((User)user);
        }
        loading = false;
        if(items.size() < GithubImpl.DEFAULT_PAGE_SIZE)
            haveMore = false;
        currPage = currPage + 1;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void intoItem(int position) {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void loadMore() {
        super.loadMore();
    }
}