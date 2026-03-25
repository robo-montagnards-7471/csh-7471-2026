package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.data.StickPosition;
import frc.robot.Config;

public class Controller {
    final static XboxController xbox_controller = new XboxController(Config.controller_port);

    private double last_right_bumper_state;

    public Controller() {
        SmartDashboard.putBoolean( "Climp Down", xbox_controller.getLeftBumperButton());
        SmartDashboard.putBoolean( "Climp Up", xbox_controller.getRightBumperButton());
        last_right_bumper_state = xbox_controller.getRightTriggerAxis();
    }
    
    public static XboxController getController() {
        return xbox_controller;
    }
    public static CommandXboxController getCommandController() {
        return new CommandXboxController( xbox_controller.getPort()
    }

    public StickPosition getLeftStickPosition() {
        return new StickPosition( xbox_controller.getLeftX(), xbox_controller.getLeftY() );
    }

    public StickPosition getRightStickPosition() {
        return new StickPosition( xbox_controller.getRightX(), xbox_controller.getRightY() );
    }

    public boolean climbUp() {
        SmartDashboard.putBoolean( "Climp Up", xbox_controller.getRightBumperButton());
        return xbox_controller.getRightBumperButton();
    }
    
    public boolean climbDown() {
        SmartDashboard.putBoolean( "Climp Down", xbox_controller.getLeftBumperButton());
        return xbox_controller.getLeftBumperButton();
    }
    
    public boolean toggleIntakeIn() {
        return xbox_controller.getAButtonPressed();
    }

    public boolean toggleIntakeOut() {
        return xbox_controller.getBButtonPressed();
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
        boolean toggle = xbox_controller.getXButtonPressed();
        SmartDashboard.putBoolean("Toggle Shooter input", toggle);
        return toggle;
    }
}
