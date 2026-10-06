# Java Console Applications

I explore file-based workflows and data structures through three console applications: a clinic system, a bank exercise, and a customer waiting queue.

## Technologies

Java 17, Maven.

## Files

- `banking-console/pom.xml`
- `clinic-management-cli/pom.xml`
- `customer-service-queue/pom.xml`

## Run

Each application is a separate Maven project. From its directory, run `mvn compile exec:java`. Without Maven, compile its `src/main/java` files with `javac -encoding UTF-8 -d out` and launch the main class defined in `pom.xml`.

## Scope and limitations

The clinic uses text files and demo accounts. The waiting queue uses bounded arrays and in-memory users. These are learning applications, not production authentication or financial systems. Local records and crash logs are not distributed.
