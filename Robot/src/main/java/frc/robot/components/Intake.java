package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

public class Intake {
    private boolean is_in;
    private boolean is_out;

    private SparkMax motor;

    private PIDController intake_pid;

    public Intake() {
        is_in = false;
        is_out = false;

        motor = new SparkMax( Config.intake, SparkLowLevel.MotorType.kBrushless );

        intake_pid = new PIDController(1.09, 1.02, 0.06);

        SmartDashboard.putNumber("Intake Speed", motor.get());
    }

    public void poll( boolean in_toggle, boolean out_toggle ) {
        if( in_toggle ) {
            is_in = !is_in;
            is_out = false;
        }
        if( out_toggle ) {
            is_out = !is_out;
            is_in = false;
        }

        if( is_in ) {
            motor.set( intake_pid.calculate( motor.get(), Config.in ) );
        }
        else if( is_out ) {
            motor.set( intake_pid.calculate( motor.get(), Config.out ) );
        }
        else {
            motor.set( intake_pid.calculate( motor.get(), 0 ) );
        }
        SmartDashboard.putNumber("Intake Speed", motor.get());
    }
}
