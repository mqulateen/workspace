package com.novus.appvariables;

/**
 *
 * @author mqul
 */
public class AppVariables {
    
    /**   String Array Index   **/
    public static int filmID = 0;
    public static int filmName = 1;
    public static int imdbRating = 2;
    public static int filmYear = 7;
    public static int directorID = 3;
    public static int directorName = 4;
    public static int actorID = 5;
    public static int actorName = 6;
        
    public static class CSV{    
        /**   CSV Path   **/
        public static String FILE_PATH = "/Users/mqul/NetBeansProjects/NovusMovieProject/Data/TestData.csv";
        public static String EXTENDED_FILE_PATH = "ExtendedTestData.csv";///Users/mqul/NetBeansProjects/NovusMovieProject/Data/ExtendedTestData.csv";
    }
    
    public static class Database{
        /**   DB Index   **/
        public static int filmID = 1;
        public static int filmName = 2;
        public static int imdbRating = 3;
        public static int filmYear = 8;
        public static int directorID = 4;
        public static int directorName = 5;
        public static int actorID = 6;
        public static int actorName = 7;
        
        /**   Stored Procedure   **/
        public static String storedProcedureName = "getAllDetails()";
        
        public static String connectionString = /*"jdbc:mysql://node146675-filmdb.j.layershift.co.uk/db_Movie";*/ "jdbc:mysql://localhost:3306/db_Movie?useSSL=false";
        public static String mysqlDriver = "com.mysql.jdbc.Driver";
        public static String username = /*"mqul";*/ "root";
        public static String password = "";
        
        public static class DatabaseCodes{
            public static final int success = 1;
            public static final int error = 0;
            public static final int lookupLinksExist = -1;
        }
    }
    
    public static class Cache{
        /**   Cache Name   **/ 
        public static String filmCacheName = "Cache_Film";
    }
    
    public static class WebProperties{
        /** External IMDB links **/
        public static String imdbFilmURL = "http://www.imdb.com/title/tt%s";
        public static String imdbProfileURL = "http://www.imdb.com/name/nm%s";
        
        /**   DropDown Default   **/
        public static final String dropDownDefault = "--SELECT--";
        
        /** Film form year range **/
        public static String yearMin = "2000";
        public static String yearMax = "2017";
    }   
}
