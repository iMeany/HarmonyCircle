// Just some playing around with music theory
// 1  2  3  4  5  6  7  8  9  10  11  12
// C  #  D  #  E  F  #  G  #  A   #   B
// I    II    III IV    V     VI     VII

// Major    =   I   III     V   =   1   5   8
// Minor    =   I   IIIb    V   =   1   4   8


package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        String __TAB_DIR = "E:\\Dropbox\\Tabs\\Brad Sucks";
        File tabFilesFolder = new File(__TAB_DIR);

        char[] n = "C1D2EF3G4A5B".toCharArray();
        char[] s = "CDEFGAB".toCharArray();


        // harmony circle as 2d array
        String[][] h = {
                {"C",   "G",    "D",    "A",    "E",    "B",    "F#",   "C#",   "G#",   "D#",   "A#",   "F"},
                {"D#",	"A#",	"F",	"C",	"G",	"D",	"A",	"E",	"B",	"F#",	"C#",	"G#"},
                {"F#",	"C#",	"G#",	"D#",	"A#",	"F",	"C",	"G",	"D",	"A",	"E",	"B"},
                {"A",	"E",	"B",	"F#",	"C#",	"G#",	"D#",	"A#",	"F",	"C",	"G",	"D"}
        };

        String[] givenNotes = {"F", "D", "A", "F#"};

        for (int j=0; j<4; j++) {
            for (int i=0; i<12; i++) {
                if (Arrays.asList(givenNotes).contains(h[j][i])) {
                    System.out.print("[" + h[j][i] + "]-------");
                } else {
                    System.out.print(" " + h[j][i] + " -------");
                }
                if (h[j][i].length()==1) {
                    System.out.print("-");
                }
            }
            System.out.println();

            if (j != 3 ) {
                for (int emptyLines=0; emptyLines<4; emptyLines++) {
                    for (int i=0; i<12; i++) {
                        System.out.print(" |         ");
                    }
                    System.out.println();
                }
            }
        }

        for (int i=0; i<7; i++) {
            int x=(getPos(s[(0+i)%7])-getPos(s[(0+i)%7])+13)%12;
            int y=(getPos(s[(2+i)%7])-getPos(s[(0+i)%7])+13)%12;
            int z=(getPos(s[(4+i)%7])-getPos(s[(0+i)%7])+13)%12;

            //System.out.print(s[(0+i)%7]+"-"+s[(2+i)%7]+"-"+s[(4+i)%7]);
            //System.out.print("  ->  " + x + "-" + y + "-" + z + " ");
            //System.out.println(getQuality(x, y, z));
        }

        File[] tabFileList = (tabFilesFolder).listFiles();

        for (File f : tabFileList) {
            if (f.getPath().endsWith(".txt")) {
                System.out.println(f.getPath());
                parseChords(f);

            }
        }
        //System.out.println(n);
    }

    public static int getPos(char note) {
        char[] n = "C1D2EF3G4A5B".toCharArray();
        int p=0;
        for (int i=0; i<12; i++) {
            p++;
            if (n[i]==note) {
                break;
            }
        }
        return p;
    }

    public static String getQuality(int x, int y, int z) {
        if (x==1 && y == 5 && z==8) return "Major";
        else if (x==1 && y == 4 && z==8) return "Minor";
        else if (x==1 && y == 4 && z==7) return "MaybeDim";
        else return "Unknown";

    }

    public static String parseChords(File f) {

        String patternStr = "([A-G][#bm]?)\\s+?";
        Pattern ptrn = Pattern.compile(patternStr);
        Matcher m;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                m = ptrn.matcher(line);
                while (m.find()) {
                    String group = m.group();
                    System.out.print(group);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "n";
        }

        return "y";
    }
}
