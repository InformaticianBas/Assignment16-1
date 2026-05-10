import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class Database {
    private static final String SQLITE_FILE = "vaporstore.db";
    private static final String DB_URL = "jdbc:sqlite:" + SQLITE_FILE;

    // Following string is used for holding current user as well as determing how a later function showCatalog() behaves.
    public static String currentUserEmail = null;

    // Establishes a Connection object that happens to be a function that throws an SQL Exceptions
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDB() {
        // Establishes the database file
        File dbFile = new File(SQLITE_FILE);
        // If file exists, it does nothing.
        // If not it, establishes a connection to SQLite database and executes SQLite statement from getInitialSQL()
        if (dbFile.exists()) {
            System.out.println("Found Database.");
        } else {
            System.out.println("Database not found. Program will create a new one.");

            try (Connection connection = connect(); 
            Statement statement = connection.createStatement()) { 
                String[] queries = GetSQLStatements.getInitialSQL();
                for (String sql: queries) {
                    if (sql != null && !sql.trim().isEmpty()) {
                        statement.executeUpdate(sql);
                    }
                }
                System.out.println("Database created!");
            } catch (SQLException e) { // Handles SQL exceptions
                System.err.println("Error initializing database: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) { // Handles other exceptions
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    public static boolean validateLogin(String email, String password) {
        String sql = "SELECT Email FROM Accounts WHERE Email = ? AND UserPassword = ?";
        try (Connection connection = connect(); PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, email);
            pstatement.setString(2, password);
            ResultSet rset = pstatement.executeQuery();
            if (rset.next()) {
                currentUserEmail = rset.getString("Email");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Login error: " + e.getMessage());
        }
        return false;
    }

    public static void createAccount(String email, String gamertag, String password) {
        String sql = "INSERT INTO Accounts(Email, Gamer_Tag, UserPassword) VALUES(?,?,?)";
        try (Connection connection = connect(); PreparedStatement pstatement = connection.prepareStatement(sql)) {
            pstatement.setString(1, email);
            pstatement.setString(2, gamertag);
            pstatement.setString(3, password);
            pstatement.executeUpdate();
            System.out.println("Account created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating account: " + e.getMessage());
        }
    }

    public static void showCatalog() {
        // If logged in, show everything. If not, don't show 'M' rated.
        boolean loggedIn = (currentUserEmail != null);
        // Query that joins three tables
        String sql = "SELECT Game_Name, Genre_Name, ESRB_Rating " +
                 "FROM Genre " +
                 "JOIN Games ON Genre.GenreID = Games.GenreID " +
                 "JOIN ESRB ON Games.ESRB_ID = ESRB.ESRB_ID " +
                 "GROUP BY GameID";

        // Appends an extra filter if not in logged in state
        if (!loggedIn) {
            sql += " HAVING NOT ESRB_Rating = 'M'";
        }

        try (Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet rset = statement.executeQuery(sql)) {

            System.out.println("--- Catalog View ---");
            while (rset.next()) {
                // Simple space-separated output
                System.out.println(rset.getString("Game_Name") + " | " + 
                                   rset.getString("Genre_Name") + " | " + 
                                   rset.getString("ESRB_Rating"));
            }
        } catch (Exception e) {
            System.out.println("Error loading view: " + e.getMessage());
        }
    }
}