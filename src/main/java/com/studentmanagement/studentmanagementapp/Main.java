// =====================================================
// STRUKTURA E PROJEKTIT
// StudentManagementAPP
// Student.java          // model
//  StudentManager.java   // PESHA kryesore e kodit (LinkedList)
//  MainGUI.java          // JavaFX GUI
// =====================================================

package com.studentmanagement.studentmanagementapp;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    static class Student {
        private String id;
        private String emri;
        private String mbiemri;
        private double mesatarja;


        public Student(String id, String emri, String mbiemri, double mesatarja) {
            this.id = id;
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.mesatarja = mesatarja;
        }


        public String getId() { return id; }
        public String getEmri() { return emri; }
        public String getMbiemri() { return mbiemri; }
        public double getMesatarja() { return mesatarja; }
        public void setEmri(String emri) { this.emri = emri; }
        public void setMbiemri(String mbiemri) { this.mbiemri = mbiemri; }
        public void setMesatarja(double mesatarja) { this.mesatarja = mesatarja; }
    }

    static class StudentManager {

        private final LinkedList<Student> studentet = new LinkedList<>();
        private final Map<String, Student> studentMap = new HashMap<>();
        private String keyOf(String id) {
            return id == null ? "" : id.trim().toLowerCase();
        }

        public boolean shtoStudent(Student s) {
            if (gjejStudent(s.getId()) != null) return false;
            studentet.add(s);
            studentMap.put(keyOf(s.getId()), s);

            return true;
        }

        public Student gjejStudent(String id) {
            ListIterator<Student> it = studentet.listIterator();
            while (it.hasNext()) {
                Student s = it.next();
                if (s.getId().equalsIgnoreCase(id)) return s;
            }
            return null;
        }

        public Student gjejStudentMeMap(String id) {
            String key = keyOf(id);
            if (key.isEmpty()) return null;
            return studentMap.get(key);
        }

        public boolean fshiStudent(String id) {
            Student s = gjejStudent(id);
            if (s == null) return false;
            studentet.remove(s);

            studentMap.remove(keyOf(id));

            return true;
        }

        public boolean perditesoStudent(String id, String emri, String mbiemri, double mes) {
            Student s = gjejStudent(id);
            if (s == null) return false;

            s.setEmri(emri);
            s.setMbiemri(mbiemri);
            s.setMesatarja(mes);

            return true;
        }

        public LinkedList<Student> getStudentet() {
            return studentet;
        }

        public void sortByNameAZ() {
            for (int i = 0; i < studentet.size() - 1; i++) {
                for (int j = 0; j < studentet.size() - i - 1; j++) {
                    String a = studentet.get(j).getEmri();
                    String b = studentet.get(j + 1).getEmri();
                    if (a.compareToIgnoreCase(b) > 0) {
                        Student temp = studentet.get(j);
                        studentet.set(j, studentet.get(j + 1));
                        studentet.set(j + 1, temp);
                    }
                }
            }
        }

        public void sortByAverageDesc() {
            for (int i = 0; i < studentet.size() - 1; i++) {
                for (int j = 0; j < studentet.size() - i - 1; j++) {
                    double a = studentet.get(j).getMesatarja();
                    double b = studentet.get(j + 1).getMesatarja();
                    if (a < b) {
                        Student temp = studentet.get(j);
                        studentet.set(j, studentet.get(j + 1));
                        studentet.set(j + 1, temp);
                    }
                }
            }
        }

        public int numriTotalStudenteve() {
            return studentet.size();
        }

        public double mesatarjaPergjithshme() {
            if (studentet.isEmpty()) return 0.0;
            double shuma = 0.0;
            for (Student s : studentet) {
                shuma += s.getMesatarja();
            }
            return shuma / studentet.size();
        }
    }

    private final StudentManager manager = new StudentManager();
    private final TableView<Student> tabela = new TableView<>();
    private final ObservableList<Student> data = FXCollections.observableArrayList();

    @Override

    public void start(Stage stage) {

        TextField idField = new TextField();
        TextField emriField = new TextField();
        TextField mbiemriField = new TextField();
        TextField mesField = new TextField();

        idField.setPromptText("ID (p.sh STU01, A12B)");
        emriField.setPromptText("Emri");
        mbiemriField.setPromptText("Mbiemri");
        mesField.setPromptText("Mesatarja (p.sh 9.5 ose 9,5)");

        Label status = new Label("");
        Button btnShto = new Button("Shto");
        Button btnFshi = new Button("Fshi");
        Button btnUpdate = new Button("Perditeso");
        Button btnPastro = new Button("Pastro fushat");
        Label lblTotalTitle = new Label("Totali i studenteve");
        Label lblTotalValue = new Label("0");

        lblTotalValue.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        VBox cardTotal = new VBox(4, lblTotalTitle, lblTotalValue);
        cardTotal.setStyle("-fx-padding: 10; -fx-border-color: #666; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label lblAvgTitle = new Label("Mesatarja e pergjithshme");
        Label lblAvgValue = new Label("0.00");
        lblAvgValue.setStyle("-fx-font-size: 18; -fx-font-weight: bold;");

        VBox cardAvg = new VBox(4, lblAvgTitle, lblAvgValue);
        cardAvg.setStyle("-fx-padding: 10; -fx-border-color: #666; -fx-border-radius: 8; -fx-background-radius: 8;");

        VBox cardsBox = new VBox(8, cardTotal, cardAvg);

        ComboBox<String> comboSort = new ComboBox<>();
        comboSort.getItems().addAll(
                "Emri (A–Z)",
                "Mesatarja (nga me e larta)"
        );
        comboSort.setValue("Emri (A–Z)");

        Button btnSort = new Button("Rendit");

        Button btnKerko = new Button("Kerko Student");

        TableColumn<Student, String> colId = new TableColumn<>("ID");

        colId.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getId()));

        TableColumn<Student, String> colEmri = new TableColumn<>("Emri");

        colEmri.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getEmri()));

        TableColumn<Student, String> colMbiemri = new TableColumn<>("Mbiemri");

        colMbiemri.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getMbiemri()));

        TableColumn<Student, Double> colMes = new TableColumn<>("Mesatarja");

        colMes.setCellValueFactory(d ->
                new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getMesatarja()).asObject());

        tabela.getColumns().setAll(colId, colEmri, colMbiemri, colMes);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setItems(data);
        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldV, s) -> {

            if (s != null) {

                idField.setText(s.getId());
                emriField.setText(s.getEmri());
                mbiemriField.setText(s.getMbiemri());
                mesField.setText(String.valueOf(s.getMesatarja()));

            }
        });

        btnShto.setOnAction(e -> {

            try {

                String id = idField.getText().trim();

                String emri = emriField.getText().trim();

                String mbiemri = mbiemriField.getText().trim();

                String mesText = mesField.getText().trim().replace(",", ".");

                double mes = Double.parseDouble(mesText);

                if (id.isEmpty()) { status.setText("Ploteso ID!"); return; }

                if (emri.isEmpty() || mbiemri.isEmpty()) {
                    status.setText("Ploteso emrin dhe mbiemrin!");
                    return;
                }

                boolean ok = manager.shtoStudent(new Student(id, emri, mbiemri, mes));
                if (ok) {

                    status.setText("Student u shtua!");

                    rifreskoTabela();

                    lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
                    lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)

                } else {
                    status.setText("Ky ID ekziston! Provo nje tjeter.");
                }
            } catch (Exception ex) {
                status.setText("Gabim inputi! Kontrollo mesataren.");
            }
        });

        btnFshi.setOnAction(e -> {
            Student s = tabela.getSelectionModel().getSelectedItem();
            if (s == null) { status.setText("Zgjidh nje student nga tabela per ta fshire."); return; }

            manager.fshiStudent(s.getId());
            status.setText("Student u fshi.");
            rifreskoTabela();

            lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
            lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)

        });

        btnUpdate.setOnAction(e -> {
            try {
                Student s = tabela.getSelectionModel().getSelectedItem();
                if (s == null) { status.setText("Zgjidh nje student nga tabela per perditesim."); return; }

                String emri = emriField.getText().trim();
                String mbiemri = mbiemriField.getText().trim();
                String mesText = mesField.getText().trim().replace(",", ".");
                double mes = Double.parseDouble(mesText);

                boolean ok = manager.perditesoStudent(s.getId(), emri, mbiemri, mes);
                if (ok) {

                    status.setText("Student u perditesua!");
                    rifreskoTabela();
                    lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
                    lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)

                } else {
                    status.setText("Nuk u gjet studenti.");
                }
            } catch (Exception ex) {
                status.setText("Gabim inputi! Kontrollo fushat.");
            }
        });

        btnPastro.setOnAction(e -> {

            idField.clear();
            emriField.clear();
            mbiemriField.clear();
            mesField.clear();
            tabela.getSelectionModel().clearSelection();
            status.setText("");
        });

        btnSort.setOnAction(e -> {
            String zgjedhja = comboSort.getValue();

            if ("Emri (A–Z)".equals(zgjedhja)) {
                manager.sortByNameAZ();
                status.setText("U rendit sipas emrit (A–Z).");
            } else {
                manager.sortByAverageDesc();
                status.setText("U rendit sipas mesatares (nga me e larta).");
            }
            rifreskoTabela();
        });

        btnKerko.setOnAction(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                status.setText("Shkruaj ID per kerkimin!");
                return;
            }

            Student s = manager.gjejStudentMeMap(id); // O(1)
            if (s == null) {
                status.setText("Nuk u gjet studenti me kete ID.");
                tabela.getSelectionModel().clearSelection();
            } else {
                status.setText("Student u gjet (Map - O(1))!");
                tabela.getSelectionModel().select(s);
                tabela.scrollTo(s);
            }
        });

        idField.setOnAction(e -> btnKerko.fire());

        lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
        lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)

        VBox forma = new VBox(8,

                cardsBox,

                new Label("Forma e Studentit:"),
                idField, emriField, mbiemriField, mesField,

                new Separator(),

                new Label("Rendit sipas:"),
                comboSort, btnSort,

                new Separator(),
                btnShto, btnKerko, btnUpdate, btnFshi, btnPastro,

                new Separator(),
                status

        );
        forma.setPrefWidth(260);

        HBox root = new HBox(15, forma, tabela);
        root.setStyle("-fx-padding: 15");

        rifreskoTabela();
        stage.setScene(new Scene(root, 950, 460));
        stage.setTitle("Student Management System ");

        stage.show();

    }
    private void rifreskoTabela() {

        data.setAll(manager.getStudentet());
        tabela.refresh();

    }
    public static void main(String[] args) {
        launch();
    }
}