
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> elemanlar = new ArrayList<>();
        ArrayList<String> hexler = new ArrayList<>();
        ArrayList<String> polinomHexler = new ArrayList<>();
        polinomHexler.add("0");
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

        ArrayList<String> binaryler = new ArrayList<>();
        binaryler.add("1");
        binaryler.add("a");
        for (int i = 2; i < enB; i++) {
            binaryler.add("a^" + String.valueOf(i));
        }

        String[] sagsol = polinom.replace("x", "a").split("a" + String.valueOf(enB) + "\\+");
        if (sagsol.length == 1) {
            sagTaraf = sagsol[0];
        } else {
            sagTaraf = sagsol[0] + sagsol[1];
        }
        int n = (int) Math.pow(2, enB) - 1;

        for (int i = 0; i < n + 1; i++) {
            String hexa = Integer.toHexString(i);
            hexler.add(hexa);
        }
        elemanlar.add("a");
        String binary1 = "";
        String[] bolunmus1 = "a".split("\\+");
        for (int j = binaryler.size() - 1; j >= 0; j--) {
            boolean varmi = false;
            for (int k = 0; k < bolunmus1.length; k++) {

                if (bolunmus1[k].equals(binaryler.get(j))) {
                    varmi = true;
                }
            }
            if (varmi == true) {
                binary1 += "1";
            } else {
                binary1 += "0";
            }
        }
        int num1 = Integer.parseInt(binary1, 2);
        String hexa1 = Integer.toHexString(num1);
        System.out.println("\na^1= a" + " (" + binary1 + "-" + hexa1 + ")");
        polinomHexler.add(hexa1);
        for (int i = 1; i < n; i++) {
            int tmp = i;
            if (tmp == enB - 1) {
                elemanlar.add(sagTaraf);
                String binary = "";
                String[] bolunmus = sagTaraf.split("\\+");
                for (int j = binaryler.size() - 1; j >= 0; j--) {
                    boolean varmi = false;
                    for (int k = 0; k < bolunmus.length; k++) {

                        if (bolunmus[k].equals(binaryler.get(j))) {
                            varmi = true;
                        }
                    }
                    if (varmi == true) {
                        binary += "1";
                    } else {
                        binary += "0";
                    }
                }
                int num = Integer.parseInt(binary, 2);
                String hexa = Integer.toHexString(num);
                System.out.println("a^" + enB + "= " + elemanlar.get(elemanlar.size() - 1) + " (" + binary + "-" + hexa + ")");
                polinomHexler.add(hexa);

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

                    eleman = eleman.replace("a^" + String.valueOf(enB) + "+", "");
                    eleman += "+" + sagTaraf;
                }
                String[] xorSplit = eleman.split("\\+");
                ArrayList<String> esitler = new ArrayList<>();
                for (int k = 0; k < xorSplit.length - 1; k++) {
                    for (int j = k + 1; j < xorSplit.length; j++) {
                        if (xorSplit[k].equals(xorSplit[j])) {
                            esitler.add(xorSplit[k]);
                        }
                    }
                }
                eleman = "";
                for (int k = 0; k < xorSplit.length; k++) {
                    boolean uyusuyor = false;
                    for (int j = 0; j < esitler.size(); j++) {
                        if (xorSplit[k].equals(esitler.get(j))) {
                            uyusuyor = true;
                        }
                    }
                    if (uyusuyor == false) {
                        eleman += xorSplit[k] + "+";
                    }
                }
                eleman = eleman.substring(0, eleman.length() - 1);
                elemanlar.add(eleman);
                String binary = "";
                String[] bolunmus = eleman.split("\\+");

                for (int j = binaryler.size() - 1; j >= 0; j--) {
                    boolean varmi = false;
                    for (int k = 0; k < bolunmus.length; k++) {

                        if (bolunmus[k].equals(binaryler.get(j))) {
                            varmi = true;
                        }
                    }
                    if (varmi == true) {
                        binary += "1";
                    } else {
                        binary += "0";
                    }
                }
                int num = Integer.parseInt(binary, 2);
                String hexa = Integer.toHexString(num);
                System.out.println("a^" + (i + 1) + "= " + eleman + " (" + binary + "-" + hexa + ")");
                polinomHexler.add(hexa);
            }
        }

        System.out.print("Haritalamayı Giriniz: x->x^");
        int haritalama = sc.nextInt(); // kullanıcının girdiği integer haritalama değerini alır.        
        String[] cikisHexa = new String[hexler.size()];
        for (int i = 0; i < cikisHexa.length; i++) {
            cikisHexa[i] = "0";
        }
        for (int i = 1; i < hexler.size(); i++) {
            int moduAlinmis;
            moduAlinmis = Math.floorMod(i * haritalama, n);

            if (moduAlinmis == 0) {
                cikisHexa[Integer.parseInt(polinomHexler.get(i), 16)] = polinomHexler.get(n);
            } else {
                cikisHexa[Integer.parseInt(polinomHexler.get(i), 16)] = polinomHexler.get(moduAlinmis);
            }
        }

        System.out.print("Giriş:");
        for (int i = 0; i < hexler.size(); i++) {
            System.out.print("\t" + hexler.get(i).toUpperCase());
        }
        System.out.println("");
        System.out.print("Çıkış:");
        for (int i = 0; i < cikisHexa.length; i++) {
            System.out.print("\t" + cikisHexa[i].toUpperCase());
        }
        System.out.println("");
    }

}
