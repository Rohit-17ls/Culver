package editor;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

public class GitUtilities {

    private GitUtilities(){}

    // Assumes that the path provided is a valid git repository
    public static HashMap<String, String> getGitConfig(String path){

        HashMap<String, String> gitConfigMap = new HashMap<>();

        Path gitConfigFilePath = Paths.get(path, ".git", "config");
        File file = new File(gitConfigFilePath.toString());

        try{
            Scanner scanner = new Scanner(file);

            while(scanner.hasNext()){
                String line = scanner.nextLine();

                if(line.startsWith("[")){
                    String[] pairs = line.substring(1, line.length() - 2).split(" \"");

                    if(pairs.length > 1){
                        gitConfigMap.put(pairs[0], pairs[1]);
                    }
                    continue;
                }

                line = line.trim();
                String[] pair = line.split(" = ");
                gitConfigMap.put(pair[0], pair[1]);
            }


            scanner.close();

        }catch(FileNotFoundException fnfe){
            return null;
        }

        return gitConfigMap;

    }

}
