# Randomized Treasure Bag

This is a Java console project in my engineering project collection.

## Run

Java 17 or later is required. From this directory:

```shell
mvn compile exec:java
```

Main class: `treasure.Ass1320220837`.

Without Maven, use PowerShell:

```powershell
$sources = Get-ChildItem src -Recurse -Filter *.java
javac -encoding UTF-8 -d out $sources.FullName
java -cp out treasure.Ass1320220837
```

These are standalone learning exercises. Compilation and representative behaviour were checked with JDK 23.

## Source code

- [Ass1320220837.java](src/treasure/Ass1320220837.java)
- [Bag.java](src/treasure/Bag.java)
- [Treasure.java](src/treasure/Treasure.java)
