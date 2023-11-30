module Final.Project.Caden.McCarty {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    exports Blackjack.Game;
    exports Blackjack.GUI;
    opens Blackjack.GUI;
}