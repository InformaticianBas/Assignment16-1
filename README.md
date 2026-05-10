# Vapor Store
This is a simple tui demonstrating knowledge of SQL and basic programming skills with Java. Tested in Windows and on Linux (Arch Linux as well as NixOS).

## Dependencies
Java, SQLite, and sqlite-jdbc must be installed on system. Tested with SQLite version 3.51.3, sqlite-jdbc 3.53.1.0, and OpenJDK version 26. Should work with most versions of each program.

### Installing Dependencies
#### SQLite
SQLite can be installed from [their website](https://sqlite.org/download.html). Also widely available on most package managers such as [scoop](https://scoop.sh/#/apps?q=sqlite&p=1), [winget](https://winget.run/search?query=sqlite), [chocolatety](https://community.chocolatey.org/packages/SQLite), [homebrew](https://formulae.brew.sh/formula/sqlite#default), and [most Linux package managers](https://repology.org/project/sqlite/versions).

#### sqlite-jdbc
A jar file is already provided in repo but you are free to grab your own.
SQLite-jdbc is available on Github (https://github.com/xerial/sqlite-jdbc/releases/tag/3.53.0.0) and on Maven (https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc). 
## Running
Clone the repository
```
git clone https://github.com/InformaticianBas/Assignment16-1.git
```

Make sure that sqlite-jdbc-*.jar is in the directory.
Examples for compiling and running the program:
```
javac -cp ".:sqlite-jdbc-[version].jar" *.java      # compile in POSIX/Unix-like environments (uses semicolon instead)
javac -cp ".;sqlite-jdbc-[version].jar" *.java      # compile on Windows 

java  -cp ".:sqlite-jdbc-[version].jar" Main        # normal launch in POSIX/Unix-like environments
java  -cp ".;sqlite-jdbc-[version].jar" Main        # normal launch on Windows (uses semicolon instead)

java  -cp .:sqlite-jdbc-[version].jar Main -h       # help option in POSIX/Unix-like environments
java  -cp .:sqlite-jdbc-[version].jar Main -l       # starts with login prompt in POSIX/Unix-like environments 
```
If using VSCode, you can also run it by adding the sqlite-jdbc as a referenced libraries then simply navigating to VaporStore file and pressing the play/run button on the top right.