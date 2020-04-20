package Exercise_34._01;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Statement;

// https://www.chegg.com/homework-help/introduction-to-java-programming-comprehensive-version-10th-edition-chapter-32-problem-1pe-solution-9780133762563
public class AccessAndUpdateStaffTable extends Application {
    private final Button VIEW_BTN;
    private final Button INSERT_BTN;
    private final Button UPDATE_BTN;
    private final Button CLEAR_BTN;
    private final TextField ID_TEXTFIELD;
    private final TextField LASTName_TEXTFIELD;
    private final TextField FIRSTName_TEXTFIELD;
    private final TextField MI_TEXTFIELD;
    private final TextField ADDRESS_TEXTFIELD;
    private final TextField CITY_TEXTFIELD;
    private final TextField STATE_TEXTFIELD;
    private final TextField TELEPHONE_TEXTFIELD;
    private final TextField EMAIL_TEXTFIELD;
    private final Label TOP_LABEL;
    // The statement for processing queries
    private Statement myStatement;



    public AccessAndUpdateStaffTable(Button view_btn, TextField id_textfield) {
        this.VIEW_BTN = new Button( "View" );
        this.INSERT_BTN = new Button( "Insert" );
        this.UPDATE_BTN = new Button( "Update" );
        this.CLEAR_BTN = new Button( "Clear" );
        this.ID_TEXTFIELD = new TextField(  );
        this.LASTName_TEXTFIELD = new TextField(  );
        this.FIRSTName_TEXTFIELD = new TextField(  );
        this.MI_TEXTFIELD = new TextField(  );
        this.ADDRESS_TEXTFIELD = new TextField(  );
        this.CITY_TEXTFIELD = new TextField(  );
        this.STATE_TEXTFIELD = new TextField(  );
        this.TELEPHONE_TEXTFIELD = new TextField(  );
        this.EMAIL_TEXTFIELD = new TextField(  );
        this.TOP_LABEL = new Label(  );
    }

    @SuppressWarnings( "Unused" ) // todo see what is it use for?
    @Override // Override the start method from Application
    public void start(Stage stage) {
        // VBox For adding All HBoxes
        VBox myBox_1 = new VBox( 5 );

        // HBox for ID
        HBox myBox_2 = new HBox( 5 );

        // Adding label and text field to HBox
        boolean addAll = myBox_2.getChildren().addAll( new Label("ID"),
                        ID_TEXTFIELD, new Label("must be 9 characters"));

        // HBox for Last name
        HBox myBox_3 = new HBox( 5 );

        // Adding label and text field to HBox
        boolean addAll1 = myBox_3.getChildren().addAll(
                new Label("Last Name"), LASTName_TEXTFIELD);

        // HBox for First name
        HBox myBox3_2 = new HBox( 5 );

        // Adding label and text field to HBox
        myBox3_2.getChildren().addAll(
                new Label("First Name"), FIRSTName_TEXTFIELD);

        // HBox for MI
        HBox myBox3_3 = new HBox( 5 );

        // Adding label and text field to HBox
        myBox3_3.getChildren().addAll( new Label( "MI" ), MI_TEXTFIELD );

        LASTName_TEXTFIELD.setPrefColumnCount( 10 );
        FIRSTName_TEXTFIELD.setPrefColumnCount( 10 );
        MI_TEXTFIELD.setPrefColumnCount( 3 );

        // HBox for Address
        HBox myBox_4 = new HBox( 5 );

        // Adding label and text field to HBox
        boolean addAll2 = myBox_2.getChildren().addAll(
                new Label("Address"), ADDRESS_TEXTFIELD );

        // HBox for City and State
        HBox myBox_5 = new HBox( 5 );

        // Adding label and text field to HBox
        boolean addAll3 = myBox_5.getChildren().addAll(
                    new Label( "City" ), CITY_TEXTFIELD,
                    new Label( "State"), STATE_TEXTFIELD);

        // HBox for Telephone number
        HBox myBox_6 = new HBox( 5 );

        // Adding label and text field to HBox
        boolean addAll4 = myBox_6.getChildren().addAll(
                new Label("Telephone"), TELEPHONE_TEXTFIELD);


        // Adding all HBoxes in VBox
        boolean addAll5 = myBox_1.getChildren().addAll( myBox3_2, myBox_3,
                                    myBox3_2, myBox3_3,myBox_4,myBox_5,myBox_6);

        // HBox for all buttons
        HBox myBox_7 = new HBox( 5 );

        boolean addAll6 = myBox_7.getChildren().addAll( VIEW_BTN, INSERT_BTN,
                                                            UPDATE_BTN, CLEAR_BTN);

        myBox_7.setAlignment( Pos.CENTER );

        BorderPane mainPane = new BorderPane(  );
        mainPane.setCenter(myBox_1);
        mainPane.setTop( TOP_LABEL );
        mainPane.setBottom( myBox_7 );


        // Set mainPane in the scene
        Scene scene = new Scene( mainPane );
        stage.setTitle( "Exercise32_01" );
        stage.setScene( scene );
        stage.show();

        //init_DB(); // todo this methd



    }
}
