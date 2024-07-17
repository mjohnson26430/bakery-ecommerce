import 'package:flutter/material.dart';
import 'dart:ui';
import 'package:flutterpr/pages/gallery_page.dart';
import 'package:flutterpr/pages/home_page.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        fontFamily: 'CustomFont', // Set CustomFont as the default font
      ),
      home: DefaultTabController(
        length: 4, // Number of tabs
        child: Scaffold(
          appBar: AppBar(

            backgroundColor:const Color(0xFF723F06) , // honey brown color for the AppBar background
            bottom: const TabBar(
              indicatorColor: Color(0xFFF5B651), // Custom indicator color
              labelColor: Color(0xFFF5B651), // Custom label color
              unselectedLabelColor: Colors.white, // Unselected label color
              tabs: [
                Tab(text: 'MENU'),
                Tab(text: 'CART'),
                Tab(text: 'GALLERY'),
                Tab(text: 'CONTACT US'),
              ],
            ),
          ),
          drawer: Drawer(
            child: Container(
              color: const Color (0xFFFAE9D9), // beige color for the Drawer body background
              child: ListView(
                padding: EdgeInsets.zero,
                children: [
                  const DrawerHeader(
                    decoration: BoxDecoration(
                      color: Color(0xFF723F06), // honey brown color for the Drawer header background
                      image: DecorationImage(
                        fit: BoxFit.fitHeight,
                        image: AssetImage("assets/crumb.png"), // Sidebar menu logo
                      ),
                    ),
                    child: Text(
                      "Drawer Header",
                      style: TextStyle(color: Colors.transparent), // Transparent text color
                    ),
                  ),
                  ListTile(
                    leading: Image.asset('assets/tables.png', width: 24, height: 24),
                    title: const Text("Bookings"),
                    onTap: () {
                      Navigator.of(context).pop();
                    },
                  ),
                  ListTile(
                    leading: Image.asset('assets/orderList.png', width: 24, height: 24),
                    title: const Text("Order List"),
                    onTap: () {
                      Navigator.of(context).pop();
                    },
                  ),
                  ListTile(
                    leading: Image.asset('assets/order.png', width: 24, height: 24),
                    title: const Text("My Account"),
                    onTap: () {
                      Navigator.of(context).pop();
                    },
                  ),
                  ListTile(
                    leading: Image.asset('assets/dine.png', width: 24, height: 24),
                    title: const Text("Notifications"),
                    onTap: () {
                      Navigator.of(context).pop();
                    },
                  ),
                ],
              ),
            ),
          ),
          body: TabBarView(
            children: [
              MenuScreen(),
              InformationScreen(),
              const GalleryScreen(),
              const ContactScreen(),
            ],
          ),
        ),
      ),
    );
  }
}


class GlassCard extends StatelessWidget {
  final Widget child;

  const GlassCard({super.key, required this.child});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: ClipRRect(
        borderRadius: BorderRadius.circular(10),
        child: BackdropFilter(
          filter: ImageFilter.blur(sigmaX: 10, sigmaY: 10),
          child: Container(
            decoration: BoxDecoration(
              color: Colors.white.withOpacity(0.1), // More transparent white color
              borderRadius: BorderRadius.circular(10),
            ),
            child: Padding(
              padding: const EdgeInsets.all(8.0),
              child: Center(child: child),
            ),
          ),
        ),
      ),
    );
  }
}



class InformationScreen extends StatelessWidget {
  InformationScreen({super.key});

  final List<Map<String, String>> imagesAndDescriptions = [
    {
      'image': 'assets/ursusCast.png',
      'description':
      'At Ursus, our mission is to bring the elegance of fine dining to everyone, creating an unforgettable experience with every meal. Founded in 1973, Ursus has always been dedicated to using the highest quality ingredients and providing impeccable service to all who walk through our doors.\n\nOur kitchen is a harmonious blend of tradition and innovation, where every dish is crafted to delight your senses and nourish your soul. We believe that exceptional food should be accessible to everyone, and our chefs are committed to creating meals that are both sophisticated and comforting.'
    },
    {
      'image': 'assets/ursusThree.jpeg',
      'description':
      'Dynamic means force and stands for the living formative forces of nature. These forces are not visible, but their biological footprints are. The effects can be seen if one learns to observe and understand the connections between the formative forces and the physical matter of all organisms.\n\nAt Ursus, we embrace this philosophy by carefully selecting our ingredients, respecting the natural essence of each element, and presenting it in a way that honors its origin. Our dishes are a testament to the rich tapestry of nature\'s bounty, thoughtfully prepared to restore, challenge, and enrich.'
    }
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.black,
        child: Center(
          child: Padding(
            padding: const EdgeInsets.all(16.0), // Adjusted padding for better layout
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                const SizedBox(height: 30),
                const Text(
                  'Welcome to Ursus',
                  style: TextStyle(
                    color: Color(0xFFF5B651),
                    fontSize: 28,
                    fontWeight: FontWeight.bold,
                    letterSpacing: 1.5,
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 20),
                const Text(
                  'Where Quality Meets Humility',
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 20,
                    fontWeight: FontWeight.w600,
                  ),
                  textAlign: TextAlign.center,
                ),
                const SizedBox(height: 30),
                Expanded(
                  child: ListView.builder(
                    itemCount: imagesAndDescriptions.length + 1,
                    itemBuilder: (context, index) {
                      if (index == 1) {
                        return const Column(
                          crossAxisAlignment: CrossAxisAlignment.center,
                          children: [
                            Text(
                              'Our Philosophy',
                              style: TextStyle(
                                color: Colors.white,
                                fontSize: 22,
                                fontWeight: FontWeight.bold,
                              ),
                              textAlign: TextAlign.center,
                            ),
                            SizedBox(height: 20),
                          ],
                        );
                      }
                      int dataIndex = index < 1 ? index : index - 1;
                      return Column(
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: [
                          ClipRRect(
                            borderRadius: BorderRadius.circular(12.0),
                            child: Image.asset(imagesAndDescriptions[dataIndex]['image']!),
                          ),
                          const SizedBox(height: 20),
                          Padding(
                            padding: const EdgeInsets.symmetric(horizontal: 12.0),
                            child: Text(
                              imagesAndDescriptions[dataIndex]['description']!,
                              style: const TextStyle(
                                color: Colors.white,
                                fontSize: 16,
                                fontWeight: FontWeight.normal,
                                height: 1.5, // Adjusted line height for better readability
                              ),
                              textAlign: TextAlign.justify,
                            ),
                          ),
                          const SizedBox(height: 30), // Increased padding between items
                        ],
                      );
                    },
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}


class ContactScreen extends StatelessWidget {
  const ContactScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.black, // Setting the background color to black
        child: const Center(
          child: Text(
            'Contact Screen',
            style: TextStyle(
              color: Colors.white, // Making text visible on black background
              fontSize: 24, // Setting text size to 24
            ),
          ),
        ),
      ),
    );
  }
}

class OrderScreen extends StatelessWidget {
  const OrderScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        color: Colors.black, // Setting the background color to black
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              const Text(
                'Order Screen',
                style: TextStyle(
                  color: Colors.white, // Making text visible on black background
                  fontSize: 24, // Setting text size to 24
                ),
              ),
              const SizedBox(height: 20),
              ElevatedButton(
                style: ElevatedButton.styleFrom(
                  backgroundColor: Colors.white, // White button background
                  foregroundColor: Colors.black, // Black text color
                ),
                onPressed: () {
                  // Add your order now logic here
                },
                child: const Text('Order Now'),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
