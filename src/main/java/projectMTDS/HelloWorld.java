package projectMTDS;

import static spark.Spark.get;
/**
 * Hello world!
 *
 */
public class HelloWorld {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
