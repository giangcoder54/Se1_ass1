package a1_2001040056;
import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class Word {
    private String text;
    private String prefix;
    private String suffix;
    private static boolean validWord  ;
    public static Set<String> stopWords = new HashSet<>();
    private static final String ALPHABETIC_PATTERN_AND_DOT = "^[a-zA-Z-'.]+";
    private static final String INVALID_ALPHANUMERIC_PATTERN = "^[^a-zA-Z0-9][a-z]*[^a-zA-Z0-9]*";
    private static final String ALPHABETIC_PATTERN = "^[a-zA-Z-']+";




    public Word() {

    }
    public Word(String prefix, String text, String suffix){
        this.text = text;
        this.prefix = prefix;
        this.suffix = suffix;


    }


    public static boolean loadStopWords(String fileName) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+"); // Split the line into words
                for (String word : words) {
                    stopWords.add(word); // add words inline into stop word
                }
            }

            reader.close();
        } catch (IOException e) {
            return false;
        }
        return true ;
    }

//    public static Word createWord(String rawText) {
//
//        String prefix ="";
//        String suffix ="";
//        String text = rawText;
//
//
//
//
//
//        // check word has number or not. If it has , it is invalid word
//        for(int i = 0;i<text.length();i++){
//            if(Character.isDigit(text.charAt(i))){
//                validWord = false;
//               return  new Word(prefix, text,suffix);
//            }
//        }
//        // check empty word
//        if(text.length() == 0|| text.isEmpty()){
//            validWord = false;
//            return new Word(prefix,text,suffix);
//        }
//        // find prefix , text and suffix
//
//
//        //check text end with 's
//        if(rawText.endsWith("'s")){
//            suffix = "'s";
//            text = text.substring(0,text.length()-2);
//
//
//        }
//        //find prefix
//        int prefixIndex = 0;
//        for(int i = 0; i < rawText.length(); i++){
//            String letter = (rawText.charAt(i) + "");
//            if(!letter.matches(ALPHABETIC_PATTERN)){
//                if(rawText.charAt(i) == '-' && i < rawText.length()-1 && letter.matches(ALPHABETIC_PATTERN)) break;
//                if(rawText.charAt(i) == '\'' && i < rawText.length()-1 && letter.matches(ALPHABETIC_PATTERN)) break;
//                prefixIndex++;
//            }else
//                break;
//        }
//        if(prefixIndex == rawText.length()) validWord = false;
//        prefix = rawText.substring(0, prefixIndex);
//
//        //find text
//        int toTextIndex = prefixIndex;
//        for(int i = toTextIndex; i < rawText.length(); i++){
//            if(Character.isLetter(rawText.charAt(i))){
//                toTextIndex++;
//            }else{
//                if(rawText.charAt(i) == '-') {
//                    toTextIndex++;
//                    continue;
//                }
//                if(rawText.charAt(i) == '\'' && i < rawText.length()-1 && rawText.charAt(i+1) == 't'){
//                    toTextIndex++;
//                    continue;
//                }
//                if(rawText.charAt(i) != '\'' && rawText.charAt(i) != '-' && i < rawText.length()-1 && Character.isLetter(rawText.charAt(i+1))){
//                    validWord = false;
//                }
//                break;
//            }
//        }
//        if(toTextIndex == prefixIndex) validWord = false;
//        text = rawText.substring(prefixIndex, toTextIndex);
//        suffix = rawText.substring(toTextIndex);
//          if (stopWords.contains(text.toLowerCase())) {
//            return new Word(prefix,text,suffix);
//        } else if (!text.matches(ALPHABETIC_PATTERN_AND_DOT)) {
//            text = rawText;
//            prefix = "";
//            suffix = "";
//            return new Word(prefix,text,suffix);
//        }
//        else if (!prefix.matches(INVALID_ALPHANUMERIC_PATTERN)) {
//            if (!prefix.isEmpty()) {
//                text = rawText;
//                prefix = "";
//                suffix = "";
//                return new Word(prefix,text,suffix);
//            }
//        }
//       else if (!suffix.matches(INVALID_ALPHANUMERIC_PATTERN)) {
//            if (!suffix.isEmpty()) {
//                text = rawText;
//                suffix = "";
//                prefix= "";
//                return new Word(prefix,text,suffix);
//            }
//        }
//       validWord = true;
//       return new Word(prefix,text,suffix);
//
//
//
//
//
//    }
public static Word createWord(String inputText) {
    String wordText = inputText;
    String wordPrefix = "";
    String wordSuffix = "";


    // Check if the word contains any numbers, if so, it is considered invalid
    for(int i = 0; i < wordText.length(); i++){
        if(Character.isDigit(wordText.charAt(i))){
            validWord = false;
            return new Word(wordPrefix, wordText, wordSuffix);
        }
    }

    // Check if the word is empty or consists of only whitespace characters
    if(wordText.length() == 0 || wordText.trim().isEmpty()){
        validWord = false;
        return new Word(wordPrefix, wordText, wordSuffix);
    }

    // Check if the word ends with "'s" and remove it if present
    if(inputText.endsWith("'s")){
        wordSuffix = "'s";
        wordText = wordText.substring(0, wordText.length() - 2);
    }

    // Find the prefix of the word
    int prefixIndex = 0;
    for(int j = 0; j < inputText.length(); j++){
        String letter = (inputText.charAt(j) + "");
        if(!letter.matches(ALPHABETIC_PATTERN)){
            if(inputText.charAt(j) == '-'  && letter.matches(ALPHABETIC_PATTERN) && j < inputText.length() - 1) break;
            if(inputText.charAt(j) == '\''  && letter.matches(ALPHABETIC_PATTERN) && j < inputText.length() - 1) break;
            prefixIndex++;
        }else{
            break;
        }
    }
    if(prefixIndex == inputText.length()) validWord = false;
    wordPrefix = inputText.substring(0, prefixIndex);

    // Find the text of the word
    int toTextIndex = prefixIndex;
    for(int i = toTextIndex; i < inputText.length(); i++){
        if(Character.isLetter(inputText.charAt(i))){
            toTextIndex++;
        }else{
            if(inputText.charAt(i) == '-') {
                toTextIndex++;
                continue;
            }
            if(inputText.charAt(i) == '\''  && inputText.charAt(i+1) == 't'&& i < inputText.length() - 1){
                toTextIndex++;
                continue;
            }
            if(inputText.charAt(i) != '\''  && i < inputText.length() - 1 && inputText.charAt(i) != '-' && Character.isLetter(inputText.charAt(i+1))){
                validWord = false;
            }
            break;
        }
    }
    if(toTextIndex == prefixIndex) validWord = false;
    wordText = inputText.substring(prefixIndex, toTextIndex);
    wordSuffix = inputText.substring(toTextIndex);

    // Check if the word is a stop word
    if(stopWords.contains(wordText.toLowerCase())){
        return new Word(wordPrefix, wordText, wordSuffix);
    }
    // Check if the word contains any invalid characters
    else if(!wordText.matches(ALPHABETIC_PATTERN_AND_DOT)){
        wordText = inputText;
        wordPrefix = "";
        wordSuffix = "";
        return new Word(wordPrefix, wordText, wordSuffix);
    }
    // Check if the prefix contains any invalid characters
    else if(!wordPrefix.matches(INVALID_ALPHANUMERIC_PATTERN)){
        if(!wordPrefix.isEmpty()){
            wordText = inputText;
            wordPrefix = "";
            wordSuffix = "";
            return new Word(wordPrefix, wordText, wordSuffix);
        }
    }
    // Check if the suffix contains any invalid characters
    else if(!wordSuffix.matches(INVALID_ALPHANUMERIC_PATTERN)){
        if(!wordSuffix.isEmpty()){
            wordText = inputText;
            wordPrefix = "";
            wordSuffix = "";
            return new Word(wordPrefix, wordText, wordSuffix);
        }
    }

    validWord = true;
    return new Word(wordPrefix, wordText, wordSuffix);
}

    public boolean isKeyword() {

        // check word is stopWords
        for(String stopWord : stopWords){
            if(stopWord.equalsIgnoreCase(text)){
                return false;
            }
        }

        if(!validWord){
            return false;
        }



       return true;
    }

    public String getText() {
        return this.text;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getSuffix() {
        return this.suffix;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Word otherWord = (Word) obj;

        // Compare the text parts without considering case sensitivity
        return text.equalsIgnoreCase(otherWord.text);
    }
    @Override
    public String toString() {
        return prefix + text + suffix;
    }

}
