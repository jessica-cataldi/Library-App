package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import util.Alerts;

public class TransactionPane 
{
	@SuppressWarnings("unused")
	private Library library;
	private FinalProjectPane fpp = new FinalProjectPane();
	private TableView<Transactions> table = new TableView<Transactions>();
	private Label label;
	private TableColumn<Transactions, String> date = new TableColumn<>("Date");
	private TableColumn<Transactions, String> title = new TableColumn<>("Title");
	private TableColumn<Transactions, String> isbn = new TableColumn<>("ISBN");
	private TableColumn<Transactions, String> author = new TableColumn<>("Author");
	private TableColumn<Transactions, String> price = new TableColumn<>("Price");
	private VBox root, textFieldBox; 
	private MenuItem accountSettings, myReservations, home, logoutItem;
	private MenuBar menuBar = new MenuBar();
	private Menu viewMenu = new Menu("View");
	
	@SuppressWarnings("unchecked")
	public TransactionPane(Library library)
	{
		this.library = library;
		
		textFieldBox = new VBox(10);
		textFieldBox.setAlignment(Pos.CENTER);
		textFieldBox.setPadding(new Insets(300, 20, 20, 20));
		NewPatronPane npp = new NewPatronPane(library);
		textFieldBox.getChildren().addAll(npp.getPatronButton());
		
		accountSettings = new MenuItem("Account Settings");
		myReservations = new MenuItem("My Reservations");
		home = new MenuItem("Home");
		logoutItem = new MenuItem("Logout");
		viewMenu.getItems().addAll(accountSettings, myReservations, home, logoutItem);
		menuBar.getMenus().addAll(viewMenu);
		
		label = new Label("Transaction History");
		label.setFont(new Font(30));
		label.setTextFill(Color.WHITESMOKE);
				
		date.setCellValueFactory(new PropertyValueFactory<Transactions, String>("date"));
		title.setCellValueFactory(new PropertyValueFactory<Transactions, String>("title"));
		isbn.setCellValueFactory(new PropertyValueFactory<Transactions, String>("isbn"));
		author.setCellValueFactory(new PropertyValueFactory<Transactions, String>("author"));
		price.setCellValueFactory(new PropertyValueFactory<Transactions, String>("price"));

		File currentUserFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\userCurrentlyLoggedIn.txt");
  	  	try 
  	  	{
  		  	@SuppressWarnings("resource")
			Scanner currentUserScanner = new Scanner(currentUserFile);
            String id = currentUserScanner.nextLine();  
            Transactions transaction = library.getTransactionBag().findById(id);

            ObservableList<Transactions> data = FXCollections.observableArrayList( new Transactions(null, library.getTransactionBag().toString(), null, null, null, null));
            date.setCellValueFactory(cellData -> new SimpleStringProperty(library.getTransactionBag().getDate()));
            title.setCellValueFactory(cellData -> new SimpleStringProperty(library.getTransactionBag().getTitle()));
            isbn.setCellValueFactory(cellData -> new SimpleStringProperty(library.getTransactionBag().getIsbn()));
            author.setCellValueFactory(cellData -> new SimpleStringProperty(library.getTransactionBag().getAuthor()));
            price.setCellValueFactory(cellData -> new SimpleStringProperty(library.getTransactionBag().getPrice()));
            table.setItems(data);
            table.getColumns().addAll(date, title, isbn, author, price);
  	  	} 
  	  	catch (FileNotFoundException e1) 
  	  	{
  		  e1.printStackTrace();
  	  	}
		root = new VBox(20);      
		root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		root.setAlignment(Pos.TOP_CENTER);       
		root.getChildren().addAll(menuBar, label, table);
		
		accountSettings.setOnAction(as -> 
		{
	          try
	          {
	        	  Scanner currentUserScanner = new Scanner(currentUserFile);
	              String id = currentUserScanner.nextLine();
	              String username = currentUserScanner.nextLine();
	              String firstname = currentUserScanner.nextLine();
	              String lastname = currentUserScanner.nextLine();
	              String phoneNumber = currentUserScanner.nextLine();
	              String email = currentUserScanner.nextLine();
	              String password = currentUserScanner.nextLine();
	              currentUserScanner.close();
	              
	              root.getChildren().clear();
	              root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
	        	
	              Label idLabel, usernameLabel, firstNameLabel, lastNameLabel, phoneNumberLabel, emailLabel;
	              Hyperlink changePasswordLink;
	              TextField usernameField, firstNameField2, lastNameField2, phoneNumberField2, emailField2;
	              HBox buttonBox, idBox, usernameBox, firstNameBox, lastNameBox, phoneNumberBox, emailBox;
	              Button updateButton;
	            
	              idLabel = new Label("ID: " + id); 
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
	              usernameField.setText(username);
	              firstNameField2.setText(firstname);
	              lastNameField2.setText(lastname);
	              phoneNumberField2.setText(phoneNumber);
	              emailField2.setText(email);
	           
	              changePasswordLink = new Hyperlink("change password");
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
	              root.getChildren().addAll(menuBar, idBox, usernameBox, firstNameBox, lastNameBox, phoneNumberBox, emailBox, buttonBox);
	        	
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
	    				library.getPatronBag().setUsername(newUsername);
	    				library.getPatronBag().setFirstName(newFirstName);
	    				library.getPatronBag().setLastName(newLastName);
	    				library.getPatronBag().setPhoneNumber(newPhoneNumber);
	    				library.getPatronBag().setEmail(newEmail);
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
	    				if(currentPassword.equals(password) && newPassword1.length() !=0 && 
	    				   newPassword1.length() !=0 && newPassword1.matches(newPassword2))
	    				{
	    					library.getPatronBag().setPassword(newPassword1);
	    					System.out.println("Success");
	    					stage.close();
	    					Alerts.passwordUpdateSuccessful();
	    				}
	    				else if(currentPassword.equals(library.getPatronBag().getPassword()) != true)
	    				{
	    					Alerts.wrongCurrentPassword();
	    				}
	    				else if(newPassword1.matches(newPassword2) != true)
	    				{
	    					Alerts.wrongPasswordMatch();
	    				}
	    			});
	    		});
	            } 
	            catch (FileNotFoundException e1)
	            {
	                e1.printStackTrace();
	            }
	    });
		myReservations.setOnAction(r ->
		{
			
		});
		home.setOnAction(h -> 
		{
			root.getChildren().clear();
	        root.setBackground(new Background(new BackgroundFill(Color.BLANCHEDALMOND, null, null)));
			root.getChildren().addAll(menuBar, textFieldBox);
		});
		logoutItem.setOnAction(lo -> 
    	{
    		fpp.logout();
    	});
	}
	
	public TableView<Transactions> getTable()
	{
		return table;
	}
	public void setTable(TableView<Transactions> table)
	{
		this.table = table;
	}
	public Pane getPane()
	{
		return root;
	}
}