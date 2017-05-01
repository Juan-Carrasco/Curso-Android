package com.example.juanc.pr2_coffee;

/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 */

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.icu.text.StringPrepParseException;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.net.URI;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numberOfCoffees = 0;
    static final int DEFAULT_PRICE = 5;
    static final int MAX_COFFEE = 100;
    // Declaration of Views

    TextView textview_quantity;// textview_order;
    EditText edittext_name;
    CheckBox checkbox_cream, checkbox_chocolate;
    String string_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview_quantity = (TextView) findViewById(R.id.quantity_text_view);
        //textview_order = (TextView) findViewById(R.id.text_view_result);
        edittext_name = (EditText) findViewById(R.id.edit_text_name);
        checkbox_cream = (CheckBox) findViewById(R.id.checkbox_cream);
        checkbox_chocolate = (CheckBox) findViewById(R.id.checkbox_chocolate);
    }
    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = DEFAULT_PRICE;
        if(checkbox_cream.isChecked()){
            price = price + 1;
        }
        if(checkbox_chocolate.isChecked()){
            price = price + 2;
        }

        displayMessage(price);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void sendMail(String name, String message){
        Intent intent_mail = new Intent(Intent.ACTION_SENDTO);
        intent_mail.setData(Uri.parse("mailto:"));
        intent_mail.putExtra(Intent.EXTRA_TEXT,message);
        intent_mail.putExtra(Intent.EXTRA_SUBJECT, "ORDER FROM " + name);
        if (intent_mail.resolveActivity(getPackageManager()) != null) {
            startActivity(intent_mail);
        }
    }
    private void displayMessage(int price){
        string_message = "";
        string_message = string_message.concat("Name: ");
        string_message = string_message.concat(edittext_name.getText().toString());
        string_message = string_message.concat("\nAdd whipped cream?: ");
        string_message = string_message.concat(String.valueOf(checkbox_cream.isChecked()));
        string_message = string_message.concat("\nAdd chocolate?: ");
        string_message = string_message.concat(String.valueOf((checkbox_chocolate.isChecked())));
        string_message = string_message.concat("\nQuantity: ");
        string_message = string_message.concat((String.valueOf(numberOfCoffees)));
        string_message = string_message.concat("\nTotal: ");
        string_message = string_message.concat(String.valueOf(numberOfCoffees * price));
        string_message = string_message.concat("$");
        sendMail(edittext_name.getText().toString(),string_message);
        Log.d("DEBUG", "1");
        //textview_order.setText(string_message);
    }
    public void increment(View view){
        if(numberOfCoffees<100) {
            numberOfCoffees++;
            display(numberOfCoffees);
        }
        else{
            Toast.makeText(MainActivity.this,"Maximum number reached", Toast.LENGTH_LONG).show();
        }
    }
    public void decrement(View view){
        if(numberOfCoffees>1) {
            numberOfCoffees--;
            display(numberOfCoffees);
        }
        else{
            Toast.makeText(MainActivity.this,"Can't order less than 1", Toast.LENGTH_LONG).show();
        }
    }

}