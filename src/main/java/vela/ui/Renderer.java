package vela.ui;

import javafx.scene.Group;
import vela.game.Board;

public interface Renderer {
    Group draw(Board board);
}
