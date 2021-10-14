package Practica01;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        int ejemplos = 500;
        int k1 = 26;
        int k2 = 61;

        Integer a[] = new Integer[10];
        Random rd = new Random();
        for(int i = 0; i < 10; i++) {
            a[i] = rd.nextInt();
        }

        Comparator<Integer> c = new EnterosComparador();
        Sort<Integer> quickSort = new QuickSort<Integer>(c);
        Sort<Integer> insertionSort = new InsertionSort<Integer>();
        List<Integer> listaQuickSort = new ArrayList<Integer>();
        List<Integer> listaInsertionSort = new ArrayList<>();

        for(int i = 0; i < ejemplos; i++) {
            listaQuickSort.add(quickSort.tMedSort(i, 100));
            listaInsertionSort.add(insertionSort.tMedSort(i, 100));
        }

        GraficaTiempos graph = new GraficaTiempos(listaQuickSort);
        graph.addList(listaInsertionSort);
        graph.pintanpow2(k1, ejemplos);
        graph.pintanlogn(k2, ejemplos);
        graph.createAndShowGui();
    }
}
