import 'package:flutter/material.dart';

class ProductDetailScreen extends StatelessWidget {
  final Map<String, String> product;

  const ProductDetailScreen({super.key, required this.product});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(product['name']!),
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [

            SizedBox(

              height: 300,
              child: PageView(
                children: [
                  Image.asset(product['image']!, fit: BoxFit.cover),
                  // Add more images here
                ],
              ),
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    product['name']!,
                    style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                  ),
                  Text(
                    product['price']!,
                    style: const TextStyle(fontSize: 20, color: Colors.green),
                  ),
                  const SizedBox(height: 10),

                  const SizedBox(height: 10),
                  const Text(
                    'Specifications:',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const Text(
                    'Detailed specifications of the product go here.',
                    style: TextStyle(fontSize: 16),
                  ),
                  const SizedBox(height: 20),
                  ElevatedButton(
                    onPressed: () {
                      // Add to cart logic here
                    },
                    child: const Text('Add to Cart'),

                  ),
                  const SizedBox(height: 20),
                  const Text(
                    'Ratings and Reviews:',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.normal),
                  ),
                  // Add ratings and reviews here
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
