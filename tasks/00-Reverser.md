## Тестовое задание
Фактически, здравствуймир с целью убедиться, что все настроили среду и освоились с github'ом.

Консольное приложение, на вход принимает список чисел, разделённых [пробельными символами](https://docs.oracle.com/javase/tutorial/essential/regex/pre_char_classes.html):
```bash
java Reverser 1\ 2 "3 4" 5
```

Приложение должно вывести эти числа в стандартный вывод в обратном порядке через пробел:
```
5 4 3 2 1
```

## Рекомендации:
 1. Смотрите в сторону [stream api](https://docs.oracle.com/javase/8/docs/api/java/util/stream/package-summary.html) в java 8.
 2. Учитывайте варианты экранирования пробелов в интерпретаторе командной строки (слешем или кавычками) и все пробельные символы, включая табуляцию и прочие.
 3. Проверяйте приложение перед сдачей: что оно работает и работает правильно.
 4. Программа должна работать за линейное время:)
