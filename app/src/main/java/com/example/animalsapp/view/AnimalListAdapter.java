package com.example.animalsapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalsapp.R;
import com.example.animalsapp.databinding.ItemAnimalBinding;
import com.example.animalsapp.model.AnimalModel;
import com.example.animalsapp.utils.Util;

import java.util.ArrayList;
import java.util.List;

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder>{

    private ArrayList<AnimalModel> animalList = new ArrayList<>();


    public void updateList(List<AnimalModel> newAnimalList){
        animalList.clear();
        animalList.addAll(newAnimalList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //View view = inflater.inflate(R.layout.item_animal,parent,false);
        ItemAnimalBinding view = DataBindingUtil.inflate(inflater,R.layout.item_animal,parent,false);
        return new AnimalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalViewHolder holder, int position) {


        /*TextView animalName = holder.itemView.findViewById(R.id.animalName);
        animalName.setText(animalList.get(position).name);*/


//        ImageView animalImage = holder.itemView.findViewById(R.id.animalImage);
//        ConstraintLayout animalLayout = holder.itemView.findViewById(R.id.animalLayout);
//
//        Util.loadImage(animalImage,animalList.get(position).imageUrl,Util.getProgressDrawable(animalImage.getContext()));
//
//
//
//        animalLayout.setOnClickListener(view -> {
//            NavDirections action = ListFragmentDirections.actionGoToDetails(animalList.get(position));
//            Navigation.findNavController(view).navigate(action);
//
//        });

        holder.itemview.setAnimal(animalList.get(position));

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder{
        ItemAnimalBinding itemview;
        public AnimalViewHolder(ItemAnimalBinding view){
            super(view.getRoot());
            itemview = view;

        }
    }
}
