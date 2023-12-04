package a1_2001040056;
    public class Match implements Comparable<Match>{
        private Doc doc;
        private Word word;
        private int frequency;
        private int firstIndex;

        public Match(Doc doc, Word word, int freq, int firstIndex) {
            this.doc = doc;
            this.word = word;
            this.firstIndex = firstIndex;
            this.frequency = freq;

        }

        public Word getWord() {
            return this.word;
        }

        public int getFreq() {
            return this.frequency;
        }

        public int getFirstIndex() {
            return firstIndex;
        }

        @Override
        public int compareTo(Match other) {
            return this.firstIndex - other.firstIndex ;

        }
    }


