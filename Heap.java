/*
 * Name: <your name>
 * EID: <your EID>
 */

// Implement your heap here
// Methods may be added to this file, but don't remove anything
// Include this file in your final submission

import java.util.ArrayList;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Heap {
    private ArrayList<Student> minHeap;
    private HashSet<Student> heapElements;  // Keeps track of students in the heap.

    public Heap() {
        minHeap = new ArrayList<Student>();
        heapElements = new HashSet<>();  // Initialize the set in the constructor.
    }

    public void buildHeap(ArrayList<Student> students) {
        minHeap = new ArrayList<Student>(students);
        heapElements.addAll(students);  // Add all students to the set.
        
        for (int i = minHeap.size() / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }    
    }

    public void insertNode(Student in) {
        minHeap.add(in);
        heapElements.add(in);  // Add the student to the set.
        siftUp(minHeap.size() - 1);
    }

    public Student findMin() {
        if (minHeap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty.");
        }
        return minHeap.get(0);
    }

    public Student extractMin() {
        if (minHeap.isEmpty()) {
            return null;
        }

        Student min = minHeap.get(0);
        int lastElementIndex = minHeap.size() - 1;
        minHeap.set(0, minHeap.get(lastElementIndex));
        minHeap.remove(lastElementIndex);
        heapElements.remove(min);  // Remove the student from the set.

        siftDown(0);
        return min;
    }

    /**
     * delete(int index)
     * Deletes an element in the min-heap given an index to delete at.
     * Time Complexity - O(log(n))
     *
     * @param index - the index of the item to be deleted in the min-heap.
     */
    public void delete(int index) {
        if (index < 0 || index >= minHeap.size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        // Swap the target element with the last element in the heap.
        int lastIndex = minHeap.size() - 1;
        swap(index, lastIndex);

        // Remove the last element (which is now the target element).
        minHeap.remove(lastIndex);

        // Sift down the swapped element to its proper position.
        if (!minHeap.isEmpty() && index < minHeap.size()) {
            siftDown(index);
        }
    }

    /**
     * changeKey(Student r, int newCost)
     * Changes minCost of Student s to newCost and updates the heap.
     * Time Complexity - O(log(n))
     *
     * @param r       - the Student in the heap that needs to be updated.
     * @param newCost - the new cost of Student r in the heap (note that the heap is keyed on the values of minCost)
     */
    public void changeKey(Student r, int newCost) {
        // Find the index of the Student in the heap.
        int index = minHeap.indexOf(r);

        if (index == -1) {
        throw new IllegalArgumentException("Student not found in heap.");
    }

        if (index != -1) {
            int oldCost = r.getminCost();
            r.setminCost(newCost);

            // If the new cost is less than the old cost, sift up.
            // If the new cost is more than the old cost, sift down.
            if (newCost < oldCost) {
                siftUp(index);
            } else if (newCost > oldCost) {
                siftDown(index);
            }
        }
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < minHeap.size(); i++) {
            output += minHeap.get(i).getName() + " ";
        }
        return output;
    }

    //HELPER METHODS:

    /**
     * Helper function to heapify (or sift down) a node.
     */
    private void siftDown(int i) {
        int leftChildIndex = 2 * i + 1;
        int rightChildIndex = 2 * i + 2;
        int smallest = i;

        // Check if left child is smaller than the current node.
        if (leftChildIndex < minHeap.size() && minHeap.get(leftChildIndex).getminCost() < minHeap.get(smallest).getminCost()) {
            smallest = leftChildIndex;
        }

        // Check if right child is smaller than the current node.
        if (rightChildIndex < minHeap.size() && minHeap.get(rightChildIndex).getminCost() < minHeap.get(smallest).getminCost()) {
            smallest = rightChildIndex;
        }

        // If the smallest is not the current node, swap them and continue sifting down.
        if (smallest != i) {
            swap(i, smallest);
            siftDown(smallest);
        }
    }

    /**
     * Helper function to heapify (or sift up) a node.
     */
    private void siftUp(int i) {
        // If we are at the root node, return.
        if (i == 0) {
            return;
        }

        int parentIndex = (i - 1) / 2;

        // If the parent node is larger than the current node, swap them and continue sifting up.
        if (minHeap.get(parentIndex).getminCost() > minHeap.get(i).getminCost()) {
            swap(i, parentIndex);
            siftUp(parentIndex);
        }
    }

    /**
     * Helper function to swap two nodes in the heap.
     */
    private void swap(int i, int j) {
        // Swap elements at indices i and j in the heap.
        Student temp = minHeap.get(i);
        minHeap.set(i, minHeap.get(j));
        minHeap.set(j, temp);
    }
    public boolean isEmpty() {
        return minHeap.isEmpty();
    }

    // Checks if a student is in the heap.
    public boolean contains(Student student) {
        return heapElements.contains(student);
    }

///////////////////////////////////////////////////////////////////////////////
//                           DANGER ZONE                                     //
//                everything below is used for grading                       //
//                      please do not change :)                              //
///////////////////////////////////////////////////////////////////////////////

    public ArrayList<Student> toArrayList() {
        return minHeap;
    }
}
