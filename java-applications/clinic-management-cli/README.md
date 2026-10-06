# Clinic Management CLI

I built a file-based Java console application for clinic workflows. It provides menus for administration, reception, doctors, finance, and pharmacy.

## Source code

- [ClinicApp.java](src/clinic/ClinicApp.java)

## Structure

```text
src/clinic/   Java source files
pom.xml         Maven build and entry point
README.md       Project guide
```

## Run

Use Java 17 or later. From the repository directory:

```shell
mvn compile exec:java
```

Or use PowerShell without Maven:

```powershell
$sources = Get-ChildItem src -Recurse -Filter *.java
javac -encoding UTF-8 -d out $sources.FullName
java -cp out clinic.ClinicApp
```

## Scope and limitations

Records are kept in local text files. The project uses demonstration logins and plain-text storage. It is an educational application and is not suitable for real patient records or production access control. Authentication initialization was checked; every interactive workflow has not been exhaustively tested.
