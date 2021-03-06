package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.Alerts;

public class ReturningPatronPane 
{	
	private Library library; 
	private NewPatronPane npp = new NewPatronPane(library);
	private FinalProjectPane fpp = new FinalProjectPane();
	private MenuItem accountSettingsItem, patronReservation, patronTransactionHistory, viewMembers, home, logoutItem;
	private TextField usernameField;
	private PasswordField passwordField;
	private VBox root, textFieldBox;
	private HBox buttonBox;
	private Button loginButton;
	private MenuBar menuBar = new MenuBar();
	private Menu viewMenu = new Menu("View");
	private CheckBox monthlyNewsletter = new CheckBox();
	private File currentUserFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\userCurrentlyLoggedIn.txt");
	private Scanner currentUserScanner;

	public ReturningPatronPane(Library library)
	{
		this.library = library;
		buildTextFields();
		buildButtons();
		buildHBox();
		buildVBox();
		handleEvent();  				
	}
	private void buildVBox()
	{
		accountSettingsItem = new MenuItem("Account Settings");
		accountSettingsItem.setVisible(false);
        patronReservation = new MenuItem("My Reservations");
        patronReservation.setVisible(false);
        patronTransactionHistory = new MenuItem("Transaction History");
        patronTransactionHistory.setVisible(false);
        viewMembers = new MenuItem("Current Members");
        viewMembers.setVisible(false);
        home = new MenuItem("Home");
        home.setVisible(false);
        logoutItem = new MenuItem("Back");
		viewMenu.getItems().addAll(accountSettingsItem, patronReservation, patronTransactionHistory, viewMembers, home, logoutItem);
		menuBar.getMenus().addAll(viewMenu);
		
		root = new VBox(20);      
		root.setAlignment(Pos.TOP_CENTER);       
		root.getChildren().addAll(menuBar, textFieldBox, buttonBox);
	}
	private void buildHBox()
	{
		buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);    
		buttonBox.getChildren().add(loginButton);	
		
		textFieldBox = new VBox(10);
		textFieldBox.setAlignment(Pos.CENTER);
		textFieldBox.setPadding(new Insets(300, 20, 20, 20));
		textFieldBox.getChildren().addAll(usernameField, passwordField);
	}
	private void buildTextFields()
    {
        usernameField = new TextField();
        passwordField = new PasswordField();
		usernameField.setMaxWidth(150);
		passwordField.setMaxWidth(150);
        usernameField.setPromptText("USERNAME");
        passwordField.setPromptText("PASSWORD");    
    }
	private void buildButtons()
    {
        loginButton = new Button("Login");
    }
	
	public void handleEvent()
	{
		Patron admin = new Patron("admin", "admin", "admin");
    	library.getPatronBag().insert(admin);
		loginButton.setOnAction(e->
		{
			String firstName, lastName, phoneNumber, email, reserved, transactionHistory;
			File firstNamesFile, lastNamesFile;
			Scanner firstNameScanner, lastNameScanner;
			
	        firstNamesFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\firstNames");
	        lastNamesFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\lastNames");
	        try
	        {
	            firstNameScanner = new Scanner(firstNamesFile);
	            lastNameScanner = new Scanner(lastNamesFile);
	            
	            while (firstNameScanner.hasNextLine() && lastNameScanner.hasNextLine()) 
	            {   
	            	firstName = firstNameScanner.nextLine();
	            	lastName = lastNameScanner.nextLine();

	                Patron patron = new Patron(firstName, lastName);
	                library.getPatronBag().insert(patron);
	            }
	            firstNameScanner.close();
	            lastNameScanner.close();
	          } 
	          catch (FileNotFoundException e1)
	          {
	              e1.printStackTrace();
	          } 
			
			accountSettingsItem.setVisible(true);
	        patronReservation.setVisible(true);
	        patronTransactionHistory.setVisible(true);
	        home.setVisible(true);
	        logoutItem.setText("Logout");;
	        
			String username = usernameField.getText();
			String password = passwordField.getText();
			Patron patron = library.getPatronBag().searchByUsername(username);
			if(patron != null)
			{
				if(username.equals("admin") && password.equals("admin"))
				{
					accountSettingsItem.setVisible(false);
					patronReservation.setVisible(false);
			        patronTransactionHistory.setVisible(false);
			        viewMembers.setVisible(true);
			        
					textFieldBox.getChildren().removeAll(usernameField, passwordField);
					buttonBox.getChildren().remove(loginButton);
					textFieldBox.getChildren().addAll(npp.getAdminButton());
				}
				else if(password.matches(patron.getPassword()))
				{
					textFieldBox.getChildren().removeAll(usernameField, passwordField);
					buttonBox.getChildren().remove(loginButton);
					textFieldBox.getChildren().addAll(npp.getPatronButton());
				}
				else
				{
					Alerts.usernamePasswordMismatch();
				}
			}
			else
			{
				Alerts.usernamePasswordMismatch();
			}
			try
            {
                FileWriter file = new FileWriter("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\userCurrentlyLoggedIn.txt");
                file.write(patron.getId() + "\n" + patron.getUsername() + "\n" + patron.getFirstName() + "\n" + patron.getLastName() +
                			"\n" + patron.getPhoneNumber() + "\n" + patron.getEmail() + "\n" + patron.getPassword());                  
                file.close();
            } 
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
			accountSettingsItem.setOnAction(a ->
            {          	
                root.getChildren().clear();
                root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
            	
                Label idLabel, usernameLabel, firstNameLabel, lastNameLabel, phoneNumberLabel, emailLabel;
            	Hyperlink changePasswordLink;
            	TextField usernameField, firstNameField2, lastNameField2, phoneNumberField2, emailField2;
            	HBox buttonBox, idBox, usernameBox, firstNameBox, lastNameBox, phoneNumberBox, emailBox;
            	Button updateButton;
                
                idLabel = new Label("ID: " + patron.getId()); 
        		idLabel.setFont(new Font("Albertus Extra Bold", 20));
        		usernameLabel = new Label("Username");
        		firstNameLabel = new Label("First Name");
        		lastNameLabel = new Label("Last Name"); 
        		phoneNumberLabel = new Label("Phone Number");
        		emailLabel = new Label("Email");
        		
        		usernameField = new TextField();
                firstNameField2 = new TextField();
                lastNameField2 = new TextField();
                phoneNumberField2 = new TextField();
                emailField2 = new TextField();
                usernameField.setMaxWidth(150);
                firstNameField2.setMaxWidth(150);
        		lastNameField2.setMaxWidth(150);
        		phoneNumberField2.setMaxWidth(150);
        		emailField2.setMaxWidth(150);
        		usernameField.setText(patron.getUsername());
                firstNameField2.setText(patron.getFirstName());
                lastNameField2.setText(patron.getLastName());
                phoneNumberField2.setText(patron.getPhoneNumber());
                emailField2.setText(patron.getEmail());
               
                changePasswordLink = new Hyperlink("change password");
                monthlyNewsletter = new CheckBox("receive monthly newsletters and updates");
                updateButton = new Button("Update");
                
                idBox = new HBox(10);
                idBox.setAlignment(Pos.TOP_LEFT);    
                idBox.getChildren().add(idLabel);
        		
        		usernameBox = new HBox(10);
        		usernameBox.setAlignment(Pos.CENTER_LEFT);
        		usernameBox.setSpacing(39);
        		usernameBox.getChildren().addAll(usernameLabel, usernameField);
        				
        		firstNameBox = new HBox(10);
        		firstNameBox.setAlignment(Pos.CENTER_LEFT); 
        		firstNameBox.setSpacing(35.5);
        		firstNameBox.getChildren().addAll(firstNameLabel, firstNameField2);
        		
        		lastNameBox = new HBox(10);
        		lastNameBox.setAlignment(Pos.CENTER_LEFT);  
        		lastNameBox.setSpacing(35.5);
        		lastNameBox.getChildren().addAll(lastNameLabel, lastNameField2);
        		
        		phoneNumberBox = new HBox(10);
        		phoneNumberBox.setAlignment(Pos.CENTER_LEFT);  
        		phoneNumberBox.getChildren().addAll(phoneNumberLabel, phoneNumberField2);
        		
        		emailBox = new HBox(10);
        		emailBox.setAlignment(Pos.CENTER_LEFT);  
        		emailBox.setSpacing(61);
        		emailBox.getChildren().addAll(emailLabel, emailField2);
        		
        		buttonBox = new HBox(10);
        		buttonBox.setAlignment(Pos.CENTER);    
        		buttonBox.getChildren().addAll(updateButton, changePasswordLink);
        		
                root.setSpacing(10);
                root.getChildren().addAll(menuBar, idBox, usernameBox, firstNameBox, lastNameBox, phoneNumberBox, emailBox, monthlyNewsletter, buttonBox);
            	
                updateButton.setOnAction(u -> 
        		{
        			String newUsername = usernameField.getText();
        			String newFirstName = firstNameField2.getText();
        			String newLastName = lastNameField2.getText();
        			String newPhoneNumber = phoneNumberField2.getText();
        			String newEmail = emailField2.getText();
        			if(newUsername.length() != 0 && newFirstName.length() > 1 && newLastName.length() > 1 && 
        			   newPhoneNumber.length() == 10 && newPhoneNumber.matches("[0-9]*") && newEmail.length() != 0 && 
        			   newEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$"))
        			{
        				if(monthlyNewsletter.isSelected())
        				{
        					monthlyNewsletter.setSelected(true);
        				}
        				patron.setUsername(newUsername);
        				patron.setFirstName(newFirstName);
        				patron.setLastName(newLastName);
        				patron.setPhoneNumber(newPhoneNumber);
        				patron.setEmail(newEmail);
        				Alerts.accountInformationUpdateSuccessful();
        			}
        		});
                changePasswordLink.setOnAction(c -> 
        		{
        			PasswordField currentPasswordField, newPasswordField, retypeNewPasswordField;
        			Button saveButton;
        			VBox changePasswordBox = new VBox();
        			currentPasswordField = new PasswordField();
        			currentPasswordField.setMaxWidth(150);
        			newPasswordField = new PasswordField();
        			newPasswordField.setMaxWidth(150);
        			retypeNewPasswordField = new PasswordField();
        			retypeNewPasswordField.setMaxWidth(150);
        			currentPasswordField.setPromptText("CURRENT PASSWORD");
        			newPasswordField.setPromptText("NEW PASSWORD");
        			retypeNewPasswordField.setPromptText("RE-TYPE NEW PASSWORD");
        			saveButton = new Button ("Save");
        			changePasswordBox.getChildren().addAll(currentPasswordField, newPasswordField, retypeNewPasswordField, saveButton);
        			changePasswordBox.setSpacing(10);
        			
        			Pane changePasswordPane = new Pane();
        			changePasswordPane.getChildren().addAll(changePasswordBox);
        			Scene scene = new Scene(changePasswordPane, 200, 150);
        			Stage stage = new Stage();
        			stage.setScene(scene);
        			stage.show();
        			
        			saveButton.setOnAction(s -> 
        			{
        				String currentPassword = currentPasswordField.getText();
        				String newPassword1 = newPasswordField.getText();
        				String newPassword2 = retypeNewPasswordField.getText();
        				if(currentPassword.equals(patron.getPassword()) && newPassword1.length() !=0 && 
        				   newPassword1.length() !=0 && newPassword1.matches(newPassword2))
        				{
        					patron.setPassword(newPassword1);
        					System.out.println("Success");
        					stage.close();
        					Alerts.passwordUpdateSuccessful();
        				}
        				else if(currentPassword.equals(patron.getPassword()) != true)
        				{
        					Alerts.wrongCurrentPassword();
        				}
        				else if(newPassword1.matches(newPassword2) != true)
        				{
        					Alerts.wrongPasswordMatch();
        				}
        			});
        		});
            });
			patronTransactionHistory.setOnAction(pt ->
			{
				TransactionPane tp = new TransactionPane(library);
				TransactionBag transactionBag = new TransactionBag(LibraryInterface.TRANSACTIONBAG_MAXSIZE);
				Transactions transaction = transactionBag.findById(patron.getId());
				tp.getTable().getItems().add(transaction);
				fpp.getTransactionPane();
			});
		});
		patronReservation.setOnAction(p -> 
		{
			
		});
		viewMembers.setOnAction(v -> 
		{
			fpp.getViewMembersPane();
		});
		home.setOnAction(h -> 
		{
			getHome();
		});
		logoutItem.setOnAction(lo -> 
    	{
    		fpp.logout();
    	});
	}
	public VBox textFieldBox()
	{
		textFieldBox.getChildren().removeAll(usernameField, passwordField);
		return textFieldBox;
	}
	public VBox getHome()
	{
		try 
		{
			currentUserScanner = new Scanner(currentUserFile);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		String id = currentUserScanner.nextLine();
		String username = currentUserScanner.nextLine();
		root.getChildren().clear();
		//fpp.getPhoto();
		root.setBackground(new Background(new BackgroundFill (Color.BLANCHEDALMOND, null, null)));	 	 
		if(username.equals("admin"))
		{
	  		viewMembers.setVisible(true);
    		home.setVisible(true);
	   		logoutItem.setText("Logout");
    		root.getChildren().addAll(menuBar, textFieldBox);
	    }
		else
		{
			accountSettingsItem.setVisible(true);
			patronReservation.setVisible(true);
			patronTransactionHistory.setVisible(true);
			home.setVisible(true);
			logoutItem.setText("Logout");
			root.getChildren().addAll(menuBar, textFieldBox);
		}
		patronTransactionHistory.setOnAction(pt ->
		{
			TransactionPane tp = new TransactionPane(library);
			TransactionBag transactionBag = new TransactionBag(LibraryInterface.TRANSACTIONBAG_MAXSIZE);
			Transactions transaction = transactionBag.findById(id);
			tp.getTable().getItems().add(transaction);
			fpp.getTransactionPane();
		});
		home.setOnAction(h -> 
		{
			getHome();
		});
		//fpp.getPhoto();
		return root;
	}
	public Pane getPane()
    {
        return root;
    } 
}