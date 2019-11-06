package com.example.animalsapp.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animalsapp.R;
import com.example.animalsapp.model.AnimalModel;
import com.example.animalsapp.utils.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    @BindView(R.id.animalImage)
    ImageView animalImage;

    @BindView(R.id.animalName)
    TextView animalName;

    @BindView(R.id.animalLifeSpan)
    TextView animalLifeSpan;

    @BindView(R.id.animalLocation)
    TextView animalLocation;

    @BindView(R.id.animalDiet)
    TextView animalDiet;

    private AnimalModel mAnimalModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        ButterKnife.bind(this,view);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null){
            mAnimalModel = DetailFragmentArgs.fromBundle(getArguments()).getAnimalModel();
        }

        if (mAnimalModel != null){
            animalName.setText(mAnimalModel.name);
            animalDiet.setText(mAnimalModel.diet);
            animalLocation.setText(mAnimalModel.location);
            animalLifeSpan.setText(mAnimalModel.lifeSpan);

            Util.loadImage(animalImage,mAnimalModel.imageUrl,Util.getProgressDrawable(animalImage.getContext()));
        }
    }
}
