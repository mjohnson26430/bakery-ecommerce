import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';


void main() {
  runApp(const MySharedPreferences());
}


class MySharedPreferences extends StatefulWidget {
  const MySharedPreferences({super.key});


  @override
  State<MySharedPreferences> createState() => _MySharedPreferencesState();
}


class _MySharedPreferencesState extends State<MySharedPreferences> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "SharedPreferences",
      theme: ThemeData(primarySwatch: Colors.deepOrange),
      debugShowCheckedModeBanner: false,
      home: const MyHomePage(),
    );
  }
}
class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});


  @override
  State<MyHomePage> createState() => _MyHomePageState();
}


class _MyHomePageState extends State<MyHomePage> {
  int counter = 0;
  String keyName = 'counter';
  @override
  void initState() {
    super.initState();
    loadCounter();
  }
  void loadCounter() async{
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      counter = (prefs.getInt('counter') ?? 0);
    });
  }
  void incrementCounter() async{
    final prefs = await SharedPreferences.getInstance();
    setState(() {
      counter = (prefs.getInt(keyName) ?? 0) + 1;
      prefs.setInt(keyName, counter);
    });
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Shared Preferences"),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'You have pushed the button this many times:',
            ),
            Text(
              '$counter',
              style: const TextStyle(
                  fontSize: 30
              ),
            ),
          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),
    );
  }
}
