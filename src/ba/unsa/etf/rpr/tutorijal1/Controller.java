package ba.unsa.etf.rpr.tutorijal1;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;



public class Controller implements Initializable {
    private static boolean ispravnost = false;

    public TextField getImeTextfield() {
        return imeTextfield;
    }

    public void setImeTextfield(TextField imeTextfield) {
        this.imeTextfield = imeTextfield;
    }

    public TextField getPrezimeTextfield() {
        return prezimeTextfield;
    }

    public void setPrezimeTextfield(TextField prezimeTextfield) {
        this.prezimeTextfield = prezimeTextfield;
    }

    public TextField getIndeksTextfield() {
        return indeksTextfield;
    }

    public void setIndeksTextfield(TextField indeksTextfield) {
        this.indeksTextfield = indeksTextfield;
    }

    public TextField getJmbgTextfield() {
        return jmbgTextfield;
    }

    public void setJmbgTextfield(TextField jmbgTextfield) {
        this.jmbgTextfield = jmbgTextfield;
    }

    public DatePicker getDatum() {
        return datum;
    }

    public void setDatum(DatePicker datum) {
        this.datum = datum;
    }

    public TextField getTelTextfield() {
        return telTextfield;
    }

    public void setTelTextfield(TextField telTextfield) {
        this.telTextfield = telTextfield;
    }

    public TextField getEmailTextfield() {
        return emailTextfield;
    }

    public void setEmailTextfield(TextField emailTextfield) {
        this.emailTextfield = emailTextfield;
    }

    @FXML
    public TextField imeTextfield;

    @FXML
    private TextField prezimeTextfield;

    @FXML
    private TextField indeksTextfield;


    @FXML
    private TextField jmbgTextfield;

    @FXML
    private DatePicker datum;

    @FXML
    private ComboBox<String> mjestoRodenja;

    @FXML
    private ComboBox<String> odsjek;

    @FXML
    private TextField adresaTextfield;

    @FXML
    private TextField telTextfield;

    @FXML
    private TextField emailTextfield;

    @FXML
    private ComboBox<String> godStudija;

    @FXML
    private ComboBox<String> ciklStudija;

    @FXML
    private ComboBox<String> finansiranje;

    @FXML
    private ComboBox<String> boracke;


    private static String pogresno = "-fx-background-color: red";
    private static String validno = "-fx-background-color: green";


    @FXML
    public boolean imeIspravnost() {

        Control kontrola = imeTextfield;
        boolean validnost = true;

        if (imeTextfield.getText().isEmpty() || imeTextfield.getText().length() > 20) {
            validnost = false;
        }

        return validirajKontrolu(kontrola, "Ime nije validno", validnost);

    }

    @FXML
    public boolean prezimeIspravnost() {

        Control kontrola = prezimeTextfield;
        boolean validnost = true;

        if (prezimeTextfield.getText().isEmpty() || prezimeTextfield.getText().length() > 20) {
            validnost = false;
        }

        return validirajKontrolu(kontrola, "Prezime nije validno", validnost);
    }

    private boolean validirajKontrolu(Control kontrola, String poruka, boolean validnost){
        if(!poruka.isEmpty()){
            poruka = "Polje nije validno";

        }
        if(validnost){
            kontrola.setStyle(validno);
            kontrola.setTooltip(null);
        }
        else {
            kontrola.setStyle(pogresno);
            kontrola.setTooltip(new Tooltip(poruka));
        }

        return validnost;
    }


    @FXML
    public boolean indeksIspravnost() {

        Control kontrola = indeksTextfield;
        boolean validnost = true;

        if (indeksTextfield.getText().length() != 5) {

            validnost = false;
        }

        return validirajKontrolu(kontrola, "Indeks nije duzine 5 brojeva", validnost);
    }

    @FXML
    public boolean jmbgIspravnost() {

        Control kontrola = jmbgTextfield;
        boolean validnost = false;

        if (jmbgTextfield.getText().length() == 13) {
            String s = jmbgTextfield.getText();
            int A = Integer.parseInt(String.valueOf(s.charAt(0)));
            int B = Integer.parseInt(String.valueOf(s.charAt(1)));
            int V = Integer.parseInt(String.valueOf(s.charAt(2)));
            int G = Integer.parseInt(String.valueOf(s.charAt(3)));
            int D = Integer.parseInt(String.valueOf(s.charAt(4)));
            int crta = Integer.parseInt(String.valueOf(s.charAt(5)));
            int E = Integer.parseInt(String.valueOf(s.charAt(6)));
            int é = Integer.parseInt(String.valueOf(s.charAt(7)));
            int Z = Integer.parseInt(String.valueOf(s.charAt(8)));
            int I = Integer.parseInt(String.valueOf(s.charAt(9)));
            int J = Integer.parseInt(String.valueOf(s.charAt(10)));
            int K = Integer.parseInt(String.valueOf(s.charAt(11)));
            int L = Integer.parseInt(String.valueOf(s.charAt(12)));
            int kontrolnaCifra = Integer.parseInt(String.valueOf(s.charAt(12)));
            if (11 - ((7*(A+E) + 6*(B+é) + 5*(V+Z) + 4*(G+I) + 3*(D+J) + 2*(crta+K)) % 11) == kontrolnaCifra) {
                jmbgTextfield.setStyle(validno);
                validnost = true;
            }
        }


        return validirajKontrolu(kontrola, "JMBG nije validan", validnost);
    }

    @FXML
    public boolean datumIspravnost() {

        Control kontrola = datum;
        boolean validnost = true;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");

        LocalDate datumValue = datum.getValue();

        if(datumValue!=null && datumValue.isAfter(LocalDate.now())){
            validnost = false;
            return validirajKontrolu(kontrola, "Datum nije validan", validnost);
        }




        return validirajKontrolu(kontrola, "Datum nije validan", validnost);
    }

    @FXML
    public boolean emailIspravnost(){

        Control kontrola = emailTextfield;
        boolean validnost = false;

        String emailAdresa = emailTextfield.getText();

        String emailRegex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern sablon = Pattern.compile(emailRegex);
        Matcher matcher = sablon.matcher(emailAdresa);

        validnost = matcher.matches();
        return validirajKontrolu(kontrola, "E-mail adresa nije ispravnog formata.", validnost);
    }

    @FXML
    public boolean telefonIspravnost(){

        Control kontrola = telTextfield;
        boolean validnost = false;

        String brojTelefona = telTextfield.getText();

        String telefonRegex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$";
        Pattern sablon = Pattern.compile(telefonRegex);
        Matcher matcher = sablon.matcher(brojTelefona);

        validnost = matcher.matches();
        return validirajKontrolu(kontrola, "Telefon nije ispravan", validnost);
    }

    @FXML
    public void prijavaGreska() throws Exception {

        boolean validnost = true;
        validnost &= imeIspravnost();
        validnost &= prezimeIspravnost();
        validnost &= indeksIspravnost();
        validnost &= datumIspravnost();
        validnost &= jmbgIspravnost();
        validnost &= indeksIspravnost();
        validnost &= telefonIspravnost();
        validnost &= emailIspravnost();
        validnost &= godStudijaIspravnost();
        validnost &= finansiranjeIspravnost();
        validnost &= odsjekIspravnost();
        validnost &= borackeIspravnost();
        validnost &= ciklStudijaIspravnost();


        if (!validnost) {
            try {
                PronadjenaGreska.prikaziGresku("Greška", "Greška");
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else
            ispravnost = true;
    }

    @FXML
    public boolean odsjekIspravnost(){
        return selectIspravnost(odsjek);
    }

    @FXML
    public boolean godStudijaIspravnost(){
        return selectIspravnost(godStudija);
    }

    @FXML
    public boolean ciklStudijaIspravnost(){
        return selectIspravnost(ciklStudija);
    }

    @FXML
    public boolean finansiranjeIspravnost(){
        return selectIspravnost(finansiranje);
    }

    @FXML
    public boolean borackeIspravnost(){
        return selectIspravnost(boracke);
    }

    private boolean selectIspravnost(ComboBox<String> kontrola){

        boolean validnost = true;

        if(kontrola.getValue() == null){
            validnost = false;
        }
        return validirajKontrolu(kontrola, "Opcija nije odabrana", validnost);
    }

    @FXML
    public void prijavi() throws Exception {
        prijavaGreska();
        if (ispravnost) {
            System.out.println("Ime: " + imeTextfield.getText());
            System.out.println("Prezime: " + prezimeTextfield.getText());
            System.out.println("Broj indeksa: " + indeksTextfield.getText());
            System.out.println("JMBG: " + jmbgTextfield.getText());
            System.out.println("Datum rođenja: " + datum.getEditor().getText());
            System.out.println("Mjesto rođenja: " + mjestoRodenja.getValue());
            System.out.println("Kontakt adresa: " + adresaTextfield.getText());
            System.out.println("Broj telefona: " + telTextfield.getText());
            System.out.println("Email adresa: " + emailTextfield.getText());
            System.out.println("Odsjek: " + odsjek.getValue());
            System.out.println("Godina stuija: " + godStudija.getValue());
            System.out.println("Ciklus: " + ciklStudija.getValue());
            System.out.println("Redovan/redovan samofinansirajući: " + finansiranje.getValue());
            System.out.println("Boračke kategorije: " + boracke.getValue());
        }
    }

    private ObservableList<String> mjesta = FXCollections.observableArrayList("Sarajevo", "Zenica", "Tuzla", "Banja Luka", "Bihać", "Mostar");
    private ObservableList<String> odsjeci = FXCollections.observableArrayList("AE", "EE", "Kurs", "TK");
    private ObservableList<String> godineStudija = FXCollections.observableArrayList("Prva", "Druga", "Treća");
    private ObservableList<String> ciklus = FXCollections.observableArrayList("Bachelor", "Master", "Doktorski", "Stručni");
    private ObservableList<String> finansije = FXCollections.observableArrayList("Redovan", "Redovan samofinansirajući");
    private ObservableList<String> nesto = FXCollections.observableArrayList("DA", "NE");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mjestoRodenja.setItems(mjesta);
        odsjek.setItems(odsjeci);
        godStudija.setItems(godineStudija);
        ciklStudija.setItems(ciklus);
        finansiranje.setItems(finansije);
        boracke.setItems(nesto);
    }
}