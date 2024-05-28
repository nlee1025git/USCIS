package org.openjfx;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This class creates the object that handles the workflow of the system.
 * 
 * @author Wills Jarquin
 *
 */
public class WorkFlowObject extends Application{
	
	String id;
	
	/**
	 * This object generates an instance of a WorkFlowObject.
	 */
	public WorkFlowObject() {
		this.id = "00001";
	}

	/**
	 * This object generates an instance of a WorkFlowObject.
	 * 
	 * @param id is the id to assign to the WorkFlowObject.
	 */
	public WorkFlowObject(String id) {
		this.id = id;
	}
	
	/**
	 * This method runs the program.
	 * @param args are CLI commands that can be passed when ran.
	 */
	public static void main(String args[]) {
		launch();
	}
	
	/**
	 * This method starts a screen and initializes the Linked List that will be used through the session.
	 * @param arg0 is the stage to be rendered
	 * @throws an exception if error is detected.
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		BusinessObject bo = getTester();
		LinkedList<BusinessObject> listToReview = new LinkedList<>();
		LinkedList<BusinessObject> listToApprove = new LinkedList<>();
		listToReview.add(bo);
		arg0.setTitle("Worker Petition Form");
		arg0.setScene(getScene(arg0, listToReview, listToApprove));
		arg0.show();
	}
	
	/**
	 * This method loads a data entry object and passes the list of items in the current workflow.
	 * @param stage is the stage where we are rendering screens.
	 * @param listToReview is the list of BOs to reviews.
	 * @param listToApprove is the list of BOs to approve.
	 */
	public static void loadDataEntry(Stage stage, LinkedList<BusinessObject> listToReview, LinkedList<BusinessObject> listToApprove) {
		DataEntryObject deObjext = new DataEntryObject();
		stage.setScene(deObjext.getScene(listToReview, listToApprove, stage, false));
	}
	
	/**
	 * This method loads a review object and passes the list of items in the current workflow.
	 * @param stage is the stage where we are rendering screens.
	 * @param listToReview is the list of BOs to reviews.
	 * @param listToApprove is the list of BOs to approve.
	 */
	public static void loadReview(Stage stage, LinkedList<BusinessObject> listToReview, LinkedList<BusinessObject> listToApprove) {
		ReviewObject reObjext = new ReviewObject();
		stage.setScene(reObjext.getScene(listToReview, listToApprove, stage));
	}
	
	/**
	 * This method loads an approval object and passes the list of items in the current workflow.
	 * @param stage is the stage where we are rendering screens.
	 * @param listToReview is the list of BOs to reviews.
	 * @param listToApprove is the list of BOs to approve.
	 */
	public static void loadApproval(Stage stage, LinkedList<BusinessObject> listToReview, LinkedList<BusinessObject> listToApprove) {
		ApprovalObject apObjext = new ApprovalObject();
		stage.setScene(apObjext.getScene(listToReview, listToApprove, stage));
	}
	
	/**
	 * This method gets a default BO for testing purposes
	 * @return a tester BO.
	 */
	public static BusinessObject getTester() {
		BusinessObject petition = new BusinessObject();
		petition.setPetitionerFirstName("Test");
		petition.setPetitionerLastName("Testingson");
		petition.setBusinessName("TESTING TESTERS LLC");
		petition.setAddress("123 Main St, Fairfax, VA");
		petition.setPhoneNumber("123456789");
		petition.setEmail("testingson@gmail.com");
		petition.setEIN("1234568");
		petition.setBeneficiaryFirstName("Worker");
		petition.setBeneficiaryLastName("Testingson");
		petition.setBeneficiaryDOB("09/21/1998");
		petition.setBeneficiaryGender("Male");;
		petition.setBeneficiaryA_Number("12536");
		petition.setBeneficiaryCountryOfBirth("Spain");
		petition.setBeneficiaryAddress("123 MainInSpanish St, Madrid, Espana");
		petition.setBeneficiaryArrivalDate("09/21/2025");
		petition.setBeneficiaryPassportExpirationDate("09/21/2028");
		return petition;
	}
	
	/**
	 * This method grabs a stage and the objects and renders into the screen.
	 * @param stage where the screen will be rendered.
	 * @param boListToReview the list of BOs to review.
	 * @param boListToApprove the list of BOs to approve
	 * @return a scene to render.
	 */
	public Scene getScene(Stage stage, LinkedList<BusinessObject> boListToReview, LinkedList<BusinessObject> boListToApprove) {

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(grid, 700, 800);		
		
		Button enterNewBO = new Button();
		enterNewBO.setText("Enter a New Request");
		enterNewBO.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 18));
		enterNewBO.setStyle("-fx-background-color: #6dbf84");
		enterNewBO.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadDataEntry(stage, boListToReview, boListToApprove);
				boolean pass = true;
			}
		});
		grid.add(enterNewBO, 0, 20);
		
		Button reviewBo = new Button();
		reviewBo.setText("Review Requests");
		reviewBo.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 18));
		reviewBo.setStyle("-fx-background-color: #6dbf84");
		reviewBo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(boListToReview.size() <= 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("REQUEST APPROVAL/DENIAL");
					alert.setContentText("No items to review");
					alert.showAndWait();
				}
				else
					loadReview(stage, boListToReview, boListToApprove);
			}
		});
		grid.add(reviewBo, 1, 20);
		
		Button approveBo = new Button();
		approveBo.setText("Approve Requests");
		approveBo.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 18));
		approveBo.setStyle("-fx-background-color: #6dbf84");
		approveBo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(boListToApprove.size() <= 0) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("REQUEST APPROVAL/DENIAL");
					alert.setContentText("No items to approve");
					alert.showAndWait();
				}
				else
					loadApproval(stage, boListToReview, boListToApprove);
			}
		});
		grid.add(approveBo, 2, 20);
		return scene;
	}
	
	/**
	 * This method assigns an Id to the workflow object.
	 * @param newId is the new id to set.
	 */
	public void setId(String newId) {
		this.id = newId;
	}
	
	/**
	 * This method assigns an Id to the workflow object.
	 * @param newId is the new id to set.
	 */
	public String getId() {
		return this.id;
	}
}
