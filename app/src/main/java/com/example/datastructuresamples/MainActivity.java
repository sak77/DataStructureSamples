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
            this.color = strColor;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //workWithDynamicArrays();
        //dllOperations();
        //pushandpoptrial();
        //validateBrackets("[]{}({})");
        workWithQueues();
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


    //Array operations
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
        Car lastCar = myDoublyLinkedList.getElementAtIndex(myDoublyLinkedList.linked_list_size - 1);
        Log.i("TAG", "Last car name - " + lastCar.name);
        myDoublyLinkedList.removeItemAtEnd();
        Log.i("TAG", "New DLL size " + myDoublyLinkedList.getLinked_list_size());
        Car newLastCar = myDoublyLinkedList.getElementAtIndex(myDoublyLinkedList.linked_list_size - 1);
        Log.i("TAG", "New Last car name - " + newLastCar.name);
    }

    //Clear all
    public void emptyDll() {
        myDoublyLinkedList.clearAll();
        Log.i("TAG", "DLL size after empty - " + myDoublyLinkedList.getLinked_list_size());
    }

    //Stack operations
    private void pushandpoptrial() {
        MyStack<Car> carStack = new MyStack<>(30);
        //Add new items to the stack
        for (int i = 0; i < 10; i++) {
            carStack.push(new Car("Car " + i, "Color " + i, "Manufacturer " + i));
        }
        Car topCar = carStack.peekTop();
        //Get top item
        Log.i("TAG", "Top item in stack is " + topCar.name);

        //Now pop 2 items from stack
        for (int i = 0; i < 2; i++) {
            carStack.pop();
        }

        //Again get top Car
        topCar = carStack.peekTop();
        //Get top item
        Log.i("TAG", "Top item in stack is " + topCar.name);
    }

    //This method uses stack to validate input brackets string
    private void validateBrackets(String inputbrackets) {
        MyStack<Character> myStack = new MyStack<>(40);
        Character reverse_c = null;
        //Loop through elements in the string
        for (int i = 0; i < inputbrackets.length(); i++) {
            Character c = inputbrackets.charAt(i);
            if (myStack.getStackSize() == 0) {
                //add first element to the stack
                myStack.push(c);
                Log.i("TAG", "Pushed  :" + c);
            } else if (c.equals(reverse_c)) {
                //If matches then pop from stack
                Character pop_char = myStack.pop();
                Log.i("TAG", "Popped  :" + pop_char);
            } else {
                //else add to stack
                myStack.push(c);
                Log.i("TAG", "Pushed  :" + c);
            }
            if (myStack.getStackSize() > 0) {
                //Determine reverse of top character
                Character top = myStack.peekTop();
                reverse_c = getReverse(top);
                Log.i("TAG", "Reverse char  :" + reverse_c);
            }
        }
        //at end if stack is empty then string is valid
        if (myStack.getStackSize() == 0) {
            Log.i("TAG", "String is valid");
        } else {
            Log.i("TAG", "String is invalid");
        }
    }

    private Character getReverse(Character input) {
        switch (input) {
            case '{':
                return '}';
            case '(':
                return ')';
            case '[':
                return ']';
            default:
                return null;
        }
    }

    //Working with Queues
    public void workWithQueues() {
        MyDLLQueue<Car> myAssemblyQueue = new MyDLLQueue<>();
        //MyArrayBasedQueue<Car> myAssemblyQueue = new MyArrayBasedQueue<>(20);

        //Enqueue new Cars to the queue
        myAssemblyQueue.enqueue(new Car("Ford", "Grey", "Ford Motors"));

        for (Car currentCar: myAssemblyQueue) {
            Log.i("TAG", "Car in queue " + currentCar.name);
        }
        //Now once car is ready then dequeue it from the queue
        try {
            myAssemblyQueue.dequeue();

            Log.i("TAG", "Cars remaining in queue " + myAssemblyQueue.getQueueSize());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}