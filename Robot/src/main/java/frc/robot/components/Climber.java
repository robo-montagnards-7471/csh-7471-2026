package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

public class Climber {
    private SparkMax motor;

    private boolean is_up;
    private boolean is_down;

    public Climber() {
        is_down = false;
        is_up = false;

        motor = new SparkMax( Config.climber, SparkLowLevel.MotorType.kBrushless );

        SmartDashboard.putNumber("Climber Speed", motor.get());
    }

    public void poll( boolean climb_up, boolean climb_down ) {
        if( climb_up ) {
            is_up = true;
            is_down = false;
        }
        else if( climb_down ) {
            is_down = true;
            is_up = false;
        }
        else {
            is_up = false;
            is_down = false;
        }

        if( is_up ) {
            motor.set( Config.climb_up );
        }
        else if( is_down ) {
            motor.set( Config.climb_down );
        }
        else {
            motor.set( 0 );
        }

        SmartDashboard.putNumber("Climber Speed", motor.get());
    }
}
