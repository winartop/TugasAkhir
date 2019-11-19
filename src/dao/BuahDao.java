/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import entity.Buah;
import java.util.List;

/**
 *
 * @author arya
 */
public interface BuahDao {
    public void save(Buah buah);
    public void update(Buah buah);
    public void delete(Buah buah);
    public List<Buah> getAll();
}
