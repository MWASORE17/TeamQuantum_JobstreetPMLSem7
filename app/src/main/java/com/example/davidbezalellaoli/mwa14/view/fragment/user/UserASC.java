package com.example.davidbezalellaoli.mwa14.view.fragment.user;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.davidbezalellaoli.mwa14.R;
import com.example.davidbezalellaoli.mwa14.model.entity.User;
import com.example.davidbezalellaoli.mwa14.view.adapter.UserRVAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserASC extends Fragment {


    private RecyclerView rv;
    private UserRVAdapter adapter;

    public UserASC() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View _view = inflater.inflate(R.layout.fragment_user_asc, container, false);

        /* initiate & instantiate */
        adapter = new UserRVAdapter();
        rv = (RecyclerView) _view.findViewById(R.id.user_asc_rv);

        /* setting */
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setUsers(User.users);
        rv.setAdapter(adapter);
        return _view;
    }

}
