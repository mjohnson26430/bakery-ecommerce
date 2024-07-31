import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';

class GalleryScreen extends StatelessWidget {
  const GalleryScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final List<String> imageUrls = [
      'assets/gal1.jpg',
      'assets/gal2.jpg',
      'assets/gal3.jpg',
      'assets/gal4.jpg',
      'assets/gal5.jpg',
      'assets/gal6.jpg',
      'assets/gal7.jpg',
      'assets/gal8.jpg',
      'assets/gal9.jpg',
      'assets/gal10.jpg',
    ];

    return Scaffold(
      body: Container(
        color: const Color(0xFFFAE9D9),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.start,  // Ensure widgets are aligned at the start
          crossAxisAlignment: CrossAxisAlignment.center,  // Center the children horizontally
          children: [
            const SizedBox(height: 40),
            const Text(
              'Tag us on Instagram for a chance to be featured @TheCozyCrumb',
              style: TextStyle(
                fontSize: 18,
                fontWeight: FontWeight.bold,
                color: Color(0xFF1E1206),
              ),
            ),
            const SizedBox(height: 20),
            CarouselSlider(
              options: CarouselOptions(
                height: 400,
                autoPlay: true,
                autoPlayInterval: const Duration(seconds: 3),
                enlargeCenterPage: true,
                aspectRatio: 16 / 9,
                viewportFraction: 0.8,
              ),
              items: imageUrls.map((url) {
                return Builder(
                  builder: (BuildContext context) {
                    return Container(
                      width: MediaQuery.of(context).size.width,
                      margin: const EdgeInsets.symmetric(horizontal: 5.0),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                        image: DecorationImage(
                          image: AssetImage(url),
                          fit: BoxFit.cover,
                        ),
                      ),
                    );
                  },
                );
              }).toList(),
            ),
          ],
        ),
      ),
    );
  }
}