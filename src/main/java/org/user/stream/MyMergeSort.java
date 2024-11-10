package org.user.stream;

import org.user.impl.MyArrayList;
import org.user.list.MyList;
import org.user.stream.functionalinterfaces.MyComparator;


public class MyMergeSort<T> {

    //divide, conquer, merge
    public MyList<T> sort(MyList<T> myList, MyComparator<T> comparator) {
        if(myList.size() <= 1){
            return myList;
        }
        MyList<T> firstPart = new MyArrayList<>();
        MyList<T> lastPart = new MyArrayList<>();

        int midPoint = myList.size() / 2;

        for (int i = 0; i < midPoint; i++) {
            firstPart.add(myList.get(i));
        }
        for (int i = midPoint; i < myList.size(); i++) {
            lastPart.add(myList.get(i));
        }
        return merge(sort(firstPart, comparator), sort(lastPart, comparator), comparator);
    }

    private MyList<T> merge(MyList<T> first, MyList<T> last, MyComparator<T> comparator) {
        MyList<T> sortedList = new MyArrayList<>();
        int left = 0;
        int right = 0;

        while (left < first.size() && right < last.size()) {
            if (comparator.compare(first.get(left), last.get(right)) <= 0) {
                sortedList.add(first.get(left));
                left++;
            } else {
                sortedList.add(last.get(right));
                right++;
            }
        }
        while (left < first.size()) {
            sortedList.add(first.get(left));
            left++;
        }
        while (right < last.size()) {
            sortedList.add(last.get(right));
            right++;
        }
        return sortedList;
    }
}
