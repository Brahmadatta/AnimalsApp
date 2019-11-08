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

public class AnimalListAdapter extends RecyclerView.Adapter<AnimalListAdapter.AnimalViewHolder> implements AnimalClickListener{

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

        holder.itemview.setAnimal(animalList.get(position));
        holder.itemview.setListener(this);

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    @Override
    public void onCLick(View v) {
            for (AnimalModel animalModel : animalList){
                if (v.getTag().equals(animalModel.name)){
                    NavDirections action = ListFragmentDirections.actionGoToDetails(animalModel);
                    Navigation.findNavController(v).navigate(action);
                }
            }
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder{
        ItemAnimalBinding itemview;
        public AnimalViewHolder(ItemAnimalBinding view){
            super(view.getRoot());
            itemview = view;

        }
    }
}
