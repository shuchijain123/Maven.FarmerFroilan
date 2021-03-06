package com.zipcodewilmington.froilansfarm.Crops;

import java.util.ArrayList;
import java.util.List;

public class CropRow   {
    public boolean hasBeenFertilized() {
        return hasBeenFertilized;
    }

    public void setHasBeenFertilized(boolean hasBeenFertilized) {
        this.hasBeenFertilized = hasBeenFertilized;
        for(Crop crop : this.getCrops()){
            crop.setHasBeenFertilized(hasBeenFertilized);
        }
    }

    private boolean hasBeenFertilized;

    private List<Crop> crops;

    public CropRow(){
        crops = new ArrayList<>();
    }

    public List<Crop> getCrops() {
        return crops;
    }

    public void addCrop(Crop crop){
        crops.add(crop);
    }

    public void clearCrops(){
        crops.clear();
    }
}


