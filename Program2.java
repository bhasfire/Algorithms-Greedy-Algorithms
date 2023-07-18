/*
 * Name: <your name>
 * EID: <your EID>
 */

// Implement your algorithms here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.HashMap;

public class Program2 {
    private ArrayList<Student> students;    // this is a list of all Students, populated by Driver class
    private Heap minHeap;

    // additional constructors may be added, but don't delete or modify anything already here
    public Program2(int numStudents) {
        minHeap = new Heap();
        students = new ArrayList<Student>();
    }

    /**
     * findMinimumStudentCost(Student start, Student dest)
     *
     * @param start - the starting Student.
     * @param dest  - the end (destination) Student.
     * @return the minimum cost possible to get from start to dest.
     * Assume the given graph is always connected.
     */
    public int findMinimumStudentCost(Student start, Student dest) {
            // Reset minCost of all students to maximum (infinity), except for the start student.
        for (Student student : students) {
            if (student.equals(start)) {
                student.setminCost(0);
            } else {
                student.setminCost(Integer.MAX_VALUE);
            }
            student.setPreviousStudent(null); // set the initial predecessor of each node to null
            // Add each student to the heap.
            minHeap.insertNode(student);
        }

        // Process until all students are removed from the min heap.
        while (!minHeap.isEmpty()) {
            // Extract the student with minimum cost.
            Student current = minHeap.extractMin();

            // Print statement for debugging
            System.out.println("Extracted student: " + current.getName() + ", minCost: " + current.getminCost());

            System.out.println("Current student: " + current.getName() + ", minCost: " + current.getminCost());

            // Update the cost to its neighbors.
            for (int i = 0; i < current.getNeighbors().size(); i++) {
                Student neighbor = current.getNeighbors().get(i);
                int newCost = current.getminCost() + current.getPrices().get(i);

                System.out.println("Checking neighbor: " + neighbor.getName() + ", existing cost: " + neighbor.getminCost() + ", new cost: " + newCost);

                // If new cost is less than current cost, update it.
                if (newCost < neighbor.getminCost()) {
                    System.out.println("Updating cost for student: " + neighbor.getName() + " from " + neighbor.getminCost() + " to " + newCost);
                    // Change the cost and update the position of the student in the heap.
                    minHeap.changeKey(neighbor, newCost);
                    // Update the predecessor of the neighbor.
                    neighbor.setPreviousStudent(current);
                }   
            }
        }

        // Return the minimum cost to the destination student.
        System.out.println("Final cost for student: " + dest.getName() + " is " + dest.getminCost());
        return dest.getminCost();
    }



    /**
     * findMinimumClassCost()
     *
     * @return the minimum total cost required to connect (span) each student in the class.
     * Assume the given graph is always connected.
     */
    public int findMinimumClassCost() {
        // TODO: implement this function
        return -1;
    }

    //returns edges and prices in a string.
    public String toString() {
        String o = "";
        for (Student v : students) {
            boolean first = true;
            o += "Student ";
            o += v.getName();
            o += " has neighbors ";
            ArrayList<Student> ngbr = v.getNeighbors();
            for (Student n : ngbr) {
                o += first ? n.getName() : ", " + n.getName();
                first = false;
            }
            first = true;
            o += " with prices ";
            ArrayList<Integer> wght = v.getPrices();
            for (Integer i : wght) {
                o += first ? i : ", " + i;
                first = false;
            }
            o += System.getProperty("line.separator");

        }

        return o;
    }
    public class HeapNode {
    Student student;
    int cost;

    public HeapNode(Student student, int cost) {
        this.student = student;
        this.cost = cost;
    }
    
}


///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public Heap getHeap() {
        return minHeap;
    }

    public ArrayList<Student> getAllstudents() {
        return students;
    }

    // used by Driver class to populate each Student with correct neighbors and corresponding prices
    public void setEdge(Student curr, Student neighbor, Integer price) {
        curr.setNeighborAndPrice(neighbor, price);
    }

    // used by Driver.java and sets students to reference an ArrayList of all Students
    public void setAllNodesArray(ArrayList<Student> x) {
        students = x;
    }
}
