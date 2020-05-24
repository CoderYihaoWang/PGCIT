package util;

import javax.servlet.http.HttpServlet;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class allows you to create {@link Connection}s to databases based on a properties file.
 * <p>
 * This class has methods to allow you to load the properties file either from the src folder, or from the
 * WEB-INF folder. Whichever you choose is a personal preference (though bear in mind that loading from WEB-INF
 * only works for Servlet projects).
 * <p>
 * You may copy and re-use this class as you wish during future labs, tests and projects.
 *
 * @author Andrew Meads
 */
public class DBConnectionUtils {

    /**
     * This method will create a {@link Connection} from a properties file with the given name, located in a resource
     * directory, or the src folder. Note the difference in how you access files stored here, compared with the
     * {@link #getConnectionFromWebInf(HttpServlet, String)} method below.
     *
     * @param propsName the name of the properties file, e.g. "connection.properties"
     * @return an SQL {@link Connection}
     * @throws NullPointerException if the properties file doesn't exist in the src folder
     * @throws IOException          if the properties file can't be read
     * @throws SQLException         if a DB connection cannot be made based on the information contained in the file
     */
    public static Connection getConnectionFromClasspath(String propsName) throws IOException, SQLException {

        // The getResourceAsStream method can be used to load any files on a program's classpath.
        // Any files you put into the src folder, or within a resources folder,  will end up on
        // the classpath at runtime.
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsName)) {

            Properties props = new Properties();
            props.load(in);

            return getConnectionFromProps(props);
        }
    }

    /**
     * This method will create a {@link Connection} from a properties file with the given name, located in the
     * WEB-INF folder. Note the difference in how you access files stored here, compared with the
     * {@link #getConnectionFromClasspath(String)} method above.
     *
     * @param servlet   your Servlet object
     * @param propsName the name of the properties file, e.g. "connection.properties"
     * @return an SQL {@link Connection}
     * @throws IOException  if the properties file doesn't exist in the WEB-INF folder
     * @throws SQLException if a DB connection cannot be made based on the information contained in the file
     */
    public static Connection getConnectionFromWebInf(HttpServlet servlet, String propsName) throws IOException, SQLException {

        // The getRealPath method can be used to get a file's path, relative to a servlet's "home" directory.
        // The example shown here will return the path for a file with the given name, located inside the WEB-INF folder.
        String path = servlet.getServletContext().getRealPath("WEB-INF/" + propsName);

        try (FileReader reader = new FileReader(path)) {

            Properties props = new Properties();
            props.load(reader);

            return getConnectionFromProps(props);
        }
    }

    /**
     * This method will create a {@link Connection} from the given {@link Properties} object. It is used by the two
     * methods above.
     *
     * @param props the {@link Properties} object
     * @return an SQL {@link Connection}
     * @throws SQLException if a DB connection cannot be made based on the information contained in the properties object
     */
    public static Connection getConnectionFromProps(Properties props) throws SQLException {

        // Ensures that the database's Driver class is properly loaded from its JAR file.
        // Usually, this code is not required but it may be necessary on some web servers depending on their configuration.
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = props.getProperty("url");
        return DriverManager.getConnection(url, props);

    }

}
