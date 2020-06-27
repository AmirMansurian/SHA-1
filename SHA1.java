/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sha1;

import com.sun.xml.internal.ws.util.StringUtils;


/**
 *
 * @author amir
 */
public class SHA1 {
    
    private Integer MessageLenght;
    private String Lparam;
    private Integer Padding;
    private String PaddingParam = "1";

    
    public SHA1(String message)
    {
        
        MessageLenght = message.length();
        Lparam = Integer.toBinaryString(MessageLenght);
        
        int len = Lparam.length();

        for (int i=0; i<64-len; i++)
        {
            Lparam = "0" + Lparam;
        }
        
        Padding = 448 - (MessageLenght - 1) % 512;
        
        for (int i=0; i < Padding; i++)
        {
            PaddingParam += "0";
        }
        
        System.out.println(Lparam);
    }
    
    
    static void Compression ()
    {
        
    
    }
    
    
    
    
    public static void main(String[] args) {
        
        SHA1 hash = new SHA1("aaaaaaaa");
        
    }
    
}
