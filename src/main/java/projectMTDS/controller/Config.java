package projectMTDS.controller;

public class Config {
    public static final String FILE_SEPARATOR = System.getProperty("file.separator");
    public static final String GENERAL_PATH = System.getProperty("user.dir").replace("\\", FILE_SEPARATOR);
    public static final String IMAGE_FOLDER_DIRECTORY = GENERAL_PATH + FILE_SEPARATOR + "images" + FILE_SEPARATOR;
}
