import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;


public class Main {

    //Creating a variable Links class Hashset
    private HashSet<String> links;

    public Main() {
        //Creating an object HashSet to store the links
        links = new HashSet<String>();



        /*Here wanted to create a separator so the data stored on the text file looks better but due to time limit
        could do that later*/
        //String newLine = System.getProperty("line.separator");

    }

    public void getPageLinks(String URL) {
        //4. Checking if we already crawled the URL
        if (!links.contains(URL)) {
            try {
                // if not then we will add it to the links Set
                if (links.add(URL)) {
                    System.out.println(URL);

                }
                //Here i created A Text File for all the links found on the website and named it Crawl.txt
                PrintWriter outputfile = new PrintWriter("Crawl.txt");
                //Writing on the file all links stored using Outputfile.print
                outputfile.print(links);
                //Closing the file
                outputfile.close();



                //Here is used JSoup connect to fetch the website
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs

                //Here i parsed the HTML to get all the links. Basically in HTML we use a[href] tag to add a link so
                // we got the needed information
                Elements linksOnPage = document.select("a[href]");

                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }

        }
    }

    public static void main(String[] args) throws IOException {
        //Passing the URL as an argument
        new Main().getPageLinks("https://www.rws-solutions.com/");

    }
}
