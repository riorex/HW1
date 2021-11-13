package demo;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int storageSize;

    void clear() {
        IntStream.range(0, size()).forEach(i -> storage[i] = null);
        storageSize = 0;
    }

    void save(Resume r) {
        storage[size()] = r;
        storageSize++;
    }

    Resume get(String uuid) {
        final Resume[] resume = new Resume[1];
        IntStream.range(0, size() - 1).forEach(i -> {
            if (uuid.equals(storage[i].getUuid())) {
                resume[0] = storage[i];
            }
        });
        return resume[0];
    }

    void delete(String uuid) {
        IntStream.range(0, size() - 1).forEach(i -> {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = null;
                optimizeStorage(i);
                storageSize--;
            }
        });
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size());
    }

    int size() {
        return storageSize;
    }

    void optimizeStorage(int deletedIndex) {
        IntStream.range(deletedIndex, size() - 1).forEach(i -> {
            storage[i] = storage[i + 1];
            storage[i + 1] = null;
        });
    }
}
