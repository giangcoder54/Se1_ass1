package a1_2001040056;
import java.util.ArrayList;
import java.util.List;

public class Doc {

    private List<Word> docTitle;
    private List<Word> docBody;


    public Doc(String content) {
        docTitle = new ArrayList<>();
        docBody = new ArrayList<>();

        String[] line = content.split("\n");

        if(line.length>= 2){
            String[] title = line[0].split(" ");
            String[] body = line[1].split(" ");
            for(String x : title){
                docTitle.add(Word.createWord(x));
            }
            for(String y : body){
                docBody.add(Word.createWord(y));
            }
        }
    }



    public List<Word> getTitle() {

        return this.docTitle;
    }

    public List<Word> getBody() {
        return this.docBody;
    }

public boolean equals(Object o) {
//
    Doc d = (Doc) o;
    if (d.docBody.size() !=docBody.size() ) {
        return false;
    }
    if (docTitle.size() != d.docTitle.size()) {
        return false;
    }
    for (int j = 0; j < this.docBody.size(); j++) {
        if (!this.docBody.get(j).equals(d.docBody.get(j))) {
            return false;
        }
    }


    for (int i = 0; i < this.docTitle.size(); i++) {

        if (!this.docTitle.get(i).equals(d.docTitle.get(i))) {
            return false;
        }
    }


    return true;
}

}
