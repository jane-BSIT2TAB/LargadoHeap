import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int size;
    private int capacity;

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
        this.size = 0;
    }

    public void insert(int value) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }
        heap[size] = value;
        size++;
        moveUp(size - 1);
    }

    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        moveDown(0);
        return max;
    }

    private void moveUp(int index) {
        int parent = (index - 1) / 2;
        while (index > 0 && heap[index] > heap[parent]) {
            switchPlaces(index, parent);
            index = parent;
            parent = (index - 1) / 2;
        }
    }

    private void moveDown(int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }
        if (largest != index) {
            switchPlaces(index, largest);
            moveDown(largest);
        }
    }

    private void switchPlaces(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(10);
        int[] arr = {4, 10, 3, 5, 1, 8, 2, 7, 6, 9};
        for (int i : arr) {
            heap.insert(i);
        }
        System.out.println("Max Heap: " + Arrays.toString(heap.heap));
        System.out.println("Extracted Max: " + heap.extractMax());
        System.out.println("Max Heap After Extraction: " + Arrays.toString(heap.heap));
    }
}