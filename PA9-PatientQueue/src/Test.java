
public class Test {
    public static void main(String[] args) {
        PatientQueue test = new PatientQueue();

        Patient one = new Patient("Anat", 4);
        Patient two = new Patient("Ben", 9);
        Patient three = new Patient("Sasha", 8);
        Patient four = new Patient("Wu", 7);
        Patient five = new Patient("Rein", 6);
        Patient six = new Patient("Ford", 2);

        test.enqueue(one);
        test.enqueue(two);
        test.enqueue(three);
        test.enqueue(four);
        test.enqueue(five);
        test.enqueue(six);
        test.enqueue("Eve", 3);
        test.dequeue();
        test.changePriority("Eve", 10);
        System.out.println(test);
        
        test.enqueue("Kelly", 5);
        test.enqueue("Ross", 10);
        test.enqueue("Chris", 1);
        test.enqueue("Vince", 7);
        test.enqueue("Robert", 3);

        System.out.println(test);
        System.out.println(test.size());

        System.out.println(test.peek());
        System.out.println(test.peekPriority());

        test.dequeue();

        System.out.println(test);

        System.out.println(test.peek());
        System.out.println(test.peekPriority());

        test.clear();

        System.out.println(test);

        test.enqueue("Jack", 5);
        test.enqueue("Sarah", 3);
        test.enqueue("Lola", 8);
        test.enqueue(three);
        test.enqueue("Jack", 5);

        System.out.println(test);

        test.changePriority("Jack", 1);

        System.out.println(test);

        test.changePriority("Ahmad", 0);

        System.out.println(test);
//
//        test.enqueue(four);
//
//        System.out.println(test);
//
//        test.enqueue(five);
//
//        System.out.println(test);
//
//        test.enqueue(six);
//
//        System.out.println(test);
//
//        test.enqueue("Eve", 3);
//
//        System.out.println(test);
//
//        test.dequeue();
//
//        System.out.println(test);
//
//        test.dequeue();
//
//        System.out.println(test);
    }
    
    
//    while (pQueue[curr].priority < pQueue[pIndex(curr)].priority) {
//        if (pQueue[curr].priority == pQueue[pIndex(curr)].priority) {
//
//            String n1 = pQueue[curr].name;
//            String n2 = pQueue[pIndex(curr)].name;
//
//            int comp = n1.compareToIgnoreCase(n2);
//
//            if (comp < 0) {
//                swapPoints(curr, pIndex(curr));
//            }
//        }
//
//        swapPoints(curr, pIndex(curr));
//        curr = pIndex(curr);
//        if (curr == 1) {
//            break;
//        }
//    }
}
