package com.homework._2015_03_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Alexandr Kozlov on 21.03.2015.
 * Why complicate the work yourself
 * if you can accomplish the task with the help strings.
 *
 */
public class GetTree {
    /**
     * Print elements of tree with tabs
     * @param m count for tabs
     * @param s element of tree
     */
    private static void tabulation(int m, String s) {
        if (s.equals(" - ")) {
            while (m > 0) {
                System.out.print("\t");
                m--;
            }
        } else {
            while (m > 0) {
                System.out.print("\t");
                m--;
            }
            System.out.println(s);
        }
    }

    /**
     * Print tree
     * @param massivTree
     */
    private static void formatPrint(String[][] massivTree) {
        for (int i = 0; i < massivTree.length; i++) {
            for (int j = 0; j < massivTree[i].length; j++) {
                tabulation(j, massivTree[i][j]);
            }
        }
    }

    /**
     * @param massiv    array of strings with addresses folders/files
     * @param max   maximum size of the sub-folders
     * @return
     */
    private static String[][] formatTree(String[][] massiv, int max) {
        int str;
        String temp;
        for (int j = 0; j < max; j++) {
            str = 1;
            temp = "";
            while (temp.equals("")) {
                if (j < massiv[str - 1].length) {
                    temp = massiv[str - 1][j];
                } else {
                    str++;
                }
            }
            while (str < massiv.length) {
                if (j == 0) {
                    if (massiv[str][j].equals(temp)) {
                        massiv[str][j] = " - ";
                        str++;
                    } else {
                        temp = massiv[str][j];
                        str++;
                    }
                } else {
                    if (j < massiv[str].length) {
                        if (massiv[str][j].equals(temp) && massiv[str][j - 1].equals(" - ")) {
                            massiv[str][j] = " - ";
                            str++;
                        } else {
                            temp = massiv[str][j];
                            str++;
                        }
                    } else {
                        str++;
                    }
                }
            }
        }
        return massiv;
    }

    /**
     *  Sorted list
     * @param arrayList list with address to the directory/file
     * @return arrayList1 - sorted list without duplicate rows
     */
    private  static ArrayList<String> sortList(ArrayList<String> arrayList){
        Set<String> arraySet = new HashSet();
        arraySet.addAll(arrayList);

        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.addAll(arraySet);

        Collections.sort(arrayList1);

        return  arrayList1;
    }

    /**
     * Print tree
     * @param arrayList list with address to the directory/file
     */
    private static void print(ArrayList<String> arrayList) {
        // sort address to directory/file
        ArrayList<String> arrayList1 = sortList(arrayList);

        int max = 0;
        for (int i = 0; i < arrayList1.size(); i++) {
            for (int j = 0; j < arrayList1.get(i).split("/").length; j++) {
                if (max < arrayList1.get(i).split("/").length) {
                    max = arrayList1.get(i).split("/").length;
                }
            }
        }
        // a two-dimensional array of strings, broken down by "/"
        String[][] massivAddress = new String[arrayList1.size()][max];
        for (int i = 0; i < arrayList1.size(); i++) {
            for (int j = 0; j < massivAddress[i].length; j++) {
                massivAddress[i] = arrayList1.get(i).split("/");
            }
        }
        // let's go print
        formatPrint(formatTree(massivAddress, max));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> arrayList = new ArrayList<>();

        System.out.println("Enter the string with the address directory/file.");
        System.out.println("To print the tree of address print command \"print\".");
        System.out.println("To terminate enter the address print command \"exit\".");
        // Read lines with address
        while (true) {
            String s = reader.readLine();

            if (s.equals("exit")) {
                break;
            }
            if (s.equals("print")) {
                print(arrayList);
            }
            arrayList.add(s);
        }
    }
}
