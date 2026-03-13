package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.data.StickPosition;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;

public class Controller {
    // TODO: mettre les bonnes affaires ici, une fois la manette décidé et accepté par l'équipe
    final static XboxController xbox_controller = new XboxController( Config.controller_port );

    private double last_right_bumper_state;

    public Controller() {
        last_right_bumper_state = xbox_controller.getRightTriggerAxis();
    }
  
    public static XboxController getController() {
        return xbox_controller;
    }

    public StickPosition getLeftStickPosition() {
        return new StickPosition( xbox_controller.getLeftX(), xbox_controller.getLeftY() );
    }

    public StickPosition getRightStickPosition() {
        return new StickPosition( xbox_controller.getRightX(), xbox_controller.getRightY() );
    }

    public boolean toggleIntakeIn() {
        return xbox_controller.getAButtonPressed();
    }

    public boolean toggleIntakeOut() {
        return xbox_controller.getBButtonPressed();
    }
}
