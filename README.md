# PatientQueue
Java implementation of custom priority queue using binary min heap.

Queues are generally FIFO, but FIFO is not the best order to assist patients in a hospital, especially if some patients have more critical injuries. Each patient is given an integer priorit, where smaller is higher priority.

Once a doctor is ready to see a patient, the largest priority (smallest int) is seen first, regardless of the actual order they come in.

# Binary Heap
Priority queues are commons and its noted that they do NOT need to store its elements in sorted order. All that matters is the dequeue operation accesses the frontmost element.
The Priority Queue I am implementing sorts the array elements into a binary heap.

A binary heap is an array that obeys a heap ordering property.
Each index i is thought of as the ”parent” of the two ”child” indexes, i * 2 and i * 2 + 1. (To
simplify the index math, I left index 0 unused and starting the data at index 1.)

# Restraints
A coding exercise using ONLY arrays to implement, not extra java collections.

# Required methods
PatientQueue()
void enqueue(String name, int priority)
void enqueue(Patient patient)
String dequeue()
String peek()
int peekPriority()
void changePriority(String name, int newPriority)
boolean isEmpty()
int size()
void clear()
String toString()
