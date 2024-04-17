package editor;

import java.io.File;

public class Project {

    public static String projectPath;

    Project(){}

    public static void setProjectPath(String path){
        projectPath = path;
    }

    public static String getPathRelativeToProject(String path){
        return path.substring(projectPath.length(), path.length());
    }
}
