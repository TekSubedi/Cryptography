/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asmt2q1a;

import static asmt2q1a.Asmt2q1a.str2Binary;
import static asmt2q1a.asmt2q1b.functionF;
import java.util.*;

/**
 *
 * @author Tek Subedi
 */
public class asmt2q1c {
    // fun to do all the jobs listed in q no 3 
    static void realWorkingfun(String binaryStrg, String key, ArrayList<String> list)
    {
        // initial list with 5 elements but can be expanded
        String temp = "";
        String [] aray = new String[2];
        
        String Li = "";
        String Ri = "";
        // if the no of bits is not divisible by 16, then adding padding '0' at the end
        while(binaryStrg.length() % 16 != 0)
        {
            binaryStrg = binaryStrg + '0';
        }
        
        // these above lines plz check
        int j =0;
        int k =0;
        //System.out.println("Managed bits with padding if needed are \n" + binaryStrg);
        for(int i = 1; i<=binaryStrg.length(); i++)
        {
            // storing 16 bits into temp 
            temp = temp + binaryStrg.charAt(k++);
            // dividing string to 16 bits blocks
            if(i%16 == 0)
            {
                /*if(temp.length()<16)
                temp = '0' + temp; */
                // sends 16 bits str and returns array with two string elements (2 elements)
                // and assigned to aray
                aray = split2LandR(temp);
                // 
                for(j = 0; j<aray.length; j++)
                {
                     if(j==0)
                         Li = aray[j];
                     else
                         Ri = aray[j];
                }
                // fun call. sending upper and lower 8 bits block and key to Li and Ri
                findLiandRi(Li, Ri, key, list);
                temp = "";
            }        
        }  
    }
    // splitter into L0 and R) 8 bits each from 16 bits block
    // and returns an array containing two elements L0 and R0
    static String[] split2LandR(String blockBin)
    {
        String L0 = "";
        String R0 = "";
        String [] aray = new String[] {"", ""};
        for(int i = 0; i<blockBin.length(); i++)
        {
            // first half is put into string L0
            if(i<blockBin.length()/2)
            {
                L0 = L0 + blockBin.charAt(i);
            }  
            // second half is put into string R0
            else
                R0 = R0 + blockBin.charAt(i);
        }
        // giving elements to array
        aray[0] = L0;
        aray[1] = R0;
        return aray;
    }
    // fun to find the values of Li and Ri, receiving l, r and key
    static void findLiandRi(String L0, String R0, String key, ArrayList<String> list)
    {
        String[] ary = new String[2];
        // this array holds 4 elements in the order L1 R1 L2 R2
        String [] arry1 = new String[4];
        // array holds the first and second block of key split from 24 bits
        ary = split2LandR(key);
        String L1 = "";
        String L2 = "";
        String R1 = "";
        String R2 = "";
        String outPut = "";
       
        String first12Bits = ary[0];
        String last12Bits = ary[1];
        // I need to make it more generic later
        for(int i = 1; i<=2; i++)
        {
            if(i==1)
            {
                L1 = R0;
                String temp = (functionF(R0, ary[0]));
                for(int x =0; x<L0.length(); x++)
                {
                    R1 = R1 + (L0.charAt(x) ^ temp.charAt(x));
                }  
            }  
            else
            {
                L2 = R1;
                String temp = (functionF(R1, ary[1]));
                for(int x = 0; x<L0.length(); x++)
                {
                    R2 = R2 + (L1.charAt(x) ^ temp.charAt(x));
                }
            }
              
        }
        //System.out.println("Info: L2 : " + L2 + "\tR2 : " + R2);
        //arry elements orger is L1, R1, L2 and R2
        arry1[0] = L1; arry1[1] = R1; arry1[2] = L2; arry1[3] = R2;
        // outPut is L2R2
        outPut = arry1[2] + arry1[3];
        // check if I need to do the P inverse as in his book page 45 for output
        // if needed it like finding the 2's complements 
        list.add(outPut);
    }  
}

class Test{
     public static void main(String[] args)
    {
        //local declarations and assinments
        ArrayList<String> list=new ArrayList<String>();//Creating arraylist 
        String inputStr = "(hello, dear. how are you?)";
        String binaryStrg = "";
        String key = "101001001111010101011101";
        asmt2q1c testObj = new asmt2q1c();
        String [] binStrArray = str2Binary(inputStr);
         for (int i = 0; i<binStrArray.length; i++) {
            binaryStrg = binaryStrg + binStrArray[i];
        }
        System.out.println("This is just a check that its converted to binary \n" + binaryStrg);
        testObj.realWorkingfun(binaryStrg, key, list);
        // for iterating the list
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            System.out.println("Final output i.e. ciphertext block from each block(16) bits (L2R2) : " +iterator.next());
    } 
}
}

