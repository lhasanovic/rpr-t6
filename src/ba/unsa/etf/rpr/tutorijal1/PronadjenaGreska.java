package ba.unsa.etf.rpr.tutorijal1;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

public class PronadjenaGreska {

    public static void prikaziGresku(String naziv, String poruka) {
        Stage prozor = new Stage();

        prozor.initModality(Modality.APPLICATION_MODAL);
        prozor.setTitle(naziv);
        prozor.setMinWidth(250);
        prozor.setMinHeight(300);

        Label lbl = new Label();
        lbl.setText(poruka);

        Button closeBtn = new Button("Zatvori");
        closeBtn.setOnAction(e -> prozor.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(lbl, closeBtn);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        prozor.setScene(scene);
        prozor.showAndWait();
    }

}
