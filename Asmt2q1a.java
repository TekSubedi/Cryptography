package asmt2q1a;

import static asmt2q1a.asmt2q1b.convert2Int;

/**
 *
 * @author Tek Subedi
 */
public class Asmt2q1a {

    // converting string characters into binary string
    static String[] str2Binary(String str)
    {
        String[] array = new String[str.length()];
        int loopCounter = str.length();

        for (int i = 0; i < loopCounter; i++)
        {
            // converting each character to ascii valueue
            int value = Integer.valueOf(str.charAt(i));
            // converts ascii valueues of english letter  to corresponding valueues given in the question
            if(value>96 && value<123)
                value -= 97;
            // if not english letter enters this else block
            else
            {
                // switch to convert the ascii valueues of the given strings except english letter
                switch(value)
                {
                    case 32:
                        value -= 6;
                        break;
                    case 46:
                        value = value - 19;
                        break;
                    case 44:
                        value -= 16;
                        break;
                    case 63:
                        value -= 34;
                        break;
                    case 40:
                        value -= 10;
                        break;
                    case 41:
                        value -= 10;
                        break;
                }
            }
            //System.out.println(value); --- can print just to check

            // Converting ascii valueues to binary string and putting into bString
            String bString = "";
            while (value > 0)
            {
                if (value % 2 == 1)
                {
                    bString += '1';
                }
                else
                    bString += '0';
                value /= 2;
            }
            bString = reverseStringPos(bString);
            array[i] = bString;
        }
        return array;
    }

    static String reverseStringPos(String bStr)
    {
        char[] bArray = bStr.toCharArray();
        int i, j;
        j = bArray.length - 1;

        for (i = 0; i < j; i++, j--)
        {
            // Swaping values of i and j
            char temp = bArray[i];
            bArray[i] = bArray[j];
            bArray[j] = temp;
        }
        String returnString = String.valueOf(bArray);
         // 
        while(returnString.length()<5)
        {
            returnString = '0' + returnString;
        }
        return returnString;
    }
    public static char binary2English(String binStr)
    {
        String englishString = "";
        int number = convert2Int(binStr);
        if(number >= 0 && number <= 25)
            number += 97;
        else
            switch(number)
            {
                case 26:
                    number += 6;
                    break;
                case 27:
                    number += 19;
                    break;
                case 28:
                    number += 16;
                    break;
                case 29:
                    number += 34;
                    break;
                case 30:
                    number += 10;
                    break;
                case 31:
                    number += 10;
                    break;   
            }
        // (char)number is converting number to corresponding letter from ascii value
        char returningChar = (char)number;
        
        return returningChar;
    }
    public static void main(String[] args)
    {
        System.out.println("Tek Subedi\nCS 4476\nAssignment 2 Q 1.1");
        String reversedEngStr = "";
        String str = "hello";
        System.out.println("Original String before converting to binary : \n" + str);
        String [] arry = str2Binary(str);
        // enhanced for loop
        for (String arry1 : arry) {
            System.out.println(arry1);
        }
        System.out.println("Time to check the reverse ie converting binary rep to english : ");
        for(int i = 0; i<arry.length; i++)
        {
            reversedEngStr = reversedEngStr + binary2English(arry[i]);   
        }
        System.out.println(reversedEngStr);
    }
}
