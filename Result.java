package a1_2001040056;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Result implements Comparable<Result> {
    private Doc doc;
    private List<Match> matches;
    private int matchCount;
    private int totalFrequency;
    private double averageFirstIndex;

    public Result(Doc d, List<Match> matches) {
        this.doc = d;
        this.matches = matches;
        this.matchCount = matches.size();
        for (Match match : matches) {
            totalFrequency += match.getFreq();
            averageFirstIndex += match.getFirstIndex();
        }
        averageFirstIndex /= matches.size();
    }
    public int getTotalFrequency() {
        return totalFrequency;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public double getAverageFirstIndex() {
        return averageFirstIndex;
    }


    public String htmlHighlight() {
        List<Word> titleWords = new ArrayList<>();
        for(Word titleWord : doc.getTitle()){
            titleWords.add(titleWord);
        }
        List<Word> bodyWords = new ArrayList<>();

        for(Word bodyWord : doc.getBody()){
            bodyWords.add(bodyWord);
        }
        String result = "<h3>";

        for(Word titleWord : titleWords){
            boolean isMatch = false;
            for (Iterator<Match> matchIterator = matches.iterator(); matchIterator.hasNext(); ) {
                // Get the next Match object from the iterator
                Match currentMatch = matchIterator.next();

                // Extract the Word associated with the current Match
                Word associatedWord = currentMatch.getWord();
                 if(titleWord.equals(associatedWord)){
                     result += titleWord.getPrefix() + "<u>" + titleWord.getText() + "</u>" + titleWord.getSuffix()+" ";

                    isMatch = true;
                 }

            }
            if (!isMatch) {
                result += titleWord + " ";
            }

        }

        String paragraphStartTag = "</h3><p>";
        result = result.trim();
        result = result + paragraphStartTag;

        for (Iterator<Word> wordIterator = bodyWords.iterator(); wordIterator.hasNext(); ) {
            Word currentWord = wordIterator.next();
            boolean isMatch = false;
            int matchIndex = 0;

            if (matchIndex < matches.size()) {
                do {
                    Match currentMatch = matches.get(matchIndex);
                    Word highlightedWord = currentMatch.getWord();

                    if (currentWord.equals(highlightedWord)) {
                        result += currentWord.getPrefix() + "<b>" + currentWord.getText() + "</b>" + currentWord.getSuffix() + " ";
                        isMatch = true;
                    }

                    matchIndex++;
                } while (matchIndex < matches.size());
            }

            if (!isMatch) {
                String space = " ";
                result += currentWord + space;
            }
        }

        result = result.trim();
        result += "</p>";
        return String.format(result);

    }

    @Override
    public int compareTo(Result otherResult) {
        if (this.matchCount <= otherResult.matchCount) {
            if (this.matchCount == otherResult.matchCount) {
                if (this.totalFrequency <= otherResult.totalFrequency) {
                    if (this.totalFrequency == otherResult.totalFrequency) {
                        if (this.totalFrequency != otherResult.totalFrequency) {
                            return 0;
                        }
                        if (this.averageFirstIndex > otherResult.averageFirstIndex) {
                            return -1;
                        } else if (this.averageFirstIndex < otherResult.averageFirstIndex) {
                            return 1;
                        }
                    } else {
                        return 1;
                    }
                } else {
                    return -1;
                }
            } else if (this.matchCount < otherResult.matchCount) {
                return 1;
            }
        } else {
            return -1;
        }
        return 0;
    }



    public Doc getDoc() {
        return this.doc;
    }
}

