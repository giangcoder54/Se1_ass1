package a1_2001040056;
import java.util.*;

public class Query {

    private List<Word> keyWords;
    public Query(String searchPhrase) {
        keyWords = new ArrayList<>();
        String[] words = searchPhrase.split(" ");
        for(String word : words){
            Word word1 = Word.createWord(word);
            if(word1.isKeyword()){
                keyWords.add(word1);
            }
        }
    }

    public List<Word> getKeywords() {
        return this.keyWords;
    }

    public List<Match> matchAgainst(Doc d) {
        // (Doc d, Word w, int freq, int firstIndex)
        List<Match> matchList = new ArrayList<>();
        List<Word> wordList = new ArrayList<>();

        wordList.addAll(d.getTitle());
        wordList.addAll(d.getBody());
        int firstMatchIndex;
        int frequency;

        for(int i = 0; i < keyWords.size(); i++){
            Word wordToCompare = keyWords.get(i);
            if(wordList.contains(wordToCompare)){
                frequency = Collections.frequency(wordList, wordToCompare);
                    firstMatchIndex = wordList.indexOf(wordToCompare);
                matchList.add(new Match(d, wordToCompare, frequency, firstMatchIndex));
            }
        }

            Collections.sort(matchList);


        return matchList;

    }

}
