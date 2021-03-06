package com.zipcodewilmington.froilansfarm;

import com.zipcodewilmington.froilansfarm.Crops.*;
import com.zipcodewilmington.froilansfarm.Edibles.Carrot;
import com.zipcodewilmington.froilansfarm.Edibles.EarCorn;
import com.zipcodewilmington.froilansfarm.Edibles.Tomato;
import com.zipcodewilmington.froilansfarm.FarmStructures.Field;
import com.zipcodewilmington.froilansfarm.Interfaces.Edible;
import com.zipcodewilmington.froilansfarm.People.Farmer;
import com.zipcodewilmington.froilansfarm.Vehicles.Tractor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TuesdayTests {

    FroilansFarm froilansFarm;
    Tractor tractor;
    Farmer froilan;

    @Before
    public void setUp(){
        froilansFarm = FroilansFarm.getInstance().testFroilansFarm();
        tractor = (Tractor) froilansFarm.getFarm().getFarmVehicles().get(0);
        froilan = froilansFarm.getFroilan();
    }

    @Test
    public void mountTractorTest(){
        // When
        froilan.mount(tractor);
        boolean actual = tractor.isMounted();

        // Then
        Assert.assertTrue(actual);
    }

    @Test
    public void dismountTractorTest(){
        // Given
        froilan.mount(tractor);

        // When
        froilan.dismount(tractor);
        boolean actual = tractor.isMounted();

        // Then
        Assert.assertFalse(actual);
    }


   @Test
    public void harvestCropsThatHaveBeenFertilizedTest(){
        // Given
       Field field = froilansFarm.getField();

       froilan.mount(tractor);
        for (CropRow cropRow : field.getCropRows()){
            for(Crop crop : cropRow.getCrops()){
                crop.setHasBeenFertilized(true);
                crop.setHasBeenHarvested(false);
            }
        }

        // When
        tractor.operate(froilansFarm.getFarm());

        // Then
        for(CropRow cropRow : field.getCropRows()){
            for(Crop crop : cropRow.getCrops()){
                Assert.assertTrue(crop.hasBeenHarvested());
            }
        }
    }

    @Test
    public void harvestCropsThatHaveNotBeenFertilized(){
        // Given
        Field field = froilansFarm.getField();

        froilan.mount(tractor);
        for (CropRow cropRow : field.getCropRows()){
            for(Crop crop : cropRow.getCrops()){
                crop.setHasBeenFertilized(false);
                crop.setHasBeenHarvested(false);
            }
        }

        // When
        tractor.operate(froilansFarm.getFarm());

        // Then
        for(CropRow cropRow : field.getCropRows()){
            for(Crop crop : cropRow.getCrops()){
                Assert.assertFalse(crop.hasBeenHarvested());
            }
        }
    }

    @Test
    public void harvestCropsThatHaveAlreadyBeenHarvestedTest() {
        // Given
        Field field = froilansFarm.getField();

        froilan.mount(tractor);
        for (CropRow cropRow : field.getCropRows()) {
            for (Crop crop : cropRow.getCrops()) {
                crop.setHasBeenFertilized(true);
                crop.setHasBeenHarvested(true);
            }
        }
        int expected = 0;

        // When
        tractor.operate(froilansFarm.getFarm());
        int actual = froilansFarm.getFarm().getSilo().getEdibles().size();

        // Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void harvestCropsSiloTest() {
        // Given
        Field field = froilansFarm.getField();

        froilan.mount(tractor);
        for (CropRow cropRow : field.getCropRows()) {
            for (Crop crop : cropRow.getCrops()) {
                crop.setHasBeenFertilized(true);
                crop.setHasBeenHarvested(false);
            }
        }

        List<Edible> expected = new ArrayList<>();
        expected.add(new EarCorn());
        expected.add(new Tomato());
        expected.add(new EarCorn());
        expected.add(new Carrot());
        expected.add(new Tomato());

        List<Edible> actual;

        // When
        tractor.operate(froilansFarm.getFarm());
        actual = froilansFarm.getFarm().getSilo().getEdibles();

        // Then
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i).getClass(), actual.get(i).getClass());
        }
    }

    @Test
    public void tractorMakeNoiseTest(){
        // Given
        String expected = "Chug-chug-chug!";

        // When
        String actual = tractor.makeNoise();

        // Then
        Assert.assertEquals(expected, actual);
    }
}
