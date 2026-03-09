package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.data.StickPosition;
import frc.robot.Config;

public class Controller {
    // TODO: mettre les bonnes affaires ici, une fois la manette décidé et accepté par l'équipe
    final static CommandXboxController xbox_controller = new CommandXboxController(0);

    private double bumper_sensitivity;

    Controller() {
        bumper_sensitivity = Config.bumper_sensitivity;
    }

    public static CommandXboxController getController() {
        return xbox_controller;
    }

    public StickPosition getLeftStickPosition() {
        return new StickPosition( xbox_controller.getLeftX(), xbox_controller.getLeftY() );
    }

    public StickPosition getRightStickPosition() {
        return new StickPosition( xbox_controller.getRightX(), xbox_controller.getRightY() );
    }

    public boolean getLauncherToggle() {
        double right_bumper_state = xbox_controller.getRightTriggerAxis();
        if( right_bumper_state > bumper_sensitivity ) {
            return true;
        }
        return false;
    }
}
 