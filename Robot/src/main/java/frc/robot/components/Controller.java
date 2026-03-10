package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
import frc.robot.data.StickPosition;

public class Controller {
    // TODO: mettre les bonnes affaires ici, une fois la manette décidé et accepté par l'équipe
    final static XboxController xbox_controller = new XboxController(0);
    Controller() {}

    public static XboxController getController() {
        return xbox_controller;
    }

    public StickPosition getLeftStickPosition() {
        return new StickPosition( xbox_controller.getLeftX(), xbox_controller.getLeftY() );
    }

    public StickPosition getRightStickPosition() {
        return new StickPosition( xbox_controller.getRightX(), xbox_controller.getRightY() );
    }

    public boolean climbUp() {
        return xbox_controller.getRightBumperButton();
    }

    public boolean climbDown() {
        return xbox_controller.getLeftBumperButton();
    }
}
 