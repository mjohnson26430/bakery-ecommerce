import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: ApiDemo(),
    );
  }
}

class ApiDemo extends StatefulWidget {
  const ApiDemo({super.key});

  @override
  State<ApiDemo> createState() => _ApiDemoState();
}

class _ApiDemoState extends State<ApiDemo> {
  List<dynamic> users = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: const Text(
          "API Demo",
          style: TextStyle(backgroundColor: Colors.blue, color: Colors.white),
        ),
      ),
      body: ListView.builder(
        itemCount: users.length,
        itemBuilder: (context, index) {
          final user = users[index];
          final email = user['email'];
          final name = user['name']['first'];
          final image = user['picture']['thumbnail'];
          return ListTile(
            leading: ClipRRect(
              borderRadius: BorderRadius.circular(100),
              child: Image.network(image),
            ),
            title: Text(name),
            subtitle: Text(email),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: fetchApi,
        shape: const CircleBorder(),
        backgroundColor: Colors.blue,
        child: const Icon(Icons.add, color: Colors.white),
      ),
    );
  }

  void fetchApi() async {
    print("Fetch Users Called");
    const url = "https://randomuser.me/api/?results=15";
    final uri = Uri.parse(url);
    final response = await http.get(uri);
    final body = response.body;
    final json = jsonDecode(body);
    setState(() {
      users = json['results'];
    });
    print("Fetch API is completed");
  }
}

class PostApi extends StatefulWidget {
  const PostApi({super.key});

  @override
  State<PostApi> createState() => _PostApiState();
}

class _PostApiState extends State<PostApi> {
  final String apiUrl = 'https://jsonplaceholder.typicode.com/posts';
  final TextEditingController nameController = TextEditingController();
  final TextEditingController emailController = TextEditingController();
  String result = '';

  @override
  void dispose() {
    nameController.dispose();
    emailController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Post API Call"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(10),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            TextField(
              controller: nameController,
              decoration: const InputDecoration(labelText: 'Name'),
            ),
            TextField(
              controller: emailController,
              decoration: const InputDecoration(labelText: 'Email'),
            ),
            ElevatedButton(
              onPressed: postData,
              child: const Text('Submit'),
            ),
            const SizedBox(height: 20.0),
            Text(
              result,
              style: const TextStyle(fontSize: 16.0),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> postData() async {
    try {
      final response = await http.post(
        Uri.parse(apiUrl),
        headers: <String, String>{
          'Content-Type': 'application/json; charset=UTF-8',
        },
        body: jsonEncode(<String, dynamic>{
          'name': nameController.text,
          'email': emailController.text,
        }),
      );
      if (response.statusCode == 201) {
        final responseData = jsonDecode(response.body);
        setState(() {
          result =
          'ID: ${responseData['id']}\nName: ${responseData['name']}\nEmail: ${responseData['email']}';
        });
      } else {
        throw Exception('Failed to post data');
      }
    } catch (e) {
      setState(() {
        result = 'Error: $e';
      });
    }
  }
}
