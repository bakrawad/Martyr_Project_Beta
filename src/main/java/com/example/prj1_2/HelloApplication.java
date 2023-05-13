package com.example.prj1_2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HelloApplication extends Application {
    static DLinkedList<Location>Dlist = new DLinkedList<>();
    static SLinkedList<Martyr>list = new SLinkedList<>();
    static NodeD<Location>temp = Dlist.getHead();
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane bpm = new BorderPane();
        Image image = new Image("quds.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        Background background = new Background(backgroundImage);
        TabPane tabPane = new TabPane();
        bpm.setCenter(tabPane);
        bpm.setBackground(background);
        Button btLodthefile = new Button("Load The File");
        bpm.setId("bpm");
        //bp.setStyle("-fx-background-color: #76817f;");
        Button btsavetofile = new Button("Save to File");
        btsavetofile.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
        btLodthefile.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 13));
        HBox hBoxtop = new HBox(10);
        hBoxtop.setAlignment(Pos.CENTER);
        HBox hBoxdow = new HBox(10);
        hBoxdow.setAlignment(Pos.CENTER);
        bpm.setTop(hBoxtop);
        bpm.setBottom(hBoxdow);
        HBox hBoxcent = new HBox(10);
        hBoxcent.setAlignment(Pos.CENTER);
       // bpm.setCenter(hBoxcent);
        hBoxdow.getChildren().addAll(btsavetofile);
        hBoxtop.getChildren().addAll(btLodthefile);

        //Buttons to go next and Previous in linked-list

        Button btnext = new Button("Next");
        btnext.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        Button btprev = new Button("Previous");
        btprev.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btnext.setDisable(true);
        btprev.setDisable(true);


        //scene two for Location Screen
        GridPane gpL = new GridPane();
        Tab sceneL = new Tab("Location Screen");
        gpL.setVgap(5);
        gpL.setHgap(5);
        sceneL.setContent(gpL);
        tabPane.getTabs().add(sceneL);
        Label lbInsert = new Label("Insert New Location");
        lbInsert.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbupdate = new Label("Update the Location");
        lbupdate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Button btsearch = new Button("Search");
        btsearch.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbdelete = new Label("Delete the Location");
        lbdelete.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lblocationscreen =new Label("Location Screen");
        lblocationscreen.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22));
        Label lbselect =new Label("Select the Location");
        lbselect.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        HBox hBoxlb = new HBox();
        hBoxlb.setAlignment(Pos.CENTER);
        hBoxlb.getChildren().addAll(lblocationscreen);
        ComboBox comboBox = new ComboBox<>();
        gpL.add(lbInsert,0,3);
        gpL.add(lbupdate,0,4);
        gpL.add(btsearch,3,4);
        gpL.add(lbdelete,0,5);
        gpL.add(hBoxlb,0,0);
        gpL.add(lbselect,0,6);
        gpL.add(comboBox,1,6);
        TextField txInsert = new TextField();
        TextField txupdate = new TextField();

        Button btupdate = new Button("Update");
        btupdate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Button btinsertL = new Button("Insert");
        btinsertL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Button btdeleteL = new Button("Delete");
        btdeleteL.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        gpL.add(btinsertL,2,3);
        gpL.add(btdeleteL,2,5);
        btupdate.setDisable(true);
        gpL.add(btupdate,5,4);
        txupdate.setEditable(false);
        TextField txsearch = new TextField();
        TextField txdelete = new TextField();
       // gpL.setStyle("-fx-background-color: #76817f;");
        gpL.setBackground(background);
        gpL.add(txInsert,1,3);
        gpL.add(txupdate,1,4);
        gpL.add(txsearch,2,4);
        gpL.add(txdelete,1,5);

        btinsertL.setOnAction(e ->{
            System.out.println("ss");
            String text = txInsert.getText();
            if (ishasInt(text)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Enter a valid Location ");
                alert.show();
                txInsert.clear();
                return;
            }
            if (!Dlist.findLoc(new Location(text))){
                Dlist.insertSorted(new Location(text));
                for (int i = 0; i <list.size() ; i++) {
                    Martyr m = new Martyr(list.get(i).getName(), list.get(i).getAge(), list.get(i).getLocation(), list.get(i).getDate(), list.get(i).getGender());
                    if (list.get(i).getLocation().equals(text)) {
                        Dlist.findNode(new Location(text)).getData().getMartyr().Insertsorted(m);
                    }
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Done !!");
                alert.setContentText("the Location "+text+" has been Added !!");
                alert.show();
                txInsert.clear();
                comboBox.getItems().add(text);
                btnext.setDisable(false);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("the Location "+text+" already Exists");
                alert.show();
            }
        });

        btsearch.setOnAction(e ->{
            String text = txsearch.getText();
            System.out.println("hi");
            if (ishasInt(text)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Enter a valid Location ");
                alert.show();
                txsearch.clear();
                return;
            }
            if (Dlist.findLoc(new Location(text))){
                btupdate.setDisable(false);
                txupdate.setEditable(true);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Done !!");
                alert.setContentText("the Location "+text+" has been found !!");
                alert.show();
            }
        });
        btupdate.setOnAction(e ->{
            if (!btupdate.isDisable()){
                String text = txsearch.getText();
                String up = txupdate.getText();
                if (ishasInt(up)){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error !!");
                    alert.setContentText("Enter a valid Location ");
                    alert.show();
                    txupdate.clear();
                    return;
                }
                if (!Dlist.findLoc(new Location(text))){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR !!");
                    alert.setContentText("the Location "+text+" not found !!");
                    alert.show();
                    txsearch.clear();
                    txupdate.clear();
                    return;
                }
                Dlist.UpdateLoc(new Location(text),new Location(up));
                for (int i = 0; i <list.size() ; i++) {
                    Martyr m = new Martyr(list.get(i).getName(), list.get(i).getAge(), list.get(i).getLocation(), list.get(i).getDate(), list.get(i).getGender());
                    if (list.get(i).getLocation().equals(up)) {
                        Dlist.findNode(new Location(up)).getData().getMartyr().Insertsorted(m);
                    }
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Done !!");
                alert.setContentText("the Location "+text+" has been updated !!");
                alert.show();
                txsearch.clear();
                txupdate.clear();
                btupdate.setDisable(true);
                txupdate.setEditable(false);
                comboBox.getItems().remove(text);
                comboBox.getItems().add(up);

            }
        });

        btdeleteL.setOnAction(e ->{
            String text = txdelete.getText();
            if (ishasInt(text)){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Enter a valid Location ");
                alert.show();
                txdelete.clear();
                return;
            }
            if (Dlist.findLoc(new Location(text))){
                Dlist.Deleteloc(new Location(text));
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Done !!");
                alert.setContentText("the Location "+text+" has been Deleted !!");
                alert.show();
                txdelete.clear();
                comboBox.getItems().remove(text);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("the Location "+text+" is not found !!");
                alert.show();
            }
        });

        //scene three for Martyrs Screen
        GridPane gpM = new GridPane();
        Tab sceneM = new Tab("Martyrs Screen");
        tabPane.getTabs().add(sceneM);
        sceneM.setContent(gpM);
        gpM.setHgap(5);
        gpM.setVgap(5);
        Label lbInsertm = new Label("Insert New Martyrs");
        lbInsertm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbupdatem = new Label("Update the Martyrs");
        lbupdatem.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Button btsearchm = new Button("Search");
        btsearchm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbdeletem = new Label("Delete the Martyrs");
        lbdeletem.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lblocationscreenm =new Label("Martyrs Screen");
        lblocationscreenm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22));
        Label lbselectm =new Label("Search");
        lbselectm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        HBox hBoxlbm = new HBox();
        hBoxlbm.setAlignment(Pos.CENTER);
        hBoxlbm.getChildren().addAll(lblocationscreenm);
        TextField txSearchm = new TextField();
        Button btSearchm1 = new Button("Search");
        btSearchm1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        TextField txage = new TextField();
        TextField txlocation = new TextField();
        TextField txdate = new TextField();
        TextField txgender = new TextField();


        gpM.add(lbInsertm,0,3);
        gpM.add(lbupdatem,0,4);
        //gpM.add(btsearchm,3,4);
        gpM.add(lbdeletem,0,5);
        gpM.add(hBoxlbm,0,0);
        gpM.add(lbselectm,0,6);
        gpM.add(txSearchm,1,6);//
        gpM.add(btSearchm1,2,6);
        TextField txname = new TextField();
        TextField txupdatem = new TextField();



        Button btinsertLm = new Button("Insert");
        btinsertLm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Button btdeleteLm = new Button("Delete");
        btdeleteLm.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        //gpM.add(btinsertLm,6,3);
        gpM.add(btdeleteLm,2,5);
        HBox vBox2=new HBox(5);
        vBox2.getChildren().addAll(btsearchm);
        gpM.add(vBox2,2,4);

        //gpM.add(btupdatem,5,4);
        txupdatem.setEditable(false);
        TextField txsearchm = new TextField();
        TextField txdeletem = new TextField();
        //gpM.setStyle("-fx-background-color: #76817f;");
        txname.setMaxSize(100,80);
        txage.setMaxSize(100,80);
        txlocation.setMaxSize(100,80);
        txdate.setMaxSize(100,80);
        txgender.setMaxSize(80,80);
        txSearchm.setMaxSize(100,80);
        txsearchm.setMaxSize(100,80);
        txdeletem.setMaxSize(100,80);
        txupdatem.setMaxSize(100,80);
        gpL.setBackground(background);
        gpM.add(txname,1,3);
       // gpM.add(txage,2,3);
        //gpM.add(txlocation,3,3);
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(txage,txdate,txgender,btinsertLm);
        gpM.add(hBox,2,3);

        Label lbname = new Label("Name");
        lbname.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbage = new Label("Age");
        lbage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        //Label lbLocation = new Label("Location");
        //lbLocation.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbdate = new Label("                    Date");
        lbdate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbGender = new Label("            Gender");
        lbGender.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));



        HBox hBox1 = new HBox(5);
        hBox1.getChildren().addAll(lbdate,lbGender);
        gpM.add(hBox1,2,2);
        gpM.add(lbage,2,2);
        gpM.add(lbname,1,2);



        gpM.add(txsearchm,1,4);
        gpM.add(txdeletem,1,5);

        btinsertLm.setOnAction(e->{
            String name = txname.getText();
            String age = txage.getText();
            String location = comboBox.getValue()+"";
            String date = txdate.getText();
            String gender = txgender.getText();
            if (location.equals("")|| location.equals(null + "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("PLease select the Location");
                alert.show();
                return;
            }
            if (!ishasInt(age)||isValidD(date)){
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                try {
                    Date date1 = formatter.parse(date);
                    Martyr m = new Martyr(name,Integer.parseInt(age),location,date1,gender.charAt(0));
                    Dlist.findNode(new Location(location)).getData().getMartyr().Insertsorted(m);
                    System.out.println(Dlist.findNode(new Location(location)).getData().getMartyr().printS());
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
                catch (NullPointerException e1){
                    System.out.println(e1);
                }
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Done !!");
                alert.setContentText("the Martyr has been Added !!");
                alert.show();

            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Enter a Valid value!!");
                alert.show();
            }
            txname.clear();
            txage.clear();
            txdate.clear();
            txgender.clear();
        });





        //stage for Update the Martyr

        Stage stage1 = new Stage();
        BorderPane pane = new BorderPane();
        pane.setBackground(background);
        Scene sceneupdate = new Scene(pane,600,250);
        VBox vBox1 = new VBox(5);
        HBox hBox2 = new HBox(5);
        //vBox1.getChildren().add(hBox2);
        TextField txname1 =new TextField();
        TextField txage1 = new TextField();

        TextField txdate1 = new TextField();
        TextField txgender1 = new TextField();
        hBox2.getChildren().addAll(txname1,txage1,txdate1,txgender1);
        Button btUp = new Button("Update");
        btUp.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        //vBox1.getChildren().add(btUp);
        pane.setCenter(vBox1);
        vBox1.setAlignment(Pos.CENTER);
        hBox2.setAlignment(Pos.CENTER);
        stage1.setScene(sceneupdate);
        stage1.setTitle("Update the Martyr");
        Label lbname1 = new Label(" Name");
        lbname1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbage1 = new Label("                   Age");
        lbage1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbLocation1 = new Label("                  Location");
        lbLocation1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbdate1 = new Label("                       Date");
        lbdate1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        Label lbGender1 = new Label("                     Gender");
        lbGender1.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));

        HBox hBox3 = new HBox(5);
        hBox3.getChildren().addAll(lbname1,lbage1,lbdate1,lbGender1);
        vBox1.getChildren().addAll(hBox3,hBox2,btUp);


        btsearchm.setOnAction(e->{
            String location = comboBox.getValue()+"";
            String text= txsearchm.getText();
            if (location.equals("")|| location.equals(null + "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("PLease select the Location");
                alert.show();
                return;
            }for (int i = 0; i < Dlist.findNode(new Location(location)).getData().getMartyr().size(); i++) {
                if (Dlist.findNode(new Location(location)).getData().getMartyr().get(i).getName().equals(text)){
                    stage1.show();

                    Martyr m = Dlist.findNode(new Location(location)).getData().getMartyr().get(i);

                    //Martyr newd = new Martyr()

                }
            }
        });

        btUp.setOnAction(e ->{
            String location = comboBox.getValue()+"";
            String text= txsearchm.getText();
            String name = txname1.getText();
            String age = txage1.getText();
            String date = txdate1.getText();
            String gender = txgender1.getText();

            for (int i = 0; i < Dlist.findNode(new Location(location)).getData().getMartyr().size(); i++) {
                if (Dlist.findNode(new Location(location)).getData().getMartyr().get(i).getName().equals(text)){
                    stage1.show();

                    Martyr m = Dlist.findNode(new Location(location)).getData().getMartyr().get(i);

                    String named = m.getName();
                    String aged = m.getAge()+"";
                    String dated = m.getDate()+"";
                    String genderd = m.getGender()+"";
                    if (name.equals("")|| name.equals(null + "")){
                        name=named;

                    }if (age.equals("")|| age.equals(null + "")){
                        age=aged;

                    }if (date.equals("")|| date.equals(null + "")){
                        date=dated;

                    }if (gender.equals("")|| gender.equals(null + "")){
                        gender=genderd;

                    }
                    if (!ishasInt(age)||isValidD(date)){
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                        try {
                            Date date1 = formatter.parse(date);
                            Martyr mn = new Martyr(name,Integer.parseInt(age),location,date1,gender.charAt(0));
                            Dlist.findNode(new Location(location)).getData().getMartyr().Update(m,mn);
                            System.out.println(Dlist.findNode(new Location(location)).getData().getMartyr().printS());

                        } catch (ParseException ex) {
                            throw new RuntimeException(ex);
                        }
                        catch (NullPointerException e1){
                            System.out.println(e1);
                        }
                        catch (Exception e2){
                            System.out.println(e2);
                        }
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Done !!");
                        alert.setContentText("the Martyr has been Updated !!");
                        alert.show();



                    }else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error !!");
                        alert.setContentText("Enter a Valid value!!");
                        alert.show();
                    }

                }
            }

            txage1.clear();
            txdate1.clear();
            txgender1.clear();
            txname1.clear();
            txsearchm.clear();
            stage1.close();

        });



        btdeleteLm.setOnAction(e ->{

            String location = comboBox.getValue()+"";
            String text= txdeletem.getText();
            if (location.equals("")|| location.equals(null + "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("PLease select the Location");
                alert.show();
                return;
            }
            for (int i = 0; i < Dlist.findNode(new Location(location)).getData().getMartyr().size(); i++) {
                if (Dlist.findNode(new Location(location)).getData().getMartyr().get(i).getName().equals(text)){
                    Martyr m = Dlist.findNode(new Location(location)).getData().getMartyr().get(i);
                    Dlist.findNode(new Location(location)).getData().getMartyr().Delete(m);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Done !!");
                    alert.setContentText("the Martyr has been Removed !!");
                    alert.show();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Done !!");
            alert.setContentText("the Martyr not found !!");
            alert.show();
            txdeletem.clear();

        });

        btSearchm1.setOnAction(e ->{
            String location = comboBox.getValue()+"";
            String text= txSearchm.getText();

            if (location.equals("")|| location.equals(null + "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("PLease select the Location");
                alert.show();
                return;
            }
            for (int i = 0; i < Dlist.findNode(new Location(location)).getData().getMartyr().size(); i++) {
                if (Dlist.findNode(new Location(location)).getData().getMartyr().get(i).getName().contains(text)){

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Done !!");
                    alert.setContentText("the Martyr has been Found !!");
                    alert.show();
                    return;
                }
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Done !!");
            alert.setContentText("the Martyr not found !!");
            alert.show();
            txSearchm.clear();
        });





        //scene four for Statistics Screen
        GridPane gpS= new GridPane();
        Tab sceneS = new Tab("Statistics Screen");
        tabPane.getTabs().add(sceneS);
        sceneS.setContent(gpS);
        gpS.setVgap(5);
        gpS.setHgap(5);
        VBox hBoxRa = new VBox(5);
        RadioButton btage,btgender,btavg,btdate ;
        btage = new RadioButton();
        btgender = new RadioButton();
        btavg = new RadioButton();
        btdate = new RadioButton();
        btage.setText("Search by age");
        btage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btgender.setText("Search by Gender");
        btgender.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btavg.setText("Search by Average Age");
        btavg.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        btdate.setText("Search by Date That Has The Maximum");
        btdate.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        hBoxRa.getChildren().addAll(btage,btgender,btavg,btdate);
        gpS.add(hBoxRa,1,3);
        //Button btbackS = new Button("Back");
       // btbackS.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        //gpS.setStyle("-fx-background-color: #76817f;");
        gpS.setBackground(background);
       // gpS.add(btbackS,1,51);
        ToggleGroup group = new ToggleGroup();
        group.getToggles().addAll(btage,btgender,btdate,btavg);
        Button btSearcs= new Button("Search");
        btSearcs.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        gpS.add(btSearcs,1,4);
        Label lblocationscreenS =new Label("Statistics Screen");
        lblocationscreenS.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 22));
        gpS.add(lblocationscreenS,1,0);
        TextArea textArea = new TextArea();
        gpS.add(textArea,3,5);

        btSearcs.setOnAction(e ->{
            String location = comboBox.getValue()+"";


            if (location.equals("")|| location.equals(null + "")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("PLease select the Location");
                alert.show();
                return;
            }
            else if (btage.isSelected()){
                textArea.clear();
                textArea.setText(Dlist.findNode(new Location(location)).getData().ageNumberFinder());
            }
            else if (btgender.isSelected()){
                textArea.clear();
                textArea.setText(Dlist.findNode(new Location(location)).getData().genderFinder()[0]+" Is the Number of Male \n the Number of Female is "+Dlist.findNode(new Location(location)).getData().genderFinder()[1]);

            }
            else if (btavg.isSelected()){
                textArea.clear();
                textArea.setText("the Average of Martyr Is : "+Dlist.findNode(new Location(location)).getData().findAgeAVG());
            }
            else if (btdate.isSelected()){
                textArea.setText("The Date That Has The Maximum Number Of Martyrs is : "+ Dlist.findNode(new Location(location)).getData().maxDateFinder()+"");
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error !!");
                alert.setContentText("Please Choose One Of Above !!");
                alert.show();
            }

        });
//        gpS.add(btnext,3,30);
//        gpS.add(btprev,1,30);
        VBox vBox = new VBox(10);

        Label node = new Label();
        vBox.getChildren().addAll(btprev,node,btnext);
        gpS.add(vBox,1,30);
        //gpS.add(node,2,30);
        node.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

        btnext.setOnAction(e ->{
            try {
                String loc = comboBox.getValue()+"";
                System.out.println(loc);
                if (temp==null){

                    btnext.setDisable(false);
                }else {
                    temp=temp.getNext();
                    comboBox.getSelectionModel().select(temp.getData().getLocation());
                    node.setText(temp.getData().getLocation());
                    btprev.setDisable(false);


                }
            }catch (Exception e1){

            }


        });
        btprev.setOnAction(e ->{
            String loc = comboBox.getValue()+"";
        try {
            if (temp==null){
                btprev.setDisable(false);

            }else {
                temp=temp.getPrev();
                comboBox.getSelectionModel().select(temp.getData().getLocation());
                node.setText(temp.getData().getLocation());

            }
        }catch (Exception e1){

        }


        });

        Stage stage2 = new Stage();
        Stage stage3 = new Stage();
        btLodthefile.setOnAction(e ->{
            FileChooser fc = new FileChooser();
            File file = fc.showOpenDialog(stage2);// open stage file chooser
            if (file != null) {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    String csvDelimiter = ",";

                    while ((line = br.readLine()) != null) {
                        String[] data = line.split(csvDelimiter);
                        if (!isValidD(data[3])){
                            data[3]="00/00/0000";
                        }
                        if (!isValidN(data[1])){
                            data[1]="0";
                        }
                        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                        Date date1 = formatter.parse(data[3]);


                        Martyr m = new Martyr(data[0],Integer.parseInt(data[1]),data[2],date1,data[4].charAt(0));
                        list.Insertsorted(m);


                    }
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Done!");
                    alert.setContentText("the item has been added!!");
                    alert.show();
                    br.close();
                } catch (IOException e1) {
                    e1.printStackTrace();

                }catch (NumberFormatException | ParseException q) {
                    System.out.println();
                }
                catch (Exception e1) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error!");
                    alert.setContentText("Please Select a valid file !!");
                    alert.show();
                }
            }
        });
        btsavetofile.setOnAction(e ->{
            String location = comboBox.getValue()+"";

            FileChooser fc = new FileChooser();
            fc.setTitle("Select a folder to save the file");
            //fc.setInitialDirectory(new File(System.getProperty("output")));
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("CSV Files", "*.csv"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
            );
            File selectedFolder = fc.showSaveDialog(stage3);

            if (selectedFolder != null) {
                try {
                    File file = new File(selectedFolder.getParentFile(), "btselem.csv");
                    BufferedWriter br = new BufferedWriter(new FileWriter(file));
                    br.write("Name,Age,Event location - District,Date of death,Gender\n");
//                   for (int i = 0; i < Dlist.findNode(new Location(location)).getData().getMartyr().size(); i++) {
//                       br.write(Dlist.findNode(new Location(location)).getData().getMartyr().get(i).toString());
//                    }
                    for (int i = 0; i<Dlist.size();i++){
                        for (int j =0;j<Dlist.get(i).getMartyr().size();j++){
                            br.write(Dlist.get(i).getMartyr().get(j).toString());
                        }
                    }

                    br.close();
                } catch (IOException ex) {
                    System.out.println(ex);
                }
            }
                });



        Scene scene = new Scene(bpm,800,600);
        stage.setIconified(false);

        stage.setScene(scene);
        stage.setTitle("Hello!");
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

    public static boolean ishasInt(String data){
            try {
                Double d = Double.parseDouble(data);
            }catch (NumberFormatException e){
                return false;
            }
        return true;
    }
    public static boolean isValidD(String dateStr) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            Date date1 = formatter.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    public static boolean isValidN(String date){
        try{
            Integer.parseInt(date);
        }catch (NumberFormatException e){
            System.out.print("");
            return false;
        }
        return true;
    }

}