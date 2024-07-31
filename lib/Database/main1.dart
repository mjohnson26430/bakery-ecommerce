import 'package:flutter/material.dart';
import 'booklist_page.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();
  runApp(MaterialApp(
    home: BookList(),
    debugShowCheckedModeBanner: false,
  ));
}
