package frc.robot.components;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Config;

public class PilotModifier {
    private final SendableChooser<Double> pilot_chooser;
    private double current_modifier = 1.0;
    private double last_pilot_modifier = current_modifier;

    public PilotModifier() {
        pilot_chooser = new SendableChooser<>();
        pilot_chooser.setDefaultOption("William", Config.william_modifier);
        pilot_chooser.addOption("Vincent", Config.vincent_modifier);
        pilot_chooser.addOption("Other", Config.other_modifier);
        SmartDashboard.putData("Pilot", pilot_chooser);
        SmartDashboard.putNumber( "Swerve Speed multiplier", current_modifier);
    }

    public double getCurrentModifier() {
        double current_pilot_modifier = pilot_chooser.getSelected();

        if( current_pilot_modifier != last_pilot_modifier ) {
            SmartDashboard.putNumber( "Swerve Speed multiplier", current_pilot_modifier);
        }
        
        last_pilot_modifier = current_pilot_modifier;

        current_modifier = SmartDashboard.getNumber("Swerve Speed multiplier", current_modifier);
        return current_modifier;
    }
}
