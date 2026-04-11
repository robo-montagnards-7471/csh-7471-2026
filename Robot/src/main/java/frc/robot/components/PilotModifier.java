package frc.robot.components;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Config;

public class PilotModifier {
    private double current_modifier = Config.start_modifier;

    public PilotModifier() {
        SmartDashboard.putNumber( "Swerve Speed multiplier", current_modifier);
    }

    public double getCurrentModifier() {
        current_modifier = SmartDashboard.getNumber("Swerve Speed multiplier", current_modifier);
        return current_modifier;
    }
}
