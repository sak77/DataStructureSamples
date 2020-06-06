package com.example.datastructuresamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    MyDynamicArray<Car> array = new MyDynamicArray<>();

    //define local class Car
    class Car {
        private String name;
        private String color;
        private String manufacturer;

        Car(String strName, String strColor, String strManufacturer) {
            this.name = strName;
            this.color = strManufacturer;
            this.color= strColor;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArray();
        //printArrayElements();

        //Now delete one item and print array elements
        array.deleteElementAt(7);

        //Add element
        Car myNewCar = new Car("Bentley", "Black", "Rolls Royce");
        array.addElement(myNewCar);

        //Check if new item was added to list
        if (array.contains(myNewCar)) {
            Log.i("TAG", "Your car is added successfully!");
        } else {
            Log.i("TAG", "New car not found!");
        }
    }


    private void populateArray() {
        for (int i = 0; i < 10; i++) {
            Car myCar = new Car("Car Name " + i, "Color " + i, "Manufacturer " + i);
            array.addElement(myCar);
        }
    }

    private void printArrayElements() {
        for (int i = 0; i <= array.last_item_index; i++) {
            Car currentCar = array.getElementAt(i);
            Log.i("TAG", "Car Name = " + currentCar.name + ", Color = " + currentCar.color);
        }
    }
}