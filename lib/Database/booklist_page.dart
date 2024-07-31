import 'package:flutter/material.dart';
import 'book_model.dart';
import 'db_helper.dart';
import 'add_book_page.dart';

class BookList extends StatefulWidget {
  @override
  _BookListState createState() => _BookListState();
}

class _BookListState extends State<BookList> {
  final dbHelper = DbHelper();
  List<Book> bookList = [];

  @override
  void initState() {
    super.initState();
    refreshBooks();
  }

  void refreshBooks() async {
    bookList = await dbHelper.getBooks();
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Book List'),
      ),
      body: ListView.builder(
        itemCount: bookList.length,
        itemBuilder: (context, index) {
          final book = bookList[index];
          return ListTile(
            title: Text(book.name),
            subtitle: Text('\$${book.price}'),
            trailing: IconButton(
              icon: Icon(Icons.delete),
              onPressed: () async {
                await dbHelper.delete(book.id!);
                refreshBooks(); // Update the list after deletion
              },
            ),
            onTap: () async {
              final result = await Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => AddBook(book: book)),
              );
              if (result == true) {
                refreshBooks(); // Refresh the list if a book is saved/updated
              }
            },
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        onPressed: () async {
          final result = await Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => AddBook()),
          );
          if (result == true) {
            refreshBooks(); // Refresh the list if a new book is added
          }
        },
      ),
    );
  }
}
