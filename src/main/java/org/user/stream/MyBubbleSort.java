package org.user.stream;

import org.user.impl.MyArrayList;
import org.user.stream.functionalinterfaces.MyComparator;

public class MyBubbleSort<T> {

    public MyArrayList<T> sort(MyArrayList<T> list, MyComparator<T> comparator) {
        int length = list.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T element = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, element);
                }
            }
        }
        return list;
    }
}
