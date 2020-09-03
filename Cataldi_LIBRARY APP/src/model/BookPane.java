package model;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.Alerts;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BookPane
{	
	private Library library;
	private NewPatronPane npp = new NewPatronPane(library);
	private FinalProjectPane fpp = new FinalProjectPane();
	private MenuItem accountSettingsItem, patronReservation, patronTransactionHistory, viewMembers, home, logoutItem;
    private TextField isbnField, authorField, priceField;
    private TextArea bookTitleField;  
    private Text available;
    private Button insertButton, searchButton, updateButton, removeButton, clearButton, checkoutButton;    
    private VBox root, textFieldBox, availableOrNot;    
    private HBox buttonBox, buttonBox2;
    private MenuBar menuBar = new MenuBar();
	private Menu viewMenu = new Menu("View");
	private ComboBox<String> comboBox1;
	private File currentUserFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\userCurrentlyLoggedIn.txt");
	private Scanner currentUserScanner;
		
    public BookPane(Library library)
    {
    	this.library = library;
        buildTextFields();
        buildButtons();      
        buildHBoxes();
        buildVBox();
        handleEvent();       
    } 
    private void buildVBox()
    {
        root = new VBox(20); 
        textFieldBox = new VBox(10);
        availableOrNot = new VBox(10);
        
        bookTitleField.setMaxSize(150, 60);
        isbnField.setMaxWidth(150);
        authorField.setMaxWidth(150);
        priceField.setMaxWidth(150);
        bookTitleField.setWrapText(true);
        
        accountSettingsItem = new MenuItem("Account Settings");
        patronReservation = new MenuItem("My Reservations");
        patronTransactionHistory = new MenuItem("Transaction History");
        viewMembers = new MenuItem("Current Members");
        home = new MenuItem("Home");
        logoutItem = new MenuItem("Logout");
        viewMenu.getItems().addAll(accountSettingsItem, patronReservation, patronTransactionHistory, viewMembers, home, logoutItem);
		menuBar.getMenus().addAll(viewMenu);

		available = new Text();
        textFieldBox.setAlignment(Pos.CENTER_LEFT);
        textFieldBox.getChildren().addAll(bookTitleField, isbnField, authorField, priceField);
        availableOrNot.setAlignment(Pos.TOP_LEFT);
        availableOrNot.getChildren().add(available);
        root.setAlignment(Pos.TOP_CENTER); 
        root.getChildren().addAll(menuBar, textFieldBox, availableOrNot, buttonBox, buttonBox2);
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void buildHBoxes()
    { 
    	comboBox1 = new ComboBox();
        comboBox1.setPromptText("-SEARCH BY-");
        comboBox1.setItems(FXCollections.observableArrayList("Title", "Author", "ISBN"));
    	
        buttonBox = new HBox(10);
        buttonBox2 = new HBox(10);
                   
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox2.setAlignment(Pos.BOTTOM_LEFT);
              
        buttonBox.getChildren().addAll(insertButton, searchButton, comboBox1, updateButton, removeButton, clearButton);
        buttonBox2.getChildren().add(checkoutButton);
    }
    private void buildTextFields()
    {
        bookTitleField = new TextArea();
        isbnField = new TextField();
        authorField = new TextField();
        priceField = new TextField();
        bookTitleField.setPromptText("BOOK TITLE");
        isbnField.setPromptText("ISBN");
        authorField.setPromptText("AUTHOR");
        priceField.setPromptText("PRICE");
    }        
    private void buildButtons()
    {
        insertButton = new Button("INSERT");
        searchButton = new Button("SEARCH");
        updateButton = new Button("UPDATE");
        removeButton = new Button("REMOVE");
        clearButton = new Button ("CLEAR");
        checkoutButton = new Button("Checkout Book");
    }
    public void handleEvent()
    { 
      priceField.setDisable(true);
      insertButton.setOnAction(e ->
      {
		  String bookTitle = bookTitleField.getText();
		  String isbn = isbnField.getText();
		  String author = authorField.getText();
		  if(bookTitle.length() != 0 && isbn.length() != 0 && author.length() != 0)
		  {
			  library.getBookList().insertBook(bookTitle, isbn, author, false);
			  bookTitleField.clear();
			  isbnField.clear();
			  authorField.clear();
		  }	
		  else
		  {
			  Alerts.wrongBookAlert();
		  }	  
      });  
      searchButton.setOnAction(e ->
      {
          String isbn = isbnField.getText();
          String title = bookTitleField.getText();
          String author = authorField.getText();
          isbnField.setDisable(true);
          if(comboBox1.getValue() == "ISBN")
          {
	          Book book = library.getBookList().findByIsbn(isbn);
	          if (book != null)
	          {   
	        	  bookTitleField.setText(book.getTitle());
	        	  authorField.setText(book.getAuthor());
	        	  isbnField.setText(book.getIsbn());
	        	  priceField.setText(book.getPrice());
	        	  if(book.getCheckedOut() == true)
	        	  {
	        		  getNotAvailable();
	        	  }
	        	  else
	        	  {
	        		  getAvailable();
	        	  }
	          } 
          }
          else if(comboBox1.getValue() == "Author")
          {
	          Book book = library.getBookList().findByAuthor(author);
	          if (book != null)
	          {   
	        	  bookTitleField.setText(book.getTitle());
	        	  authorField.setText(book.getAuthor());
	        	  isbnField.setText(book.getIsbn());
	        	  priceField.setText(book.getPrice());
	        	  if(book.getCheckedOut() == true)
	        	  {
	        		  getNotAvailable();	        	  
	        	  }
	        	  else
	        	  {
	        		  getAvailable();
	        	  }
	          } 
          }
          else if(comboBox1.getValue() == "Title")
          {
	          Book book = library.getBookList().findByTitle(title);
	          if (book != null)
	          {   
	        	  bookTitleField.setText(book.getTitle());
	        	  authorField.setText(book.getAuthor());
	        	  isbnField.setText(book.getIsbn());
	        	  priceField.setText(book.getPrice());
	        	  if(book.getCheckedOut() == true)
	        	  {
	        		  getNotAvailable();	        	  
	        	  }
	        	  else
	        	  {
	        		  getAvailable();
	        	  }
	          } 
          }
          else
          {
              Alerts.errorSearchAlert();
          } 
     });
      updateButton.setOnAction(e ->
      {
          String isbn = isbnField.getText();
          library.getBookList().findByIsbn(isbn);
          String bookTitle = bookTitleField.getText();
          String author = authorField.getText();
          boolean checkedOut = library.getBookList().findByIsbn(isbn).getCheckedOut();
          if(bookTitle.length() != 0 && isbn.length() != 0 && author.length() != 0)
          {
        	  library.getBookList().insertBook(bookTitle, isbn, author, checkedOut);
        	  
        	  bookTitleField.clear();
        	  isbnField.clear();
        	  authorField.clear();
        	  priceField.clear();
          
        	  isbnField.setDisable(false);
          }
          else
          {
              Alerts.wrongBookAlert();
          }
      });  
      removeButton.setOnAction(e ->
      {
          String isbn = isbnField.getText();
          library.getBookList().findByIsbn(isbn);
          library.getBookList().removeByIsbn(isbn);
          bookTitleField.clear();
          isbnField.clear();
          authorField.clear();
          priceField.clear();
          isbnField.setDisable(false);
      }); 
      checkoutButton.setOnAction(c ->
      {
    	  try 
    	  {
    		  @SuppressWarnings("resource")
    		  Scanner currentUserScanner = new Scanner(currentUserFile);
              String id = currentUserScanner.nextLine();  
              final Stage dialog = new Stage();
              Button confirmButton, cancelButton;
              confirmButton = new Button("Confirm");
              cancelButton = new Button("Cancel");
              dialog.initModality(Modality.APPLICATION_MODAL);
              VBox dialogVbox = new VBox(20);
              dialogVbox.setAlignment(Pos.CENTER);
              dialogVbox.getChildren().add(new Text("  Confirm Checkout"));
              dialogVbox.getChildren().addAll(confirmButton, cancelButton);
              Scene dialogScene = new Scene(dialogVbox, 120, 120);
              dialog.setScene(dialogScene);
              dialog.show();
              confirmButton.setOnAction(cc ->
              {
            	  java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            	  java.sql.Date date = new java.sql.Date(timeStamp.getTime());
            	  String isbn = isbnField.getText();
            	  Book book = library.getBookList().findByIsbn(isbn);
            	  book.setCheckedOut(true);
            	  getNotAvailable();
            	  Transactions transaction = new Transactions(String.valueOf(date), book.getTitle(), book.getIsbn(), book.getAuthor(), book.getPrice(), id);
            	  library.getTransactionBag().insertTransaction(transaction);
            	  dialog.close();
              });
              cancelButton.setOnAction(cb -> 
              {
            	  dialog.close();
              });
    	  } 
    	  catch (FileNotFoundException e1) 
    	  {
    		  e1.printStackTrace();
    	  }
      });
      clearButton.setOnAction(e -> 
      {        
          bookTitleField.clear();
          isbnField.clear();
          authorField.clear();
          priceField.clear();
          available.setVisible(false);
          isbnField.setDisable(false);
      }); 
      accountSettingsItem.setOnAction(a ->
      {  
    	  File currentUserFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\userCurrentlyLoggedIn.txt");
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
      ReturningPatronPane rpp = new ReturningPatronPane(library);
      home.setOnAction(h -> 
      {
    	  try 
    	  {
			currentUserScanner = new Scanner(currentUserFile);
			String id = currentUserScanner.nextLine();
			String username = currentUserScanner.nextLine();
			root.getChildren().clear();
	     	 //fpp.getPhoto();
	     	 root.setBackground(new Background(new BackgroundFill (Color.BLANCHEDALMOND, null, null)));
	     	 if(username.equals("admin"))
	     	 {
	         	 rpp.textFieldBox().getChildren().addAll(npp.getAdminButton());;

	     	 }
	     	 else
	     	 {
	     		 rpp.textFieldBox().getChildren().addAll(npp.getPatronButton());;
	     	 }
	     	 root.getChildren().add(rpp.getHome());
    	  } 
    	  catch (FileNotFoundException e1) 
    	  {
			e1.printStackTrace();
    	  }
      });
      viewMembers.setOnAction(v -> 
		{
			fpp.getViewMembersPane();
		});
      patronTransactionHistory.setOnAction(pt ->
      {
    	  fpp.getTransactionPane();
      });
      logoutItem.setOnAction(lo -> 
  	{
  		fpp.logout();
  	});
      
    try
    {
    	Image image = new Image(new FileInputStream("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\book.png"));
    	ImageView imageView = new ImageView(image);
    	imageView.setFitHeight(150); 
    	imageView.setFitWidth(150);
    	HBox imageBox = new HBox();
    	imageBox.getChildren().add(imageView);
    	root.getChildren().addAll(imageBox);
    } 
    catch (FileNotFoundException e1)
    {
    	e1.printStackTrace();
    }
    }
    
    public Pane getAdminBookPane()
    { 
    	accountSettingsItem.setVisible(false);
    	patronReservation.setVisible(false);
    	patronTransactionHistory.setVisible(false);
    	root.setBackground(new Background(new BackgroundFill (Color.TEAL, null, null)));
    	buttonBox2.getChildren().remove(checkoutButton);
        return root;
    }
    public Pane getPatronBookPane()
    { 
    	viewMembers.setVisible(false);
    	root.setBackground(new Background(new BackgroundFill (Color.TEAL, null, null)));
    	buttonBox.getChildren().removeAll(updateButton, insertButton, removeButton);
        return root;
    }
    public void getAvailable()
    {
    	available.setText("Available");
    	available.setFill(Color.LIMEGREEN);
    	available.setFont(new Font(30));
    	available.setVisible(true);
    }
    public void getNotAvailable()
    {
    	available.setText("Checked Out");
    	available.setFill(Color.RED);
    	available.setFont(new Font(30));
    	available.setVisible(true);
    }
    public void importBook()
    {
    	File titleFile, isbnFile, firstNameFile, lastNameFile;
		Scanner titleScanner, isbnScanner, firstNameScanner, lastNameScanner;
        String title, isbn, authors;
        titleFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\bookTitle");
        isbnFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\isbn");
        firstNameFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\firstNames");
        lastNameFile = new File("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\lastNames");
         try
         {
             titleScanner = new Scanner(titleFile);
             isbnScanner = new Scanner(isbnFile); 
             firstNameScanner = new Scanner(firstNameFile);
             lastNameScanner = new Scanner (lastNameFile);
             
             while (titleScanner.hasNextLine() && isbnScanner.hasNextLine() &&
                     firstNameScanner.hasNextLine() && lastNameScanner.hasNextLine()) 
             {   
                 title = titleScanner.nextLine();
                 isbn = isbnScanner.nextLine(); 
                 authors = firstNameScanner.nextLine() + " " + lastNameScanner.nextLine();
          	 
                 library.getBookList().insertBook(title, isbn, authors, false);
             }
             titleScanner.close();
             isbnScanner.close();
             firstNameScanner.close();
           } 
           catch (FileNotFoundException e1)
           {
               e1.printStackTrace();
           }
         System.out.println("Book Import Complete.");
         exportBook();
    }
    public void exportBook()
	{
		try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("books.txt"));
            oos.writeObject(library.getBookList().toString());                   
            oos.close();
        } 
        catch (IOException e1)
        {
            e1.printStackTrace();
        }
        System.out.println("Book Export Complete.");            
	}
}