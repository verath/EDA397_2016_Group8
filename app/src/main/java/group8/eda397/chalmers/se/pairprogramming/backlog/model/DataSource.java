package group8.eda397.chalmers.se.pairprogramming.backlog.model;

import java.util.List;

/**
 * Created by m_cal on 2016-04-21.
 */
public interface DataSource<T> {

    T get(String id);

    boolean save(T item);

    boolean delete(String id);

    List<T> getAll();
}
