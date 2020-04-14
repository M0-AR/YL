package Exercise_34._01;

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    }

    @SuppressWarnings( "Unused" ) // todo see what is it use for?
    @Override // Override the start method from Application
    public void start(Stage stage) throws Exception {

    }
}
