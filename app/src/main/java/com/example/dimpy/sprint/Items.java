package com.example.dimpy.sprint;

/**
 * Created by dimpy on 4/6/17.
 */

public class Items {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String itemName;
    private String itemValue;
    private String itemWeight;
    private String selectedWeight;
    private int ImageResourceId = NO_IMAGE_PROVIDED;

    Items(String itemName, String itemValue, String itemWeight, int ImageResourceId, String selectedWeight){
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.itemWeight = itemWeight;
        this.ImageResourceId = ImageResourceId;
        this.selectedWeight = selectedWeight;
    }

    public String getItemName(){
        return itemName;
    }
    public String getItemValue(){
        return itemValue;
    }
    public String getItemWeight(){
        return itemWeight;
    }
    public String getSelectedWeight(){
        return  selectedWeight;
    }
    public int getImageResourceId(){
        return ImageResourceId;
    }
    public void setSelectedWeights(String n){
        selectedWeight = n;
        //return selectedWeight;
    }


}
