import 'package:flutter/material.dart';
import '../widgets/category_card.dart';

class MenuScreen extends StatelessWidget {
  final List<Map<String, String>> imagesAndDescriptions = [
    {'image': 'assets/bread.jpg', 'description': 'BREAD/LOAVES'},
    {'image': 'assets/cake.jpg', 'description': 'CAKES'},
    {'image': 'assets/beverage.jpg', 'description': 'BEVERAGES'},
    {'image': 'assets/donut.jpg', 'description': 'PASTRIES'},
  ];

  MenuScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: const Color(0xFFFAE9D9), // Setting the background color to beige
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Padding(
                padding: EdgeInsets.only(top: 20.0), // Adjust the top padding value as needed
                child: Text(
                  "MENU",
                  style: TextStyle(
                    color: Color(0xFF1E1206), // Sub heading text
                    fontSize: 25, // Setting text size to 25
                  ),
                ),
              ),
              const SizedBox(height: 20),
              Expanded(
                child: ListView.builder(
                  itemCount: imagesAndDescriptions.length,
                  itemBuilder: (context, index) {
                    return CategoryCard(
                      imagesAndDescriptions: imagesAndDescriptions,
                      index: index,
                    );
                  },
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

