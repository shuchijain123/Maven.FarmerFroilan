package com.zipcodewilmington.froilansfarm.Crops;

import com.zipcodewilmington.froilansfarm.Edibles.Tomato;

public class TomatoPlant extends Crop {

    private boolean hasBeenFertilized;
    private boolean hasBeenHarvested;

    public TomatoPlant(boolean hasBeenHarvested, boolean hasBeenFertilized) {
        super(hasBeenHarvested, hasBeenFertilized);
    }

    public TomatoPlant(){
        super(false, false);

    }

    @Override
    public Tomato yield(){
        return  null;
    }

    @Override
    public boolean isHasBeenFertilized() {
        return hasBeenFertilized;
    }

    @Override
    public void setHasBeenFertilized(boolean hasBeenFertilized) {
        this.hasBeenFertilized = hasBeenFertilized;
    }

    @Override
    public boolean isHasBeenHarvested() {
        return hasBeenHarvested;
    }

    @Override
    public void setHasBeenHarvested(boolean hasBeenHarvested) {
        this.hasBeenHarvested = hasBeenHarvested;
    }
}
