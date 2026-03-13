package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.data.StickPosition;

import frc.robot.Config;

public class Controller {
    final static XboxController xbox_controller = new XboxController( Config.controller_port );
    public Controller() {
        SmartDashboard.putBoolean( "Climp Down", xbox_controller.getLeftBumperButton());
        SmartDashboard.putBoolean( "Climp Up", xbox_controller.getRightBumperButton());
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

    public boolean climbUp() {
        SmartDashboard.putBoolean( "Climp Up", xbox_controller.getRightBumperButton());
        return xbox_controller.getRightBumperButton();
    }
    
    public boolean climbDown() {
        SmartDashboard.putBoolean( "Climp Down", xbox_controller.getLeftBumperButton());
        return xbox_controller.getLeftBumperButton();
    }
}
 