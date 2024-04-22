package tokenizers;

import ui.colors.CulverColor;

import java.awt.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Tokenizer {

    // LinkedHashMap for insertion order preserving hashmap
    protected LinkedHashMap<Pattern, Color> patternColorPairs = new LinkedHashMap<>();
    public static final Pattern PATTERN_IDENTIFIER = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
    public static final Pattern PATTERN_NUMERIC_LITERAL = Pattern.compile("(\\+|-)?([\\d]+\\.[\\d]*|[\\d]*\\.[\\d]+|\\d+)");
    public static final Pattern PATTERN_PUNCTUATOR = Pattern.compile("[\\p{Punct}]");
    public static final Pattern PATTERN_SPACES = Pattern.compile("\\s+");
    public static final Pattern PATTERN_SINGLE_LINE_COMMENT = Pattern.compile("//.*");
    public static final Pattern PATTERN_STRING_LITERAL = Pattern.compile("[\"][^\"]*[\"]");

//    float f = 2.33;

    public Tokenizer(){
    }

    public void addPattern(String regex, Color color){
        Pattern pattern = Pattern.compile(regex);
        this.patternColorPairs.put(pattern, color);
    }

    public void addPattern(Pattern pattern, Color color){
        this.patternColorPairs.put(pattern, color);
    }

    public LinkedHashMap<Integer, Color> getStyledText(String text){

//        text = text.replace("\n", "\n\n");
        LinkedHashMap<Integer, Color> codeMap = new LinkedHashMap<>();
        int ind = 0;

        while(ind < text.length()){
            boolean foundMatch = false;
            Color longestMatchingPatternColor = CulverColor.WHITE;
            Pattern longestMatchingPattern = null;
            int longestMatchingPatternLength = 0;

            for(HashMap.Entry<Pattern, Color> entry : this.patternColorPairs.entrySet()){
                Pattern pattern = entry.getKey();
                Matcher matcher = pattern.matcher(text.substring(ind));

                boolean isMatch = matcher.lookingAt();
                foundMatch = foundMatch || isMatch;

                if(isMatch){
                    String matchedText = matcher.group();
                    int matchedTextLength = matchedText.length();
                    if(matchedTextLength > longestMatchingPatternLength){
                        longestMatchingPatternLength = matchedTextLength;
                        longestMatchingPatternColor = entry.getValue();
                        longestMatchingPattern = pattern;
                    }
                }
            }

            if(foundMatch){
                System.out.println("MATCH " + longestMatchingPattern.pattern()
                                    + "\n****$" + text.substring(ind, ind + longestMatchingPatternLength) + "$**** ");

                int matchEndInd = ind + longestMatchingPatternLength;
                codeMap.put(matchEndInd, longestMatchingPatternColor);
                ind = matchEndInd;
            }else {
                codeMap.put(++ind, Color.WHITE);
            }

        }

        return codeMap;


    }

}
