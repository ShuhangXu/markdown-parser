//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

//import javax.lang.model.util.ElementScanner14;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        //System.out.println(currentIndex);
        //System.out.println(markdown.length());
        while(currentIndex < markdown.length()) {

            int openBracket = markdown.indexOf("[", currentIndex);
            //System.out.println(openBracket);

            int closeBracket = markdown.indexOf("]", openBracket);
            //System.out.println(closeBracket);

            int openParen = markdown.indexOf("(", closeBracket);
            //System.out.println(openParen);

            int closeParen = markdown.indexOf(")", openParen);
            //System.out.println(closeParen);
            
            if(openBracket == -1 || closeBracket == -1 || openParen == -1 || closeParen == -1){
                // In this iteration, we don't have enough []{}
                currentIndex = markdown.length();
                break;
            }
            else if(closeBracket + 1 == openParen && openBracket != -1){
                /*if(markdown.indexOf("\n", openBracket-1) != openBracket-1){
                    currentIndex = closeParen + 1;
                    System.out.println("1");
                }*/
                if(markdown.indexOf(" ", openParen) < closeParen && markdown.indexOf(" ", openParen)!= -1){
                    // There is space between two parens
                    currentIndex = closeBracket + 1;
                    //System.out.println("6");
                }
                else if(markdown.indexOf("\n", closeParen) == -1){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                    //System.out.println("5");
                    break;
                }
                else if(markdown.indexOf("\n", openBracket) < closeParen){
                    // There is \n between openBracket and closeParen
                    // In other words, they are not on the same line
                    //System.out.println("n" + markdown.indexOf("\n", openBracket));
                    currentIndex = closeParen + 1;
                    //System.out.println("2");
                }
                else if(markdown.charAt(openBracket - 1) == '!'){
                    currentIndex = closeParen + 1;
                    //System.out.println("3");
                }
                else{
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                    currentIndex = closeParen + 1;
                    //System.out.println("4");
                }
                
            }
            else{
                break;
            }
            /* toReturn.add(markdown.substring(openParen + 1, closeParen));
            currentIndex = closeParen + 1; */

            //System.out.println(currentIndex);
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
