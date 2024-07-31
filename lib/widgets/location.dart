import 'package:flutter/material.dart';
import 'package:location/location.dart';

void main() {
  runApp(const MyApp());
  MyLocationService().requestLocationPermission();
}
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    var locationData = MyLocationService().getMyCurrentLocation();
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.blue),
        useMaterial3: true,
      ),
      home: FutureBuilder<LocationData>(
        future: locationData,
        builder: (BuildContext context, AsyncSnapshot<LocationData> snapshot) {
          var latitude = snapshot.data?.latitude;
          var longitude = snapshot.data?.longitude;
          return Scaffold(
            appBar: AppBar(
              backgroundColor: Theme.of(context).colorScheme.inversePrimary,
              title: const Text('Location Data'),
            ),
            body: Center(
              child: Text("$latitude and $longitude"),
            ),
          );
        },
      ),
    );
  }
}
class MyLocationService {
  Location location = Location();

  Future<bool> requestLocationPermission() async {
    final permission = await location.requestPermission();
    return permission == PermissionStatus.granted;
  }

  Future<LocationData> getMyCurrentLocation() async {
    final enabled = await location.serviceEnabled();
    if(!enabled){
      final result = await location.requestService();
      if(result == true){
        debugPrint("Service has been enabled");
      } else {
        throw Exception("GPS service not enabled");
      }
    }
    final locationData = await location.getLocation();
    return locationData;
  }
}