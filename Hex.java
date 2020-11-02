/******************************************************************************
 *  Michelle Lu PD 5
 *  Cybersecurity
 *
 *  Compilation:  javac Hex.java
 *  Execution:    java Hex human key message
 *  Dependencies: None
 *
 *  Takes 3 inputs (human/numOut; key;message)
 *  Key and Message are extended ASCII textfiles
 *  Returns the xor'ed value of the text and key string either in characters (human) or hex values (numOut)
 *
 *
 *  % java human key1 message1
 *    )$--.
 *  % java numOut key1 message1
 *    29 24 2d 2d 2e
 *
 ******************************************************************************/
import java.nio.file.*;
public class Hex {

    public static int intValues (String text, int index){
        int intvalue = (int)(text.charAt(index));
        return intvalue;
    }

    // loops the key to be the same length as the input
    public static String loopedKey (String key, String inp) {
        String loopedKey = "";
        if (inp.length() > key.length()) {
            for (int i = 0; i < (inp.length() / key.length()); i++) {
                loopedKey += key;
            }
            for (int j = 0; j < (inp.length() % key.length()); j++) {
                loopedKey += "" + key.charAt(j) + "";
            }
        }
        else loopedKey = key;
        return loopedKey;
    }

    public static void main(String[]args){
        String mode = null;
        String keyfile = null;
        String inpfile = null;
        String key = null;
        String inp = null;
        boolean debug = false;
        try{
            mode = args[0];
            keyfile = args[1];
            inpfile = args[2];
            key = Files.readString(Path.of(keyfile));
            key = key.substring(0, key.length() - 1);
            inp = Files.readString(Path.of(inpfile));
            inp = inp.substring(0, inp.length() - 1);
            if(debug){
                System.out.println("mode:" + mode);
                System.out.println("key: " + key);
                System.out.println("inp: " + inp);
            }
        }catch(Exception e){
            e.printStackTrace();
            System.exit(1);
        }

        // Human
        if (mode.equals("human")){
            String loopedKey = loopedKey(key, inp);
            for (int i = 0; i < loopedKey.length(); i++){
                int intKey = intValues(loopedKey,i);
                int intInp = intValues(inp,i);
                String letter = Character.toString((char)intInp^intKey);
                System.out.print(letter);
            }
            System.out.println();
        }

        // NumberOutput
        if (mode.equals("numOut")) {
            String output = "";
            String loopedKey = loopedKey(key, inp);
            for (int i = 0; i < loopedKey.length(); i++){
                int intKey = intValues(loopedKey,i);
                int intInp = intValues(inp,i);
                System.out.print(Integer.toHexString(intInp^intKey) + " ");
            }
            System.out.println();
        }
    }
}
