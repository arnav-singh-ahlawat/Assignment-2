/**
 * @author Arnav Singh - 40258921
 * @author Sahar Hassanabadi -
 */

import java.util.*;
import java.io.*;

public class ListTester {
    private static final int[] TEST_SIZES = {10, 100, 1000, 10000, 100000};

    public static void main(String[] args) throws Exception {
        PrintWriter writer = new PrintWriter("testrun.txt");

        for (int n : TEST_SIZES) {
            long testStart = System.nanoTime();
            writer.printf("+-+-+-+-+-+-+ N = %d +-+-+-+-+-+-+%n%n", n);
            System.out.printf("Running test for N = %d...%n", n);

            // Initialize each list type
            MyArrayList<Integer> myArrayList = new MyArrayList<>();
            MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
            ArrayList<Integer> javaArrayList = new ArrayList<>();
            LinkedList<Integer> javaLinkedList = new LinkedList<>();

            // Run tests for each list type
            testAll("MyArrayList", myArrayList, n, writer);
            testAll("ArrayList", javaArrayList, n, writer);
            testAll("MyLinkedList", myLinkedList, n, writer);
            testAll("LinkedList", javaLinkedList, n, writer);

            double durationMs = (System.nanoTime() - testStart) / 1_000_000.0;
            System.out.printf("! Completed all tests for N = %d in %.2f ms%n%n", n, durationMs);
        }
        writer.close();
    }

    // Overloaded testAll Methods
    private static void testAll(String label, MyArrayList<Integer> list, int n, PrintWriter writer) {
        testInsertions(label, list, n, writer);
        testRemovals(label, list, n, writer);
    }

    private static void testAll(String label, MyLinkedList<Integer> list, int n, PrintWriter writer) {
        testInsertions(label, list, n, writer);
        testRemovals(label, list, n, writer);
    }

    private static void testAll(String label, List<Integer> list, int n, PrintWriter writer) {
        testInsertions(label, list, n, writer);
        testRemovals(label, list, n, writer);
    }

    // Insertion tests for each list type
    private static void testInsertions(String label, List<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();
        writer.println("********** " + label + " **********");

        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, rand.nextInt(2*n));
        writer.println("Insert @start: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2*n));
        writer.println("Insert @end: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int idx = list.size() == 0 ? 0 : rand.nextInt(list.size());
            list.add(idx, rand.nextInt(2*n));
        }
        writer.println("Insert @random: " + duration(start) + " ms");
    }

    private static void testInsertions(String label, MyArrayList<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();
        writer.println("********** " + label + " **********");

        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, rand.nextInt(2*n));
        writer.println("Insert @start: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2*n));
        writer.println("Insert @end: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int idx = list.size() == 0 ? 0 : rand.nextInt(list.size());
            list.add(idx, rand.nextInt(2*n));
        }
        writer.println("Insert @random: " + duration(start) + " ms");
    }

    private static void testInsertions(String label, MyLinkedList<Integer> list, int n, PrintWriter writer) {
        Random rand = new Random();
        writer.println("********** " + label + " **********");

        list.clear();
        long start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(0, rand.nextInt(2*n));
        writer.println("Insert @start: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2*n));
        writer.println("Insert @end: " + duration(start) + " ms");

        list.clear();
        start = System.nanoTime();
        for (int i = 0; i < n; i++) {
            int idx = list.size() == 0 ? 0 : rand.nextInt(list.size());
            list.add(idx, rand.nextInt(2*n));
        }
        writer.println("Insert @random: " + duration(start) + " ms");
    }

    // Removal tests for each list type
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
            for (int i = 0; i < n; i++) list.remove((Integer) rand.nextInt(2*n));
        }) + " ms\n");
    }

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
            for (int i = 0; i < n; i++) list.remove((Integer) rand.nextInt(2*n));
        }) + " ms\n");
    }

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
            for (int i = 0; i < n; i++) list.remove((Integer) rand.nextInt(2*n));
        }) + " ms\n");
    }

    // Utility methods for filling lists and timing operations
    private static void fill(List<Integer> list, int n, Random rand) {
        list.clear();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2*n));
    }

    private static void fill(MyArrayList<Integer> list, int n, Random rand) {
        list.clear();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2*n));
    }

    private static void fill(MyLinkedList<Integer> list, int n, Random rand) {
        list.clear();
        for (int i = 0; i < n; i++) list.add(rand.nextInt(2*n));
    }

    private static double duration(long start) {
        return (System.nanoTime() - start) / 1_000_000.0;
    }

    private static double timed(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return duration(start);
    }
}