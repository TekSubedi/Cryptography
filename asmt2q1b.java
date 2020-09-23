/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmt2q1a;

import static asmt2q1a.Asmt2q1a.str2Binary;

/**
 *
 * @author Tek Subedi
 */
public class asmt2q1b {
    public static void main(String[] args)
    {
        // local declarations and assinments
        String str1 = "hello";
        String kstr2 = "k";
        String finalOutputStr = "";
        // function call to convert to bit strings
        String bSt =  convertTostr(str1);
        String rKey = convertTostr1(kstr2);
        System.out.println("12 bits K string is : " + rKey);
        finalOutputStr = functionF(bSt, rKey); 
        System.out.println("Final 8 bits output(combination of two output from s-boxes) of function f is: " + finalOutputStr);
    }
    // function to convert input B char/string to binary bits
    public static String convertTostr(String str1)
    {
        String[] arr = str2Binary(str1);
        str1 = arr[0];
        // making the string converted bits to 8 bits if it has less than 8 bits
        while(str1.length()<8)
        {
            str1 = '0' + str1;
        }
        System.out.println("8 bits B :" + str1);
        return str1;   
    }
    //function to convert the key to bit string
    public static String convertTostr1(String str1)
    {
        // calling the function which does the conversion job in real
        String [] ar = str2Binary(str1);
        str1 = ar[0];
        // making the string converted bits to 12 if it has less than 12 bits
        while(str1.length()<12)
        {
            str1 = '0' + str1;
        }
        return str1;
    }
    // function doing all the required jobs as per the question
    public static String functionF(String bst, String rKey)
    {
        String B1 = "";
        String B2 = "";
        String xorEd = "";
        String outputB1 = "";
        String outputB2 = "";
        int j = bst.length();
        int [][] intS1 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5}, 
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
        int [][] intS2 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, 
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9}, 
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}, 
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
        // expanding the 8 bits to 12 bits as per question requirement, see 1.1 for details
        // (For example, 10110101 is expanded to 101101010111)
        if(bst.length()<12)
        {
            for(int i = 0; i<j; i++)
        {
            if(i%2 == 1)
            bst = bst + bst.charAt(i);  
        }
            
        }
        // XORed between B (bst) and K (round key)
        for(int i = 0; i<bst.length(); i++)
        {
            xorEd = xorEd + (bst.charAt(i) ^ rKey.charAt(i));
        }
        // spliting xorEd str into B1 and B2 to put B1 into S1 and B2 into S2
        for(int i = 0; i<xorEd.length(); i++)
        {
            if(i<6)
                B1 = B1 + xorEd.charAt(i);
            else
                B2 = B2 + xorEd.charAt(i);
        }
        // 6 inputs to S box spits 4 bits outputs
        //The 4 bits binary outputs from S1 and S2 respectively are outputB1 and outputB2
        outputB1 = outputfromInputandSbox(B1, intS1); 
        outputB2 = outputfromInputandSbox(B2, intS2);
        return (outputB1 + outputB2);
       
    }
    // function to convert to binary string
    // I think below fun is not used as and similar fun is there in q1
    // this can be used if regular ascii is used   ?????? not used
    static String[] str2Binary1(String str)
    {
        String[] array = new String[str.length()];
        int loopCounter = str.length();
        int value;
        for (int i = 0; i < loopCounter; i++)
        {
            // converting each character to ascii value
            value = Integer.valueOf(str.charAt(i));

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
            bString = reverseStringPos1(bString);
            array[i] = bString;

            //System.out.println(bString);
        }
        return array;
    }
    // function to put the converted binary string elements in correct order
     // I think below fun is not used as and similar fun is there in q1
    // this can be used if regular ascii is used   ????? Not used
    static String reverseStringPos1(String bStr)
    {
        char[] bArray = bStr.toCharArray();
        int i, j;
        j = bArray.length-1;

        for (i = 0; i < j; i++, j--)
        {
            // Swaping values of i and j
            char temp = bArray[i];
            bArray[i] = bArray[j];
            bArray[j] = temp;
        }
        String returnString = String.valueOf(bArray);
         // 
        return returnString;
    }
    // this function takes 6 bits input and s box and spits out 4 bits output
    static String outputfromInputandSbox(String input, int sBox[][])
    {
        int i, j;
        String rowStr = "";
        String colStr = "";
        String returningStr = "";
        // finding the the row and colomun index value
        for(i = 0; i<input.length(); i++)
        {
            // first and last elements are combined and converted into int and found the row index (i)
            if(i== 0 || i == 5)
                rowStr = rowStr + input.charAt(i); 
            // rest of the elements (in string) is converted into int and found the colom index(j)
            else
                colStr = colStr + input.charAt(i);
        }
        // convert2Int will convert binary to decimal
        i = convert2Int(rowStr);
        j = convert2Int(colStr);
        returningStr = convertIntTobinStr(sBox[i][j]);        
        return returningStr;
    }
    // function that will take binary and convert into decimal, regular int
    static int convert2Int(String str3)
    {
        int total = 0;
        // finding the highest power for 2
        int pw = str3.length() -1;
        // we are doing 1*2^3 + 0* 2^2 + 1* 2^1 + 1* 2^0 some thing like this 
        for(int i= 0; i<str3.length(); i++)
        {
            total +=  (Character.getNumericValue(str3.charAt(i))) * (int)(Math.pow(2, pw));
            pw--;   
        }      
        return total;
    }
    // function converting int to binary string
    static String convertIntTobinStr(int intS1)
    {
         String bString = "";
            while (intS1> 0)
            {
                if (intS1 % 2 == 1)
                {
                    bString += '1';
                }
                else
                    bString += '0';
                intS1 /= 2;
            }
            // calling this fun to put the binary in right order
            bString = reverseStringPos1(bString);
            while(bString.length()<4)
            {
                bString = '0' + bString;
            }
        
        return bString;
    }
}
