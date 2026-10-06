# Account Modeling Exercise

This is a Java console project in my engineering project collection.

## Run

Java 17 or later is required. From this directory:

```shell
mvn compile exec:java
```

Main class: `accounts.AssignmentOfAnalysis1`.

Without Maven, use PowerShell:

```powershell
$sources = Get-ChildItem src -Recurse -Filter *.java
javac -encoding UTF-8 -d out $sources.FullName
java -cp out accounts.AssignmentOfAnalysis1
```

These are standalone learning exercises. Compilation and representative behaviour were checked with JDK 23.

## Source code

- [Account.java](src/accounts/Account.java)
- [AssignmentOfAnalysis1.java](src/accounts/AssignmentOfAnalysis1.java)
- [Person.java](src/accounts/Person.java)
