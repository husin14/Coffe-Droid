package com.example.d2a.coffedroid;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{


    int quantity = 0;
    int price = 0;
    TextView tvSummary;
            public final static String STATE_HASIL="";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSummary = findViewById(R.id.tvSummary);
        if (savedInstanceState != null)
        {
            String hasil= savedInstanceState.getString(STATE_HASIL);
            tvSummary.setText(hasil);
        }
    }

    //agar nilai tetap tersimpan ketika hp dirotate
public void onSaveInstanceState(Bundle outState)
{
    outState.putString(STATE_HASIL,tvSummary.getText().toString());
    super.onSaveInstanceState(outState);
}

public void Decrement (View view)
{
    //quantity = 0
    if (quantity == 0)
    {
        display (quantity);
    }
    else
    {
        quantity -=1;
    }
}

public void increment (View view)
{
    //quantity = quantity + 1
    quantity+=1;
    display (quantity);
}

public void display(int quantity)
{
    TextView tvQuantity = findViewById(R.id.tvQuantity);
    tvQuantity.setText(""+quantity);

}

public void btnReport (View view)
{
    //edittext
    EditText edtName = findViewById(R.id.edtName);
    String name = edtName.getText().toString();

    //checkBox
    String topping = "";
    CheckBox WhippedCreamCB = findViewById(R.id.cbWCream);
    boolean hasWhippedCream = WhippedCreamCB.isChecked();

    CheckBox Choco = findViewById(R.id.cbWcoco);
    boolean hasChoco = Choco.isChecked();

    //kondisi
    if (hasWhippedCream == true && hasChoco == true) {
            topping = "Whipped Cream & Chocolate";
        } else if (hasChoco == true) {
            topping = "Chocolate";
        } else if (hasWhippedCream == true) {
            topping = "Whipped Cream";
        } else {
            topping = "Tanpa Topping";
        }

    price = CalculatePrice (hasWhippedCream,hasChoco);
    String orderSummary = CreateOrderSummary (price,name,topping);
    DisplaySummary (price, orderSummary);
    }

    public void submitOrder(View view)
    {
        EditText edtName = findViewById(R.id.edtName);
        String name = edtName.getText().toString();

        //checkBox
        String topping = "";
        CheckBox WhippedCreamCB = findViewById(R.id.cbWCream);
        boolean hasWhippedCream = WhippedCreamCB.isChecked();

        CheckBox Choco = findViewById(R.id.cbWcoco);
        boolean hasChoco = Choco.isChecked();

        //kondisi
        if (hasWhippedCream == true && hasChoco == true) {
            topping = "Whipped Cream & Chocolate";
        } else if (hasChoco == true) {
            topping = "Chocolate";
        } else if (hasWhippedCream == true) {
            topping = "Whipped Cream";
        } else {
            topping = "Tanpa Topping";
        }

        price = CalculatePrice (hasWhippedCream,hasChoco);
        String orderSummary = CreateOrderSummary (price,name,topping);

        Intent implicitIntent = new Intent (Intent.ACTION_SENDTO);

        //
        implicitIntent.setData(Uri.parse("mailto:emailtujuan@gmail.com"));

        //subjectemail
        implicitIntent.putExtra(Intent.EXTRA_SUBJECT, "Rekap pesanan dari" +name);

        //isi email
        implicitIntent.putExtra(Intent.EXTRA_TEXT,orderSummary);

      //  if (implicitIntent.resolveActivity(getPackageManager()) !=null)
       // {
            startActivity(implicitIntent);
      //  }

    }
    public int CalculatePrice(boolean hasWhippedCream, boolean hasChoco)
    {
        int hargaawal=5000;
        if (hasWhippedCream)
        {
            hargaawal = hargaawal + 2000;
        }
        if (hasChoco)
        {
            hargaawal = hargaawal + 3000;
        }

        return quantity * hargaawal;
}

    private String CreateOrderSummary (int price, String name,String topping)
    {
        String priceMeesage =
                "Nama : "+name
                +"\nQuantity :"+quantity
                +"\nTopping :"+topping
                +"\nTotal Harga:"+price;

                return priceMeesage;
    }

    public void DisplaySummary (int price, String Summary)
    {
        TextView tvSummary = findViewById(R.id.tvSummary);
        tvSummary.setText (Summary);
    }

}