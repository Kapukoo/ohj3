package fi.tuni.prog3.calc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class Calculator extends Application {

    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Calculator");
        
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 350,400);
        stage.setScene(scene);
        
        // labessit vasen
        Label first = new Label ("First operand:");
        grid.add(first,0,0);
        Label second = new Label ("Second operand:");
        grid.add(second,0,1);
        first.setId("labeldOp1");
        second.setId("labeldOp2");
        
        // teksti fieldit oikeel
        TextField in1 = new TextField();
        grid.add(in1, 1,0);
        TextField in2 = new TextField();
        grid.add(in2, 1,1);
        in1.setId("fieldOp1");
        in2.setId("fieldOp2");
        
        // sit buttonssei
        HBox hbBtn = new HBox(10);
        grid.add(hbBtn, 1, 2);

        Button add = new Button("Add");
        hbBtn.getChildren().add(add);

        Button sub = new Button("Subtract");
        hbBtn.getChildren().add(sub);

        Button mul = new Button("Multiply");
        hbBtn.getChildren().add(mul);

        Button div = new Button("Divide");
        hbBtn.getChildren().add(div);
        add.setId("btnAdd");
        sub.setId("btnSub");
        mul.setId("btnMul");
        div.setId("btnDiv");
        
        // result paneelit ja jutut
        Label result = new Label ("Result:");
        grid.add(result,0,3);
        Label resultField = new Label("");
        resultField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        grid.add(resultField, 1, 3);
        result.setId("labelRes");
        resultField.setId("fieldRes");
        
        
        // sit vähä häppenikiä
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(in1.getText());
                var b = Double.parseDouble(in2.getText());
                var r = a+b;
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        sub.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(in1.getText());
                var b = Double.parseDouble(in2.getText());
                var r = a-b;
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        mul.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(in1.getText());
                var b = Double.parseDouble(in2.getText());
                var r = a*b;
                resultField.setText(String.format("%.2f", r));
            }
        });
        
        div.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                var a = Double.parseDouble(in1.getText());
                var b = Double.parseDouble(in2.getText());
                var r = 0.0;
                if (b != 0){ r = a/b; }
                resultField.setText(String.format("%.1f", r));
            }
        });
        
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}