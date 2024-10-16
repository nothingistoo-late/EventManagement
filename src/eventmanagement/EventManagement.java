/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventmanagement;

import controller.EventList;
import controller.Menu;
import interfaces.I_Menu;

/**
 *
 * @author HP
 */
public class EventManagement {

    private static final String EVENT_FILE = "event.dat";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        I_Menu menu = new Menu();
        menu.addItem("1. Create a new event");
        menu.addItem("2. Check if an event exists");
        menu.addItem("3. Search for event information by location");
        menu.addItem("4. Update event details");
        menu.addItem("5. Delete event");
        menu.addItem("6. Save events to a file");
        menu.addItem("7. Print the list of events from the file");
        menu.addItem("8. Quit");
        int choice;
        boolean cont = true;   
        EventList eventList = new EventList();
        eventList.readFile(EVENT_FILE);
        while (cont) {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    eventList.addEvent();
                    break;
                case 2:
                    eventList.checkExistEvent();
                    break;
                case 3:
                    eventList.searchEventByLocation();
                    break;
                case 4:
                    eventList.updateEvent();
                    break;
                case 5:
                    eventList.deleteEvent();
                    break;
                case 6:
                    eventList.saveToFile(EVENT_FILE);
                    break;
                case 7:
                    eventList.printListFromFile(EVENT_FILE);
                    break;
                case 8:
                    cont = menu.confirmYesNo("Do you want to quit?(Y/N)");
                    break;
            }
        }
    }
    
}
