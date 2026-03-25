package frc.robot.components;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.data.StickPosition;
import java.math.BigDecimal;
import java.math.RoundingMode;
import frc.robot.Config;

public class Controller {
    final private static XboxController xbox_controller = new XboxController(Config.controller_port);
    private double swerve_angle = 0;
    private double last_right_bumper_state;
    
    public Controller() {
        SmartDashboard.putBoolean( "Climp Down", xbox_controller.getLeftBumperButton());
        SmartDashboard.putBoolean( "Climp Up", xbox_controller.getRightBumperButton());
        last_right_bumper_state = xbox_controller.getRightTriggerAxis();
    }

    public XboxController getController() {
        return new XboxController( xbox_controller.getPort() );
    }
    public CommandXboxController getCommandController() {
        return new CommandXboxController( xbox_controller.getPort() );
    }

    public StickPosition getLeftStickPosition() {
        return new StickPosition( xbox_controller.getLeftX(), xbox_controller.getLeftY() );
    }

    public StickPosition getRightStickPosition() {
        return new StickPosition( xbox_controller.getRightX(), xbox_controller.getRightY() );
    }


    public void poll() {
        double modifier = xbox_controller.getRightX()*-Config.rotation_speed;

        BigDecimal bd = new BigDecimal(Double.toString(modifier));
        bd = bd.setScale(1, RoundingMode.HALF_UP);
        double rounded_modifier = bd.doubleValue();
        
        SmartDashboard.putNumber("Swerve angle", swerve_angle);
        SmartDashboard.putNumber("Modifier", rounded_modifier);
        
        swerve_angle += rounded_modifier;
        if( Math.abs( swerve_angle ) > 1 ) {
            swerve_angle = swerve_angle%1;
        }
    }

    public double getSwerveAngle() {
        return swerve_angle;
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
