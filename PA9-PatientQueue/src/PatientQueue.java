import java.util.NoSuchElementException;

/*
 * Author: Chris Castillo
 * Class: CSC 210
 * Purpose: This program constructs a Priority Queue backed by an array list. It is noted
 *          that this priority queue follows standard binary min heap. Where smallest
 *          number is at the front of queue.
 * 
 * This program has several public methods for accessing the Priority Queue:
 * enqueue - adds a patient into the queue based on priority
 *     (NOTE: This method is overloaded for patient obj and string name/int priority)
 * dequeue - Removes and returns (the name) of the front most patient in the queue
 * peek - Return the name of the frontmost patient, but doesnt remove it from queue
 * peekPriority - returns the priority of frontmost patient but doesnt remove from queue
 * changePriority - changes the priority of an existing patient in queue.
 *     (NOTE: It either bubbles up or down depending on what priority is changed to)
 * isEmpty - Returns true if queue is empty, false otherwise
 * clear - removes all patients from the queue
 * toString - returns a string representation of the queue
 * 
 * EXAMPLE USAGE:
 * PatientQueue myQ = new PatientQueue();
 * myQ.enqueue("Abby", 5)
 * myQ.enqueue(PatientOne)
 * myQ.dequeue()
 * myQ.clear()
 * 
 * All commands above are supported by this program. It assumes all inputs for those
 * methods are of similar format as described above
 */
public class PatientQueue {
    // Declare private variables to keep track and access the Queue
    private int cap = 10;
    private int size;
    private Patient[] pQ;
    
    /*
     * A public constructor that initializes the pQ to have
     * size = 0; capacity of 10; and a new array
     */
    public PatientQueue() {
        size = 0;
        pQ = new Patient[cap];
    }
    
    /*
     * A method that adds a patient into the patientQueue based on their given priority
     * (NOTE: They will be bubbled up to keep appropriate heap order)
     */
    public void enqueue(Patient patient) {
        if (size + 1 >= cap) { doubleCap(); }
        if (size == 0) { size++; pQ[size] = patient; return; }
        
        size++;
        pQ[size] = patient;
        bubbleUp(size, pIndex(size));
    }
    
    /*
     * An overloaded method that adds a patient into the queue based on given priority
     * (NOTE: They will be bubbled up to keep appropriate heap order)
     */
    public void enqueue(String name, int priority) {
        Patient newPatient = new Patient(name, priority);
        this.enqueue(newPatient);
    }
    
    /*
     * A private helper method that moves the current patient position up the priority
     * queue if the priority is more immediate than its parent position. If patient
     * has a priority of 8 and its parent is 4, it "bubbles up" and swaps places with
     * the parent and it continues until its in the correct priority position in the Queue
     */
    private void bubbleUp(int pos, int parent) {
        // If it reaches the first position, it can't bubble up anymore
        if (pos == 1) { return; }
        // Loops through checking priority of child and parent
        if (pQ[pos].priority <= pQ[parent].priority) {
            if (pQ[pos].priority == pQ[parent].priority) {
                int comp = pQ[pos].name.compareTo(pQ[parent].name);
                if (comp < 0) { 
                    swapPatient(pos, parent); pos = parent;
                    bubbleUp(pos, pIndex(pos));
                }
            } else {
                swapPatient(pos, parent); pos = parent;
                bubbleUp(pos, pIndex(pos));
            }
        }
    }
    
    /*
     * A method that removes the patient frontmost in the queue.
     * (NOTE: It must then "bubble down" to get the correct order of patients and their
     * priorities) throws an exception if the queue is empty
     * 
     * Return Value: Returns the String name of the patient at the front of the
     * queue
     */
    public String dequeue() {
        if (size == 0) { throw new NoSuchElementException(); }
        
        String patientName = pQ[1].name;
        pQ[1] = pQ[size];
        size--;
        bubbleDown(1);
        return patientName;
    }
    
    /*
     * A private helper method that bubbles a patient down from the queue until
     * their correct priority position is reached in the queue.
     * (NOTE: If the current position priority is greater than their children position
     * which is pos*2 and (pos*2) + 1, you swap the position.
     */
    private void bubbleDown(int pos) {
        if (pos*2 > size) { return; }
        // Compares the left and right children to see which index to swap with
        int switchPos = compareLeftRight(pos);
        if (pQ[pos].priority > pQ[switchPos].priority) {
            swapPatient(pos, switchPos); pos = switchPos;
            bubbleDown(pos);
        } else if (pQ[pos].priority == pQ[switchPos].priority) {
            int comp = pQ[pos].name.compareTo(pQ[switchPos].name);
            if (comp > 0) {
                swapPatient(pos, switchPos); pos = switchPos;
                bubbleDown(pos);
            }
        } else {
            return;
        }
    }
    
    /*
     * A private method that compares two children node of a position and returns
     * the index that has a higher priority.
     * 
     * Return Value: the index of the children patient position that should be swapped
     */
    private int compareLeftRight(int pos) {
        int left = left(pos);
        int right = right(pos);
        // Checks if there only exists a left child node
        if (left <= size && right > size) { return left; }
        // If both children priority are equal, it compares names alphabetically
        if (pQ[left].priority == pQ[right].priority) {
            int comp = pQ[left].name.compareTo(pQ[right].name);
            if (comp <= 0) {
                return left;
            } else if (comp > 0) {
                return right;
            }
        }
        // If left priority is greater, returns right and vice versa
        if (pQ[left].priority > pQ[right].priority) {
            return right;
        } else {
            return left;
        }
    }
    
    /*
     * A method that changes a patients priority in the queue. Then it bubbles up or
     * down accordingly to the new priority. It is noted that any duplicate patients
     * only the first occurrence will be changed.
     */
    public void changePriority(String name, int newP) {
        for (int i = 1; i <= size; i++) {
            if (pQ[i].name.equals(name)) {
                int original = pQ[i].priority;
                pQ[i].priority = newP;
                if (original < newP) {
                    bubbleDown(i);
                    break;
                } else if (original > newP) {
                    bubbleUp(i, pIndex(i));
                    break;
                }
            }
        }
    }
    
    /*
     * A method that returns the name of the front patient in the queue.
     * (NOTE: Throws NoSuchElementException if queue is empty)
     * 
     * Return Value: Name of patient at front of queue
     */
    public String peek() {
        if (size == 0) { throw new NoSuchElementException(); }
        return pQ[1].name;
    }
    
    /*
     * A method that returns the priority of the front patient in the queue.
     * (NOTE: Throws No Element Exception if Queue is Empty)
     * 
     * Return Value: Priority of patient (frontmost in queue)
     */
    public int peekPriority() {
        if (this.isEmpty()) { throw new NoSuchElementException(); }
        return pQ[1].priority;
    }
    
    /*
     * A method that checks if there are any patients in the queue
     * 
     * Return Value: Return true if empty, false otherwise
     */
    public boolean isEmpty() { return (size == 0); }
    
    /*
     * A method that returns how many patients are in the queue
     * 
     * Return Value: int number of patients
     */
    public int size() { return size; }
    
    /*
     * A method that clears all the patients from the queue
     */
    public void clear() {
        size = 0; cap = 10;
        pQ = new Patient[cap];
    }
    
    /*
     * A private method that doubles the capacity of the array backing the queue
     */
    private void doubleCap() {
        cap *= 2;
        Patient[] newArr = new Patient[cap];
        for (int i = 1; i <= size; i++) {
            newArr[i] = pQ[i];
        }
        pQ = newArr;
    }
    
    /*
     * A private method that switches two patients in the queue
     */
    private void swapPatient(int firstPos, int secondPos) {
        Patient temp;
        temp = pQ[firstPos];
        pQ[firstPos] = pQ[secondPos];
        pQ[secondPos] = temp;
    }
    
    // Gets parent index from a given position in queue
    private int pIndex(int position) { return position / 2; }
    // Gets left child of parent position in queue
    private int left(int position) { return position * 2; }
    // Gets right child of parent position in queue
    private int right(int position) { return (position * 2) + 1; }
    
    /*
     * A method that constructs a string representation of the patient queue
     * 
     * Return Value: String representation of pQ
     */
    public String toString() {
        String retval = "";
        for (int i = 1; i <= size; i++) {
            if (i == size) {
                retval += pQ[i].name + " (" + pQ[i].priority + ")";
            } else {
                retval += pQ[i].name + " (" + pQ[i].priority + ")" + ", ";
            }
        }
        return "{" + retval + "}";
    }
}