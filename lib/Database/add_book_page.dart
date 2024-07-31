import 'package:flutter/material.dart';
import 'book_model.dart';
import 'db_helper.dart';

class AddBook extends StatefulWidget {
  final Book? book;

  const AddBook({Key? key, this.book}) : super(key: key);

  @override
  _AddBookState createState() => _AddBookState();
}

class _AddBookState extends State<AddBook> {
  final dbHelper = DbHelper();
  final nameController = TextEditingController();
  final priceController = TextEditingController();

  @override
  void initState() {
    super.initState();
    if (widget.book != null) {
      nameController.text = widget.book!.name;
      priceController.text = widget.book!.price.toString();
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.book == null ? "Add Book" : "Edit Book"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: nameController,
              decoration: InputDecoration(labelText: 'Name'),
            ),
            TextField(
              controller: priceController,
              decoration: InputDecoration(labelText: 'Price'),
              keyboardType: TextInputType.number,
            ),
            ElevatedButton(
              onPressed: () async {
                if (nameController.text.isNotEmpty && priceController.text.isNotEmpty) {
                  final book = Book(
                    id: widget.book?.id,
                    name: nameController.text,
                    price: double.parse(priceController.text),
                  );
                  if (widget.book == null) {
                    await dbHelper.insert(book);
                  } else {
                    await dbHelper.update(book);
                  }
                  Navigator.pop(context, true); // Return true to indicate success
                }
              },
              child: Text('Save'),
            ),
          ],
        ),
      ),
    );
  }
}
