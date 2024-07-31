class CartItem {
  final String name;
  final String image;
  final int price;
  int quantity; // Ensure this is mutable

  CartItem({
    required this.name,
    required this.image,
    required this.price,
    required this.quantity,
  });

  // Method to set quantity
  void setQuantity(int newQuantity) {
    quantity = newQuantity;
  }
}
