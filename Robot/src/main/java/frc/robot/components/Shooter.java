package frc.robot.components;

import com.revrobotics.spark.SparkMax;


// import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
    private boolean is_output_active;
    boolean is_input_active;

    private SparkFlex output_motor_leader;
    private SparkFlex output_motor_follower;
    private SparkMax input_motor;


    public Shooter() {
        is_output_active = true;
        is_input_active = false;
        output_motor_leader = new SparkFlex( Config.shooter_output_leader, SparkLowLevel.MotorType.kBrushless );
        if( Config.has_follower ) {
            output_motor_follower = new SparkFlex( Config.shooter_output_follower, SparkLowLevel.MotorType.kBrushless );
        }
        input_motor = new SparkMax( Config.shooter_input, SparkLowLevel.MotorType.kBrushless );
        
        SmartDashboard.putNumber( "Shooter Leader", output_motor_leader.get() );
        if( Config.has_follower ) {
            SmartDashboard.putNumber("Shooter Follower", output_motor_follower.get());
        }
        SmartDashboard.putNumber( "Shooter Input", input_motor.get() );

    }

    private void setOutputMotors( double destination ) {
        double output_leader_voltage = destination;
        output_motor_leader.set( output_leader_voltage );
        if( Config.has_follower ) {
            double output_follower_voltage =  -destination;
            output_motor_follower.set( output_follower_voltage );
            SmartDashboard.putNumber("Shooter Follower", output_motor_follower.get());
            SmartDashboard.putNumber( "Shooter Follower Target", -destination );
        }
        SmartDashboard.putNumber( "Shooter Leader", output_motor_leader.get() );
        SmartDashboard.putNumber( "Shooter Leader Target", destination );
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
            input_motor.set( Config.shooter_input );
            SmartDashboard.putNumber( "Shooter Input Target", Config.shooter_input );
        }
        else {
            input_motor.set( 0 );
            SmartDashboard.putNumber( "Shooter Input Target", 0 );
        }

        SmartDashboard.putNumber( "Shooter Input", input_motor.get() );
    }
}
