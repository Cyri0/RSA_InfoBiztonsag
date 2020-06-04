package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField inputTextField;
    @FXML
    TextField resultText;

    MyRSA rsa = null;

    @FXML
    private void code(ActionEvent event){
        String plainText = inputTextField.getText();
        resultText.setText(rsa.encryption(plainText));
    }

    @FXML
    private void decode(ActionEvent event){
        System.out.println("Decode");
        String text = inputTextField.getText();
        BigInteger result = rsa.decryption(new BigInteger(text));
        Charset c = StandardCharsets.UTF_8;
        String decText = new String(result.toByteArray(), c);
        resultText.setText(decText);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rsa = new MyRSA();
        System.out.println("Kulcs: " + rsa.getTheKey());
        System.out.println("Titkos kulcs: " + rsa.getSecretKey());
    }
}
