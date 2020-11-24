
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> elemanlar = new ArrayList<>();
        Scanner sc = new Scanner(System.in); //System.in is a standard input stream  
        System.out.print("İndirgenemez polinomu giriniz ör(x4+x+1): ");
        String polinom = sc.nextLine();              //reads string   
        String parcalar[] = polinom.split("\\+");
        System.out.print("Sonlu cismi oluşturan elemanlar listeleniyor...");
        int enB = 0;
        String sagTaraf = "";
        for (int i = 0; i < parcalar.length; i++) { //polinomun derecesini bulmak için
            if (parcalar[i].length() == 2) {
                char ikili[] = parcalar[i].toCharArray();
                if (ikili.length == 2 && Integer.parseInt(String.valueOf(ikili[1])) > enB) {
                    enB = Integer.parseInt(String.valueOf(ikili[1]));
                }
            }
        }
        String[] sagsol = polinom.replace("x", "a").split("a" + String.valueOf(enB) + "\\+");
        if (sagsol.length == 1) {
            sagTaraf = sagsol[0];
        } else {
            sagTaraf = sagsol[0] + sagsol[1];
        }
        System.out.println(sagTaraf);
        int n = (int) Math.pow(2, enB) - 1;
        System.out.println(n);
        elemanlar.add("a");
        for (int i = 1; i < n; i++) {
            int tmp = i;
            if (tmp == enB - 1) {
                elemanlar.add(sagTaraf);
                System.out.println(elemanlar.get(elemanlar.size() - 1));
            } else {
                String[] artiSplit = elemanlar.get(i - 1).split("\\+");
                String eleman = "";
                for (int j = 0; j < artiSplit.length; j++) {
                    String[] usSPlit = artiSplit[j].split("\\^");
                    if (usSPlit.length == 1 && "a".equals(usSPlit[0])) {
                        eleman += "a^2+";
                    } else if (usSPlit.length == 1 && "1".equals(usSPlit[0])) {
                        eleman += "a+";
                    }
                    if (usSPlit.length == 2) {
                        eleman += "a^" + String.valueOf(Integer.parseInt(usSPlit[1]) + 1) + "+";
                    }

                }
                eleman = eleman.substring(0, eleman.length() - 1);
                if (eleman.contains("a^" + String.valueOf(enB))) {
                    eleman = eleman.replace("a^" + String.valueOf(enB), sagTaraf);
                }
                String[] xorSplit = eleman.split("\\+");
                ArrayList<String> esitler = new ArrayList<>();
                for (int k = 0; k < xorSplit.length-1; k++) {
                    for (int j = k+1; j < xorSplit.length; j++) {
                        if (xorSplit[k].equals(xorSplit[j])) {
                            esitler.add(xorSplit[k]);
                        }
                    }
                }
                eleman="";
                for (int k = 0; k < xorSplit.length; k++) {
                    boolean uyusuyor=false;
                    for (int j = 0; j < esitler.size(); j++) {
                        if(xorSplit[k].equals(esitler.get(j))){
                            uyusuyor=true;
                        }
                    }
                    if (uyusuyor==false) {
                        eleman+=xorSplit[k]+"+";
                    }
                }
                eleman = eleman.substring(0, eleman.length() - 1);
                elemanlar.add(eleman);
                System.out.println("a^" + (i + 1) + "= " + eleman);
            }
        }

    }

}
