# JSON Parser Application

## English

This is a Java application for reading and writing JSON files using the Jackson library. The application demonstrates various JSON operations including:

- **Reading JSON files** from the file system
- **Writing JSON data** to files
- **Modifying JSON structures** by adding, removing, or updating fields
- **Converting JSON to Java objects** and vice versa
- **Working with arrays and objects** in JSON

### Features

- `addArrayObject()` - Adds an array of objects to a JSON structure
- `editMap()` - Modifies a JSON map by adding a city field and removing color fields from lines
- `saveMyLines()` - Serializes and deserializes a list of metro lines
- `readConnectionToObject()` - Reads JSON connections into Java objects
- `writeJson()` / `readJson()` - Basic JSON serialization and deserialization
- `readStation()` - Reads station data from a JSON file
- `readJsonLines()` - Parses metro lines from JSON
- `getConnections()` - Analyzes station connections
- `getLines()` - Processes line data with station counts

### Requirements

- Java 11 or higher
- Maven
- Jackson library (included in pom.xml)

### How to Run

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```

---

## Українська

Це Java-додаток для читання та запису JSON-файлів за допомогою бібліотеки Jackson. Додаток демонструє різні операції з JSON, включаючи:

- **Читання JSON-файлів** з файлової системи
- **Запис JSON-даних** у файли
- **Модифікація JSON-структур** шляхом додавання, видалення або оновлення полів
- **Конвертація JSON в Java-об'єкти** і навпаки
- **Робота з масивами та об'єктами** в JSON

### Функціональність

- `addArrayObject()` - Додає масив об'єктів до JSON-структури
- `editMap()` - Модифікує JSON-карту, додаючи поле міста та видаляючи поля кольорів з ліній
- `saveMyLines()` - Серіалізує та deseріалізує список ліній метро
- `readConnectionToObject()` - Читає JSON-з'єднання в Java-об'єкти
- `writeJson()` / `readJson()` - Базова серіалізація та десеріалізація JSON
- `readStation()` - Читає дані станції з JSON-файлу
- `readJsonLines()` - Парсить лінії метро з JSON
- `getConnections()` - Аналізує з'єднання станцій
- `getLines()` - Обробляє дані ліній з кількістю станцій

### Вимоги

- Java 11 або вище
- Maven
- Бібліотека Jackson (включена в pom.xml)

### Як запустити

```bash
mvn compile exec:java -Dexec.mainClass="org.example.Main"
```
