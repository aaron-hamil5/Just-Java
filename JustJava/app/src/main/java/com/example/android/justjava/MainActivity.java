package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**

 This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**

     This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice(quantity, 5);
        CheckBox whippedCreamCheckBox = findViewById(R.id.whippedCream);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        if (hasWhippedCream == true){
            price += ( 1 * quantity);
        }

        EditText NameBox = findViewById(R.id.NameOfUser);
        String UsersName = NameBox.getText().toString();

        CheckBox chocolate_checkbox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolate_checkbox.isChecked();
        if (hasChocolate == true){
            price += (2 * quantity);
        }

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "aaronleon28@gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order For " + UsersName);
        intent.putExtra(Intent.EXTRA_TEXT, createOrderSummary(price, hasWhippedCream, hasChocolate, UsersName));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice(int amount, int cost) {
        return amount * cost;
    }

    /**
     This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity = quantity + 1;
        if (quantity > 100) {
            Toast.makeText(this, "You cannot go over 100 coffees", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }

    /**
     This method is called when the negitive button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        if (quantity < 1) {
            Toast.makeText(this, "You cannot go under 1 coffee", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }


    /**
     * This method displays the given price on the screen.
     *
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(int price, boolean Cream, boolean Chocolate, String Person) {
        String priceMessage = "Name: " + Person;
        priceMessage += "\nAdd Whipped Cream: " + Cream;
        priceMessage += "\nAdd chocolate: " + Chocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**

     This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView =  findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}