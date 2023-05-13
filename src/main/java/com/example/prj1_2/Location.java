package com.example.prj1_2;

import java.util.Date;

public class Location implements Comparable<Location> {

    private String location;
    private SLinkedList<Martyr> martyr;


    public Location(String location) {
        this.location = location;
        this.martyr = new SLinkedList<>();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SLinkedList<Martyr> getMartyr() {
        return martyr;
    }

    public void setMartyr(SLinkedList<Martyr> martyr) {
        this.martyr = martyr;
    }


    public double findAgeAVG() {
        Node<Martyr> curr = martyr.getHead().getNext();

        if(curr == null )
            return 0;

        int count = 0;
        double sum = 0.0;

        while (curr != null) {
            count++;
            sum += curr.getData().getAge();
            curr = curr.getNext();
        }


        return sum / count;
    }

    public int[] genderFinder() {
        int[] counts = new int[2];

        Node<Martyr> curr = martyr.getHead().getNext();

        if(curr == null )
            return null;

        while (curr != null) {

            if(curr.getData().getGender() == 'M')
                counts[0]++;
            if(curr.getData().getGender() == 'F')
                counts[1]++;

            curr = curr.getNext();
        }

        return counts;
    }


    public String ageNumberFinder() {

        Node<Martyr> curr = martyr.getHead().getNext();
        int counter = 0;
        String res = "";
        for (int i = 1; i < 100; i++) {
            while (curr != null) {
                if (curr.getData().getAge() == i)
                    counter++;

                curr = curr.getNext();
            }
            if (counter >=1){
                res += "The number of martyrs at age " + i + " is "+ counter+"\n";
            }

            counter = 0;
            curr = martyr.getHead().getNext();
        }

        return res;

    }

    public Date maxDateFinder() {
        int counter = 0;
        int max = 1;

        if(martyr.getHead().getNext() == null)
            return null;

        Date maxDate = martyr.getHead().getNext().getData().getDate();
        Date currDate = martyr.getHead().getNext().getData().getDate();
        Node<Martyr> curr = martyr.getHead().getNext();

        while (curr != null) {
            if(curr.getData().getDate().equals(currDate)) {
                counter++;
            } else if (!curr.getData().getDate().equals(currDate)) {
                if(counter > max){
                    max = counter;
                    maxDate = currDate;
                }
                counter = 0;
                currDate = curr.getData().getDate();
            }
            curr = curr.getNext();
        }

        return maxDate;
    }


    @Override
    public String toString() {
        return "Location{" +
                "location='" + location + '\'' +
                ", martyr=" + martyr +
                '}';
    }

    @Override
    public int compareTo(Location o) {
        return location.compareTo(o.getLocation());
    }

    public boolean equals(Location o) {
        if (location.equals(o.getLocation())){
            return true;
        }
        return false;
    }

}
