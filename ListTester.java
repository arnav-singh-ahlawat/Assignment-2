/**
 * @author Arnav Singh - 40258921
 * @author Sahar Hassanabadi - 40242124
 */

import java.util.*;
import java.io.*;

public class ListTester {
    // Different list sizes to test
    private static final int[] TEST_SIZES = {10, 100, 1000, 10000, 100000};

    public static void main(String[] args) throws Exception {
        // Output goes to file
        PrintWriter writer = new PrintWriter("testrun.txt");

        // Iterates through each test size N and runs performance tests for all list implementations
        for (int n : TEST_SIZES) {
            long testStart = System.nanoTime();
            writer.printf("+-+-+-+-+-+-+ N = %d +-+-+-+-+-+-+%n%n", n);
            System.out.printf("Running test for N = %d...%n", n);

            // Create instances of each list type
            MyArrayList<Integer> myArrayList = new MyArrayList<>();
            MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
            ArrayList<Integer> javaArrayList = new ArrayList<>();
            LinkedList<Integer> javaLinkedList = new LinkedList<>();

            // Run tests
            testAll("MyArrayList", myArrayList, n, writer);
            testAll("ArrayList", javaArrayList, n, writer);
            testAll("MyLinkedList", myLinkedList, n, writer);
            testAll("LinkedList", javaLinkedList, n, writer);

            double durationMs = (System.nanoTime() - testStart) / 1_000_000.0;
            System.out.printf("! Completed all tests for N = %d in %.2f ms%n%n", n, durationMs);
        }

        writer.close(); // Save results
    }

    // Overloaded testAll for different list types

    // Creating an instance of the custom MyArrayList implementation
    private static void testAll(String label, MyArrayList<Integer> list, int n, PrintWriter writer) {
        testInsertions(label, list, n, writer);
        testRemovals(label, list, n, writer);
    }

    // Creating an instance of the custom MyLinkedList implementation
    private static void testAll(String label, MyLinkedList<Integer> list, int n, PrintWriter writer) {
        testInsertions(label, list, n, writer);
        testRemovals(label, list, n, writer);
    }

    // Creating instances of Java’s built-in ArrayList and LinkedList
    private static void testAll(String label, List<Integer> list, int n, PrintWriter writer) {
        testInsertions(label, list, n, writer);
        testRemovals(label, list, n, writer);
    }

    // INSERTION TESTS

    // Executes all tests for Java’s List interface (ArrayList or LinkedList)
    private static void testInsertions(String label, List<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();
        writer.println("********** " + label + " **********");

        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, rand.nextInt(2 * n)); // Insert N elements at the beginning of the list
        writer.println("Insert @start: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2 * n)); // Insert N elements at the end of the list
        writer.println("Insert @end: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int idx = list.size() == 0 ? 0 : rand.nextInt(list.size());
            list.add(idx, rand.nextInt(2 * n)); // Insert N elements at random positions in the list
        }
        writer.println("Insert @random: " + duration(start) + " ms");
    }

    // Executes all tests for the MyArrayList implementation
    private static void testInsertions(String label, MyArrayList<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();
        writer.println("********** " + label + " **********");

        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, rand.nextInt(2 * n));
        writer.println("Insert @start: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2 * n));
        writer.println("Insert @end: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int idx = list.size() == 0 ? 0 : rand.nextInt(list.size());
            list.add(idx, rand.nextInt(2 * n));
        }
        writer.println("Insert @random: " + duration(start) + " ms");
    }

    // Executes all tests for the MyLinkedList implementation
    private static void testInsertions(String label, MyLinkedList<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();
        writer.println("********** " + label + " **********");

        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, rand.nextInt(2 * n));
        writer.println("Insert @start: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2 * n));
        writer.println("Insert @end: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int idx = list.size() == 0 ? 0 : rand.nextInt(list.size());
            list.add(idx, rand.nextInt(2 * n));
        }
        writer.println("Insert @random: " + duration(start) + " ms");
    }

    // REMOVAL TESTS

    // Runs all removal tests for Java’s List interface (ArrayList or LinkedList)
    private static void testRemovals(String label, List<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();

        writer.println("Remove @start: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(0);
        }) + " ms");

        writer.println("Remove @end: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(list.size() - 1);
        }) + " ms");

        writer.println("Remove @random: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(rand.nextInt(list.size()));
        }) + " ms");

        writer.println("Remove byvalue: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove((Integer) rand.nextInt(2 * n));
        }) + " ms\n");
    }

    // Runs all removal tests for the MyArrayList implementation
    private static void testRemovals(String label, MyArrayList<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();

        writer.println("Remove @ Start: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(0);
        }) + " ms");

        writer.println("Remove @end: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(list.size() - 1);
        }) + " ms");

        writer.println("Remove @random: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(rand.nextInt(list.size()));
        }) + " ms");

        writer.println("Remove byvalue: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove((Integer) rand.nextInt(2 * n));
        }) + " ms\n");
    }

    // Runs all removal tests for the MyLinkedList implementation
    private static void testRemovals(String label, MyLinkedList<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();

        writer.println("Remove @start: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(0);
        }) + " ms");

        writer.println("Remove @end: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(list.size() - 1);
        }) + " ms");

        writer.println("Remove @random: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove(rand.nextInt(list.size()));
        }) + " ms");

        writer.println("Remove byvalue: " + timed(() -> {
            fill(list, n, rand);
            for (int i = 0; i < n; i++) list.remove((Integer) rand.nextInt(2 * n));
        }) + " ms\n");
    }

    // UTILITY METHODS

    // Fills Java’s List interface (ArrayList or LinkedList) with random values
    private static void fill(List<Integer> list, int n, Random rand) {
        list.clear();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2 * n));
    }

    // Fills MyArrayList with random values
    private static void fill(MyArrayList<Integer> list, int n, Random rand) {
        list.clear();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2 * n));
    }

    // Fills MyLinkedList with random values
    private static void fill(MyLinkedList<Integer> list, int n, Random rand) {
        list.clear();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2 * n));
    }

    // Returns duration from start to now in ms
    private static double duration(long start) {
        return (System.nanoTime() - start) / 1_000_000.0;
    }

    // Times a Runnable and returns duration in ms
    private static double timed(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return duration(start);
    }
}