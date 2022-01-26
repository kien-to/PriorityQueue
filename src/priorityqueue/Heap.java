package priorityqueue;

import java.util.Comparator;

import app.Patient;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;

  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      //TODO: Implement this method.
      this.comparator = comparator;
      this.isMaxHeap = isMaxHeap;
      this.heap = (T[]) new Patient[INIT_SIZE];
      this.numElements = 0;
  }

public boolean verifyMinHeap(T[] heap) {
    if (isMinHeap(0, heap) > 0) return false;
    return true;
 }

 private int isMinHeap(int index, T[] heap){
    int childIndexLeft = 2*index+1;
    int childIndexRight = 2*index+2;
    if (childIndexLeft >= numElements) return 0;
    if (compare(heap[childIndexLeft],heap[index]) > 0 || compare(heap[childIndexRight],heap[index]) > 0) {
      return 1;
    }
    if (compare(heap[childIndexLeft],heap[index]) > 0 && compare(heap[childIndexRight],heap[index]) <= 0) {
      return 0;
    }
    return isMinHeap(childIndexLeft, heap) + isMinHeap(childIndexRight, heap);
   }
  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   */
  public void bubbleUp(int index) {
      //TODO: Implement this method.
      int parentIndex = (index - 1)/2;
      if (compare(heap[index],heap[parentIndex]) <= 0) {
        return;
      }
      else{
        swap(index, parentIndex);
        bubbleUp(parentIndex);
      }
  }

  private void swap(int i1, int i2){
    T temp = heap[i2];
    heap[i2] = heap[i1];
    heap[i1] = temp;
  }

  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   */
  public void bubbleDown(int index) {
      //TODO: Implement this method.
      int childIndex = 2*index + 1;

      if(childIndex >= numElements){
        return;
      }

      T maxValue = heap[index];
      int maxIndex = -1;
      for (int i=0; i < 2 && i + childIndex < numElements; i++) {
        if (compare(heap[i + childIndex],maxValue) > 0) {
          maxValue = heap[i + childIndex];
          maxIndex = i + childIndex;
        }
      }

      if (compare(heap[index],maxValue) == 0) return;
      else {
        swap (index, maxIndex);
        bubbleDown(maxIndex);
      }
  }

  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = (numElements == 0);
      //TODO: Implement this method.
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int size(){
    int size = numElements;
      //TODO: Implement this method.
        return size;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a variable, compareSign, 
   * beased on the state of the boolean variable isMaxHeap. It then calls the compare method from the 
   * comparator object and multiplies its output by compareSign.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * negative int otherwise (if isMaxHeap),
   * return negative int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * positive int otherwise (if ! isMinHeap).
   */
  public int compare(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
    if (isEmpty()) {
      throw new QueueUnderflowException();
    }
     T data = heap[0];
      //TODO: Implement this method.
    return data;
  }  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeue() throws QueueUnderflowException{
    if (isEmpty()) {
      throw new QueueUnderflowException();
    }
    T data = heap[0];
      //TODO: Implement this method.
    for (int i = 0; i < numElements - 1; i++) {
      heap[i] = heap[i+1];
    }
    numElements--;
    bubbleDown(0);
    return data;
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueue(T newElement) {
      //TODO: Implement this method.
      expandCapacity();
      if(numElements==0) 
      {
        heap[0] = newElement;
      }
      else{
        int i = numElements-1;
        while(i>=0 && compare(newElement, heap[i]) > 0){
          heap[i+1] = heap[i];
          i--;
        }
        heap[i+1] = newElement;
      }
    numElements++;
  }

  private void expandCapacity(){
    T[] newHeap = (T[]) new Patient[numElements+1];
    for(int i=0; i< numElements;i++){
      newHeap[i]=heap[i];
  }
  heap = newHeap;
  }

  рubliс stаtiс vоid mаin(String[] аrgs)
    {
    int[] А = {1, 2, 3, 4, 5, 6};
      
    // stаrt with index 0 (the rооt оf the heар)
    int index = 0;
      
    if (verifyMinHeар(А, index)) {
    System.оut.рrintln("The given аrrаy is а min-heар");
    }
    else {
    System.оut.рrintln("The given аrrаy is nоt а min-heар");
    }
        }
    }
//   public boolean verifyMeanHeap(Heap<Patient> heap) {
//     if (isMinHeap(0, heap) > 0) return false;
//     return true;
//  }

//  private int isMinHeap(int index, Heap<Patient> heap){
//     int childIndexLeft = 2*index+1;
//     int childIndexRight = 2*index+2;
//     if (childIndexLeft >= heap.size()) return 0;
//     if (compare(heap[childIndexLeft],heap[index]) > 0 || compare(heap[childIndexRight],heap[index]) > 0) {
//       return 1;
//     }
//     if (compare(heap[childIndexLeft],heap[index]) > 0 && compare(heap[childIndexRight],heap[index]) <= 0) {
//       return 0;
//     }
//     return isMinHeap(childIndexLeft, heap) + isMinHeap(childIndexRight, heap);
//    }
}