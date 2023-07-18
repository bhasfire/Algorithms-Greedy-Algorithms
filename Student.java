/*
 * Name: <your name>
 * EID: <your EID>
 */

// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.*;

public class Student {
    private int minCost;
    private Student previousStudent;  // New field
    private int name;
    private ArrayList<Student> neighbors;
    private ArrayList<Integer> prices;
    private boolean inMST; // Indicate whether this student is in MST or not.
    private int costToMST;  // New field

    public Student(int x) {
        name = x;
        minCost = Integer.MAX_VALUE;
        costToMST = Integer.MAX_VALUE;  // Initialize costToMST
        neighbors = new ArrayList<Student>();
        prices = new ArrayList<Integer>();
        inMST = false; // Initialize inMST as false.

    }
    
    public int getCostToMST() {
        return costToMST;
    }

    public void setCostToMST(int cost) {
        this.costToMST = cost;
    }

    public int getPriceTo(Student s) {
        int index = this.neighbors.indexOf(s);
        if (index == -1) {
            throw new IllegalArgumentException("Student " + s.getName() + " is not a neighbor of " + this.getName());
        }
        return this.prices.get(index);
    }
    public void setInMST(boolean inMST) {
        this.inMST = inMST;
    }
    public boolean isInMST() {
        return this.inMST;
    }

    public Student getPreviousStudent() {
        return previousStudent;
    }

    public void setPreviousStudent(Student student) {
        this.previousStudent = student;
    }

    public void setNeighborAndPrice(Student n, Integer w) {
        for (int i = 0; i < this.neighbors.size(); i++) {
        if (this.neighbors.get(i).getName() == n.getName()) {
            this.neighbors.set(i, n);
            this.prices.set(i, w);
            return;
        }
    }
    
    // If the neighbor does not exist yet, add it to the list
    this.neighbors.add(n);
    this.prices.add(w);
    }

    public ArrayList<Student> getNeighbors() {
        return neighbors;
    }

    public ArrayList<Integer> getPrices() {
        return prices;
    }

    public int getminCost() { return minCost; }

    public void setminCost(int x) {
        minCost = x;
    }

    public void resetminCost() {
        minCost = Integer.MAX_VALUE;
    }

    public int getName() {
        return name;
    }
    
}
