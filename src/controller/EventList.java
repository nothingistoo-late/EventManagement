/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import interfaces.I_EventList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import models.Event;
import utils.Util;

/**
 *
 * @author HP
 */
public class EventList extends ArrayList<Event> implements I_EventList {

    @Override
    public void addEvent() {

        int cmd;
        do {
            System.out.println("\n====================================");
            System.out.println("\t  ADD NEW EVENT");

            // Generate unique event ID
            String eventId;
            do {
                eventId = generateId(1000, "EV");
            } while (checkDuplicateId(eventId));

            // Collect event details
            String name = Util.getStringByFormat("- Enter Event Name: ", "Please do not leave it blank.", "^[^\\s]{5,}$");
            LocalDate date = Util.getDate("- Enter Date(YYYY-MM-DD): ", "Please do not leave it blank.", "yyyy-MM-dd");
            String location = Util.getString("- Enter Location: ", "Please do not leave it blank.");
            int attendees = Util.getInteger("- Number Of Attendees:", "Please input a valid number", 0, Integer.MAX_VALUE);
            boolean status = Util.getBoolean("Is this event has open ?: ", "Please input available/not available", "Available", "Not Available");

            Event event = new Event(eventId, name, date, location, attendees, status, true);
            this.add(event);

            // Prompt user to continue adding tours
            cmd = Util.getInteger("<?> Do you want to continue importing a new Event? (0. NO / 1. YES): ", "Invalid selection!", 0, 1);

        } while (cmd != 0);
    }

    @Override
    public void updateEvent() {

        if (!this.isEmpty() && checkDelete() == true) {
            System.out.println("List Events:");
            displayEvents(this);

            // Update tour information
            String eventIdToUpdate = Util.getStringByFormat("Enter the ID of the tour to update information:", "Enter the right format EVXXX", "^EV\\d{3}$");
            Event eventToUpdate = findEventById(eventIdToUpdate);
            if (eventToUpdate != null) {
                System.out.println("Event Details: ");
                eventToUpdate.setName(Util.getString("Input the name: ", "Input Again!!", eventToUpdate.getName()));
                eventToUpdate.setDate(Util.getDate("Input date: ", "Input Again!!", "yyyy-MM-dd", eventToUpdate.getDate()));
                eventToUpdate.setLocation(Util.getString("Input the Location: ", "Input Again", eventToUpdate.getLocation()));
                eventToUpdate.setAttendees(Util.getInteger("Input number of attendees", "Input Again", 0, Integer.MAX_VALUE, eventToUpdate.getAttendees()));
                eventToUpdate.setStatus(Util.getBoolean("Change the status event to? :", "Input Again", "Available", "Not available", eventToUpdate.isStatus()));
                System.out.println("\nEVENT " + eventToUpdate.getEventId() + " AFTER UPDATE: ");
                System.out.println("|   ID   |           NAME            |     DATE    |   LOCATION   | ATTENDEES |   STATUS   |");
                System.out.println("|--------|---------------------------|-------------|--------------|-----------|------------|");
                System.out.printf("| %-7s| %-26s| %-12s| %-13s| %-10d| %-11s|\n",
                        eventToUpdate.getEventId(), eventToUpdate.getName(), eventToUpdate.getDate(), eventToUpdate.getLocation(),
                        eventToUpdate.getAttendees(), eventToUpdate.isStatus() ? "Available" : "Not Available");

                System.out.println("Update information successfully !");
            } else {
                System.out.println("Event with ID " + eventIdToUpdate + " not found.");
            }
        } else {
            System.err.println("List is Empty!!");
        }
    }

    private Event findEventById(String eventId) {
        for (Event event : this) {
            if (event.getEventId().equalsIgnoreCase(eventId)) {
                return event;
            }
        }
        return null; //  not found
    }

    private boolean checkDelete() {
        for (Event event : this) {
            if (event.isIsDelete() == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void deleteEvent() {
        if (!this.isEmpty() && checkDelete() == true) {
            System.out.println("List Events:");
            displayEvents(this);

            // Update tour information
            String eventIdToUpdate = Util.getStringByFormat("Enter the ID of the tour to update information:", "Enter the right format EVXXX", "^EV\\d{3}$");
            Event eventToUpdate = findEventById(eventIdToUpdate);
            if (eventToUpdate != null) {
                eventToUpdate.setIsDelete(false); //xóa rồi thì set lại isDelete == false: đã xóa
                System.out.println("Event delete successfully !!!");
            } else {
                System.out.println("Event with ID " + eventIdToUpdate + " not found.");
            }
        }else {
            System.err.println("List is Empty!!");
        }
    }

    @Override
    public void searchEventByLocation() {
        String keyword = Util.getString("Input search keyword Location: ", "Please input again !!");
        List<Event> results = new ArrayList<>();
        for (Event event : this) {
            if (event.getLocation().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(event);
            }
        }
        displayEvents(results);
    }

    @Override
    public void checkExistEvent() {
        String eventIdToCheck = Util.getStringByFormat("Enter the ID of the tour to update information:", "Enter the right format EVXXX", "^EV\\d{3}$");
        Event evenToCheck = findEventById(eventIdToCheck);
        if (evenToCheck != null && checkDelete() == true) {
            System.out.println("Event with ID " + eventIdToCheck + " Existed.");
        } else {
            System.out.println("Event with ID " + eventIdToCheck + " not found.");
        }
    }

    @Override
    public void saveToFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
            System.out.println("Events data saved successfully to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving events data to file: " + fileName);
        }
    }

    public void readFile(String file) {
        try (FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis)) {
            List<Event> events = (List<Event>) ois.readObject();
            this.clear();
            this.addAll(events);
            System.out.println("Events data loaded successfully from file: " + file);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading events data from file: " + file);
        }
    }

    @Override
    public void printListFromFile(String file) {
        readFile(file);
        displayEvents(this);
    }

    private void displayEvents(List<Event> eventList) {
        if(checkDelete() == true){
        System.out.println("\nLIST EVENTS:");
        System.out.println("|   ID   |           NAME            |     DATE    |   LOCATION   | ATTENDEES |   STATUS   |");
        System.out.println("|--------|---------------------------|-------------|--------------|-----------|------------|");
        for (Event event : eventList) {
            if (event.isIsDelete() == true) {
                System.out.printf("| %-7s| %-26s| %-12s| %-13s| %-10d| %-11s|\n",
                        event.getEventId(), event.getName(), event.getDate(), event.getLocation(),
                        event.getAttendees(), event.isStatus() ? "Available" : "Not Available");
            }
        }
        } else{
            System.err.println("List is empty!!");
        }
    }

    private boolean checkDuplicateId(String id) {
        for (Event event : this) {
            if (event.getEventId().equalsIgnoreCase(id)) {
                return true; // Return true immediately when a duplicate is found
            }
        }
        return false; // Return false if no duplicate is found
    }
    //AUTO GENERATE EVENT ID:

    private String generateId(int bound, String idCodeFormat) {
        int idNumber;

        Random random = new Random();
        idNumber = random.nextInt(bound);

        if (idNumber < 10) {
            return idCodeFormat + "00" + idNumber;
        }
        if (idNumber < 100) {
            return idCodeFormat + "0" + idNumber;
        }
        return idCodeFormat + idNumber;
    }
}
