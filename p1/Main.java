package p1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args){
        int ejemplos = 10;

        Integer a[] = new Integer[10];
        Random rd = new Random();
        for(int i = 0; i < 10; i++) {
            a[i] = rd.nextInt();
        }

        Comparator<Integer> c = new EnterosComparador();
        Sort<Integer> quickSort = new QuickSort<Integer>(c);
        List<Integer> listaQuickSort = new ArrayList<Integer>();

        for(int i = 0; i < ejemplos; i++) {
            listaQuickSort.add(quickSort.tMedSort(i, 100));
        }

        GraficaTiempos graph = new GraficaTiempos(listaQuickSort);
        graph.createAndShowGui();

    }
}