/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmt2q1a;
import static asmt2q1a.Asmt2q1a.binary2English;
import static asmt2q1a.Asmt2q1a.str2Binary;
import static asmt2q1a.asmt2q1c.realWorkingfun;
import java.util.*;

/**
 *
 * @author Tek Subedi
 */
public class asmt2q1d {
    
    public static void main(String[] args)
    {
        // local declaration and assignments
        ArrayList<String> myList=new ArrayList<String>();//Creating arraylist 
        String plainText = "how do you like computer science";
        System.out.println("The plain Text is : " + plainText);
        String key = "101101010010100101101011";
        String cipherText = "";
        String binStr = "";
        String englishString = "";
        String [] binStrgArray = str2Binary(plainText);
        for (int i = 0; i<binStrgArray.length; i++) {
            binStr = binStr + binStrgArray[i];
        }
         // function from question no 1.3, please see question 1.3 for details
        realWorkingfun(binStr, key, myList);
        // done to iterate through list elements
        Iterator iterator = myList.iterator();
        while(iterator.hasNext()) {
            cipherText = cipherText + iterator.next();
         }
        System.out.println("CipherText in Binary bits is: " + cipherText);
   
        //ArrayList<String> newList = new ArrayList<String>();
        String temp = "";
        int p =0;
        int k = 0;
        System.out.println("The size of cipher text before padding is---:" + cipherText.length());
        // adding padding if cipherText size if not divisible by 5
        while(cipherText.length() % 5 != 0)
        {
            cipherText = cipherText + '0';
        } 
        // englishtext array will have the size of ciphertext /5
        String [] englishText = new String[cipherText.length()/5];
        System.out.println("The size of cipher tex is :" + cipherText.length());
        //putting 5 bits block of binary to array as an elemements since we need 5 bits blocks to convert to eng
        for(int i=1; i<=cipherText.length(); i++)
        {
            temp = temp + cipherText.charAt(k++);
            if(i%5 == 0)
            {
                englishText[p++] = temp;
                // emptying temp
                temp = "";
            }       
        }
        // loop to make a call to binary2English fun to convert binary block to plain english 
        for(int c = 0; c<englishText.length; c++)
        {
            // sending every array element (5 bits binary block to binary2English fun to convert
            // to plain text and putting the return string char into string
            englishString = englishString + binary2English(englishText[c]);
        }
        System.out.println("The ciphertext in english as per q no 1.1 is: \n" + englishString);        
    }
}
