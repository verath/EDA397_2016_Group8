package se.chalmers.eda397.group8.pairprogramming.data;

import java.util.List;

/**
 * Common interface for data sources.
 */
public interface DataSource<T> {

    /**
     * Gives the element with the specified ID.
     *
     * @param id the of the element to return
     * @return the element, or <code>null</code> if none was found
     */
    T get(String id);

    /**
     * Saves an element. If an element with the same ID already exist then the specified element
     * will overwrite the current element. Otherwise, it simply adds the element to the data source.
     *
     * @param item the item to add
     * @return whether the operation was successful
     */
    boolean save(T item);

    /**
     * Deletes the element with the specified ID from the data source.
     *
     * @param id the ID of the element to remove
     * @return <code>true</code> if the element was removed, <code>false</code> if the element was
     * not found in the data source
     */
    T delete(String id);

    /**
     * Gives all elements in the data source.
     *
     * @return all elements
     */
    List<T> getAll();
}
