// =====================================================
// STRUKTURA E PROJEKTIT (AKADEMIKE) - PJESA E IGOREES
// StudentManagementGUI
// Student.java          // model
//  StudentManager.java   // PESHA kryesore e kodit (LinkedList)
//  MainGUI.java          // JavaFX GUI
// =====================================================

// ===================== IGORA START (PACKAGE) =====================
package com.studentmanagement.studentmanagementapp;  // ← NDRYSHUAR NGA org.example NË com.studentmanagement
// Deklaron paketen ku ndodhet klasa kryesore e aplikacionit.
// Package perdoret per organizimin logjik te klasave dhe shmangien e konflikteve te emrave.
// ===================== IGORA END (PACKAGE) =======================


// ===================== IGORA START (IMPORTS - JAVAFX + LINKEDLIST) =====================
// Importon klasen Application qe eshte baza e cdo aplikacioni JavaFX.
import javafx.application.Application;

// Importon klasa per menaxhimin e listave te dhenave qe lidhen me GUI.
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

// Importon klasa per ndertimin e skenes grafike.
import javafx.scene.Scene;

// Importon komponentet baze te nderfaqes grafike (Button, Label, TableView, etj).
import javafx.scene.control.*;

// Importon layout-et per organizimin vizual te elementeve.
import javafx.scene.layout.*;

// Importon klasen Stage qe perfaqeson dritaren kryesore te aplikacionit.
import javafx.stage.Stage;

// Importon strukturen LinkedList per ruajtje dinamike te studenteve.
import java.util.LinkedList;

// Importon ListIterator per iterim te kontrolluar mbi LinkedList.
import java.util.ListIterator;
// ===================== IGORA END (IMPORTS - JAVAFX + LINKEDLIST) =======================


// ===================== GOCA 3 START (IMPORTS) =====================
import java.util.HashMap;
import java.util.Map;
// ===================== GOCA 3 END (IMPORTS) =======================


public class Main extends Application {

    // ===================== IGORA START (MODEL - STUDENT) =====================
    // Tema: LinkedList + Administrim Dinamik
    // Leksioni: Listat, Stivat, Radhet
    // Model: Student

    // Kjo klase e brendshme perfaqeson modelin Student.
    // Perdoret per te ruajtur te dhenat baze te cdo studenti.
    static class Student {

        // Ruhet ID unike e studentit (shkronja dhe numra).
        private String id;

        // Ruhet emri i studentit.
        private String emri;

        // Ruhet mbiemri i studentit.
        private String mbiemri;

        // Ruhet mesatarja e studentit si numer decimal.
        private double mesatarja;

        // Konstruktor qe inicializon te gjitha fushat e studentit.
        public Student(String id, String emri, String mbiemri, double mesatarja) {
            this.id = id;
            this.emri = emri;
            this.mbiemri = mbiemri;
            this.mesatarja = mesatarja;
        }

        // Metode getter per ID.
        public String getId() { return id; }

        // Metode getter per emrin.
        public String getEmri() { return emri; }

        // Metode getter per mbiemrin.
        public String getMbiemri() { return mbiemri; }

        // Metode getter per mesataren.
        public double getMesatarja() { return mesatarja; }

        // Metode setter per ndryshimin e emrit.
        public void setEmri(String emri) { this.emri = emri; }

        // Metode setter per ndryshimin e mbiemrit.
        public void setMbiemri(String mbiemri) { this.mbiemri = mbiemri; }

        // Metode setter per ndryshimin e mesatares.
        public void setMesatarja(double mesatarja) { this.mesatarja = mesatarja; }
    }
    // ===================== IGORA END (MODEL - STUDENT) =======================


    // ===================== IGORA START (STUDENT MANAGER - LINKEDLIST CRUD + ITERATOR) =====================
    // LinkedList<Student>
    // add / remove / search / update
    // iterim me ListIterator

    // Kjo klase menaxhon listen e studenteve duke perdorur LinkedList.
    static class StudentManager {

        // Lista dinamike qe ruan te gjithe studentet.
        private final LinkedList<Student> studentet = new LinkedList<>();

        // ===================== GOCA 3 START (MAP + HELPERS) =====================
        // GOCA 3 — HashMap per kerkime te shpejta (O(1) mesatarisht)
        private final Map<String, Student> studentMap = new HashMap<>();

        private String keyOf(String id) {
            return id == null ? "" : id.trim().toLowerCase();
        }
        // ===================== GOCA 3 END (MAP + HELPERS) =======================


        public boolean shtoStudent(Student s) {
            if (gjejStudent(s.getId()) != null) return false;
            studentet.add(s);

            // ===================== GOCA 3 START (SYNC ADD) =====================
            // GOCA 3 — sinkronizo Map kur shtohet student
            studentMap.put(keyOf(s.getId()), s);
            // ===================== GOCA 3 END (SYNC ADD) =======================

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

        // ===================== GOCA 3 START (FAST SEARCH METHOD) =====================
        // GOCA 3 — metoda e re: kerkimi O(1) me Map
        public Student gjejStudentMeMap(String id) {
            String key = keyOf(id);
            if (key.isEmpty()) return null;
            return studentMap.get(key);
        }
        // ===================== GOCA 3 END (FAST SEARCH METHOD) =======================


        public boolean fshiStudent(String id) {
            Student s = gjejStudent(id);
            if (s == null) return false;
            studentet.remove(s);

            // ===================== GOCA 3 START (SYNC REMOVE) =====================
            // GOCA 3 — sinkronizo Map kur fshihet student
            studentMap.remove(keyOf(id));
            // ===================== GOCA 3 END (SYNC REMOVE) =======================

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

        // ===================== GOCA 2 START (SORTING METHODS - BUBBLE SORT) =====================
        // GOCA 2 — Bubble Sort: Emri (A–Z)
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

        // GOCA 2 — Bubble Sort: Mesatarja (nga me e larta)
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
        // ===================== GOCA 2 END (SORTING METHODS - BUBBLE SORT) =====================


        // ===================== GOCA 4 START (STATISTIKA - TOTALI + MESATARJA) =====================
        // GOCA 4 — STATISTIKA + KOMPLEKSITET
        // Totali: O(1) (size())
        public int numriTotalStudenteve() {
            return studentet.size();
        }

        // Mesatarja e pergjithshme: O(n) (nje kalim ne liste)
        public double mesatarjaPergjithshme() {
            if (studentet.isEmpty()) return 0.0;
            double shuma = 0.0;
            for (Student s : studentet) {
                shuma += s.getMesatarja();
            }
            return shuma / studentet.size();
        }
        // ===================== GOCA 4 END (STATISTIKA - TOTALI + MESATARJA) =======================

    }
    // ===================== IGORA END (STUDENT MANAGER - LINKEDLIST CRUD + ITERATOR) =======================


    // ===================== IGORA START (GUI - JAVAFX TABLE + CRUD) =====================
    // Koment ndarjeje: tregon qe ketu fillon pjesa e GUI-se (JavaFX Table + CRUD)

    private final StudentManager manager = new StudentManager();
    // Krijon nje objekt "manager" qe do merret me logjiken e studenteve (shtim/fshirje/perditesim).
    // E le "final" sepse nuk do ta nderrosh me nje instance tjeter me vone.

    private final TableView<Student> tabela = new TableView<>();
    // Krijon nje tabele JavaFX (TableView) qe do shfaqe rreshta me objekte te tipit Student.

    private final ObservableList<Student> data = FXCollections.observableArrayList();
    // Krijon listen "data" ku ruhen Studentet qe do shfaqen ne tabele.
    // ObservableList njofton automatikisht tabelen kur shton/fshin elemente.

    // JavaFX e therrret automatikisht start() kur aplikacioni nis
    @Override
    public void start(Stage stage) {
        // Metoda kryesore ku ndertohet GUI-ja.
        // "stage" eshte dritarja kryesore e aplikacionit.

        TextField idField = new TextField();
        // Krijon fushe inputi ku perdoruesi shkruan ID-n e studentit.

        TextField emriField = new TextField();
        // Krijon fushe inputi ku perdoruesi shkruan emrin.

        TextField mbiemriField = new TextField();
        // Krijon fushe inputi ku perdoruesi shkruan mbiemrin.

        TextField mesField = new TextField();
        // Krijon fushe inputi ku perdoruesi shkruan mesataren (numer me presje/pike).

        idField.setPromptText("ID (p.sh STU01, A12B)");
        // Vendos tekst udhezues (placeholder) brenda fushes ID, para se te shkruhet dicka.

        emriField.setPromptText("Emri");
        // Vendos placeholder per fushen e emrit.

        mbiemriField.setPromptText("Mbiemri");
        // Vendos placeholder per fushen e mbiemrit.

        mesField.setPromptText("Mesatarja (p.sh 9.5 ose 9,5)");
        // Vendos placeholder per mesataren dhe i tregon user-it formatin e pranueshem.

        Label status = new Label("");
        // Krijon nje label bosh qe zakonisht perdoret per mesazhe (gabim/sukses) ne UI.

        Button btnShto = new Button("Shto");
        // Krijon butonin "Shto" qe do perdoret per te shtuar student ne liste/tabele.

        Button btnFshi = new Button("Fshi");
        // Krijon butonin "Fshi" qe do fshije studentin e zgjedhur (ose sipas ID).

        Button btnUpdate = new Button("Perditeso");
        // Krijon butonin "Perditeso" per te ndryshuar te dhenat e nje studenti ekzistues.

        Button btnPastro = new Button("Pastro fushat");
        // Krijon butonin "Pastro fushat" per te zbrazur TextField-et (reset inputet).

        // ===================== GOCA 4 START (2 CARDS VIZUALE - TOTALI + MESATARJA) =====================
        // 2 "cards" ne te majte, ngjitur me butonat
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

        // wrapper per cards
        VBox cardsBox = new VBox(8, cardTotal, cardAvg);
        // ===================== GOCA 4 END (2 CARDS VIZUALE - TOTALI + MESATARJA) =======================


        // ===================== GOCA 2 START (SORTING UI - DROPDOWN + BUTTON) =====================
        ComboBox<String> comboSort = new ComboBox<>();
        comboSort.getItems().addAll(
                "Emri (A–Z)",
                "Mesatarja (nga me e larta)"
        );
        comboSort.setValue("Emri (A–Z)");

        Button btnSort = new Button("Rendit");
        // ===================== GOCA 2 END (SORTING UI - DROPDOWN + BUTTON) =======================


        // ===================== GOCA 3 START (SEARCH UI) =====================
        Button btnKerko = new Button("Kerko Student");
        // ===================== GOCA 3 END (SEARCH UI) =======================


        // ===================== IGORA START (TABLE COLUMNS + BINDING) =====================
        TableColumn<Student, String> colId = new TableColumn<>("ID");
        // Krijon nje kolone te tabeles me titullin "ID".
        // Kolona do shfaqe te dhena te tipit String nga objekti Student.

        colId.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getId()));
        // Percakton se cfare vlere shfaqet ne secilen qelize te kolones ID.
        // d.getValue() merr studentin e rreshtit aktual.
        // getId() merr ID-ne e studentit.
        // SimpleStringProperty e ben vleren te pershtatshme per JavaFX TableView.

        TableColumn<Student, String> colEmri = new TableColumn<>("Emri");
        // Krijon kolonen "Emri" qe do shfaqe emrin e studentit.

        colEmri.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getEmri()));
        // Lidh kolonen Emri me fushen emri te objektit Student.

        TableColumn<Student, String> colMbiemri = new TableColumn<>("Mbiemri");
        // Krijon kolonen "Mbiemri".

        colMbiemri.setCellValueFactory(d ->
                new javafx.beans.property.SimpleStringProperty(d.getValue().getMbiemri()));
        // Lidh kolonen Mbiemri me fushen mbiemri te studentit.

        TableColumn<Student, Double> colMes = new TableColumn<>("Mesatarja");
        // Krijon kolonen "Mesatarja" qe mban vlera numerike (Double).

        colMes.setCellValueFactory(d ->
                new javafx.beans.property.SimpleDoubleProperty(
                        d.getValue().getMesatarja()).asObject());
        // Merr mesataren e studentit.
        // SimpleDoubleProperty perdoret per numer.
        // asObject() e konverton ne Double (sepse TableColumn kerkon Object).

        tabela.getColumns().setAll(colId, colEmri, colMbiemri, colMes);
        // Vendos te gjitha kolonat ne tabele me nje rresht te vetem.

        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        // Ben qe kolonat te shtrihen automatikisht sipas gjeresise se tabeles.

        tabela.setItems(data);
        // Lidh tabelen me listen "data".
        // Cdo ndryshim ne data (shto/fshi) reflektohet automatikisht ne tabele.

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldV, s) -> {
            // Vendos nje listener qe reagon kur perdoruesi zgjedh nje rresht ne tabele.
            // obs = observimi
            // oldV = studenti i zgjedhur me pare
            // s = studenti i ri i zgjedhur

            if (s != null) {
                // Kontrollon qe te kete realisht nje student te zgjedhur.

                idField.setText(s.getId());
                // Vendos ID-ne e studentit te zgjedhur ne fushen ID.

                emriField.setText(s.getEmri());
                // Vendos emrin ne TextField e emrit.

                mbiemriField.setText(s.getMbiemri());
                // Vendos mbiemrin ne TextField e mbiemrit.

                mesField.setText(String.valueOf(s.getMesatarja()));
                // Vendos mesataren ne TextField.
                // String.valueOf konverton Double ne tekst.
            }
        });
        // ===================== IGORA END (TABLE COLUMNS + BINDING) =======================


        // ===================== IGORA START (CRUD HANDLERS) =====================
        // Koment ndarjeje: ketu fillon pjesa ku lidhen butonat me veprimet CRUD (Create/Read/Update/Delete)

        btnShto.setOnAction(e -> {
            // Vendos cfare ndodh kur klikohet butoni "Shto".
            // e = eventi i klikimit (ActionEvent)

            try {
                // Fillon nje bllok try per te kapur gabime (p.sh. nese mesatarja nuk eshte numer)

                String id = idField.getText().trim();
                // Merr tekstin nga fusha ID dhe heq hapesirat ne fillim/fund me trim().

                String emri = emriField.getText().trim();
                // Merr emrin nga fusha emri dhe e pastron nga hapesirat.

                String mbiemri = mbiemriField.getText().trim();
                // Merr mbiemrin nga fusha mbiemri dhe e pastron.

                String mesText = mesField.getText().trim().replace(",", ".");
                // Merr tekstin e mesatares, heq hapesirat dhe zevendeson presjen me pike.
                // Kjo behet sepse disa shkruajne 9,5 dhe Double.parseDouble pranon 9.5

                double mes = Double.parseDouble(mesText);
                // Kthen mesText ne numer double.
                // Nese mesText nuk eshte numer, do hidhe NumberFormatException dhe kapet nga catch.

                if (id.isEmpty()) { status.setText("Ploteso ID!"); return; }
                // Kontrollon nese ID eshte bosh.
                // Nese po: shfaq mesazh te status dhe ndalon metoden me return.

                if (emri.isEmpty() || mbiemri.isEmpty()) {
                    status.setText("Ploteso emrin dhe mbiemrin!");
                    return;
                }
                // Kontrollon nese emri ose mbiemri jane bosh.
                // Nese po: shfaq mesazh gabimi dhe ndalon veprimin.

                boolean ok = manager.shtoStudent(new Student(id, emri, mbiemri, mes));
                // Krijon nje objekt Student me te dhenat e marra dhe e dergon te manager per ta shtuar.
                // kthen true/false (ok) nese u shtua ose jo (p.sh. nese ID ekziston).

                if (ok) {
                    // Nese shtimi u krye me sukses:

                    status.setText("Student u shtua!");
                    // Shfaq mesazh suksesi te label status.

                    rifreskoTabela();
                    // Rifreskon tabelen (zakonisht e rimbush ObservableList ose e ben reload nga manager).

                    // ===================== GOCA 4 START (UPDATE CARDS PAS SHTIMIT) =====================
                    lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
                    lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)
                    // ===================== GOCA 4 END (UPDATE CARDS PAS SHTIMIT) =======================
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

            // ===================== GOCA 4 START (UPDATE CARDS PAS FSHIRJES) =====================
            lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
            lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)
            // ===================== GOCA 4 END (UPDATE CARDS PAS FSHIRJES) =======================
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

                    // ===================== GOCA 4 START (UPDATE CARDS PAS PERDITESIMIT) =====================
                    lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
                    lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)
                    // ===================== GOCA 4 END (UPDATE CARDS PAS PERDITESIMIT) =======================
                } else {
                    status.setText("Nuk u gjet studenti.");
                }
            } catch (Exception ex) {
                status.setText("Gabim inputi! Kontrollo fushat.");
            }
        });

        btnPastro.setOnAction(e -> {
            // Percakton veprimin qe ndodh kur klikohet butoni "Pastro fushat".

            idField.clear();
            // Fshin tekstin nga fusha ID.

            emriField.clear();
            // Fshin tekstin nga fusha Emri.

            mbiemriField.clear();
            // Fshin tekstin nga fusha Mbiemri.

            mesField.clear();
            // Fshin tekstin nga fusha Mesatarja.

            tabela.getSelectionModel().clearSelection();
            // Heq cdo rresht te zgjedhur nga tabela.

            status.setText("");
            // Pastron mesazhin e statusit (gabim ose sukses).
        });
        // ===================== IGORA END (CRUD HANDLERS) =======================


        // ===================== GOCA 2 START (SORTING HANDLER) =====================
        // GOCA 2 — Dropdown + Buton Rendit
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
        // ===================== GOCA 2 END (SORTING HANDLER) =======================


        // ===================== GOCA 3 START (SEARCH HANDLER + ENTER) =====================
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

        // Enter te ID = Kerko
        idField.setOnAction(e -> btnKerko.fire());
        // ===================== GOCA 3 END (SEARCH HANDLER + ENTER) =======================


        // ===================== GOCA 4 START (INIT CARDS NE START) =====================
        // Kur hapet aplikacioni, shfaq vlerat fillestare
        lblTotalValue.setText(String.valueOf(manager.numriTotalStudenteve())); // O(1)
        lblAvgValue.setText(String.format("%.2f", manager.mesatarjaPergjithshme())); // O(n)
        // ===================== GOCA 4 END (INIT CARDS NE START) =======================


        // ===================== IGORA START (LAYOUT + SCENE) =====================
        // Koment ndarjeje: ketu fillon pjesa e ndertimit te pamjes (layout) dhe vendosja ne scene

        VBox forma = new VBox(8,
                // Krijon nje VBox (layout vertikal) ku elementet vendosen njeri poshte tjetrit.
                // 8 = hapesira (spacing) vertikale midis elementeve.

                // Cards box vendoset ne te majte, ngjitur me butonat
                cardsBox,
                // Vendos cardsBox si elementi i pare ne VBox (zakonisht per shfaqje info/karta/sumarizim).

                new Label("Forma e Studentit:"),
                // Krijon nje label titull per seksionin e formes.

                idField, emriField, mbiemriField, mesField,
                // Shton fushat e inputit (ID, Emri, Mbiemri, Mesatarja) ne VBox ne rend.

                new Separator(),
                // Shton nje vije ndarese vizuale per ta ndare seksionin e formes nga ai i sortimit.

                new Label("Rendit sipas:"),
                // Label per seksionin e renditjes (sorting).

                comboSort, btnSort,
                // Shton dropdown (comboSort) ku zgjidhet kriteri i renditjes dhe butonin (btnSort) per ekzekutim.

                new Separator(),
                // Ndarje tjeter vizuale para seksionit te butonave CRUD.

                btnShto, btnKerko, btnUpdate, btnFshi, btnPastro,
                // Shton te gjithe butonat e veprimeve: Shto, Kerko, Perditeso, Fshi, Pastro.

                new Separator(),
                // Ndarje para statusit.

                status
                // Shton label "status" ku shfaqen mesazhe suksesi/gabimi.
        );

        forma.setPrefWidth(260);
        // Vendos gjeresine e preferuar te VBox "forma" (kolona ne te majte) ne 260px.

        HBox root = new HBox(15, forma, tabela);
        // Krijon root layout horizontal (HBox) ku elementet vendosen majtas-djathtas.
        // 15 = hapesira horizontale midis "forma" dhe "tabela".
        // "forma" vendoset majtas, "tabela" djathtas.

        root.setStyle("-fx-padding: 15");
        // Shton padding rreth layout-it root (hapesire nga anet) 15px.

        rifreskoTabela();
        // Mbush ose perditeson tabelen me te dhenat aktuale (p.sh. nga manager ne ObservableList).

        stage.setScene(new Scene(root, 950, 460));
        // Krijon Scene me root layout.
        // 950 x 460 = permasat e dritares.

        stage.setTitle("Student Management System ");
        // Vendos titullin e dritares (shfaqet siper).

        stage.show();
        // Shfaq dritaren dhe nis vizualisht aplikacionin.

        // ===================== IGORA END (LAYOUT + SCENE) =======================
    }

    // ===================== IGORA START (REFRESH TABLE) =====================
    // Koment ndarjeje: kjo pjese merret me rifreskimin e TableView

    private void rifreskoTabela() {
        // Metode ndihmese qe perditeson te dhenat e tabeles.

        data.setAll(manager.getStudentet());
        // Merr listen e studenteve nga manager dhe e vendos ne ObservableList "data".
        // setAll fshin te dhenat e vjetra dhe vendos te rejat.

        tabela.refresh();
        // Rifreskon vizualisht TableView qe ndryshimet te shfaqen menjehere.
    }
    // ===================== IGORA END (REFRESH TABLE) =======================


    // ===================== IGORA START (MAIN) =====================
    public static void main(String[] args) {
        // Metoda kryesore main, pika nga ku nis programi Java.

        launch();
        // Therrit JavaFX Application launch().
        // Kjo metode hap dritaren dhe therrit automatikisht start().
    }
    // ===================== IGORA END (MAIN) =======================
}