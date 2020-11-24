
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
        System.out.print("İndirgenemez polinomu giriniz ör(x4+x+1): ");
        String polinom = sc.nextLine();              //reads string   
        String parcalar[] = polinom.split("\\+");
        System.out.print("Sonlu cismi oluşturan elemanlar listeleniyor...");
        int enB=0;
        for(int i=0; i<parcalar.length;i++){ //polinomun derecesini bulmak için
            if(parcalar[i].length()==2){
                char ikili[] = parcalar[i].toCharArray();
                if(ikili.length==2 && Integer.parseInt(String.valueOf(ikili[1]))>enB){
                    enB=Integer.parseInt(String.valueOf(ikili[1]));
                }
            }
        }
        int n=(int) Math.pow(2,enB)-1;
        System.out.println(n);
        
    }

}
