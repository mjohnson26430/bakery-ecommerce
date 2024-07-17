import 'package:flutter/material.dart';

// Data model
class Product {
  final String title;
  final String imagePath;
  final double price;

  Product({required this.title, required this.imagePath, required this.price});
}

final List<Product> products = [
  // Product data added here
  Product(
    title: 'Anatomy Of A Fall',
    imagePath: 'assets/anatomyFall.jpg',
    price: 29.59,
  ),
  Product(
    title: 'The Red Shoes',
    imagePath: 'assets/redShoe.jpg',
    price: 19.99,
  ),
  Product(
    title: 'Trainspotting',
    imagePath: 'assets/trainspotting.jpg',
    price: 20.19,
  ),
  Product(
    title: 'La Haine',
    imagePath: 'assets/hair.jpg',
    price: 25.00,
  ),
];

// Design the UI for a product item
// This layout is what the user will see
class ProductItem extends StatelessWidget {
  final Product product;

  const ProductItem({super.key, required this.product});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          AspectRatio(
            aspectRatio: 3 / 2, // Adjust the aspect ratio as needed
            child: Image.asset(
              product.imagePath,
              fit: BoxFit.cover, // Adjust the image to cover the entire box
            ),
          ),
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Text(
              product.title,
              style: const TextStyle(fontWeight: FontWeight.bold),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 8.0),
            child: Text('\$${product.price.toString()}'),
          ),
        ],
      ),
    );
  }
}

// Set up the GridView
class ProductGrid extends StatelessWidget {
  const ProductGrid({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('NEW RELEASES'),
      ),
      body: GridView.builder(
        padding: const EdgeInsets.all(10.0),
        itemCount: products.length,
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2, // Number of columns
          crossAxisSpacing: 20.0, // Increased spacing between columns
          mainAxisSpacing: 20.0, // Increased spacing between rows
          childAspectRatio: 0.8, // Adjusted aspect ratio
        ),
        itemBuilder: (ctx, i) => ProductItem(product: products[i]),
      ),
    );
  }
}

// Main Application screen sets the material app and product grid as the home screen
// All in all it's not dissimilar to what's done in Android Studio; it's basically like setting up a RecyclerView
// and making an adapter and models. The same concepts apply, just done in one code instead of different activities and XML files.
void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'GridView Practice',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const ProductGrid(),
    );
  }
}
