/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2tel;

/**
 *
 * @author Gabriel
 */
import java.lang.Math;

public class Lab2Tel {

    final static int NBBITS=8;
    
    public static void main(String[] args) {
        boolean[] bits;
        bits=encodageNRZ_L('c',1);
        System.out.println(bitsToString(bits));
        bits=encodageNRZ_S('c',1);
        System.out.println(bitsToString(bits));
    }
    
    //fonction qui recoit le code ascii d’un caractère et génère une suite bits en format NRZ L auquel s’ajoute un bit de parité
    //entrées: int codeAscii, int parite
    //sorties: boolean[] bits
    public static boolean[] encodageNRZ_L(int codeAscii,int parite)
    {
        boolean[] bits=new boolean[NBBITS];
        double temp=0;
        int nbUn=0;
        for(int i=1;i<NBBITS;i++)
        {
            if(codeAscii>=(temp=Math.pow(2,NBBITS-(i+1))))
            {
                codeAscii-=temp;
                bits[i]=true;
                nbUn++;
            }
        }
        bits[0]=parite>0?((nbUn+parite)%2==1):false;
        return bits;
    }
    
    //fonction qui recoit le code ascii d’un caractère et génère une suite bits en format NRZ S auquel s’ajoute un bit de parité.
    //entrées: int codeAscii, int parite
    //sorties: boolean[] bits
    public static boolean[] encodageNRZ_S(int codeAscii,int parite)
    {
        boolean[] bits=encodageNRZ_L(codeAscii,parite);
        int zero=0;
        for(int i=1;i<NBBITS;i++)
        {
            if(!bits[i])
            {
                zero++;
            }
            bits[i]=zero%2==1;
        }
        bits[0]=parite>0?(((NBBITS-1)-zero+parite)%2==1):false;
        return bits;
    }
    
    //fonction qui recoit une suite de bits et génere les fréquences associées
    //entrées: boolean[] bits, double frequence
    //sorties: double[] frequences
    public static String bitsToString(boolean[] bits)
    {
        String affichage="";
        for(int i=0;i<NBBITS;i++)
        {
            affichage+=bits[i]?1:0;
        }
        return affichage;
    }
    
    //fonction qui recoit une suite de bits et une chaine de charactère
    //entrées: boolean[] bits
    //sorties: String affichage
    public static double[] frequencesParBit(boolean[] bits,double frequence)
    {
        double[] frequences=new double[NBBITS];
        for(int i=0;i<NBBITS;i++)
        {
            frequences[i]=bits[i]?frequence*2:frequence;
        }
        return frequences;
    }
}
