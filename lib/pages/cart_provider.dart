import 'package:flutter/material.dart';
import '../Models/cart_item.dart';

class CartProvider extends ChangeNotifier {
  List<CartItem> _cartItems = [];

  List<CartItem> get cartItems => _cartItems;

  void addToCart(CartItem item) {
    _cartItems.add(item);
    notifyListeners();
  }

  int calculateTotalCost() {
    return _cartItems.fold<int>(0, (total, current) {
      return total + current.price * current.quantity;
    });
  }
}
