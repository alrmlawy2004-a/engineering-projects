# Clinic Management CLI

This is a Java console project in my engineering project collection.

## Run

Java 17 or later is required. From this directory:

```shell
mvn compile exec:java
```

Main class: `com.mycompany.clinicapp.ClinicApp`.

Without Maven, use PowerShell:

```powershell
$sources = Get-ChildItem src/main/java -Recurse -Filter *.java
javac -encoding UTF-8 -d out $sources.FullName
java -cp out com.mycompany.clinicapp.ClinicApp
```

See the [collection documentation](../README.md) for the application's scope and limitations. Compilation has been checked with JDK 23; interactive workflows are not exhaustively tested.
