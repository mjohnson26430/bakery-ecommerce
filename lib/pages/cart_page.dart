import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import '../Models/cart_item.dart';
import 'cart_provider.dart';

class CartPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final cartProvider = Provider.of<CartProvider>(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text('Your Cart'),
        backgroundColor: Colors.white,
        actions: [
          TextButton(
            onPressed: () {
              Navigator.pop(context);
            },
            child: const Text(
              'Continue Shopping',
              style: TextStyle(color: Colors.black,
            ),
          ),
          )],
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Expanded(
              child: ListView.builder(
                itemCount: cartProvider.cartItems.length,
                itemBuilder: (context, index) {
                  var item = cartProvider.cartItems[index];
                  return Card(
                    margin: const EdgeInsets.symmetric(vertical: 8.0),
                    child: Padding(
                      padding: const EdgeInsets.all(16.0),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Row(
                            children: [
                              Image.asset(
                                item.image,
                                width: 50,
                                height: 50,
                                fit: BoxFit.cover,
                              ),
                              const SizedBox(width: 16),
                              Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                  Text(
                                    item.name,
                                    style: const TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 16,
                                    ),
                                  ),
                                  const Text(
                                    'SKU: 14169',
                                    style: TextStyle(
                                      color: Colors.grey,
                                    ),
                                  ),
                                  const Text(
                                    'In Stock',
                                    style: TextStyle(
                                      color: Colors.green,
                                    ),
                                  ),
                                ],
                              ),
                              const Spacer(),
                              Column(
                                crossAxisAlignment: CrossAxisAlignment.end,
                                children: [
                                  Text('\$${item.price}'),
                                  Row(
                                    children: [
                                      IconButton(
                                        icon: const Icon(Icons.remove),
                                        onPressed: () {
                                          // Decrease quantity logic
                                        },
                                      ),
                                      Text('${item.quantity}'),
                                      IconButton(
                                        icon: const Icon(Icons.add),
                                        onPressed: () {
                                          // Increase quantity logic
                                        },
                                      ),
                                    ],
                                  ),
                                  Text('Total: \$${item.price * item.quantity}'),
                                ],
                              ),
                            ],
                          ),
                          const Align(
                            alignment: Alignment.centerRight,
                            child: TextButton(
                              onPressed: null, // Edit item functionality
                              child: Text(
                                'Edit',
                                style: TextStyle(
                                  color: Colors.blue,
                                ),
                              ),
                            ),
                          ),
                        ],
                      ),
                    ),
                  );
                },
              ),
            ),
            Container(
              padding: const EdgeInsets.all(16.0),
              decoration: BoxDecoration(
                color: Colors.grey[200],
                borderRadius: BorderRadius.circular(10),
              ),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  const Text('Promotions', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                  const SizedBox(height: 10),
                  Row(
                    children: [
                      Expanded(
                        child: TextField(
                          decoration: InputDecoration(
                            hintText: 'Enter your code',
                          ),
                        ),
                      ),
                      ElevatedButton(
                        onPressed: () {},
                        child: const Text('Submit'),
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  const Text('Order Summary', style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
                  const SizedBox(height: 10),
                  Text('Subtotal: \$${cartProvider.calculateTotalCost()}'),
                  Text('Shipping cost: \$18.97'),
                  const Text('Shipping Discount: -\$18.97'),
                  const Text('Estimated Sales Tax: TBD'),
                  const Divider(),
                  Text('Estimated Total: \$${cartProvider.calculateTotalCost()}', style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
                  const SizedBox(height: 20),
                  ElevatedButton(
                    onPressed: () {},
                    child: const Text('CHECKOUT',
                      style: const TextStyle(color: Colors.white),
                    ),

                    style: ElevatedButton.styleFrom(

                      minimumSize: const Size(double.infinity, 36),
                      backgroundColor: const Color(0xFF723F06),
                    ),
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
