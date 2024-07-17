import 'package:flutter/material.dart';

import '../pages/listing_page.dart';

class CategoryCard extends StatelessWidget {
  const CategoryCard({
    super.key,
    required this.imagesAndDescriptions,
    required this.index,
  });

  final List<Map<String, String>> imagesAndDescriptions;
  final int index;

  @override
  Widget build(BuildContext context) {
    return Card(
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.circular(15.0),
      ),
      elevation: 5,
      margin: const EdgeInsets.symmetric(vertical: 10.0, horizontal: 16.0),
      child: Column(
        children: [
          Stack(
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(15.0),
                child: Image.asset(
                  imagesAndDescriptions[index]['image']!,
                  height: 150, // Set the desired height
                  width: double.infinity,
                  fit: BoxFit.cover,
                ),
              ),
              const Positioned(
                top: 10,
                right: 10,
                child: Icon(
                  Icons.arrow_forward_ios,
                  color: Colors.white,
                  size: 24,
                ),
              ),
              Positioned.fill(
                child: Material(
                  color: Colors.transparent,
                  child: InkWell(
                    borderRadius: BorderRadius.circular(15.0),
                    splashColor: Colors.black26,
                    onTap: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => ProductListingScreen(
                            category: imagesAndDescriptions[index]['description']!,
                          ),
                        ),
                      );
                    },
                  ),
                ),
              ),
            ],
          ),
          const SizedBox(height: 10),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16.0),
            child: Text(
              imagesAndDescriptions[index]['description']!,
              textAlign: TextAlign.center,
              style: const TextStyle(
                color: Color(0xFF1E1206), // Main menu description
                fontSize: 19, // Setting text size to 19
                fontStyle: FontStyle.italic, // Italic style for a refined look
              ),
            ),
          ),
          const SizedBox(height: 20),
        ],
      ),
    );
  }
}
