public class GetSQLStatements {
    public static String[] getInitialSQL() {
        return new String[] {
            // Create Tables
            "CREATE TABLE IF NOT EXISTS Accounts (" +
            "    UserID INTEGER PRIMARY KEY NOT NULL," +
            "    Email TEXT NOT NULL," +
            "    Gamer_Tag TEXT NOT NULL," +
            "    DOB DATE CHECK (DOB < '2012-05-06')," + 
            // Hardcoded value to prevent underage users.
            // Ideally, a script would update this. Potentially with a cron timer.
            "    UserPassword TEXT NOT NULL" +
            ");",

            "CREATE TABLE IF NOT EXISTS Genre (" +
            "    GenreID INTEGER PRIMARY KEY NOT NULL," +
            "    Genre_Name TEXT NOT NULL" +
            ");",

            "CREATE TABLE IF NOT EXISTS ESRB (" +
            "    ESRB_ID INTEGER PRIMARY KEY NOT NULL," +
            "    ESRB_Rating TEXT NOT NULL CHECK (ESRB_Rating IN ('E','E10+','T','M'))," +
            "    ESRB_Description TEXT NOT NULL" +
            ");",

            "CREATE TABLE IF NOT EXISTS Warnings (" +
            "    WarningID INTEGER PRIMARY KEY NOT NULL," +
            "    Warning_Description TEXT NOT NULL UNIQUE," +
            "    ESRB_ID INTEGER REFERENCES ESRB(ESRB_ID)" +
            ");",

            "CREATE TABLE IF NOT EXISTS Games (" +
            "    GameID INTEGER PRIMARY KEY NOT NULL," +
            "    Game_Name TEXT NOT NULL," +
            "    GenreID INTEGER NOT NULL REFERENCES Genre(GenreID)," +
            "    Game_Studio TEXT NOT NULL," +
            "    Date_Published DATE NOT NULL," +
            "    Publisher TEXT NOT NULL," +
            "    WarningID INTEGER REFERENCES Warnings(WarningID)," +
            "    ESRB_ID INTEGER NOT NULL REFERENCES ESRB(ESRB_ID)," +
            "    PRICE REAL," +
            "    Markdown REAL," +
            "    Discount REAL" +
            ");",

            // Indices
            "CREATE INDEX IF NOT EXISTS ix_Accounts ON Accounts(Gamer_Tag);",
            "CREATE INDEX IF NOT EXISTS ix_Games ON Games(Game_Name, Date_Published, ESRB_ID);",
            "CREATE INDEX IF NOT EXISTS ix_ESRB ON ESRB(ESRB_Rating);",
            "CREATE INDEX IF NOT EXISTS ix_Warnings ON Warnings(Warning_Description);",

            // Inserts: Genre
            "INSERT INTO Genre VALUES(1,'Action');",
            "INSERT INTO Genre VALUES(2,'Adventure');",
            "INSERT INTO Genre VALUES(3,'Fighting');",
            "INSERT INTO Genre VALUES(4,'Platformer');",
            "INSERT INTO Genre VALUES(5,'Puzzle');",
            "INSERT INTO Genre VALUES(6,'Racing');",
            "INSERT INTO Genre VALUES(7,'Role-Playing');",
            "INSERT INTO Genre VALUES(8,'Shooter');",
            "INSERT INTO Genre VALUES(9,'Simulation');",
            "INSERT INTO Genre VALUES(10,'Sports');",
            "INSERT INTO Genre VALUES(11,'Strategy');",
            "INSERT INTO Genre VALUES(12,'Survival');",
            "INSERT INTO Genre VALUES(13,'Horror');",
            "INSERT INTO Genre VALUES(14,'Stealth');",
            "INSERT INTO Genre VALUES(15, 'Life Simulation');",

            // Inserts: ESRB
            "INSERT INTO ESRB VALUES(1,'E','E for Everyone.');",
            "INSERT INTO ESRB VALUES(2,'E10+','Everyone 10 and older.');",
            "INSERT INTO ESRB VALUES(3,'T','Recommended for teens and older');",
            "INSERT INTO ESRB VALUES(4,'M','M for Mature. Content is generally suitable for ages 17 and up.');",

            // Inserts: Warnings
            "INSERT INTO Warnings VALUES(1,'Contains strong language',4);",
            "INSERT INTO Warnings VALUES(2,'Contains a lot of violence',4);",
            "INSERT INTO Warnings VALUES(3,'Contains sexual content',4);",
            "INSERT INTO Warnings VALUES(4,'Depicts blood and gore',4);",
            "INSERT INTO Warnings VALUES(5,'Promotes gambling or contains microtransactions bought with real world currency with gambling-like system',4);",
            "INSERT INTO Warnings VALUES(6,'Depicts illegal substances',4);",
            "INSERT INTO Warnings VALUES(7,'Depicts alcohol and/or tobacco use',3);",
            "INSERT INTO Warnings VALUES(8,'Contains suggestive themes',3);",
            "INSERT INTO Warnings VALUES(9,'Contains mild language',3);",
            "INSERT INTO Warnings VALUES(10,'Contains mild violence',3);",
            "INSERT INTO Warnings VALUES(11,'Contains mild suggestive themes',3);",
            "INSERT INTO Warnings VALUES(12, 'Contains crude humor',3);",
            "INSERT INTO Warnings VALUES(13,'Contains themes related to gambling',3);",
            "INSERT INTO Warnings VALUES(14,'Contains minor depiction of blood',3);",
            "INSERT INTO Warnings VALUES(15,'Contains mild cartoon violence',2);",
            "INSERT INTO Warnings VALUES(16,'Contains mild fantasy violence',2);",
            "INSERT INTO Warnings VALUES(17,'Contains spicy language',2);",
            "INSERT INTO Warnings VALUES(18,'Contains minimal suggestive themes',2);",
            "INSERT INTO Warnings VALUES(19,'Contains unrealistic blood',2);",
            "INSERT INTO Warnings VALUES(20,'Contains very little cartoon violence',1);",
            "INSERT INTO Warnings VALUES(21,'Contains very little and/or infrequent harsh language',1);",
            "INSERT INTO Warnings VALUES(22,'None',1);",

            // Inserts: Accounts
            "INSERT INTO Accounts VALUES(1,'thegamer123@example.com','TheGamer123','2000-05-06','veryweakpassword');",
            "INSERT INTO Accounts VALUES(2,'fromthelandofflowers@random.org','FloridaMan','2005-06-17','aslightlyst0ng3rpassword');",
            "INSERT INTO Accounts VALUES(3,'racoonbandit@example.com','SlyCooperFan','1995-03-17','r@ndomwords');",
            "INSERT INTO Accounts VALUES(4,'waitingforfriday@emailprovider.net','WeekdaysSuck','1971-07-28','cardboard_shirt');",
            "INSERT INTO Accounts VALUES(5,'ergosum@example.com','IGameThereforeIAm','2011-11-29','garden_flash');",
            "INSERT INTO Accounts VALUES(6,'dualshock@smptserver.us','ControllerBreaker','2002-05-14','ps5_user');",
            "INSERT INTO Accounts VALUES(7,'ursaminor@imaphost.xyz','Bear','2010-09-28', 'UPPERCASELETTERS');",
            "INSERT INTO Accounts VALUES(8,'pinkelephant@random.org','FuchsiaMammal','2006-04-15','r4ndomcharacters');",
            "INSERT INTO Accounts VALUES(9,'jumpmansson@example.com','ItsMeLuigi','2001-06-23','wnfenwfwni');",
            "INSERT INTO Accounts VALUES(10,'angrygreek@electronicmail.io','Kratos','1990-08-12','godofwarfan');",
            "INSERT INTO Accounts VALUES(11,'orangejustice@random.org','FortnitePlayer321','2011-01-01','vqenfgwq');",
            "INSERT INTO Accounts VALUES(12,'cozycat@emailprovider.net','CatLover','2003-02-22','ilikecats123');",
            "INSERT INTO Accounts VALUES(13,'flowmytears@electronicmail.io','ThePoliceManSaid','2004-03-31','PhilipKDickFan');",
            "INSERT INTO Accounts VALUES(14,'sadlasagna@imaphost.xyz','BlueGarfield','2008-05-07','afhmwnguew');",
            "INSERT INTO Accounts VALUES(15,'janesbrother@smpt.us','JohnDoe','2005-01-02','johnspassword');",
            "INSERT INTO Accounts VALUES(16,'johnssister@emailprovider.net','JaneDoe','2005-01-01','janespassword');",

            // Inserts: Games
            // As you can tell, these are parodies of popular real life games
            "INSERT INTO Games VALUES(1,'Dragon Souls',1,'Away Software','2011-09-22','Away Software',4,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(2,'Marlo Cart',6,'Nentinto','2017-04-28','Nentinto',22,1,40.00,0.00,0.00);",
            "INSERT INTO Games VALUES(3,'Beacon',2,'Very Massive Games','2011-07-20','Watcher Bros Entertainment',15,2,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(4,'DigBuild',1,'Gadget Studio','2011-11-18','Picocode Corporation',15,2,20.00,0.00,0.00);",
            "INSERT INTO Games VALUES(5,'Cosmic Battle: Doomed Dynasty',1,'Revive','2019-11-15','Digital Creations',10,3,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(6,'Cosmic Battle: The Endurer',1,'Revive','2023-04-28','Digital Creations',10,3,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(7,'Revered Competitors',8,'WebSoft Games','2024-12-06','WebSoft Games',10,3,10.00,0.00,0.00);",
            "INSERT INTO Games VALUES(8,'Twists',4,'Techla','2024-05-14','Techla',15,2,30.00,0.00,0.00);",
            "INSERT INTO Games VALUES(9,'Silver Ends Salvation',7,'ShinyCrystal Games','2010-05-18','ShinyCrystal Games',13,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(10,'Silver Ends Salvation 2',7,'ShinyCrystal Games','2019-12-05','ShinyCrystal Games',13,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(11,'Kitten Space Agency',9,'Battalion Games','2015-04-27','GrabThree Interactive',20,1,25.00,0.00,0.00);",
            "INSERT INTO Games VALUES(12,'NBA XXV',10,'Imaginary Ideas','2024-10-03','GrabThree Interactive',9,3,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(13,'Cursed',8,'Unique Software','2016-05-13','Bezatha',4,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(14,'Astralspring Grove',9,'QuestioningSimian','2016-02-26','QuestioningSimian',16,2,30.00,0.00,0.00);",
            "INSERT INTO Games VALUES(15,'The Darkness Inside',13,'Mambo Gamecraft','2014-10-13','Bezatha',4,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(16,'Slumbering Hounds',7,'Allied Charge Games','2014-10-08','Block Unix',2,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(17,'Outlive',13,'Green Basket','2013-09-04','Green Basket',2,4,40.00,0.00,0.00);",
            "INSERT INTO Games VALUES(18,'The Myth of Arya: Sorrows of the Empire',7,'Nentinto','2023-05-12','Nentinto',15,2,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(19,'Faerie Crown',7,'Away Software','2022-02-22','Away Software',4,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(20,'Grand Tour',6,'Torse Computer Entertainment','2022-03-04','Torse Interactive Entertainment',22,1,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(21,'The Bystander',5,'Techla','2016-01-26','Techla',22,1,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(22,'Dwelling Vices: Township',13,'LidInc','2021-05-07','LidInc',2,4,60.00,0.00,0.00);",
            "INSERT INTO Games VALUES(23,'Apparition of Hokkaido',1,'Counter Blow Games','2020-07-17','Torse Interactive Entertainment',2,4,60.00,0.00,0.00);"
        };
    }
}