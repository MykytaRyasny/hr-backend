package com.tfg.proyect.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * The type Employee creator utils.
 */
public class EmployeeCreatorUtils {

  private EmployeeCreatorUtils() {

  }

  /**
   * Generate password string.
   *
   * @return the string
   */
  public static String generatePassword() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";
    return RandomStringUtils.random(10, characters);
  }

  /**
   * Generate username string.
   *
   * @param firstName the first name
   * @param lastName  the last name
   * @return the string
   */
  public static String generateUsername(String firstName, String lastName) {
    return String.format("%s.%s@libreria.com", firstName, lastName);
  }


  /**
   * Validate dni boolean.
   *
   * @param dni the dni
   * @return the boolean
   */
  public static boolean validateDNI(String dni) {
    Pattern regexp = Pattern.compile("\\d{8}[A-Z]");
    final String DIGIT_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
    final String[] notValid = new String[]{"00000000T", "00000001R", "99999999R"};
    return Arrays.binarySearch(notValid, dni) < 0
        && regexp.matcher(dni).matches()
        && dni.charAt(8) == DIGIT_CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
  }
}
