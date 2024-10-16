/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class Util {
    private static Scanner sc = new Scanner(System.in);

        public static int getInteger(String inputMess, String errorMess, int lowest, int highest){
        int number;
        while (true) {
            try {
                System.out.print(inputMess);
                
                number = Integer.parseInt(sc.nextLine());

                if ((number >= lowest) && (number <= highest)) {
                    return number;
                } else {
                    System.err.println("<!> " + errorMess);
                }
            } catch (Exception e) {
                System.err.println("<!> " + errorMess);
            }
        }
    }
    
    public static int getInteger(String inputMess, String errorMess, int lowest, int highest, int oldValue) {
        int number;
        while (true) {
            try {
                System.out.print(inputMess + " (press Enter to keep '" + oldValue + "'): ");
                String input = sc.nextLine().trim();

               
                    number = Integer.parseInt(input);
                    if (number >= lowest && number <= highest) {
                        return number;
                    } else {
                        System.err.println("<!> " + errorMess);
                    }
                    System.out.println("Using old value: " + oldValue);
                    return oldValue;
            } catch (NumberFormatException e) {
                System.err.println("<!> " + errorMess);
            }
        }
    }

    public static double getDouble(String inputMess, String errorMess, double lowest, double highest) {
        double number;
        while (true) {
            try {
                System.out.print("" + inputMess);
                number = Double.parseDouble(sc.nextLine());

                if ((number >= lowest) && (number <= highest)) {
                    return number;
                } else {
                    System.err.println("<!> " + errorMess);
                }
            } catch (Exception e) {
                System.err.println("<!> " + errorMess);
            }
        }
    }
    public static double getDouble(String inputMess, String errorMess, double lowest, double highest, double oldValue) {
        double number;
        while (true) {
            try {
                System.out.print("" + inputMess + " (press Enter to keep '" + oldValue + "'): ");
                String input = sc.nextLine().trim();

                if (!input.isEmpty()) {
                    number = Double.parseDouble(input);
                    if (number >= lowest && number <= highest) {
                        return number;
                    } else {
                        System.err.println("<!> " + errorMess);
                    }
                } else {
                    System.out.println("Using old value: " + oldValue);
                    return oldValue;
                }
            } catch (NumberFormatException e) {
                System.err.println("<!> " + e);
            }
        }
    }

    public static String getString(String inputMess, String errorMess) {
        String input;
        while (true) {
                System.out.print(inputMess);
                input = sc.nextLine();
                input.trim();

                if ((input.length() > 0) && (!input.isEmpty())) {
                    return input;
                } else {
                    System.err.println("<!> " + errorMess);
                }
        }
    }
    
    public static String getString(String inputMess, String errorMess, String oldValue) {
        String input;
        while (true) {
            try {
                System.out.print("" + inputMess + " (press Enter to keep '" + oldValue + "'): ");
                input = sc.nextLine();
                input = input.trim();

                if (!input.isEmpty()) {
                    return input;
                } else {
                    System.out.println("Using old value: " + oldValue);
                    return oldValue;
                }
            } catch (Exception e) {
                System.err.println("<!> " + errorMess);
            }
        }
    }
    public static boolean getBoolean(String inputMess, String errorMess, String available, String notAvailable) {
        String input;
        while (true) {
                System.out.print("" + inputMess);
                input = sc.nextLine().trim();

                if (!input.isEmpty()) {
                    if (input.equalsIgnoreCase(available)) {
                        return true;
                    } else if (input.equalsIgnoreCase(notAvailable)) {
                        return false;
                } else {
                    System.err.println("<!> " + errorMess);
                }
        }
    }
    }
     public static boolean getBoolean(String inputMess, String errorMess, String available, String notAvailable, boolean oldValue) {
        while (true) {
            try {
                System.out.print("" + inputMess + " (press Enter to keep '" + oldValue + "'): ");
                String input = sc.nextLine().trim();

                if (!input.isEmpty()) {
                    if (input.equalsIgnoreCase(available) || input.equalsIgnoreCase(notAvailable)) {
                        return Boolean.parseBoolean(input);
                    } else {
                        System.err.println("<!> " + errorMess);
                    }
                } else {
                    System.out.println("Using old value: " + oldValue);
                    return oldValue;
                }
            } catch (NumberFormatException e) {
                System.err.println("<!> " + e);
            }
        }
    }   
     
      public static LocalDate getDate(String inputMess, String errorMess, String format) {
        LocalDate date;
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        
        while (true) {
            try {
                System.out.print(inputMess);
                String input = sc.nextLine();
                date = LocalDate.parse(input, formatter);
                return date;
            } catch (DateTimeParseException e) {
                System.err.println("<!> " + e);
            }
        }
    }
      public static LocalDate getDate(String inputMess, String errorMess, String format, LocalDate oldValue) {
        LocalDate date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        
        while (true) {
            try {
                System.out.print(inputMess + " (press Enter to keep '" + oldValue.format(formatter) + "'): ");
                String input = sc.nextLine().trim();

                if (!input.isEmpty()) {
                    date = LocalDate.parse(input, formatter);
                    return date;
                } else {
                    System.out.println("Using old value: " + oldValue.format(formatter));
                    return oldValue;
                }
            } catch (DateTimeParseException e) {
                System.err.println("<!> " + errorMess);
            }
        }
    }

    public static String getStringByFormat(String inputMess, String errorMess, String format) {
        String inputString;

        while (true) {
            try {
                System.out.print("" + inputMess);
                inputString = sc.nextLine();

                if (inputString.matches(format)) {
                    return inputString;
                } else {
                    System.err.println("<!> Please enter the correct format!");
                }
            } catch (Exception e) {
                System.err.println("<!>" + e);
            }
        }
    }
}
