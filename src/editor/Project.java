package editor;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Project {

    private static class InstanceHolder{
        private static final Project INSTANCE = new Project("");
    }

    private String projectPath;

    private Project(String projectPath){
        this.projectPath = projectPath;
    }

    public static Project getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public String getProjectPath(){
        return this.projectPath;
    }

    public void setProjectPath(String path){
        projectPath = path;
    }

    public String getPathRelativeToProject(String path){
        return path.substring(projectPath.length(), path.length());
    }

    public boolean isGitInitialized(){
        File file = new File(projectPath);
        String[] files = file.list();

        for(String folder : files){
            if(".git".equals(folder)) return true;
        }

        return false;
    }
}
