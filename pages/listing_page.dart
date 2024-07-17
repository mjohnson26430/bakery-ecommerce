import 'package:flutter/material.dart';
import 'product_details_page.dart';

class ProductListingScreen extends StatefulWidget {
  final String category;

  const ProductListingScreen({super.key, required this.category});

  @override
  _ProductListingScreenState createState() => _ProductListingScreenState();
}

class _ProductListingScreenState extends State<ProductListingScreen> {
  final List<Map<String, String>> breadProducts = [
    {'name': 'Sourdough', 'image': 'assets/sourdough.jpeg', 'price': '\$600'},
    {'name': 'Whole wheat', 'image': 'assets/wheat.jpg', 'price': '\$300'},
    {'name': 'Rye Bread', 'image': 'assets/rye.jpg', 'price': '\$400'},
    {'name': 'Croissant', 'image': 'assets/croissant.jpg', 'price': '\$600'},
    {'name': 'Naan', 'image': 'assets/naan.jpg', 'price': '\$500'},
    {'name': 'Focaccia', 'image': 'assets/focaccia.jpg', 'price': '\$800'},
  ];

  final List<Map<String, String>> cakeProducts = [
    {'name': 'Chocolate Cake', 'image': 'assets/chocolate.jpeg', 'price': '\$700'},
    {'name': 'Carrot cake', 'image': 'assets/carrot.jpg', 'price': '\$500'},
    {'name': 'Red Velvet Cake', 'image': 'assets/velvet.jpg', 'price': '\$600'},
    {'name': 'Cheesecake', 'image': 'assets/cheese.jpg', 'price': '\$900'},
    {'name': 'Black Forest Cake', 'image': 'assets/forrest.jpg', 'price': '\$750'},
    {'name': 'Lemon Cake', 'image': 'assets/lemon.jpg', 'price': '\$550'},
  ];

  final List<Map<String, String>> beverageProducts = [
    {'name': 'Espresso', 'image': 'assets/espresso.jpg', 'price': '\$600'},
    {'name': 'Latte', 'image': 'assets/latte.jpg', 'price': '\$650'},
    {'name': 'Cappuccino', 'image': 'assets/cappuccino.jpeg', 'price': '\$550'},
    {'name': 'Mocha', 'image': 'assets/mocha.jpg', 'price': '\$400'},
    {'name': 'Iced coffee', 'image': 'assets/coffee.jpg', 'price': '\$500'},
    {'name': 'Lemonade', 'image': 'assets/lemonade.jpg', 'price': '\$350'},
  ];

  final List<Map<String, String>> pastryProducts = [
    {'name': 'Glazed donut', 'image': 'assets/glazed.jpg', 'price': '\$400'},
    {'name': 'Danish', 'image': 'assets/danish.jpg', 'price': '\$500'},
    {'name': 'Blueberry Muffin', 'image': 'assets/muffin.jpg', 'price': '\$550'},
    {'name': 'Coffee Eclair', 'image': 'assets/eclair.png', 'price': '\$700'},
    {'name': 'Cranberry Orange Scone', 'image': 'assets/scone.jpg', 'price': '\$850'},
    {'name': 'Apple Pie', 'image': 'assets/pie.jpg', 'price': '\$850'},
  ];

  List<Map<String, String>> displayedProducts = [];

  @override
  void initState() {
    super.initState();
    displayedProducts = getProductList();
  }

  List<Map<String, String>> getProductList() {
    switch (widget.category) {
      case 'BREAD/LOAVES':
        return breadProducts;
      case 'CAKES':
        return cakeProducts;
      case 'BEVERAGES':
        return beverageProducts;
      case 'PASTRIES':
        return pastryProducts;
      default:
        return [];
    }
  }

  void searchProduct(String query) {
    final products = getProductList();
    final filteredProducts = products.where((product) {
      final nameLower = product['name']!.toLowerCase();
      final searchLower = query.toLowerCase();
      return nameLower.contains(searchLower);
    }).toList();

    setState(() {
      displayedProducts = filteredProducts;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFF723F06), // honey brown color for the AppBar background
        title: Text(
            widget.category,
        style: TextStyle (color:Colors.white),
      ),
    ),

      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(10.0),
            child: TextField(
              decoration: InputDecoration(
                hintText: 'Search',
                prefixIcon: const Icon(Icons.search),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(10.0),
                ),
              ),
              onChanged: searchProduct,
            ),
          ),
          Expanded(
            child: GridView.builder(
              padding: const EdgeInsets.all(10.0),
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                crossAxisSpacing: 10.0,
                mainAxisSpacing: 10.0,
                childAspectRatio: 0.75, // Adjust the aspect ratio to make images uniform
              ),
              itemCount: displayedProducts.length,
              itemBuilder: (context, index) {
                return GestureDetector(
                  onTap: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => ProductDetailScreen(product: displayedProducts[index]),
                      ),
                    );
                  },
                  child: Card(
                    child: Stack(
                      children: [
                        Column(
                          children: [
                            Image.asset(
                              displayedProducts[index]['image']!,
                              fit: BoxFit.cover,
                              height: 150,
                              width: double.infinity,
                            ),
                            Padding(
                              padding: const EdgeInsets.all(8.0),
                              child: Column(
                                children: [
                                  Text(displayedProducts[index]['name']!),
                                  Text(displayedProducts[index]['price']!),
                                ],
                              ),
                            ),
                          ],
                        ),
                        const Positioned(
                          top: 8,
                          right: 8,
                          child: Icon(
                            Icons.shopping_cart,
                            color: Colors.black,
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
