import 'package:flutter/material.dart';
import 'dart:ui';
import 'package:provider/provider.dart';
import 'pages/gallery_page.dart';
import 'pages/home_page.dart';
import 'pages/cart_page.dart';
import 'pages/cart_provider.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => CartProvider(),
      child: MaterialApp(
        theme: ThemeData(
          fontFamily: 'CustomFont', // Set CustomFont as the default font
        ),
        debugShowCheckedModeBanner: false,
        home: DefaultTabController(
          length: 3, // Number of tabs
          child: Scaffold(
            appBar: AppBar(
              title: Image.asset(
                'assets/crumb.png',
                alignment: Alignment.center,
                width: 400,
                height: 80,
              ),
              backgroundColor: const Color(0xFF723F06), // Honey brown color for the AppBar background
              bottom: const TabBar(
                indicatorColor: Color(0xFFF5B651),
                labelColor: Color(0xFFF5B651),
                unselectedLabelColor: Colors.white,
                tabs: [
                  Tab(text: 'MENU'),
                  Tab(text: 'CART'),
                  Tab(text: 'GALLERY'),
                ],
              ),
            ),
            drawer: Drawer(
              child: Container(
                color: const Color(0xFFFAE9D9),
                child: ListView(
                  padding: EdgeInsets.zero,
                  children: [
                    const DrawerHeader(
                      decoration: BoxDecoration(
                        color: Color(0xFF723F06),
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
                MenuScreen(), // Your MenuScreen class implementation
                CartPage(), // No need to pass an empty list now
                const GalleryScreen(), // Your GalleryScreen class implementation
              ],
            ),
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
              color: Colors.white.withOpacity(0.1),
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
