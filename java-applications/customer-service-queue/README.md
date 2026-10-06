# Customer Service Queue

I built a customer waiting workflow with a circular queue and a stack for served-customer history. Employee and administrator menus expose the corresponding views.

## Source code

- [Customer.java](src/queue/Customer.java)
- [QueueApplication.java](src/queue/QueueApplication.java)
- [SimpleQueue.java](src/queue/SimpleQueue.java)
- [SimpleStack.java](src/queue/SimpleStack.java)
- [User.java](src/queue/User.java)

## Structure

```text
src/queue/   Java source files
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
java -cp out queue.QueueApplication
```

## Scope and limitations

The queue and served history each hold 20 customers. Freed queue slots can be reused. Full structures are handled without removing a waiting customer. Users and credentials are demonstration data held in memory; this is not production authentication. Type exit at the welcome prompt to quit.
