# Vapor Store
This is a simple tui demonstrating knowledge of SQL and basic programming skills with Java. Tested in Windows and on Linux (Arch Linux).

## Dependencies
Java, SQLite, and sqlite-jdbc must be installed on system. Tested with SQLite version 3.51.3, sqlite-jdbc 3.53.1.0, and OpenJDK version 26. Should work with most versions of each program.

### Installing Dependencies
#### SQLite
SQLite can be installed from their website (https://sqlite.org/download.html). Also widely available on most package managers such as scoop (https://scoop.sh/#/apps?q=sqlite&p=1), winget (https://winget.run/search?query=sqlite), chocolatety (https://community.chocolatey.org/packages/SQLite), homebrew (https://formulae.brew.sh/formula/sqlite#default), and most Linux package managers (https://repology.org/project/sqlite/versions).

#### sqlite-jdbc
A jar file is already provided in repo but you are free to grab your own.
SQLite-jdbc is available on Github (https://github.com/xerial/sqlite-jdbc/releases/tag/3.53.0.0) and on Maven (https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc). 
## Running
Make sure that sqlite-jdbc-*.jar is in the directory.
Examples for compiling and running the program:
```
javac -cp sqlite-jdbc-3.x.jar *.java
java  -cp .:sqlite-jdbc-3.x.jar Main          # normal launch
java  -cp .:sqlite-jdbc-3.x.jar Main -h        # help
java  -cp .:sqlite-jdbc-3.x.jar Main -l TheGamer123 veryweakpassword
```