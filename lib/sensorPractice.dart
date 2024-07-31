import 'dart:async';
import 'package:flutter/material.dart';
import 'package:sensors_plus/sensors_plus.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Sensor Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepOrange),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Accelerometer Sensor Example'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  AccelerometerEvent? _accelerometerEvent;
  late StreamSubscription<AccelerometerEvent> _streamSubscription;

  @override
  void initState() {
    super.initState();
    _streamSubscription = accelerometerEvents.listen((AccelerometerEvent event) {
      setState(() {
        _accelerometerEvent = event;
      });
    });
  }

  @override
  void dispose() {
    _streamSubscription.cancel();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Accelerometer values:',
              style: TextStyle(fontSize: 20),
            ),
            const SizedBox(height: 10),
            if (_accelerometerEvent != null)
              Text(
                'X: ${_accelerometerEvent!.x.toStringAsFixed(2)}, '
                    'Y: ${_accelerometerEvent!.y.toStringAsFixed(2)}, '
                    'Z: ${_accelerometerEvent!.z.toStringAsFixed(2)} ',
                style: const TextStyle(fontSize: 20, color: Colors.red),
              )
            else
              const Text('Accelerometer sensor data not found.'),
          ],
        ),
      ),
    );
  }
}
