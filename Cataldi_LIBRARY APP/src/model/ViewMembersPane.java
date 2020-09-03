package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ViewMembersPane 
{
	@SuppressWarnings("unused")
	private Library library;
	private FinalProjectPane fpp = new FinalProjectPane();
	private TableView<Patron> table = new TableView<Patron>();
	private Label label;
	private TableColumn<Patron, String> id = new TableColumn<>("ID");
	private TableColumn<Patron, String> username = new TableColumn<>("Username");
	private TableColumn<Patron, String> lastname = new TableColumn<>("Last Name");
	private TableColumn<Patron, String> firstname = new TableColumn<>("First Name");
	private TableColumn<Patron, String> contactInfo = new TableColumn<>("Contact Information");
	private TableColumn<Patron, String> phoneNumber = new TableColumn<>("Phone Number");
	private TableColumn<Patron, String> email = new TableColumn<>("Email");
	private VBox root, textFieldBox;
	private MenuItem viewMembers, home, logoutItem;
	private MenuBar menuBar = new MenuBar();
	private Menu viewMenu = new Menu("View");
	
	@SuppressWarnings("unchecked")
	public ViewMembersPane(Library library)
	{
		this.library = library;
		
		textFieldBox = new VBox(10);
		textFieldBox.setAlignment(Pos.CENTER);
		textFieldBox.setPadding(new Insets(300, 20, 20, 20));
		NewPatronPane npp = new NewPatronPane(library);
		textFieldBox.getChildren().addAll(npp.getAdminButton());

		viewMembers = new MenuItem("Current Members");
        home = new MenuItem("Home");
        logoutItem = new MenuItem("Logout");
		
		viewMenu.getItems().addAll(viewMembers, home, logoutItem);
		menuBar.getMenus().addAll(viewMenu);
		
		label = new Label("Current Members");
		label.setFont(new Font(30));
		label.setTextFill(Color.YELLOW);
				
		id.setCellValueFactory(new PropertyValueFactory<Patron, String>("id"));
		username.setCellValueFactory(new PropertyValueFactory<Patron, String>("username"));
		lastname.setCellValueFactory(new PropertyValueFactory<Patron, String>("last name"));
		firstname.setCellValueFactory(new PropertyValueFactory<Patron, String>("first name"));
		contactInfo.setCellValueFactory(new PropertyValueFactory<Patron, String>("contact information"));
		phoneNumber.setCellValueFactory(new PropertyValueFactory<Patron, String>("phone number"));
		email.setCellValueFactory(new PropertyValueFactory<Patron, String>("email"));
		contactInfo.getColumns().addAll(phoneNumber, email);

		ObservableList<Patron> data = FXCollections.observableArrayList( new Patron(library.getPatronBag().toString(), null));
		id.setCellValueFactory(cellData -> new SimpleStringProperty(library.getPatronBag().getId()));
		username.setCellValueFactory(cellData -> new SimpleStringProperty(library.getPatronBag().getUsername()));
		lastname.setCellValueFactory(cellData -> new SimpleStringProperty(library.getPatronBag().getLastname()));
		firstname.setCellValueFactory(cellData -> new SimpleStringProperty(library.getPatronBag().getFirstname()));
		phoneNumber.setCellValueFactory(cellData -> new SimpleStringProperty(library.getPatronBag().getPhoneNumber()));
		email.setCellValueFactory(cellData -> new SimpleStringProperty(library.getPatronBag().getEmail()));
		table.setItems(data);
		table.getColumns().addAll(id, lastname, firstname, contactInfo);

		root = new VBox(20); 
		root.setSpacing(30);
		root.getChildren().clear();
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		root.setAlignment(Pos.TOP_CENTER);       
		root.getChildren().addAll(menuBar, label, table);
		
		viewMembers.setOnAction(v -> 
		{
			fpp.getViewMembersPane();
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
	public Pane getPane()
	{
		return root;
	}
}