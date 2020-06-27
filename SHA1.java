/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha1;

import com.sun.xml.internal.ws.util.StringUtils;
import static java.lang.Math.pow;


/**
 *
 * @author amir
 */
public class SHA1 {
    
    private Integer MessageLenght;
    private String Lparam;
    private Integer Padding;
    private String PaddingParam = "1";
    static private Integer[] X;
    static private Integer[] IV = {0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0};
    static private Integer[] K = {0x5A827999, 0x6ED9EBA1, 0x8F1BBCDC, 0xCA62C1D6};
    static private Integer[] IVV;

    
    public SHA1(String message)
    {
        
        byte [] bytes = message.getBytes();
        
        for (Byte b:bytes){
            
            int val = b;
            for (int i=0; i<8; i++)
            {
                message += (val&128) == 0 ? "0":"1";  
                val <<= 1;
            }
            
        }
        
        
        
        MessageLenght = message.length();
        Lparam = Integer.toBinaryString(MessageLenght);
        
        int len = Lparam.length();

        for (int i=0; i<64-len; i++)
        {
            Lparam = "0" + Lparam;
        }
        
        Padding = 448 - (MessageLenght + 1) % 512;
        
        for (int i=0; i < Padding; i++)
        {
            PaddingParam += "0";
        }

        
        message = message + PaddingParam + Lparam;
        
        System.out.println(message.length());
        
        X = new Integer[20];
        String temp ="";
        int k=0;
        for (int i=0; i<message.length(); i++)
        {   
             if (i%32 == 0 && i!=0){
                temp = ""; 
                X[i] = Integer.parseInt(temp, 2);
                k++;
             }
             
            temp += message.charAt(i);
        }
        
        X[16] = Integer.rotateLeft(X[0] ^ X[2] ^ X[8] ^ X[13], 1);
        X[17] = Integer.rotateLeft(X[1] ^ X[3] ^ X[9] ^ X[14], 1);
        X[18] = Integer.rotateLeft(X[2] ^ X[4] ^ X[10] ^ X[15], 1);
        X[19] = Integer.rotateLeft(X[3] ^ X[5] ^ X[11] ^ X[16], 1);
       
        IVV = new Integer[5];
        
        
    }
    
    static Integer function (int n){
        
        Integer res = 0;
        
        if (n==0)
            res = ((IVV[1]&IVV[2]) | (~(IVV[1])&IVV[3]));
        else if (n==1 || n==3)
            res = (IVV[1] ^ IVV[2] ^ IVV[3]);
        else if (n==2)
            res = ((IVV[1]&IVV[2]) | (IVV[1]&IVV[3]) | (IVV[3]&IVV[2]));
        
        return res;
    }
    
    
    static void Compression ()
    {
        for (int j=0; j<4; j++)
        {
            for (int i=0; i<20; i++)
            {
                Integer[] temp = new Integer[5];
                IVV = IV;
                temp[4] = IVV[3];
                temp[3] = IVV[2];
                temp[1] = IVV[0];
                temp[2] = Integer.rotateLeft(IVV[1], 30);

                temp[0] = IVV[4] + function(j) % (int)pow(2, 0x20);
                temp[0] += Integer.rotateLeft(IVV[0], 5) % (int)pow(2, 0x20);
                temp[0] += X[i] % (int)pow(2, 0x20);
                temp[0] += K[j]% (int)pow(2, 0x20);

                IVV = temp;
            }
            
            for (int i=0; i<20; i++)
            {
                X[i] = Integer.rotateLeft(X[4] ^ X[6] ^ X[12] ^ X[17], 1);
            }
            
        }
    }
    
    
    
    
    public static void main(String[] args) {
        
        //SHA1 hash = new SHA1("aaaaaaaaaaaaaaaaaaaaaaaa");
        
        Integer[] a = {1, 2, 3, 4, 5};
        Integer[] b = a;
        a[1] = 6;
        System.out.println(b[0]);
        
    }
    
}
