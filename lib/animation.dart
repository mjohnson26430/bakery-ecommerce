import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(title: Text('Dice Roller')),
        body: DiceImage(),
      ),
    );
  }
}

class DiceImage extends StatefulWidget {
  @override
  _DiceImageState createState() => _DiceImageState();
}

class _DiceImageState extends State<DiceImage> {
  int _currentIndex = 0;
  final List<String> _diceImages = [
    'assets/die1.png',
    'assets/die2.jpg',
    'assets/die3.jpeg',
    'assets/die4.jpeg',
    'assets/die5.jpeg',
    'assets/die6.jpeg',
  ];

  void _rollDice() {
    setState(() {
      _currentIndex = (_currentIndex + 1) % _diceImages.length; // Cycles through dice images
    });
  }

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Image.asset(_diceImages[_currentIndex]), // Displays the current die image
          SizedBox(height: 20),
          ElevatedButton(
            onPressed: _rollDice, // Changes the displayed die image
            child: Text('Roll Dice'),
          ),
        ],
      ),
    );
  }
}
