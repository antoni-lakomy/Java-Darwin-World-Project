package agh.ics.oop.util;

import java.util.Arrays;

public class ByteComparator {

    public static boolean equals(Byte[] array1, Byte[] array2) {
        // Check for null arrays
        if (array1 == null || array2 == null) {
            return array1 == array2; // Both null or one is null
        }

        // Check for array length mismatch
        if (array1.length != array2.length) {
            return false;
        }

        // Compare element by element
        for (int i = 0; i < array1.length; i++) {
            if (!array1[i].equals(array2[i])) {
                return false;
            }
        }

        return true;
    }
}

