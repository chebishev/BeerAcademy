package com.example.android.beeracademy;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MoreBeer extends AppCompatActivity {

    //these variables will be called in different methods. That's why they are outside everything, except the class

    //this variable will be increment/decrement and will be used in the final calculations
    public int quantity = 0;

    //reference to the name field
    EditText nameField;

    //reference to the e-mail field
    EditText mailField;

    //reference to the first check box
    CheckBox darkAleCheckBox;

    //reference to the second check box
    CheckBox doubleBeerCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_beer);

        //references to the fields that are required for the submiting order and sedndig e-mail message:
        // name field, e-mail field, and the check boxes
        nameField = (EditText) findViewById(R.id.name_field);
        mailField = (EditText) findViewById(R.id.email_address);
        darkAleCheckBox = (CheckBox) findViewById(R.id.dark_ale_checkbox);
        doubleBeerCheckBox = (CheckBox) findViewById(R.id.double_beer_checkbox);

    }


    //this method will be called when we want to display some text on the screen under the increment/decrement buttons
    //it has defined place of 100dp width, which is approximately 6 rows
    private void displayMessage(String message) {
        TextView textView = (TextView) findViewById(R.id.order_text_view);
        textView.setText(message);
    }


    //this method only displays the number of beers. will work with the quantity variable
    private void displayQuantity(int numberOfBeers) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfBeers);
    }


    //works with "-" button. Can't display numbers under 0
    public void decrement(View view) {
        if (quantity == 0) {
            Toast.makeText(this, "There are no free beers here!", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        displayQuantity(quantity);

    }

    //works with the "+" button. Can't display numbers above 100
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 beers", Toast.LENGTH_SHORT).show();

            return;
        }
        quantity += 1;
        displayQuantity(quantity);

    }

    //this method will work with the values of the check boxes and the quantity variable
    public int calculatePrice(boolean darkAle, boolean doubleBeer) {
        int basePrice = 5;

        //add +1 if the box is checked
        if (darkAle) {
            basePrice += 1;
        }

        //add +2 if the box is checked
        if (doubleBeer) {
            basePrice += 2;
        }

        //calculates total price of the order
        return quantity * basePrice;
    }

    //this method will take all the values and put them in 6row string for sending mail or reviewing the order in its window
    private String createOrderSummary(String name, String mail, int price, boolean darkAle, boolean doubleBeer) {

        //updating the price message with the information of all values
        String priceMessage = "Name: " + name;
        priceMessage += "\nEmail: " + mail;
        //if the "Dark Ale" box is checked the "true" text will be changed to "Yes"
        //else the text will be "No"
        if (!darkAle) {
            priceMessage += "\nDark Ale: No";
        } else priceMessage += "\nDark Ale: Yes";
        //if the "Double beer" box is checked the "true" text will be changed to "Yes"
        //else the text will be "No"
        if (!doubleBeer) {
            priceMessage += "\nDouble Beer: No";
        } else priceMessage += "\nDouble Beer: Yes";
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }


    //this method will work when the Order button is clicked
    public void submitOrder(View view) {


        //gets information of edit text fields and checkboxes
        String name = nameField.getText().toString();

        String mail = mailField.getText().toString();

        boolean isDarkAle = darkAleCheckBox.isChecked();

        boolean isDoubleBeer = doubleBeerCheckBox.isChecked();


        //calculates the price with the information of the checkboxes
        int price = calculatePrice(isDarkAle, isDoubleBeer);


        String priceMessage = createOrderSummary(name, mail, price, isDarkAle, isDoubleBeer);

        if (quantity != 0) {
            displayMessage(priceMessage);
            //if quantity is 0 you will see this message
        } else {
            displayMessage("Nothing ordered yet! \nPlease press the \"+\" button!");
        }

    }

    //this method will work when the Send To.. button is clicked
    public void sendMail(View view) {
        String name = nameField.getText().toString();

        String mail = mailField.getText().toString();

        boolean isDarkAle = darkAleCheckBox.isChecked();

        boolean isDoubleBeer = doubleBeerCheckBox.isChecked();

        int price = calculatePrice(isDarkAle, isDoubleBeer);
        String priceMessage = createOrderSummary(name, mail, price, isDarkAle, isDoubleBeer);


        //make intent to the mail app
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        intent.putExtra(Intent.EXTRA_SUBJECT, "More Beer order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        //starts the funcionality only if quantity is more than 0
        if (intent.resolveActivity(getPackageManager()) != null && quantity > 0) {
            startActivity(intent);
            //returns message if quantity is 0
        } else {
            displayMessage("Nothing to send! \nComplete the order first!");
        }
    }

    //this method will work when the Reset button is clicked
    public void resetOrder(View view) {

        //clear name field
        nameField.setText("");

        //clear e-mail field
        mailField.setText("");

        //clear checkbox 1
        darkAleCheckBox.setChecked(false);

        //clear checkbox 2
        doubleBeerCheckBox.setChecked(false);

        //set the default order message
        displayMessage(getString(R.string.total).toString());

        //reset quantity value
        quantity = 0;

        //display quantity value
        displayQuantity(0);
    }

}