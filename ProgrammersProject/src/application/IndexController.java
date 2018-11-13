package application;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Programmer;

public class IndexController {
	
	
	@FXML 
	private TextField tfFile;
	@FXML
	private TextField tfSearchPro;
	@FXML
	private TextField tfSearchPar;
	@FXML
	private Button btloadFile;
	@FXML
	private Button btsearchFile;
	@FXML
	private Button btsearchPro;
	@FXML
	private Button btsearchPar;
	@FXML
	private Button btPaintProgra;
	@FXML
	private Button btPaintPar;	
	@FXML
	private Label lbLoad;
	@FXML
	private Label lbFindPro;
	@FXML
	private Label lbFindPar;
	@FXML
	private Label lbTimeFindPro;
	@FXML
	private Label lbTimefindPar;
	@FXML
	private Label lbName;
	@FXML
	private Label lbLastname;
	@FXML
	private Label lbID;
	@FXML
	private Label lbEmail;
	@FXML
	private Label lbGender;
	
	
	
	
	
	private File file;
	
	
	
	public IndexController() {
		
		
	}
	
	public void initialize() {
		clickedSearchFile();
		loadProgrammers(); 
		searchProgrammer();
	}
	
	
	public void clickedSearchFile() {
		btsearchFile.setOnMouseClicked((MouseEvent)->{
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open file");
			//File savedFile = fileChooser.showOpenDialog(null);
			file = fileChooser.showOpenDialog(null);
			if(file!=null) {
				tfFile.setText(file.getAbsolutePath());
			}
				
		});
	}
	
	public void loadProgrammers() {
		btloadFile.setOnMouseClicked((MouseEvent)->{
			try {	
				if(file!=null)
				Main.getEvent().setFile(file);
				Main.getEvent().loadProgrammers();
			}
			catch(NullPointerException e) {
//				Alert alert = new Alert(AlertType.ERROR);
//				alert.setTitle("Numbers");
//				alert.setHeaderText("Exception");
//				alert.setContentText("Please insert numbers");
//				alert.showAndWait();
				e.printStackTrace();
			}	
		});
	}
	
	
	public void searchProgrammer() {
		btsearchPro.setOnMouseClicked((MouseEvent)->{
			if(tfSearchPro.getText()!="") {
				
				try {
					Programmer programmer = Main.getEvent().searchProgrammer(tfSearchPro.getText(),Main.getEvent().getRoot());
					lbName.setText("Name:  " + programmer.getFirst_name());
					lbLastname.setText("Lastname:  " + programmer.getLast_name());
					lbEmail.setText("Email: " + programmer.getEmail());
					lbID.setText("ID: " + programmer.getId());
					lbGender.setText("Gender: " + programmer.getGender());
					lbTimeFindPro.setText(""+ Main.getEvent().getTimeProgrammer() +" ms");
				}catch(NumberFormatException e){
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Numbers");
					alert.setHeaderText("Exception");
					alert.setContentText("Please insert numbers");
					alert.showAndWait();
				}catch(NullPointerException e) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Not Found");
					alert.setHeaderText("Exception");
					alert.setContentText("Programmer not found");
					alert.showAndWait();
				}				
			}
		});
	}
	
	
	
}
