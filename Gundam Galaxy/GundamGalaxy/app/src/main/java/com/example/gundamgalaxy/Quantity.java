package com.example.gundamgalaxy;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Quantity {
    private TextView quantityTextView;
    private Button minusButton;
    private Button plusButton;
    private int quantity;

    public Quantity(TextView quantityTextView, Button minusButton, Button plusButton) {
        this.quantityTextView = quantityTextView;
        this.minusButton = minusButton;
        this.plusButton = plusButton;

        // Set initial quantity to 1
        this.quantity = 1;
        updateQuantityTextView();

        // Set onClickListeners for plus and minus buttons
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementQuantity();
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementQuantity();
            }
        });
    }

    // Method to increment the quantity
    private void incrementQuantity() {
        quantity++;
        updateQuantityTextView();
    }

    // Method to decrement the quantity
    private void decrementQuantity() {
        if (quantity > 1) {
            quantity--;
            updateQuantityTextView();
        }
    }

    // Method to update the quantity TextView
    private void updateQuantityTextView() {
        quantityTextView.setText(String.valueOf(quantity));
    }

    // Method to get the current quantity
    public int getQuantity() {
        return quantity;
    }
}


