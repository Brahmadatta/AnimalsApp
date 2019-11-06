package com.example.animalsapp.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.animalsapp.R;
import com.example.animalsapp.model.AnimalModel;
import com.example.animalsapp.viewmodel.ListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    @BindView(R.id.animalRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.listError)
    TextView listEror;

    @BindView(R.id.animalList)
    RecyclerView animalList;

    @BindView(R.id.loadingView)
    ProgressBar loadingView;

    private ListViewModel mListViewModel;

    private AnimalListAdapter mListAdapter = new AnimalListAdapter();

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ButterKnife.bind(this,view);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mListViewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        mListViewModel.animals.observe(getViewLifecycleOwner(), list -> {

            if (list != null){
                animalList.setVisibility(View.VISIBLE);
                mListAdapter.updateList(list);

            }
        });

        mListViewModel.loading.observe(this,loading -> {
            loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
            if (loading){
                listEror.setVisibility(View.GONE);
                animalList.setVisibility(View.GONE);
            }
        });

        mListViewModel.loadError.observe(this,error -> {
            listEror.setVisibility(error ? View.VISIBLE : View.GONE);
        });

        mListViewModel.refresh();

        if (animalList != null){
            animalList.setLayoutManager(new GridLayoutManager(getContext(),2));
            animalList.setAdapter(mListAdapter);

        }

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            listEror.setVisibility(View.GONE);
            loadingView.setVisibility(View.GONE);
            animalList.setVisibility(View.GONE);
            //mListViewModel.refresh();
            mListViewModel.hardRefresh();
            mSwipeRefreshLayout.setRefreshing(false);
        });
    }
}
