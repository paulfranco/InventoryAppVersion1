package co.paulfran.paulfranco.inventoryappversion1.data;

import android.provider.BaseColumns;

public final class ProductContract {

    // Constructor
    private ProductContract(){}

    public static abstract class ProductEntry implements BaseColumns {

        // Table Name
        public final static String TABLE_NAME = "products";

        // Column Names Constants
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME = "product_name";
        public final static String COLUMN_PRODUCT_PRICE = "price";
        public final static String COLUMN_PRODUCT_QTY = "quantity";
        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_PRODUCT_SUPPLIER_PHONE = "supplier_phone";

    }
}
