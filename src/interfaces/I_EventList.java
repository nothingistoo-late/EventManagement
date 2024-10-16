/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

/**
 *
 * @author HP
 */

//CRUD - create, read, update, delete
public interface I_EventList {
    void addEvent();
    void checkExistEvent();
    void updateEvent();
    void deleteEvent();
    void searchEventByLocation();
    void saveToFile(String file);
    void printListFromFile(String file);
}
