import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

class Wordle {

    public static String compareString(String str1, String str2) {
        String sample="xxxxx";
        for(int k=0;k<str1.length();k++) {
            char cmprs=str2.charAt(k);
            if(str1.contains(String.valueOf(cmprs))) {

                if(str1.indexOf(str2.charAt(k))==k)
                {
                    sample = sample.substring(0, k) + "+"
                            + sample.substring(k + 1);
                }
                else {
                    sample = sample.substring(0, k) + "-"
                            + sample.substring(k + 1);
                }
            }
        }
        return sample;
    }
    public static void compareStringStatus(String sample, String str2)
    {
        String common="";
        String notIn="";
        int indx;
        for(int k=0;k<sample.length();k++) {
            char cmprs=sample.charAt(k);
            if(String.valueOf(cmprs).equals("x")) {

                char val=str2.charAt(k);
                notIn=notIn+String.valueOf(val)+",";
            }
            else
            {
                char val=str2.charAt(k);
                common+=String.valueOf(val)+",";
            }
        }
        System.out.println("The word to be guessed has shape -contain the letter(s)"+common+" as well and does not contain the letter(s)"+notIn);
    }

    public static void main(String[] args) throws Exception {
        //String[] DICTIONARY= {"CAT"};
        ArrayList<String> DICTIONARY_VAL = new ArrayList<String>();

        if (args.length == 0)
            return;
        int counterWords = 0;
        File file = new File(args[0]);
        Scanner in = new Scanner(file);
        while(in.hasNext())
        {
            DICTIONARY_VAL.add(in.next());
            counterWords++;
        }



        ArrayList<String> DICTIONARY_entered = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        int min = 0;
        int max = DICTIONARY_VAL.size() - 1;
        int b = (int) (Math.random() * (max - min + 1) + min);

        String value;
        boolean flag;

        do {
            System.out.println("Guess the 5-letter word!");
            value = scanner.nextLine();
            flag = false;
            if (value.equals(":help")) {
                flag = true;
                System.out.println(
                        "program will select randomly a word from a dictionary of a given length and you will ask the user to guess it. The user is only allowed to guess words that have the exact same length. "
                                + "\nIf the guess is correct, You wins;");

            } else if (value.equals(":giveup")) {
                flag = true;
                System.out.println("The value was " + DICTIONARY_VAL.get(b));
                value = "STOP";
            } else if (value.equals(":info")) {
                flag = true;

                if(DICTIONARY_entered.size()>0) {
                    int leng=DICTIONARY_entered.size()-1;
                    String lastEntered=DICTIONARY_entered.get(leng);
                    String cmp=compareString(DICTIONARY_VAL.get(b),lastEntered);
                    compareStringStatus(cmp,lastEntered);
                }else {
                    System.out.println("Enter your guess");
                }




            } else if (value.matches("[a-z]+")) {
                for (int i = 0; i < DICTIONARY_VAL.size(); i++) {
                    if (DICTIONARY_VAL.get(i).equals(value)) {
                        flag = true;
                        DICTIONARY_entered.add(value);
                    }
                }

                System.out.println("\n");
                for (int j = 0; j < DICTIONARY_entered.size(); j++) {
                    System.out.println(DICTIONARY_entered.get(j));
                    String cmp=compareString(DICTIONARY_VAL.get(b),DICTIONARY_entered.get(j));
                    System.out.println(cmp);
                }

                if (value.equals(DICTIONARY_VAL.get(b))) {
                    System.out.println("Yes you guessed correctly in only " + DICTIONARY_entered.size() + " tries!");
                    System.out.println("I am proud of you");
                    value = "STOP";
                }
            }

            if (flag == false) {
                System.out.println("Enter valid input");
                continue;
            }

        } while (value != "STOP");

    }

}
