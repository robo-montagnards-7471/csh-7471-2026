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
        is_output_active = Config.shooter_output_start_state;
        is_input_active = Config.shooter_input_start_state;
        output_motor_leader = new SparkFlex( Config.shooter_output_leader, SparkLowLevel.MotorType.kBrushless );
        SmartDashboard.putNumber( "Shooter Leader", output_motor_leader.get() );

        if( Config.has_follower ) {
            output_motor_follower = new SparkFlex( Config.shooter_output_follower, SparkLowLevel.MotorType.kBrushless );
            SmartDashboard.putNumber("Shooter Follower", output_motor_follower.get());
        }

        input_motor = new SparkMax( Config.shooter_input, SparkLowLevel.MotorType.kBrushless );
        SmartDashboard.putNumber( "Shooter Input", input_motor.get() );
    }

    private void setOutputMotors( double destination ) {
        double output_leader_voltage = destination;
        output_motor_leader.set( output_leader_voltage );
        SmartDashboard.putNumber( "Shooter Leader", output_motor_leader.get() );

        if( Config.has_follower ) {
            double output_follower_voltage = destination;
            output_motor_follower.set( output_follower_voltage );
            SmartDashboard.putNumber("Shooter Follower", output_motor_follower.get());
        }
    }

    public void poll( boolean toggle_input, boolean toggle_output ) {
        SmartDashboard.putBoolean("toggle_input", toggle_input);
        if( toggle_input ) {
            is_input_active = !is_input_active;
        }
        if( toggle_output ) {
            is_output_active = !is_output_active;
        }

        
        if( is_output_active ) {
            setOutputMotors( safeAcceleration( Config.shooter_output_power ) );
        }
        else {
            setOutputMotors( 0 );
        }

        if( is_input_active ) {
            input_motor.set( safeAcceleration( Config.shooter_input ) );
        }
        else {
            input_motor.set( 0 );
        }

        SmartDashboard.putNumber( "Shooter Input", input_motor.get() );
        SmartDashboard.putBoolean( "Shooter Input State", is_input_active );
    }

    private double safeAcceleration( double current_speed ) {
        double to_return = current_speed + 0.1;
        if( current_speed < 0.05 ) {
            to_return = current_speed + 0.01;
        }
        if( to_return > 1 ) {
            to_return = 1;
        }
        return to_return;
    }
}
