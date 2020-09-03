package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.BackupAndRestore;
import util.ImportAndExportFiles;

public class FinalProjectPane
{
	private static Library library = new Library();   	
	private static BorderPane root = new BorderPane();
	private static VBox accountBox = new VBox();
	private static Menu viewMenu = new Menu("View");
    private static Menu fileMenu = new Menu("File");
    private static MenuBar menuBar = new MenuBar();
    private static Button loginButton, createAccount; 
    private static ImportAndExportFiles importAndExportFiles = new ImportAndExportFiles(library);
    private static BackupAndRestore backupAndRestore = new BackupAndRestore(library);
    private static MenuItem patronImportItem, patronExportItem, bookImportItem, bookExportItem, backupItem, restoreItem, bubbleSortItem, selectionSortItem, 
    						insertionSortItem, accountSettingsItem, patronReservation, patronTransactionHistory, bookItem, logoutItem;

    public static void startFinalProject(Stage primaryStage) throws Exception
    {  
        patronImportItem = new MenuItem("Patron");
        patronExportItem = new MenuItem("Patron");
        bookImportItem = new MenuItem("Book");
        bookExportItem = new MenuItem("Book");
        backupItem = new MenuItem("Backup");
        restoreItem = new MenuItem("Restore");
        bubbleSortItem = new MenuItem("Bubble Sort");
        selectionSortItem = new MenuItem("Selection Sort");
        insertionSortItem = new MenuItem("Insertion Sort");
        accountSettingsItem = new MenuItem("Account Settings");
        patronReservation = new MenuItem("My Reservations");
        patronTransactionHistory = new MenuItem("Transaction History");
        bookItem = new MenuItem("Books");
        logoutItem = new MenuItem("Logout");
        
        Menu importMenu, exportMenu;
        importMenu = new Menu("Import");
        exportMenu = new Menu("Export");
        viewMenu = new Menu("View");
        importMenu.getItems().addAll(patronImportItem, bookImportItem);
        exportMenu.getItems().addAll(patronExportItem, bookExportItem);
        viewMenu.setDisable(true);       
        viewMenu.getItems().addAll(accountSettingsItem, patronReservation, patronTransactionHistory, bookItem, logoutItem);
        fileMenu.getItems().addAll(importMenu, exportMenu, new SeparatorMenuItem(), backupItem,restoreItem, bubbleSortItem, selectionSortItem, 
        							insertionSortItem, new SeparatorMenuItem());
        menuBar.getMenus().addAll(fileMenu, viewMenu);
        
        loginButton = new Button("Login");
    	createAccount = new Button("Create Account");
    	  	  	
    	accountBox.getChildren().addAll(loginButton, createAccount);
    	accountBox.setAlignment(Pos.CENTER);
    	accountBox.setPadding(new Insets(30, -10, -300, -20));
    	accountBox.setSpacing(25);
    	root.setCenter(accountBox);
    	            
        //account buttons
        {
	    	loginButton.setOnAction(l -> 
	    	{
	    		Pane loginPane = new ReturningPatronPane(library).getPane(); 
	    		root.setCenter(loginPane); 
	    		//viewMenu.setDisable(false);
	    		primaryStage.setTitle("Library App");	    	 
	    	});
	    	createAccount.setOnAction(c -> 
	    	{
	    		Pane newUserPane = new NewPatronPane(library).getPane(); 
	    		root.setCenter(newUserPane); 
	    		//viewMenu.setDisable(false);
	    		primaryStage.setTitle("Library App");
	        });
        } 
        //Import Items
        {
            patronImportItem.setOnAction(e -> 
            {
            	importAndExportFiles.importPatron();
            });
            bookImportItem.setOnAction(e -> 
            {
            	importAndExportFiles.importBook();
            });
        }   
        //Export Items
        {
            patronExportItem.setOnAction(e -> 
            {
                importAndExportFiles.exportPatron();
            });
            bookExportItem.setOnAction(e -> 
            {
                importAndExportFiles.exportBook();     
            });
        }
        //Backup and Restore
        {
            backupItem.setOnAction(e -> 
            {
                   backupAndRestore.backup();
            });                 
            restoreItem.setOnAction(e -> 
            {
               backupAndRestore.restore();
            });               
        }       
        //View Menu
        {            
            patronReservation.setOnAction(pr -> 
            {
            	Pane reservationPane = new Pane();
            	root.setCenter(reservationPane);
            });
            patronTransactionHistory.setOnAction(pth -> 
            {
            	Pane transactionPane = new Pane();
            	root.setCenter(transactionPane);
            });
        }      
        		
        root.setBackground(new Background(new BackgroundFill (Color.BLANCHEDALMOND, null, null)));
        getPhoto();
        
        Scene scene = new Scene(root, 450, 530); 
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void getPhoto()
    {
    	try
        {
        	Image image = new Image(new FileInputStream("C:\\Users\\jessica\\CSE218\\LIBRARY APP\\src\\textFiles\\CataldiLibraryApp.png"));
        	ImageView imageView = new ImageView(image);
        	imageView.setFitHeight(300); 
        	imageView.setFitWidth(300);
        	HBox imageBox = new HBox();
        	imageBox.setPadding(new Insets(30, 200, 200, 75));
        	imageBox.getChildren().add(imageView);
        	root.getChildren().add(imageBox);
        } 
        catch (FileNotFoundException e1)
        {
        	e1.printStackTrace();
        }
    }
    public void logout()
	{
    	root.setCenter(accountBox);
    	viewMenu.setDisable(true);    
	}
    public void getPatronBookPane()
    {
    	Pane patronBookPane = new BookPane(library).getPatronBookPane();          
        root.setCenter(patronBookPane);     
    }
    public void getAdminBookPane()
    {
    	Pane adminBookPane = new BookPane(library).getAdminBookPane(); 
        root.setCenter(adminBookPane);   
    }
    public void getTransactionPane()
    {
    	Pane transactionPane = new TransactionPane(library).getPane();
    	root.setCenter(transactionPane);
    }
    public void getViewMembersPane()
    {
    	Pane viewMembersPane = new ViewMembersPane(library).getPane();
    	root.setCenter(viewMembersPane);
    }
}