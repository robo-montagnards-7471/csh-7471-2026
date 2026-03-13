package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
// import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.data.StickPosition;
import frc.robot.Config;

public class Controller {
    // TODO: mettre les bonnes affaires ici, une fois la manette décidé et accepté par l'équipe
    final static XboxController xbox_controller = new XboxController( 0 );

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

    public boolean getShooterOutputToggle() {
        double right_bumper_state = xbox_controller.getRightTriggerAxis();
        boolean toggle = false;
        if( right_bumper_state > Config.bumper_sensitivity && last_right_bumper_state < Config.bumper_sensitivity ) {
            toggle = true;
        }
        last_right_bumper_state = right_bumper_state;
        return toggle;
    }

    public boolean getShooterInputToggle() {
        return xbox_controller.getXButtonPressed();
    }
}
 