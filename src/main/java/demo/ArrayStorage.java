package demo;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[3];
    private int storageSize;


    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }


    void clear() {
        IntStream.range(0, storage.length).forEach(i -> {
            if (null != storage[i]) {
                storage[i] = null;
            }
        });
        setStorageSize(0);
    }

    void save(Resume r) {
        IntStream.range(0, storage.length).filter(i -> isNull(storage[i])).findFirst().ifPresent(i -> {
            storage[i] = r;
            storageSize++;
        });
    }


    Resume get(String uuid) {
        final Resume[] resume = new Resume[1];
        IntStream.range(0, getStorageSize()).forEach(i -> {
            if (uuid.equals(storage[i].getUuid())) {
                resume[0] = storage[i];
            }
        });
        return resume[0];
    }

    void delete(String uuid) {
        IntStream.range(0, getStorageSize()).forEach(i -> {
            if (null != storage[i] && uuid.equals(storage[i].getUuid())) {
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
        return getStorageSize();
    }

    void optimizeStorage(int deletedIndex) {
        IntStream.range(deletedIndex, getStorageSize() - 1).forEach(i -> {
            storage[i] = storage[i + 1];
            storage[i + 1] = null;
        });
    }
}
