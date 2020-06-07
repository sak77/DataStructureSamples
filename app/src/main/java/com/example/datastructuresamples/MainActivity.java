package com.example.datastructuresamples;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    MyDynamicArray<Car> array = new MyDynamicArray<>();
    MyDoublyLinkedList<Car> myDoublyLinkedList = new MyDoublyLinkedList<>();


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
        //workWithDynamicArrays();
        dllOperations();
    }


    private void workWithDynamicArrays() {
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

    //My doubly linked lists
    private void dllOperations() {
        populateMyDLL();
        addNewElementToDLL();
        removeFirstFromDll();
        removeLastFromDll();
        emptyDll();
    }

    //Add new items to the linked list
    private void populateMyDLL() {
        for (int i = 0; i < 10; i++) {
            Car myCar = new Car("Car Name " + i, "Color " + i, "Manufacturer " + i);
            //myDoublyLinkedList.addFirstElement(myCar);
            myDoublyLinkedList.addLastElement(myCar);
        }

        Log.i("TAG", "DLL size after adding elements - " + myDoublyLinkedList.getLinked_list_size());
    }

    //Add element at specific index
    public void addNewElementToDLL() {
        Car myNewCar = new Car("Mercedes AMG", "Red", "Mercedes");
        //Assuming we have 10 items in the dll, so adding this in between
        myDoublyLinkedList.addElementAt(myNewCar, 5);

        Car response = myDoublyLinkedList.getElementAtIndex(5);

        Log.i("TAG", "Car added at index  5 - " + response.name);
    }

    //Remove first
    public void removeFirstFromDll() {
        //Print data before removing element
        Log.i("TAG", "Original DLL size " + myDoublyLinkedList.getLinked_list_size());
        Car firstCar = myDoublyLinkedList.getElementAtIndex(0);
        Log.i("TAG", "First car name - " + firstCar.name);
        myDoublyLinkedList.removeItemAtStart();
        Log.i("TAG", "New DLL size " + myDoublyLinkedList.getLinked_list_size());
        Car newFirstCar = myDoublyLinkedList.getElementAtIndex(0);
        Log.i("TAG", "New First car name - " + newFirstCar.name);
    }

    //Remove last
    public void removeLastFromDll() {
        //Print data before removing element
        Log.i("TAG", "Original DLL size " + myDoublyLinkedList.getLinked_list_size());
        Car lastCar = myDoublyLinkedList.getElementAtIndex(myDoublyLinkedList.linked_list_size -1);
        Log.i("TAG", "Last car name - " + lastCar.name);
        myDoublyLinkedList.removeItemAtEnd();
        Log.i("TAG", "New DLL size " + myDoublyLinkedList.getLinked_list_size());
        Car newLastCar = myDoublyLinkedList.getElementAtIndex(myDoublyLinkedList.linked_list_size -1);
        Log.i("TAG", "New Last car name - " + newLastCar.name);
    }

    //Clear all
    public void emptyDll() {
        myDoublyLinkedList.clearAll();
        Log.i("TAG", "DLL size after empty - " + myDoublyLinkedList.getLinked_list_size());
    }
}