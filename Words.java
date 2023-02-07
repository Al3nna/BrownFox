//This program was made by Alenna for the CTE course "Software & Programming Development 2" instructed by Mr. Gross
//Email - alenna.castaneda@oneidaihla.org

import java.util.ArrayList;
import java.util.Scanner;

public class Words {
    //creating attributes
    String text;
    String word;
    String testString = "the quick Brown Fox Jumps over the lazy dog";
    String capitalized;

    //separates string into individual words
    public void separate (String content) {
        //creating attributes
        char space = ' ';
        int sIndex = 0;
        int lastSIndex;
        int sLength = 0;

        //looping through conent
        for (int i = 0; i < content.length(); i++) {
            //resetting variables
            word = " ";

            if (content.charAt(i) == space) {
                //setting new lastSentenceIndex
                lastSIndex = sIndex;
                //setting new SentenceIndex
                sIndex = i;

                //if space if first that's detected
                if (lastSIndex == 0) {
                    word = content.substring(lastSIndex, sIndex);
                    capitalized = "yes";
                //if space is not the first detected
                } else {
                    //skips index containing the space
                    word = content.substring(lastSIndex+1, sIndex);
                }

                //if word is not " " or empty
                if (word != " " && word != "") {
                    //adding 1 to sentenceLength
                    sLength++;
                    //running clean function
                    clean(word, capitalized);
                }
            }
            if (i == content.length()-1) {
                word = content.substring(sIndex+1, content.length());
                //if word is not " " or empty
                if (word != " " && word != "") {
                    //adding 1 to sentenceLength
                    sLength++;
                    //running clean function
                    clean(word, capitalized);
                }
            }
        }
        System.out.println("Sentence Word Number: "+sLength);
    }

    //converts content letter case and eliminates puncuation
    public void clean (String word,String capitalized) {
        //converts word to lowercase
        word = word.toLowerCase();
        //if word is the first of sentence
        if (capitalized == "yes" || capitalized == "next") {
            //replacing first letter with capitalized version
            word = word.substring(0,1).toUpperCase()+word.substring(1);
        }
        //running removePunctuation function
        removePuncuation(word);
    }

    //creating puncuation arraylist
    ArrayList<String> puncuation = new ArrayList<>();

    //setting puncuation
    public void setPuncuation() {
        //adding puncuation marks to arraylist
        puncuation.add(".");
        puncuation.add(",");
        puncuation.add("?");
        puncuation.add("!");
        puncuation.add(":");
        puncuation.add(";");
        puncuation.add("-");
        puncuation.add("_");
        puncuation.add("...");
    }

    //removing puncuation
    public void removePuncuation(String word) {
            //setting attributes
        int l = word.length();
        String ellipsis = "...";
        int remove = word.length()-1;
        boolean complete = false;
        boolean newS = false; //Next word starts new sentence
        boolean dots = false; //puncuation is an ellipsis

        //getting word's last character
        char last = word.charAt(word.length()-1);
        //if word is at least 3 characters long 
        if (word.length() >= 3) {
            //getting word's last 3 characters
            String last3 = word.substring(l-3, l);

            //if puncuation has not been removed
            if (complete != true) {
                //if the last3 = "..."
                if (last3.equals(ellipsis)) {
                    //removing last 3 indexes
                    word = word.substring(0, remove-2);
                    complete = true;
                    dots = true;
                }
            }
        }

        //if puncuation has not been removed
        if (complete != true) {
            //looping through puncuation array & skipping last value
            for (int i = 0; i < puncuation.size()-1; i++) {
                //if last is a puncuation mark
                if (last == puncuation.get(i).charAt(0)) {
                    //removing last index
                    word = word.substring(0, remove);
                    complete = true;
                }
            }
        }

        //checking if puncuation ends sentence
        if (last == '?' || last == '!' || last == '.' && dots != true) {
            newS = true;
        }

        //if next word starts a new sentence
        if (complete != false && newS != false) {
            capitalized = "next";
        } else {
            capitalized = "no";
        }

        //running printWord function
        printWord(word);
    }

    //printing word
    public void printWord(String word) {
        System.out.println(word);
    }

    //runs code
    public static void main(String[] args) {
        //creating new scanner that reads from System.in
        Scanner scan = new Scanner(System.in);

        //creating new Words object
        Words test = new Words();
        //setting puncuation for test
        test.setPuncuation();

        System.out.println("Welcome to Word Separation!");
        System.out.println();
        System.out.println("An example of the program has been done using: "+test.testString);

        //separating testString into words
        test.separate(test.testString);
        System.out.println();

        //creating new Words object
        Words custom = new Words();
        //setting puncuation for custom
        custom.setPuncuation();

        System.out.println();
        System.out.println("Your turn!");
        System.out.println();

        //getting text from next user input
        custom.text = scan.nextLine();
        //closing scanner
        scan.close();

        //separating custom text into words
        custom.separate(custom.text);
    }
}