package org.openjfx;

import java.util.LinkedList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * This function is in charge of displaying the original form for the user.
 * @author Wills Jarquin.
 */

public class DataEntryObject extends Application{
	
	/** A new BO to start when submitted */
	BusinessObject bo = new BusinessObject();
	
	/**
	 * This method shows a screen.
	 * @param bo is the bo to rendered.
	 */
	public void showScreen(BusinessObject bo) {
		this.bo = bo;
		launch();
	}
	
	/**
	 * This method calls a window to start rendering the form
	 * @param arg0 is the stage to be rendered. 
	 */
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		arg0.setTitle("Worker Petition Form");
		arg0.show();
	}
	
	/**
	 * This method grabs a stage and the objects and renders into the screen.
	 * @param boListToReview is the list of BOs to review.
	 * @param boListToApprove is the list of BOs to approve.
	 * @param stage is the stage to be rendered.
	 * @param existingRequest is whether or not this is a new or an existing request.
	 * @return a scene to render.
	 */
	public Scene getScene(LinkedList<BusinessObject> boListToReview, LinkedList<BusinessObject> boListToApprove, Stage stage, boolean existingRequest) {
		if(existingRequest)
			this.bo = boListToReview.getFirst();
		this.bo = bo;
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		Scene scene = new Scene(grid, 700, 800);
		
		Text sceneTitle = new Text("Worker Petition By US Approved Employer Form");
		sceneTitle.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 20));
		grid.add(sceneTitle, 0, 0, 2, 1);
		
		Line line0 = new Line();
		line0.setStartX(100);
		line0.setStartY(100);
		line0.setEndX(500);
		line0.setEndY(100);
		grid.add(line0, 0, 1);
		
		Text subTitle_1 = new Text("Petitioner's Info");
		subTitle_1.setFont(Font.font(STYLESHEET_CASPIAN, 18));
		grid.add(subTitle_1, 0, 2);
		
		Label pFirstNameLabel = new Label("First Name: ");
		pFirstNameLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(pFirstNameLabel, 0, 3);
		TextField pFirstNameInput = new TextField();
		pFirstNameInput.setText(this.bo.getPetitionerFirstName());
		grid.add(pFirstNameInput, 1, 3);
		
		Label pLastNameLabel = new Label("Last Name: ");
		pLastNameLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(pLastNameLabel, 0, 4);
		TextField pLastNameInput = new TextField();
		pLastNameInput.setText(this.bo.getPetitionerLastName());
		grid.add(pLastNameInput, 1, 4);
		
		Label companyNameLabel = new Label("Company Name: ");
		companyNameLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(companyNameLabel, 0, 5);
		TextField companyNameInput = new TextField();
		companyNameInput.setText(this.bo.getBusinessName());
		grid.add(companyNameInput, 1, 5);
		
		Label pAddressLabel = new Label("Address:  ");
		pAddressLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(pAddressLabel, 0, 6);
		TextField pAddressInput = new TextField();
		pAddressInput.setText(this.bo.getAddress());
		grid.add(pAddressInput, 1, 6);
		
		
		Label pPhoneNumberLabel = new Label("Phone Number: ");
		pPhoneNumberLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(pPhoneNumberLabel, 0, 7);
		TextField pPhoneNumberInput = new TextField();
		pPhoneNumberInput.setText(this.bo.getPhoneNumber());
		grid.add(pPhoneNumberInput, 1, 7);
		
		
		Label pEmailLabel = new Label("Last Name: ");
		pEmailLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(pEmailLabel, 0, 8);
		TextField pEmailInput = new TextField();
		pEmailInput.setText(this.bo.getEmail());
		grid.add(pEmailInput, 1, 8);
		
		Label einSsnLabel = new Label("EIN/ITIN/SSN: ");
		einSsnLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(einSsnLabel, 0, 9);
		TextField einSsnInput= new TextField();
		einSsnInput.setText(this.bo.getEIN());
		grid.add(einSsnInput, 1, 9);	
		
		Line line1 = new Line();
		line1.setStartX(100);
		line1.setStartY(100);
		line1.setEndX(500);
		line1.setEndY(100);
		grid.add(line1, 0, 10);
		
		
		/**
		 * Beneficiary's fields
		 */
		
		Text subTitle_2 = new Text("Beneficiary's Info");
		subTitle_2.setFont(Font.font(STYLESHEET_CASPIAN, 18));
		grid.add(subTitle_2, 0, 11, 2, 1);
		
		Label bFirstNameLabel = new Label("First Name: ");
		bFirstNameLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(bFirstNameLabel, 0, 12);
		TextField bFirstNameInput = new TextField();
		bFirstNameInput.setId("bFirstNameInput");
		bFirstNameInput.setText(this.bo.getBeneficiaryFirstName());
		grid.add(bFirstNameInput, 1, 12);
		
		Label bLastNameLabel = new Label("Last Name: ");
		bLastNameLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(bLastNameLabel, 0, 13);
		TextField bLastNameInput = new TextField();
		bLastNameInput.setText(this.bo.getBeneficiaryLastName());
		grid.add(bLastNameInput, 1, 13);
		
		Label dobLabel = new Label("Date Of Birth: ");
		dobLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(dobLabel, 0, 14);
		DatePicker dobInput = new DatePicker();
		bLastNameInput.setText(this.bo.getBeneficiaryDOB());
		grid.add(dobInput, 1, 14);
		
		Label genderLabel = new Label("Gender:  ");
		genderLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(genderLabel, 0, 15);
		TextField genderInput = new TextField();
		genderInput.setText(this.bo.getBeneficiaryGender());
		grid.add(genderInput, 1, 15);
		
		
		Label originLabel = new Label("Country Of Origin: ");
		originLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(originLabel, 0, 16);
		TextField originInput = new TextField();
		originInput.setText(this.bo.getBeneficiaryCountryOfBirth());
		grid.add(originInput, 1, 16);
		
		
		Label bAddressLabel = new Label("Address: ");
		bAddressLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(bAddressLabel, 0, 17);
		TextField bAddressInput = new TextField();
		bAddressInput.setText(this.bo.getBeneficiaryAddress());
		grid.add(bAddressInput, 1, 17);
		
		Label passportExpirationDateLabel = new Label("Passport Expiration Date: ");
		passportExpirationDateLabel.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 14));
		grid.add(passportExpirationDateLabel, 0, 18);
		DatePicker passportExpirationDateInput= new DatePicker();
		grid.add(passportExpirationDateInput, 1, 18);
		
		Line line2 = new Line();
		line2.setStartX(100);
		line2.setStartY(100);
		line2.setEndX(500);
		line2.setEndY(100);
		grid.add(line2, 0, 19);
		
		Button submitButton = new Button();
		submitButton.setText("Submit");
		submitButton.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 18));
		submitButton.setStyle("-fx-background-color: #6dbf84");
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				boolean pass = true;
				BusinessObject bo = new BusinessObject();
				bo.setPetitionerFirstName(pFirstNameInput.getText());
				bo.setPetitionerLastName(pLastNameInput.getText());
				bo.setBusinessName(companyNameInput.getText());
				bo.setAddress(pAddressInput.getText());
				bo.setPhoneNumber(pPhoneNumberInput.getText());
				bo.setEmail(pEmailInput.getText());
				bo.setEIN(einSsnInput.getText());
				bo.setBeneficiaryFirstName(bFirstNameInput.getText());
				bo.setBeneficiaryLastName(bLastNameInput.getText());
				bo.setBeneficiaryDOB(""+dobInput.getValue());
				bo.setBeneficiaryGender(genderInput.getText());;
				bo.setBeneficiaryCountryOfBirth(originInput.getText());
				bo.setBeneficiaryAddress(bAddressInput.getText());
				bo.setBeneficiaryPassportExpirationDate(""+passportExpirationDateInput.getValue());
				if(existingRequest) {
					boListToReview.set(0, bo);
					ReviewObject re = new ReviewObject();
					stage.setScene(re.getScene(boListToReview, boListToApprove, stage));
				}
				else {
					boListToReview.add(bo);
					WorkFlowObject wf = new WorkFlowObject();
					stage.setScene(wf.getScene(stage, boListToReview, boListToApprove));
				}
				
			}
		});
		grid.add(submitButton, 0, 20);
		
		Button goBackButton = new Button();
		goBackButton.setText("Main Menu");
		goBackButton.setFont(Font.font(STYLESHEET_CASPIAN, FontWeight.BOLD, 18));
		goBackButton.setStyle("-fx-background-color: #afb839");
		goBackButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				WorkFlowObject wf = new WorkFlowObject();
				stage.setScene(wf.getScene(stage, boListToReview, boListToApprove));
			}
		});
		grid.add(goBackButton, 1, 20);
		return scene;
	}	
	
}
