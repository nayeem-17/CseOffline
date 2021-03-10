package level2_term1.DSA.offline01;

import java.util.Arrays;

class Array {
    private int len;
    private String[] array;

    // Constructors
    public Array() {
        array = null;
        len = 0;
    }

    public Array(int n) {
        len = n;
        array = new String[n];
    }

    public Array(Array arr) {
        this.setLen(arr.length());
        this.array = new String[arr.len];
        System.arraycopy(arr.getArray(), 0, this.array, 0, arr.length());
    }

    // Methods
    public String[] getArray() {
        return array;
    }

    public String getAnElement(int i) {
        return this.array[i];
    }

    public void add(String element) {
        if (this.len == 0) {
            this.len = 1;
            this.array = new String[] { element };
        } else {
            String[] newArr = new String[this.len + 1];
            System.arraycopy(this.array, 0, newArr, 0, this.len);
            newArr[this.len] = element;
            this.array = newArr;
            this.len += 1;
        }

    }

    public void add(int i, String element) {
        String[] newArr = new String[this.len + 1];
        System.arraycopy(this.array, 0, newArr, 0, i);
        newArr[i] = element;
        for (int m = i; m < this.len; m++) {
            newArr[m + 1] = this.array[m];
        }
        this.array = newArr;
        this.len += 1;
    }

    public void remove(String element) {
        if (this.isEmpty()) {
            System.out.println(" The array is empty");
        } else if (this.len == 1) {
            this.len = 0;
            this.array = null;
        } else {
            for (int i = 0; i < this.len; i++) {
                if (this.array[i].compareTo(element) == 0) {
                    this.setLen(this.len - 1);
                    for (int j = i; j < this.len; j++) {
                        this.array[j] = this.array[j + 1];
                    }
                    String[] temp = new String[this.len];
                    System.arraycopy(this.array, 0, temp, 0, this.len);
                    this.array = temp;
                }
            }
        }
    }

    public int[] findIndex(String element) {
        int count = 0;
        int[] temp = new int[this.len];
        for (int i = 0; i < this.len; i++) {
            if (this.array[i].compareTo(element) == 0) {
                temp[count] = i;
                count++;
            }
        }
        int[] found = new int[count];
        System.arraycopy(temp, 0, found, 0, count);
        return found;

    }

    public Array subArray(int start, int end) {
        int size = end - start;
        Array subArr = new Array(size);
        subArr.setLen(size);
        System.arraycopy(this.array, start, subArr.array, 0, size);
        return subArr;
    }

    public Array merge(Array a1, Array a2) {
        int totoalLength = a1.length() + a2.length();
        Array mergedArray = new Array(totoalLength);
        int i = 0, j = 0, k = 0;
        while (true) {
            if (i < a1.len && j < a2.len) {
                if (a1.array[i].compareTo(a2.array[j]) < 0) {
                    mergedArray.array[k] = a1.array[i];
                    i++;
                    k++;
                } else {
                    mergedArray.array[k] = a2.array[j];
                    k++;
                    j++;
                }
            } else if (i == a1.len) {
                if (j < a2.len) {
                    for (int m = j; m < a2.len; m++) {
                        mergedArray.array[k] = a2.array[m];
                        k++;
                    }
                    break;
                } else {
                    break;
                }
            } else if (j == a2.len) {
                if (i < a1.len) {
                    for (int m = i; m < a1.len; m++) {
                        mergedArray.array[k] = a1.array[m];
                        k++;
                    }
                    break;
                } else {
                    break;
                }
            }
        }
        return mergedArray;
    }

    public int length() {
        return len;
    }

    public void setLen(int n) {
        this.len = n;
    }

    public boolean isEmpty() {
        if (len == 0)
            return true;
        return false;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public static void main(String[] args) throws Exception {
        Array array1 = new Array(5);
        System.out.println("array one is " + array1);

        Array array2 = new Array();
        System.out.println("Array two is " + array2 + " . is Empty? " + array2.isEmpty());

        array2.add("a");
        array2.add("b");
        array2.add("b");
        array2.add("c");
        System.out.println("Array two is " + array2);

        Array array3 = new Array(array2);
        System.out.println("Array three is " + array3);

        array3.add(1, "d");
        System.out.println("Array three is " + array3);

        array3.remove("d");
        System.out.println("Array three is " + array3);

        int[] indexes = array2.findIndex("b");
        System.out.println("Indexes are " + Arrays.toString(indexes));

        Array mergedArray = array2.merge(array3, array2);
        System.out.println("Merged array is " + mergedArray);

        Array subArray = mergedArray.subArray(1, 4);
        System.out.println("Sub array is " + subArray);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "Array [array=" + Arrays.toString(array) + ", len=" + len + "]";
    }
}