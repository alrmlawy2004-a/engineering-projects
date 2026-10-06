# Banking Console Exercise

I practice account initialization, deposits, withdrawals, and balance display in a small Java console exercise.

## Source code

- [Bank.java](src/banking/Bank.java)
- [Bank1.java](src/banking/Bank1.java)

## Structure

```text
src/banking/   Java source files
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
java -cp out banking.Bank
```

## Scope and limitations

The model rejects negative and non-finite monetary operations and preserves the balance on an overdraft. It uses floating-point amounts and has no persistence, transaction ledger, or production banking controls.
