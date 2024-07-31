import 'package:flutter/material.dart';
import 'product_details_page.dart';

class ProductListingScreen extends StatefulWidget {
  final String category;

  const ProductListingScreen({super.key, required this.category});

  @override
  _ProductListingScreenState createState() => _ProductListingScreenState();
}

class _ProductListingScreenState extends State<ProductListingScreen> {
  final List<Map<String, dynamic>> breadProducts = [
    {
      'name': 'Sourdough',
      'images': ['assets/sourdough.jpeg', 'assets/sourdough1.jpg', 'assets/sourdough3.jpg'],
      'price': '\$600',
      'description': 'A tangy, chewy bread with a crisp, golden crust. Perfect for sandwiches or as a side to your favorite soup'
    },
    {
      'name': 'Whole wheat',
      'images': ['assets/wheat.jpg', 'assets/wheat1.jpg', 'assets/wheat9.jpg'],
      'price': '\$300',
      'description': 'A hearty, nutty loaf packed with whole grains. Deliciously wholesome and perfect for a healthy breakfast toast.'
    },
    {
      'name': 'Rye Bread',
      'images': ['assets/rye.jpg', 'assets/rye1.jpg', 'assets/rye5.jpg'],
      'price': '\$400',
      'description': 'Rich, dense, and full of earthy flavor. Ideal for pairing with savory deli meats and cheeses.'
    },
    {
      'name': 'Croissant',
      'images': ['assets/croissant.jpg', 'assets/croissant2.jpg', 'assets/croissant3.jpg'],
      'price': '\$600',
      'description': 'Flaky, buttery layers that melt in your mouth. A classic French pastry, perfect with coffee or tea.'
    },
    {
      'name': 'Naan',
      'images': ['assets/naan.jpg', 'assets/naan2.jpg', 'assets/naan3.jpg'],
      'price': '\$500',
      'description': 'Soft, pillowy flatbread with a slightly smoky flavor. Best served warm and ideal for scooping up curries and dips.'
    },
    {
      'name': 'Focaccia',
      'images': ['assets/focaccia.jpg', 'assets/focaccia9.jpg', 'assets/focacia2.jpeg'],
      'price': '\$800',
      'description': 'Light, airy bread topped with olive oil and herbs. A savory Italian delight that pairs wonderfully with soups and salads.'
    },
  ];

  final List<Map<String, dynamic>> cakeProducts = [
    {
      'name': 'Chocolate Cake',
      'images': ['assets/chocolate.jpeg', 'assets/chocolate9.jpg', 'assets/chocolate10.jpg'],
      'price': '\$700',
      'description': 'Rich, moist layers of decadent chocolate, topped with a velvety chocolate ganache. A chocoholic’s dream!'
    },
    {
      'name': 'Carrot cake',
      'images': ['assets/carrot.jpg', 'assets/carrot9.jpg', 'assets/carrot10.jpg'],
      'price': '\$500',
      'description': 'Spiced to perfection and studded with fresh carrots and walnuts, topped with creamy, tangy cream cheese frosting.'
    },
    {
      'name': 'Red Velvet Cake',
      'images': ['assets/velvet.jpg', 'assets/velvet11.jpg', 'assets/velvet10.jpg'],
      'price': '\$600',
      'description': 'Luxuriously smooth with a hint of cocoa, crowned with a luscious cream cheese frosting. An elegant classic.'
    },
    {
      'name': 'Cheesecake',
      'images': ['assets/cheese.jpg', 'assets/cheese9.jpg', 'assets/cheese10.jpg'],
      'price': '\$900',
      'description': 'Creamy and indulgent with a buttery graham cracker crust. Available in classic, fruit-topped, or chocolate-drizzled varieties.'
    },
    {
      'name': 'Black Forest Cake',
      'images': ['assets/forrest.jpg', 'assets/forrest9.jpg', 'assets/forrest10.jpeg'],
      'price': '\$750',
      'description': 'Decadent layers of chocolate cake, whipped cream, and cherry filling, adorned with chocolate shavings and cherries.'
    },
    {
      'name': 'Lemon Cake',
      'images': ['assets/lemon.jpg', 'assets/lemon9.jpeg', 'assets/lemon10.jpg'],
      'price': '\$550',
      'description': 'Light, zesty cake bursting with fresh lemon flavor, finished with a sweet, tangy lemon glaze. Refreshingly delightful.'
    },
  ];

  final List<Map<String, dynamic>> beverageProducts = [
    {
      'name': 'Espresso',
      'images': ['assets/espresso.jpg', 'assets/espresso9.jpg', 'assets/latte10.jpg'],
      'price': '\$600',
      'description': 'Bold and robust with a rich crema. A perfect shot of pure coffee bliss to energize your day.'
    },
    {
      'name': 'Latte',
      'images': ['assets/latte.jpg', 'assets/latte11.jpg', 'assets/latte12.jpg'],
      'price': '\$650',
      'description': 'Smooth and creamy, combining rich espresso with steamed milk and a delicate layer of froth. A comforting classic.'
    },
    {
      'name': 'Cappuccino',
      'images': ['assets/cappuccino.jpeg', 'assets/cappuccino9.jpg', 'assets/cappuccino10.jpg'],
      'price': '\$550',
      'description': 'Equal parts espresso, steamed milk, and froth. A harmonious blend with a velvety texture and a touch of elegance.'
    },
    {
      'name': 'Mocha',
      'images': ['assets/mocha.jpg', 'assets/mocha9.jpg', 'assets/mocha10.jpg'],
      'price': '\$400',
      'description': 'A delightful fusion of espresso, steamed milk, and rich chocolate syrup, topped with a swirl of whipped cream. Chocolatey indulgence in every sip.'
    },
    {
      'name': 'Iced coffee',
      'images': ['assets/coffee.jpg', 'assets/coffee9.jpg', 'assets/coffee10.png'],
      'price': '\$500',
      'description': 'Refreshingly cool and invigorating, brewed strong and served over ice. Perfect for a hot day pick-me-up.'
    },
    {
      'name': 'Lemonade',
      'images': ['assets/lemonade.jpg', 'assets/lemonade9.jpg', 'assets/lemonade10.jpg'],
      'price': '\$350',
      'description': 'Freshly squeezed lemons with just the right amount of sweetness. A crisp and tangy refreshment to brighten your day.'
    },
  ];

  final List<Map<String, dynamic>> pastryProducts = [
    {
      'name': 'Glazed donut',
      'images': ['assets/glazed.jpg', 'assets/donut9.png', 'assets/donut10.jpg'],
      'price': '\$400',
      'description': 'Light and airy with a sweet, glossy glaze. A classic treat that melts in your mouth with each bite.'
    },
    {
      'name': 'Danish',
      'images': ['assets/danish.jpg', 'assets/danish9.jpg', 'assets/danish10.jpg'],
      'price': '\$500',
      'description': 'Flaky pastry filled with rich cream cheese or fruit preserves. A delightful blend of buttery and sweet flavors.'
    },
    {
      'name': 'Blueberry Muffin',
      'images': ['assets/muffin.jpg', 'assets/muffin9.jpg', 'assets/muffin10.jpg'],
      'price': '\$550',
      'description': 'Moist and fluffy, bursting with juicy blueberries and a hint of vanilla. Perfectly sweet and satisfying.'
    },
    {
      'name': 'Coffee Eclair',
      'images': ['assets/eclair.png', 'assets/eclair9.jpeg', 'assets/eclair10.jpg'],
      'price': '\$700',
      'description': 'Delicate choux pastry filled with creamy coffee custard and topped with a rich coffee glaze. A sophisticated indulgence.'
    },
    {
      'name': 'Cranberry Orange Scone',
      'images': ['assets/scone.jpg', 'assets/scone9.jpg', 'assets/scone10.jpg'],
      'price': '\$850',
      'description': 'Tender and crumbly with tangy cranberries and a hint of orange zest. A delightful balance of sweet and tart.'
    },
    {
      'name': 'Apple Pie',
      'images': ['assets/pie.jpg', 'assets/apple11.jpg', 'assets/pie10.jpg'],
      'price': '\$850',
      'description': 'Classic and comforting with a flaky crust and a warm, spiced apple filling. A timeless favorite with a hint of cinnamon.'
    },
  ];

  List<Map<String, dynamic>> displayedProducts = [];

  @override
  void initState() {
    super.initState();
    displayedProducts = getProductList();
  }

  List<Map<String, dynamic>> getProductList() {
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
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(20.0),
            child: Center(
              child: Text(
                widget.category,
                style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
            ),
          ),
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
                            Expanded(
                              child: Hero(
                                tag: 'product_${displayedProducts[index]['name']}',
                                child: PageView.builder(
                                  itemCount: displayedProducts[index]['images'].length,
                                  itemBuilder: (context, imageIndex) {
                                    return ClipRRect(
                                      borderRadius: BorderRadius.circular(10.0),
                                      child: Image.asset(
                                        displayedProducts[index]['images'][imageIndex],
                                        fit: BoxFit.cover,
                                      ),
                                    );
                                  },
                                ),
                              ),
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
                            Icons.favorite_border,
                            color: Colors.red,
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
