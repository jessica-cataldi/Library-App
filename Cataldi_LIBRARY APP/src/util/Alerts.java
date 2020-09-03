package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Patron;

public class Alerts
{
    public static void errorSearchAlert()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("NO RESULTS: Invalid Search Criteria");
        alert.setContentText("You are receiving this alert because the item "
                              + "you searched for is either invalid or no longer exits.");
        alert.showAndWait();
    }      
    public static void wrongBookAlert()
    {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("INPUT ERROR: Invalid Input");
        alert.setContentText("Please make sure you enter a valid book title along with"
                              + " the authors name and the ISBN");
        alert.showAndWait();
    }      
    public static void wrongInformationAlert()
    {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("WARNING");
        alert.setHeaderText("INPUT ERROR: Invalid Input" );
        alert.setContentText("Please make sure you enter a first and last name "
                              + "along with a title and a valid 10 digit phonenumber.");
        alert.showAndWait();
    }
    public static void newUserInformationAlert(Patron patron)
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("WELCOME NEW USER");
    	alert.setHeaderText("Thank you for creating an account with Cataldi Library App. \nYour login information is displayed below, "
    			+ "\nplease save it before continuing as you will need this to login in the future.\nYou may change/update this information "
    			+ "in the Account settings under the View tab.");
    	alert.setContentText("Patron ID: " + patron.getId() + "\nUsername: " + patron.getUsername() + "\nPassword: " 
    						+ patron.getPassword());
    	alert.showAndWait();
    }
    public static void passwordUpdateSuccessful()
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Password Change Successful!");
    	alert.showAndWait();
    }
    public static void wrongCurrentPassword()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("Current Password is Incorrect.");
        alert.showAndWait();
    }    
    public static void wrongPasswordMatch()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setContentText("New Passwords Do Not Match.");
        alert.showAndWait();
    }  
    public static void usernamePasswordMismatch()
    {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Account Not Found");
        alert.setContentText("Incorrect username and password.");
        alert.showAndWait();
    }
    public static void accountInformationUpdateSuccessful()
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Update Successful!");
    	alert.showAndWait();
    }
}