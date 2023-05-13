package com.example.prj1_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class test {
    static DLinkedList<Location> Dlist = new DLinkedList<>();
    static SLinkedList<Martyr> list = new SLinkedList<>();
    public static void main(String[] args) throws FileNotFoundException {
        SLinkedList<Integer> ls = new SLinkedList<>();

        readfile();
        Dlist.insertSorted(new Location("Gaza"));
        Dlist.insertSorted(new Location("Ramallah and al-Bira"));
        Dlist.insertSorted(new Location("al-Quds"));



        Node<Martyr> curr = list.getHead().getNext();
        NodeD<Location> currLo = Dlist.getHead().getNext();
        while (curr != null) {

            curr = curr.getNext();
        }


        for (int i = 0; i <list.size() ; i++) {
            Martyr m = new Martyr(list.get(i).getName(),list.get(i).getAge(),list.get(i).getLocation(),list.get(i).getDate(),list.get(i).getGender());
            if (list.get(i).getLocation().equals("Gaza")){
                Dlist.findNode(new Location("Gaza")).getData().getMartyr().Insertsorted(m);
            }
            if (list.get(i).getLocation().equals("Ramallah and al-Bira")){
                Dlist.findNode(new Location("Ramallah and al-Bira")).getData().getMartyr().Insertsorted(m);
            }
            if (list.get(i).getLocation().equals("al-Quds")){
                Dlist.findNode(new Location("al-Quds")).getData().getMartyr().Insertsorted(m);
            }
        }

//
//
//
//        for (int i = 0; i <list.size() ; i++) {
//            Martyr m = new Martyr(list.get(i).getName(),list.get(i).getAge(),list.get(i).getLocation(),list.get(i).getDate(),list.get(i).getGender());
//            if (list.get(i).getLocation().equals("Gaza")){
//                 Dlist.findNode("Gaza").getList().Insertsorted(m);
//               // temp.getList().Insertsorted(m);
//            }
//        }
//
//        for (int i = 1; i < 100; i++) {
//            int count =0;
//            for (int j = 0; j < Dlist.findNode("Gaza").getList().size(); j++) {
//               if (((Martyr)Dlist.findNode("Gaza").getList().get(j)).getAge()==i){
//                   count ++;
//               }
//            }
//            if (count>=1){
//                System.out.println("the number of die in age "+i+" is "+count);
//            }
//        }
//        System.out.println("GAZA -----------------------------------");
//        for (int i = 1; i < 100; i++) {
//            int count =0;
//            for (int j = 0; j < Dlist.findNode("Ramallah and al-Bira").getList().size(); j++) {
//                if (((Martyr)Dlist.findNode("Ramallah and al-Bira").getList().get(j)).getAge()==i){
//                    count ++;
//                }
//            }
//            if (count>=1){
//                System.out.println("the number of die in age "+i+" is "+count);
//            }
//        }
//
//        System.out.println(Dlist.findNode("Ramallah and al-Bira").getList().printS());
//
//       // System.out.println(Dlist.print());

        //System.out.println(Dlist.get(1).maxDateFinder().toString());
        System.out.println(Dlist.get(1).maxDateFinder() + " " + Dlist.get(1).getLocation());
        System.out.println(Dlist.get(1).ageNumberFinder() + " " + Dlist.get(1).getLocation());
        int [] listm = Dlist.get(0).genderFinder();
        System.out.println(listm[0]+" the Male "+"the Female (Like mohammad f) "+listm[1]);


    }
    public static void readfile() throws FileNotFoundException {
        String csvFile = "D:\\Data Structure\\prj1_0\\src\\main\\resources\\btselem.csv";
        String line;
        String csvDelimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvDelimiter);
                if (!isValidD(data[3])){
                    data[3]="00/00/0000";
                }
                if (!isValidN(data[1])){
                    data[1]="0";
                }
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date date1 = formatter.parse(data[3]);



               // System.out.println(data[3]);
                Martyr m = new Martyr(data[0],Integer.parseInt(data[1]),data[2],date1,data[4].charAt(0));
                list.Insertsorted(m);

//                NodeD node= Dlist.findNode(data[2]);
//                if(node == null){
//                   Dlist.insertSorted(data[2]);
//               }
//               NodeD node1= Dlist.findNode(data[2]);
//               node1.getList().Insertsorted(m);

                // System.out.println(data[3]);
            }
        }
        catch (IOException | NumberFormatException | ParseException e) {
            System.out.println();
        }
    }
    public static boolean isValidD(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date date1 = formatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public static boolean isValidN(String date){
        try{
            Integer.parseInt(date);
        }catch (NumberFormatException e){
            System.out.print("");
            return false;
        }
        return true;
    }


}
