import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';
import 'book_model.dart';

class DbHelper {
  static Database? _db;

  Future<Database?> get db async {
    if (_db == null) {
      _db = await initializeDb();
    }
    return _db;
  }

  Future<Database> initializeDb() async {
    final databasePath = await getDatabasesPath();
    final path = join(databasePath, "books.db");

    return await openDatabase(
      path,
      version: 1,
      onCreate: (db, version) async {
        await db.execute(
          "CREATE TABLE books ("
              "id INTEGER PRIMARY KEY AUTOINCREMENT, "
              "name TEXT, "
              "price REAL"
              ")",
        );
      },
    );
  }

  Future<int?> insert(Book book) async {
    try {
      Database? db = await this.db;
      int? result = await db?.insert('books', book.toMap());
      print("Book inserted: $result"); // Log insertion
      return result;
    } catch (e) {
      print("Error inserting book: $e");
      return null;
    }
  }

  Future<int?> delete(int id) async {
    try {
      Database? db = await this.db;
      int? result = await db?.delete('books', where: 'id = ?', whereArgs: [id]);
      print("Book deleted: $result"); // Log deletion
      return result;
    } catch (e) {
      print("Error deleting book: $e");
      return null;
    }
  }

  Future<int?> update(Book book) async {
    try {
      Database? db = await this.db;
      int? result = await db?.update('books', book.toMap(), where: 'id = ?', whereArgs: [book.id]);
      print("Book updated: $result"); // Log update
      return result;
    } catch (e) {
      print("Error updating book: $e");
      return null;
    }
  }

  Future<List<Book>> getBooks() async {
    try {
      Database? db = await this.db;
      final List<Map<String, dynamic>> result = await db!.query('books');
      print("Books fetched: ${result.length}"); // Log fetch
      return result.map((e) => Book.fromMap(e)).toList();
    } catch (e) {
      print("Error fetching books: $e");
      return [];
    }
  }
}
