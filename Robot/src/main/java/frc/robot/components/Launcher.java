package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import javax.print.attribute.standard.Destination;

// import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher {
    private boolean is_output_active;
    boolean is_input_active;

    private SparkFlex output_motor_leader;
    private SparkFlex output_motor_follower;
    private SparkMax input_motor;

    private PIDController pid_leader_controller;
    private PIDController pid_follower_controller;

    public Launcher() {
        is_output_active = true;
        is_input_active = false;
        output_motor_leader = new SparkFlex( Config.launcher_output_leader, SparkLowLevel.MotorType.kBrushless );
        output_motor_follower = new SparkFlex( Config.launcher_output_follower, SparkLowLevel.MotorType.kBrushless );
        input_motor = new SparkMax( Config.launcher_input, SparkLowLevel.MotorType.kBrushless );
        
        SmartDashboard.putNumber( "Launcher Leader", output_motor_leader.get() );
        SmartDashboard.putNumber("Launcher Follower", output_motor_follower.get());
        SmartDashboard.putNumber( "Launcher Input", input_motor.get() );

        pid_leader_controller = new PIDController(1.09, 1.02, 0.06);
        pid_follower_controller = new PIDController(1.09, 1.02, 0.06);
    }

    private void setOutputMotors( double destination ) {
        double output_leader_voltage = pid_leader_controller.calculate( output_motor_leader.get(), destination);
        double output_follower_voltage = pid_leader_controller.calculate( output_motor_follower.get(), -destination);

        output_motor_leader.set( output_leader_voltage );
        output_motor_follower.set( output_follower_voltage );
    }

    public void poll( boolean toggle_input, boolean toggle_output ) {
        if( toggle_input ) {
            is_input_active = !is_input_active;
        }
        if( toggle_output ) {
            is_output_active = !is_output_active;
        }

        
        if( is_output_active ) {
            setOutputMotors( Config.launcher_output_power );
        }
        else {
            setOutputMotors( 0 );
        }

        if( is_input_active ) {
            input_motor.set( Config.launcher_input );
        }
        else {
            input_motor.set( 0 );
        }

        SmartDashboard.putNumber( "Launcher Leader", output_motor_leader.get() );
        SmartDashboard.putNumber("Launcher Follower", output_motor_follower.get());
        SmartDashboard.putNumber( "Launcher Input", input_motor.get() );
    }
}
