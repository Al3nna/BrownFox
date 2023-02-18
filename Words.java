//This program was made by Alenna for the CTE course "Software & Programming Development 2" instructed by Mr. Gross
//Email - alenna.castaneda@oneidaihla.org

//importing required classes
import java.util.ArrayList;
import java.util.Scanner;

public class Words {
    //creating attributes
    String text;
    String word;
    String bit = null;
    String spacer;
    String testString = "the quick Brown Fox Jumps over the lazy dog";
    boolean capitalized;
    boolean nextCapitalized;
    static boolean bits = false;
    static ArrayList<String> arraylistOriginal = new ArrayList<>();
    static ArrayList<String> arraylistNew = new ArrayList<>();
    int sLength = 0;

    //separates string into chunks (separated by " ")
    public void separate (String content/*,ArrayList<String> arraylistNameOriginal,ArrayList<String> arraylistNameNew*/) {
        //creating attributes
        char space = ' ';
        int sIndex = 0;
        int lastSIndex;
        //resetting attribute
        capitalized = false;

        //looping through content
        for (int i = 0; i < content.length(); i++) {
            //resetting variable
            word = " ";
            
            //if current char = " "
            if (content.charAt(i) == space) {
                //setting new lastSentenceIndex
                lastSIndex = sIndex;
                //setting new SentenceIndex
                sIndex = i;

                //if space is first that's detected
                if (lastSIndex == 0) {
                    //recognizing word position
                    word = content.substring(lastSIndex, sIndex);
                    capitalized = true;
                //if space is not the first detected
                } else {
                    //skips previous index containing the space
                    word = content.substring(lastSIndex+1, sIndex);
                }

                //if word is not " " or empty
                if (word != " " && word != "") {
                    //running divideChunk function
                    divideChunk(word);
                }
            }
            //if the content has been looped through
            if (i == content.length()-1) {
                //if there were no spaces (current word is the first)
                if (sIndex == 0) {
                    //recognizing word position
                    word = content.substring(sIndex);
                    capitalized = true;
                //if there was >= 1 space
                } else {
                    //recognizing word position
                    word = content.substring(sIndex+1);
                }

                //if word is not " " or empty
                if (word != " " && word != "") {
                    //running divideChunk function
                    divideChunk(word);
                }
            }
        }
        System.out.println("Sentence Word Number: "+sLength);
        //running clean function
        clean(arraylistNew);
        clean(arraylistOriginal);
    }

    //converts content letter case
    public void clean (String word,boolean capitalized2) {
        //converts word to lowercase
        word = word.toLowerCase();
        //if word is the first of a sentence
        if (capitalized != false) {
            //capitalizing 1st letter and adding it to substring of remaining letters
            word = word.substring(0,1).toUpperCase()+word.substring(1);
            //resetting capitalization
            capitalized = false;
        }
        
        //adding word to arraylistNew
        arraylistNew.add(word);
        print(word);
    }

    //removing last value of arraylist if empty
    public void clean (ArrayList<String> arraylistName) {
        //if last value of arraylist = " "
        if (arraylistName.get(arraylistName.size()-1) == " ") {
            //removing last element of arraylist
            arraylistName.remove(arraylistName.size()-1);
        }
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
        puncuation.add("(");
        puncuation.add(")");
        puncuation.add("{");
        puncuation.add("}");
        puncuation.add("[");
        puncuation.add("]");
        puncuation.add("/");
        puncuation.add("|");
        puncuation.add("~");
        puncuation.add("...");
    }

    //printing word
    public void print(String word) {
        System.out.println(word);
    }

    //printing arraylist
    public void print(ArrayList<String> arraylistName) {
        //looping through arraylist
        for (int i = 0; i < arraylistName.size(); i++) {
            //printing current element
            System.out.print(arraylistName.get(i));
        }
        System.out.println();
    }
    
    //printing arraylist positions
    public void printPositions(ArrayList<String> arraylistName) {
        //looping through arraylist
        for (int i = 0; i < arraylistName.size(); i++) {
            //printing position & corresponding value
            System.out.println("Position "+i+": "+arraylistName.get(i));
        }
        System.out.println();
    }
    
    //separates words and punctuation
    public void divideChunk(String word) {
        //setting attributes
        int wIndex = 0;
        int lastWIndex;
        boolean newS; //Next word starts new sentence
        boolean dots; //spacer = "..."
        String beginningSpacer = "false";
        boolean spacerFirst = false;
        bits = false;

        //looping through word
        for (int i = 0; i < word.length(); i++) {
            //looping through punctuation arraylist (skipping "...")
            for (int k = 0; k < puncuation.size()-1; k++) {
                //resetting variable
                beginningSpacer = "false";
                //if current char = current puncutuation mark
                if (word.charAt(i) == puncuation.get(k).charAt(0)) {
                    //resetting variables
                    newS = false;
                    dots = false;
                    spacer = " ";

                    //updating values
                    lastWIndex = wIndex;
                    wIndex = i;

                    //if spacer is the first char
                    if (wIndex == 0) {
                        //if word can fit an ellipsis
                        if (wIndex <= word.length()-3) {
                            //if current punctuation is "." and is followed by ".."
                            if (word.charAt(wIndex) == '.' && word.charAt(wIndex+1) == '.' && word.charAt(wIndex+2) == '.' ) {
                                spacer = "...";
                                dots = true;
                            }
                        }
                        //if spacer is not an ellipsis
                        if (spacer != "...") {
                            //setting spacer
                            spacer = word.charAt(wIndex)+"";
                        }
                        beginningSpacer = "true";
                        spacerFirst = true;
                    }

                    //if spacer is the first detected
                    if (lastWIndex == 0) {
                        //if last spacer was a beginning spacer
                        if (beginningSpacer == "last") {
                            //acknowledging bit position
                            bit = word.substring(lastWIndex+1, wIndex);
                        //if last spacer was not a beginning spacer
                        }else if (beginningSpacer == "false") {
                            //if there was a beginning spacer
                            if (spacerFirst != false) {
                                //skipping beginning spacer
                                bit = word.substring(lastWIndex+1, wIndex);
                            } else {
                                //acknowledging bit position
                                bit = word.substring(lastWIndex, wIndex);
                            }
                        //if current spacer is beginning spacer
                        } else if (beginningSpacer == "true"){
                            //acknowledging bit position
                            bit = word.substring(lastWIndex, wIndex);
                        }
                        
                    //if spacer is not the first detected
                    } else {
                        //skipping previous spacer
                        bit = word.substring(lastWIndex+1, wIndex);
                    }
                    //if word can fit an ellipsis
                    if (wIndex <= word.length()-3) {
                        //if current punctuation is "." and is followed by ".."
                        if (word.charAt(wIndex) == '.' && word.charAt(wIndex+1) == '.' && word.charAt(wIndex+2) == '.' ) {
                            spacer = "...";
                            dots = true;
                        }
                    }
                    //if spacer is not an ellipsis
                    if (spacer != "...") {
                        //setting spacer
                        spacer = word.charAt(wIndex)+"";

                        //creating "nickname"
                        char temp = spacer.charAt(0);
                        //if spacer is the last char
                        if (wIndex == word.length()-1) {
                            //checking if the puncuation ends the sentence
                            if (temp == '!' || temp == '?' || temp == '.') {
                                newS = true;
                            }
                        }
                    }

                    //if next word starts new sentence
                    if (newS != false) {
                        nextCapitalized = true;
                    //if nextCapitalized isnt true
                    } else if (nextCapitalized != true) {
                        nextCapitalized = false;
                    }

                    //if beginning spacer = true
                    if (beginningSpacer != "false" && beginningSpacer != "last") {
                        //if spacer is not empty
                        if (spacer != null && spacer != "") {
                            //adding spacer to arraylists
                            arraylistOriginal.add(spacer);
                            arraylistNew.add(spacer);
                            bits = true;
                        }
                        //if spacer is the last char
                        if (wIndex == word.length()-1 || dots != false && wIndex+2 == word.length()-1) {
                            spacer = " ";
                            //adding spacer to arraylists
                            arraylistOriginal.add(spacer);
                            arraylistNew.add(spacer);
                        }
                    }

                    //if bit is not empty & beginning spacer = false
                    if (bit != null && bit != "" && beginningSpacer != "true" && beginningSpacer != "last") {
                        //adding bit to arraylist
                        arraylistOriginal.add(bit);
                        bits = true;
                        //adding 1 to sentenceLength
                        sLength++;
                        //running clean function
                        clean(bit, capitalized);
                        //if next bit is to be capitalized
                        if (nextCapitalized != false) {
                            //setting capitalization
                            capitalized = nextCapitalized;
                            //resetting variable
                            nextCapitalized = false;
                        }
                        //if spacer is not empty
                        if (spacer != null && spacer != "") {
                            //adding spacer to arraylists
                            arraylistOriginal.add(spacer);
                            arraylistNew.add(spacer);
                        }
                        //if spacer is the last char
                        if (wIndex == word.length()-1 || dots != false && wIndex+2 == word.length()-1) {
                            spacer = " ";
                            //adding spacer to arraylists
                            arraylistOriginal.add(spacer);
                            arraylistNew.add(spacer);
                        }
                    }
                    //cycling beginningSpacer value
                    if (beginningSpacer == "last") {
                        beginningSpacer = "false";
                    }
                    if (beginningSpacer == "true") {
                        beginningSpacer = "last";
                    }
                }
            }
            //if current char is last char & bits = true
            if (i == word.length()-1 && bits != false) {
                //acknowledging bit position
                bit = word.substring(wIndex+1);
                //if bit is not empty
                if (bit != null && bit != "") {
                    //adding bit to arraylist
                    arraylistOriginal.add(bit);
                    bits = true;
                    //adding 1 to sentenceLength
                    sLength++;
                    //running clean function
                    clean(bit, capitalized);
                    //if next bit is to be capitalized
                    if (nextCapitalized != false) {
                        //setting capitalization
                        capitalized = nextCapitalized;
                        //resetting variable
                        nextCapitalized = false;
                    }
                    spacer = " ";
                    //if spacer is not empty
                    if (spacer != null && spacer != "") {
                        //adding spacer to arraylists
                        arraylistOriginal.add(spacer);
                        arraylistNew.add(spacer);
                    }
                }
            }
        }
        //if word has no spacers
        if (bits != true) {
            //adding word to arraylist
            arraylistOriginal.add(word);
            //adding 1 to sentenceLength
            sLength++;
            //running clean function
            clean(word, capitalized);
            //if next word is to be capitalized
            if (nextCapitalized != false) {
                //setting capitalization
                capitalized = nextCapitalized;
                //resetting variable
                nextCapitalized = false;
            }
            spacer = " ";
            //if spacer is not empty
            if (spacer != null && spacer != "") {
                //adding spacer to arraylists
                arraylistOriginal.add(spacer);
                arraylistNew.add(spacer);
            }
        }
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

        System.out.println("Original");
        //printing arraylist
        test.print(arraylistOriginal);
        System.out.println();
        //printing arraylist positions
        test.printPositions(arraylistOriginal);
        //clearing arraylist
        arraylistOriginal.clear();
        System.out.println();
        System.out.println("Updated");
        //printing arraylist
        test.print(arraylistNew);
        System.out.println();
        //printing arraylist positions
        test.printPositions(arraylistNew);
        //clearing arraylist
        arraylistNew.clear();

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
        System.out.println();
        System.out.println("Original");
        //printing arraylist
        custom.print(arraylistOriginal);
        System.out.println();
        //printing arraylist positions
        custom.printPositions(arraylistOriginal);
        System.out.println();
        System.out.println("Updated");
        //printing arraylist
        custom.print(arraylistNew);
        System.out.println();
        //printing arraylist positions
        custom.printPositions(arraylistNew);
    }
}