import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * This Program takes an input folder assuming to contain
 * text files and analyzes the words in those text files,
 * printing data on each file to the console.
 * @author hankbreen
 *
 */
public class Driver {
    
    
    // create scanner and arraylist of WordCount objects
    static Scanner scn = new Scanner(System.in);
    static ArrayList<WordCount> wList = new ArrayList<WordCount>();
    static int totalCount;
    
    public static void main(String[] args) {
        
        System.out.print("Please enter directory name: ");
        long start = System.currentTimeMillis();
        String folder = scn.next();
        
        // call organize files method with folder name
        organizeFiles(folder);
        long end = System.currentTimeMillis();
        long timeRun = end - start;
        System.out.println("Time taken: " + timeRun + " ms");
        
       
    }
       
    /**
     * This method organizes the files within the folder,
     * putting them in an array of files then reading through
     * the array, calling the fileReader method for each one.
     * @param s the name of a Folder
     */
    public static void organizeFiles(String s) {
        
        File folder = new File(s);
        File[] files = folder.listFiles();
        Arrays.sort(files);
        
        System.out.println("The files are:");
        
        // first print the names of the files in directory
        
        for (File f : files) {
            System.out.println(f.toString());          
        }
        
        // reads each file in array
        for (File f : files) {
            fileReader(f);
            textGen(f);
            
        }
     }
    
    /**
     * This method reads each text file, creates Wordcount objects,
     * and adds them to the WordCount arraylist.
     * @param f
     */
    public static void fileReader(File f) {
        try {
            //scanner to read files
            Scanner in = new Scanner(f);
            in.useDelimiter("[^a-zA-Z]+");
            
            // create WordCount object with count 1 to start
            //WordCount w = new WordCount(null,0);
            
            // clear arraylist for next file read
            wList.clear();
            totalCount = 0;
            
            
            while (in.hasNext()) {
                
                String temp = in.next();        
                temp = temp.toLowerCase();
                WordCount w = new WordCount(temp,0);
                                     
                // ensure the "bad" words are not taken
                if (!temp.equals("a") && !temp.equals("the")) {
                    if(!temp.equals("an") && !temp.equals("and")) {
                        totalCount++;
                        // if the word is valid, setString to it
                        w.setString(temp);     
                        
                        // if the word is not already in the arrayList,
                        // then add it to the arraylist
                        if (!inArrList(w)) {
                            w.addToCount();
                            wList.add(w);
                                               
                        } else {
                            w.getString(wList, temp).addToCount();                                                 
                        }
                    }
                }        
            }           
            sort(wList);
            
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!");       
        }     
    }
    
    /**
     * This method checks if a wordcount object is already in the 
     * ArrayList of wordcount Objects.
     * @param w a WordCount object
     * @return a boolean value of whether its in it or not
     */
    public static boolean inArrList(WordCount w) {
        
        for (int i = 0; i < wList.size() ; ++i) {
            if (wList.get(i).getWord().equals(w.getWord())) {
                return true;
            }
        }        
        return false;
    }
    
    
    /**
     * Generates the text needed for the output
     * @param f a file (needed for name of file)
     */
    public static void textGen(File f) {
        
        System.out.println("The total number of words" +
                " in " + f + " is: " + totalCount);
        System.out.println("The top ten words in " + f);
        for (int i = 0 ; i < 10 ; ++i) {
            System.out.printf("%s\t\t%8d\n", wList.get(i).getWord(),
                    wList.get(i).getCount());
        }
        
        
        
    }
    
    /**
     * This method sorts the WordCount array based on the count of each
     * word in the array
     * @param wList an ArrayList of WordCount
     * @return the sorted ArrayList
     */
    public static ArrayList<WordCount> sort(ArrayList<WordCount> wList) {
        
        for (int i = 0; i < wList.size(); i++) {
            for (int j = i; j < wList.size(); j++) {
            
                WordCount wc = null;
                if (wList.get(j).getCount() > wList.get(i).getCount()) {
                
                    wc = wList.get(i);
                    wList.set(i, wList.get(j));
                    wList.set(j, wc);
                }
            }
        }
        return wList;
    }
    
}
