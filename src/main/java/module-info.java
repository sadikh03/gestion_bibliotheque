module sn.sadikh.intro_jpa_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.persistence;
    requires static lombok;


//    ===ajouter===
    requires org.hibernate.orm.core;
    requires java.sql;
    opens sn.sadikh.intro_jpa_javafx.controller to javafx.fxml;

    requires javafx.base;
    requires kernel;
    requires java.desktop;
    requires layout; // S'assurer que le module de base est requis

    // Autorise Hibernate à accéder aux entités [cite: 4, 7]
    opens sn.sadikh.intro_jpa_javafx.Model to org.hibernate.orm.core, javafx.base;
//    ===================================
    opens sn.sadikh.intro_jpa_javafx to javafx.fxml;
    exports sn.sadikh.intro_jpa_javafx;
}