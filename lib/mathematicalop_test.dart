import 'mathmaticalop.dart';
import 'package:test/test.dart';

void main() {
  group("Test Start, addition and subtraction", () {
    final mathematicalOperations = MathematicalOperations();

    test('Addition of two numbers', () {
      final result = mathematicalOperations.add(4, 5);
      expect(result, 9);
    });

    test('Subtraction of two numbers', () {
      final result = mathematicalOperations.subtract(5, 4);
      expect(result, -1);
     // Corrected expected result
    });
  });
}
