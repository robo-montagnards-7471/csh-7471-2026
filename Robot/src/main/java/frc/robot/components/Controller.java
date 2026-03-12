package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.data.StickPosition;
import frc.robot.Config;

public class Controller {
    final static XboxController xbox_controller = new XboxController(Config.controller_port);
    public Controller() {}

    public static CommandXboxController getController() {
        return new CommandXboxController( xbox_controller.getPort() );
    }

    public StickPosition getLeftStickPosition() {
        return new StickPosition( xbox_controller.getLeftX(), xbox_controller.getLeftY() );
    }

    public StickPosition getRightStickPosition() {
        return new StickPosition( xbox_controller.getRightX(), xbox_controller.getRightY() );
    }

    public boolean getAButton() { // Example
        return xbox_controller.getAButton();
    } 
}
