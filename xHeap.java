package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class xHeap<T> {
	private List<T> heap;
	private Comparator<T> comparator;

	public xHeap(Comparator<T> comparator) {
		this.heap = new ArrayList<>();
		this.comparator = comparator;
	}

	public void add(T element) {
		heap.add(element);
		heapifyUp(heap.size() - 1);
	}

	public T poll() {
		if (isEmpty()) {
			return null;
		}

		T root = heap.get(0);
		T lastElement = heap.remove(heap.size() - 1);

		if (!isEmpty()) {
			heap.set(0, lastElement);
			heapifyDown(0);
		}

		return root;
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	private void heapifyUp(int index) {
		while (index > 0) {
			int parentIndex = (index - 1) / 2;

			if (comparator.compare(heap.get(index), heap.get(parentIndex)) >= 0) {
				break;
			}

			swap(index, parentIndex);
			index = parentIndex;
		}
	}

	private void heapifyDown(int index) {
		int leftChildIndex;
		int rightChildIndex;
		int smallest;

		while (true) {
			leftChildIndex = 2 * index + 1;
			rightChildIndex = 2 * index + 2;
			smallest = index;

			if (leftChildIndex < heap.size() && comparator.compare(heap.get(leftChildIndex), heap.get(smallest)) < 0) {
				smallest = leftChildIndex;
			}

			if (rightChildIndex < heap.size()
					&& comparator.compare(heap.get(rightChildIndex), heap.get(smallest)) < 0) {
				smallest = rightChildIndex;
			}

			if (smallest == index) {
				break;
			}

			swap(index, smallest);
			index = smallest;
		}
	}

	private void swap(int i, int j) {
		T temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}
}
