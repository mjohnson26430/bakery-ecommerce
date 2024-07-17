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
      home: PostApi(), // This sets PostApi as the home widget
    );
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
          // Add any other data you want to send in the body
        }),
      );
      if (response.statusCode == 201) {
        final responseData = jsonDecode(response.body);
        setState(() {
          result = 'ID: ${responseData['id']}\nName: ${responseData['name']}\nEmail: ${responseData['email']}';
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
