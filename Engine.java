package a1_2001040056;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Engine {
   private Doc[] docs;

    public Engine(){

    }

    public int loadDocs(String dirname) {
        File directoryPath = null;

        directoryPath = new File(dirname);
        String[] files = directoryPath.list();
        docs = new Doc[files.length];

        for (int i = 0; i < files.length; i++) {
            String content = "";
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(dirname+"\\"+files[i]));
                String line = reader.readLine();
                while (line != null) {
                    content += line + "\n";
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            docs[i] = new Doc(content);
        }
        return files.length;
    }

    public Doc[] getDocs() {
        return this.docs;
    }

    public String htmlResult(List<Result> resList) {
        String resultHtml = "";
        for (Result result : resList) {
            resultHtml += result.htmlHighlight();
        }
        return resultHtml;
    }

    public List<Result> search(Query q) {
        //  Query q = new Query("the <context> of observer: design");
        List<Result> searchResults = new ArrayList<>();

// Iterate through a collection of documents (docs)
        for (Doc document : docs) {
            // Find matches for the query within the current document
            List<Match> matchingResults = q.matchAgainst(document);

            // Create a Result object to store the document and its matching results
            Result documentResult = new Result(document, matchingResults);

            // If there are matching results, add them to the response list
            if (!matchingResults.isEmpty()) {
                searchResults.add(documentResult);
            }
        }

// Sort the response list based on some criteria
        Collections.sort(searchResults);

// Return the sorted list of search results
        return searchResults;
    }


}
