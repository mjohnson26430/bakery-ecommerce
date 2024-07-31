import 'package:flutter/material.dart';
import 'package:photo_view/photo_view.dart';
import 'package:photo_view/photo_view_gallery.dart';
import 'package:provider/provider.dart';
import '../Models/cart_item.dart';
import 'cart_page.dart';
import 'cart_provider.dart';

class ProductDetailScreen extends StatefulWidget {
  final Map<String, dynamic> product;

  const ProductDetailScreen({super.key, required this.product});

  @override
  _ProductDetailScreenState createState() => _ProductDetailScreenState();
}

class _ProductDetailScreenState extends State<ProductDetailScreen> {
  String selectedSize = 'Medium';
  int basePrice = 0;
  int currentPrice = 0;
  int quantity = 1;
  late PageController pageController;

  @override
  void initState() {
    super.initState();
    basePrice = int.parse(widget.product['price']!.substring(1));
    currentPrice = basePrice + 200; // Default to medium size
    pageController = PageController();
  }

  @override
  void dispose() {
    pageController.dispose();
    super.dispose();
  }

  void updatePrice() {
    setState(() {
      if (selectedSize == 'Small') {
        currentPrice = basePrice;
      } else if (selectedSize == 'Medium') {
        currentPrice = basePrice + 200;
      } else if (selectedSize == 'Large') {
        currentPrice = basePrice + 400;
      }
    });
  }

  void addToCart(BuildContext context) {
    final cartProvider = Provider.of<CartProvider>(context, listen: false);
    cartProvider.addToCart(CartItem(
      name: widget.product['name'],
      image: widget.product['images'][0],
      price: currentPrice,
      quantity: quantity,
    ));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFF723F06),
        title: Text(
          widget.product['name']!,
          style: const TextStyle(color: Colors.white),
        ),
        actions: [
          IconButton(
            icon: const Icon(Icons.favorite_border),
            onPressed: () {
              // Handle favorite action
            },
          ),
        ],
      ),
      body: SingleChildScrollView(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Stack(
              children: [
                Container(
                  height: 300,
                  decoration: const BoxDecoration(
                    borderRadius: BorderRadius.only(
                      bottomLeft: Radius.circular(30),
                      bottomRight: Radius.circular(30),
                    ),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.black26,
                        blurRadius: 10,
                        offset: Offset(0, 10),
                      ),
                    ],
                  ),
                  child: ClipRRect(
                    borderRadius: const BorderRadius.only(
                      bottomLeft: Radius.circular(30),
                      bottomRight: Radius.circular(30),
                    ),
                    child: Hero(
                      tag: 'product_${widget.product['name']}',
                      child: PhotoViewGallery.builder(
                        itemCount: widget.product['images'].length,
                        builder: (context, index) {
                          return PhotoViewGalleryPageOptions(
                            imageProvider: AssetImage(widget.product['images'][index]),
                            minScale: PhotoViewComputedScale.contained,
                            maxScale: PhotoViewComputedScale.covered * 2,
                          );
                        },
                        pageController: pageController,
                        scrollPhysics: const BouncingScrollPhysics(),
                        backgroundDecoration: BoxDecoration(
                          color: Theme.of(context).canvasColor,
                        ),
                        loadingBuilder: (context, event) => Center(
                          child: Container(
                            width: 20.0,
                            height: 20.0,
                            child: CircularProgressIndicator(
                              value: event == null
                                  ? 0
                                  : event.cumulativeBytesLoaded / event.expectedTotalBytes!,
                            ),
                          ),
                        ),
                      ),
                    ),
                  ),
                ),
                Positioned(
                  left: 10,
                  top: 140,
                  child: GestureDetector(
                    onTap: () {
                      pageController.previousPage(
                        duration: const Duration(milliseconds: 300),
                        curve: Curves.easeInOut,
                      );
                    },
                    child: Container(
                      padding: const EdgeInsets.all(8),
                      decoration: BoxDecoration(
                        color: Colors.black.withOpacity(0.5),
                        shape: BoxShape.circle,
                      ),
                      child: const Icon(
                        Icons.arrow_back,
                        color: Colors.white,
                        size: 20,
                      ),
                    ),
                  ),
                ),
                Positioned(
                  right: 10,
                  top: 140,
                  child: GestureDetector(
                    onTap: () {
                      pageController.nextPage(
                        duration: const Duration(milliseconds: 300),
                        curve: Curves.easeInOut,
                      );
                    },
                    child: Container(
                      padding: const EdgeInsets.all(8),
                      decoration: BoxDecoration(
                        color: Colors.black.withOpacity(0.5),
                        shape: BoxShape.circle,
                      ),
                      child: const Icon(
                        Icons.arrow_forward,
                        color: Colors.white,
                        size: 20,
                      ),
                    ),
                  ),
                ),
              ],
            ),
            Padding(
              padding: const EdgeInsets.all(16.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Row(
                    children: [
                      Text(
                        widget.product['name']!,
                        style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
                      ),
                      const Spacer(),
                      Container(
                        padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                        decoration: BoxDecoration(
                          color: Colors.amber,
                          borderRadius: BorderRadius.circular(12),
                        ),
                        child: const Row(
                          children: [
                            Icon(Icons.star, color: Colors.white, size: 16),
                            SizedBox(width: 4),
                            Text(
                              '4.3',
                              style: TextStyle(color: Colors.white),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                  const SizedBox(height: 10),
                  Text(
                    '\$$currentPrice',
                    style: const TextStyle(fontSize: 20, color: Colors.green),
                  ),
                  const SizedBox(height: 20),
                  const Text(
                    'Size:',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 10),
                  Row(
                    children: [
                      ChoiceChip(
                        label: const Text('Small'),
                        selected: selectedSize == 'Small',
                        onSelected: (bool selected) {
                          setState(() {
                            selectedSize = 'Small';
                            updatePrice();
                          });
                        },
                      ),
                      const SizedBox(width: 10),
                      ChoiceChip(
                        label: const Text('Medium'),
                        selected: selectedSize == 'Medium',
                        onSelected: (bool selected) {
                          setState(() {
                            selectedSize = 'Medium';
                            updatePrice();
                          });
                        },
                      ),
                      const SizedBox(width: 10),
                      ChoiceChip(
                        label: const Text('Large'),
                        selected: selectedSize == 'Large',
                        onSelected: (bool selected) {
                          setState(() {
                            selectedSize = 'Large';
                            updatePrice();
                          });
                        },
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  Text(
                    widget.product['description']!,
                    style: const TextStyle(fontSize: 18, color: Colors.black),
                  ),
                  const SizedBox(height: 20),
                  Row(
                    children: [
                      const Text(
                        'Amount: ',
                        style: TextStyle(fontSize: 14, color: Colors.grey),
                      ),
                      const Spacer(),
                      Row(
                        children: [
                          IconButton(
                            icon: const Icon(Icons.remove),
                            onPressed: () {
                              setState(() {
                                if (quantity > 1) {
                                  quantity--;
                                }
                              });
                            },
                          ),
                          Text('$quantity'), // Display the quantity
                          IconButton(
                            icon: const Icon(Icons.add),
                            onPressed: () {
                              setState(() {
                                quantity++;
                              });
                            },
                          ),
                        ],
                      ),
                    ],
                  ),
                  const SizedBox(height: 20),
                  Center(
                    child: ElevatedButton(
                      onPressed: () {
                        addToCart(context);
                        Navigator.push(
                          context,
                          MaterialPageRoute(
                            builder: (context) => CartPage(),
                          ),
                        );
                      },
                      style: ElevatedButton.styleFrom(
                        backgroundColor: const Color(0xFF723F06),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20),
                        ),
                        padding: const EdgeInsets.symmetric(horizontal: 40, vertical: 15),
                      ),
                      child: const Text(
                        'Buy Now',
                        style: TextStyle(
                          fontSize: 18,
                          color: Colors.white,
                        ),
                      ),
                    ),
                  ),
                  const SizedBox(height: 20),
                  const Text(
                    'Ratings and Reviews:',
                    style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold),
                  ),
                  const SizedBox(height: 10),
                  const ReviewWidget(
                    reviewer: 'Jodiann Livingston',
                    date: 'July 11, 2024',
                    rating: 5,
                    title: 'Love Love Love',
                    review: 'Been coming here for years and the food has never failed me yet always so delicious and moist love this place down.',
                    size: 'Large',
                    verified: true,
                  ),
                  const SizedBox(height: 10),
                  const ReviewWidget(
                    reviewer: 'Kevin Campbell',
                    date: 'June 17, 2024',
                    rating: 5,
                    title: 'Absolutely Delicious',
                    review: 'The absolute best place for baked goods in the country, you will never see me going anywhere else.',
                    size: 'Medium',
                    verified: true,
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

class ReviewWidget extends StatelessWidget {
  final String reviewer;
  final String date;
  final int rating;
  final String title;
  final String review;
  final String size;
  final bool verified;

  const ReviewWidget({
    super.key,
    required this.reviewer,
    required this.date,
    required this.rating,
    required this.title,
    required this.review,
    required this.size,
    required this.verified,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: const EdgeInsets.all(8.0),
      decoration: BoxDecoration(
        border: Border.all(color: Colors.grey.shade300),
        borderRadius: BorderRadius.circular(10),
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Text(
                reviewer,
                style: const TextStyle(fontWeight: FontWeight.bold),
              ),
              const SizedBox(width: 8),
              if (verified)
                const Icon(
                  Icons.verified,
                  color: Colors.blue,
                  size: 16,
                ),
              const Spacer(),
              Text(date, style: const TextStyle(color: Colors.grey)),
            ],
          ),
          const SizedBox(height: 8),
          Row(
            children: List.generate(
              rating,
                  (index) => const Icon(Icons.star, color: Colors.amber, size: 16),
            ),
          ),
          const SizedBox(height: 8),
          Text(title, style: const TextStyle(fontWeight: FontWeight.bold)),
          const SizedBox(height: 8),
          Text(review),
          const SizedBox(height: 8),
          Text(
            ' Size: $size',
            style: const TextStyle(color: Colors.grey),
          ),
        ],
      ),
    );
  }
}
