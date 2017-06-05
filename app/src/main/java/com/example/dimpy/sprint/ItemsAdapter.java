package com.example.dimpy.sprint;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dimpy on 4/6/17.
 */
public class ItemsAdapter extends ArrayAdapter<Items> {


    public ItemsAdapter(Context context, ArrayList<Items> itemsArrayList){
        super(context, 0, itemsArrayList);
    }
/*
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        //getting the view into a variable listItemView
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.items_list, parent, false);
        }
        //This is to ensure the reusablity of a view
        Word currentWord = getItem(position);
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultLangTranslationId());
        TextView frenchTextView = (TextView) listItemView.findViewById(R.id.french_text_view);
        frenchTextView.setText(currentWord.getFrenchLangTranslationId());
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()){
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {imageView.setVisibility(View.GONE);}
        View textContainer = listItemView.findViewById(R.id.sub_linear_layout);
        int color = ContextCompat.getColor(getContext(), ColorResourceId);
        textContainer.setBackgroundColor(color);
        // we do the extra step to get tha actual value of color in form yyrrggbb or whatever
        return listItemView;
    }*/
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        //View listItemView = getView(position, convertView, parent);
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.items_list, parent, false);
        }
        Items currentItem = getItem(position);

        TextView item_nameTextView = (TextView) listItemView.findViewById(R.id.item_name_text_view);
        item_nameTextView.setText("Item: "+currentItem.getItemName());
        TextView item_valueTextView = (TextView) listItemView.findViewById(R.id.item_value_text_view);
        item_valueTextView.setText("Value: $"+currentItem.getItemValue());
        TextView item_weightTextView = (TextView) listItemView.findViewById(R.id.item_weigh_text_view);
        item_weightTextView.setText("Weight: "+currentItem.getItemWeight()+"kg");
        TextView selectedWeight = (TextView) listItemView.findViewById(R.id.selectedWeight);
        selectedWeight.setText(currentItem.getSelectedWeight());
        ImageView setImageView = (ImageView) listItemView.findViewById(R.id.image);
        setImageView.setImageResource(currentItem.getImageResourceId());
        setImageView.setVisibility(View.VISIBLE);

        return listItemView;
    }
}