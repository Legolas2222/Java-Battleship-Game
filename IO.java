import java.io.*;
/* Diese Klasse stellt diverse Eingabemoeglichkeiten zur Verfuegung.
 * Autor: Peter Schneider-Kamp, Rene Thiemann, Thomas Weiler
 * Erstellt: 23.10.2003
 * Letzte Aenderung: 18.11.2003, 16:59
 */
public class IO {
    public static final int ASK_USER = -1;
    // lese eine Zeile aus der Standardeingabe
    public static String readLine() {
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException io) {
            System.err.println("IO-Fehler!");
            return "";
        }
    }
    // lese ein Array von Integern aus der Standardeingabe
    public static int[] readIntArray() {
  String line;
  try {
      line = IO.readLine().trim();
      if (line.equals("")) {
    return new int[0];
      }
      String parts[] = line.split(" +");
      int result[] = new int[parts.length];
      for (int i = 0; i<parts.length; i++) {
    result[i] = Integer.parseInt(parts[i]);
      }
      return result;
  } catch (NumberFormatException e) {
      System.err.println("Keine Zahl!");
      return new int[0];
  }
    }
  
    /*
     * Liest einen Double-Wert von der Standardeingabe.
     * Dabei kann sowohl ein Punkt als auch ein Komma als
     * Dezimaltrennzeichen verwendet werden.
     */ 
    public static double readDouble(){
  double d = 0;
  try {
      String eingabe = IO.readLine();
      eingabe = eingabe.replaceAll(",",".");
      d = Double.parseDouble(eingabe);
  } catch (NumberFormatException e) {
      System.err.println("Keine Gleitkommazahl!");
  }
  return d;
    }
    
    /*
     * Liest einen Float-Wert von der Standardeingabe.
     * Dabei kann sowohl ein Punkt als auch ein Komma als
     * Dezimaltrennzeichen verwendet werden.
     */ 
    public static float readFloat(){
  float f = 0;
  try {
      String eingabe = IO.readLine();
      eingabe = eingabe.replaceAll(",",".");
      f = Float.parseFloat(eingabe);
  } catch (NumberFormatException e) {
      System.err.println("Keine Gleitkommazahl!");
  }
  return f;
    }
    
    // lese einen Integer aus der Standardeingabe
    public static int readInt() {
        try {
            return Integer.parseInt(IO.readLine());
        } catch (NumberFormatException e) {
            System.err.println("Keine Zahl!");
            return 0;
        }
    }
    // für Abwaertskompatibilitaet mit alter IO.java
    public static int eingabe() {
        return IO.readInt();
    }
    // gibt eine Matrix von Integer-Zahlen aus
    public static void printIntMatrix(int m[][]) {
  for (int i=0; i < m.length; i++) {
      printIntArray(m[i]);
  }
    }
    // gibt ein Array von Integer-Zahlen aus
    public static void printIntArray(int z[]) {
  for (int i = 0; i < z.length; i++) {
      System.out.print(z[i]+" ");
  }
  System.out.println();
    }
    /*
     * Liest eine Matrix der Breite width und der Hoehe
     * height ein. Falls width oder height den Wert IO.ASK_USER
     * besitzen, wird der Benutzer gefragt.
     */
    public static int[][] readIntMatrix(int width, int height) {
        while (width < 0) {
            System.out.print("Anzahl der Spalten: ");
            width = IO.readInt();
        }
        while (height < 0) {
            System.out.print("Anzahl der Zeilen: ");
            height = IO.readInt();
        }
        int[][] inhalt = new int[height][width];
        for (int i = 0; i < height; i++) {
            int[] eingabe = new int[0];
            while (eingabe.length != width) {
                System.out.print("Zeile "+(i+1)+": ");
                eingabe = IO.readIntArray();
                if (eingabe.length != width) {
                    System.out.println("Keine gueltige Zeile der Laenge "+width+"!");
                }
            }
            inhalt[i] = eingabe;
        }
        return inhalt;
    }
}
