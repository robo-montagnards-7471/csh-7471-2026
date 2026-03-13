package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import javax.print.attribute.standard.Destination;

// import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

import edu.wpi.first.math.controller.PIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
    private boolean is_output_active;
    boolean is_input_active;

    private SparkFlex output_motor_leader;
    private SparkFlex output_motor_follower;
    private SparkMax input_motor;

    private PIDController pid_leader_controller;
    private PIDController pid_follower_controller;
    private PIDController pid_input_controller;

    public Shooter() {
        is_output_active = true;
        is_input_active = false;
        output_motor_leader = new SparkFlex( Config.shooter_output_leader, SparkLowLevel.MotorType.kBrushless );
        output_motor_follower = new SparkFlex( Config.shooter_output_follower, SparkLowLevel.MotorType.kBrushless );
        input_motor = new SparkMax( Config.shooter_input, SparkLowLevel.MotorType.kBrushless );
        
        SmartDashboard.putNumber( "Shooter Leader", output_motor_leader.get() );
        SmartDashboard.putNumber("Shooter Follower", output_motor_follower.get());
        SmartDashboard.putNumber( "Shooter Input", input_motor.get() );

        pid_leader_controller = new PIDController(1.09, 1.02, 0.06);
        pid_follower_controller = new PIDController(1.09, 1.02, 0.06);
        pid_input_controller = new PIDController(1.09, 1.02, 0.06);
    }

    private void setOutputMotors( double destination ) {
        double output_leader_voltage = pid_leader_controller.calculate( output_motor_leader.get(), destination);
        double output_follower_voltage = pid_follower_controller.calculate( output_motor_follower.get(), -destination);

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
            setOutputMotors( Config.shooter_output_power );
        }
        else {
            setOutputMotors( 0 );
        }

        if( is_input_active ) {
            input_motor.set( pid_input_controller.calculate( input_motor.get(), Config.shooter_input )  );
        }
        else {
            input_motor.set( pid_input_controller.calculate( input_motor.get(), 0 ) );
        }

        SmartDashboard.putNumber( "Shooter Leader", output_motor_leader.get() );
        SmartDashboard.putNumber("Shooter Follower", output_motor_follower.get());
        SmartDashboard.putNumber( "Shooter Input", input_motor.get() );
    }
}
