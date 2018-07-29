package co.paulfran.paulfranco.inventoryappversion1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.paulfran.paulfranco.inventoryappversion1.data.ProductContract;
import co.paulfran.paulfranco.inventoryappversion1.data.ProductDBHelper;

public class EditorActivity extends AppCompatActivity {

    private ProductDBHelper mProductDBHelper;

    private EditText mNameEditText;
    private EditText mPriceEditText;
    private EditText mQuantityEditText;
    private EditText mSupplierNameEditText;
    private EditText mSupplierPhoneEditText;
    private Button addProductBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all of the views we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.productName);
        mPriceEditText = (EditText) findViewById(R.id.price);
        mQuantityEditText = (EditText) findViewById(R.id.quantity);
        mSupplierNameEditText = (EditText) findViewById(R.id.supplier);
        mSupplierPhoneEditText = (EditText) findViewById(R.id.supplierPhone);
        addProductBtn = (Button) findViewById(R.id.add_product_btn);

        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertProduct();
            }
        });

    }
    private void insertProduct() {
        // Read from the input fields
        String nameString = mNameEditText.getText().toString().trim();
        String priceString = mPriceEditText.getText().toString().trim();
        float price = Float.parseFloat(priceString);
        String quantityString = mQuantityEditText.getText().toString().trim();
        int quantity = Integer.parseInt(quantityString);
        String supplier = mSupplierNameEditText.getText().toString().trim();
        String supplierPhone = mSupplierPhoneEditText.getText().toString().trim();

        mProductDBHelper  = new ProductDBHelper(this);

        // Get the data repository in write mode
        SQLiteDatabase db = mProductDBHelper.getWritableDatabase();

        // Create ContentValues object
        ContentValues values = new ContentValues();

        // Store Key/Value Pairs
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, nameString);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_PRICE, price);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_QTY, quantity);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplier);
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE, supplierPhone);

        // Insert the values into the database
        long newRowID = db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);

        if (newRowID == -1) {
            Toast.makeText(this, R.string.error_saving, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, getString(R.string.product_saved) + newRowID, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditorActivity.this, MainActivity.class);
            startActivity(intent);
        }

    }



}
