package frc.robot.components;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

public class Launcher {
    boolean is_output_active;
    boolean is_input_active;

    SparkFlex output_motor_leader;
    SparkFlex output_motor_follower;
    SparkMax input_motor;

    Launcher() {
        is_output_active = true;
        is_input_active = true;
        output_motor_leader = new SparkFlex( Config.launcher_output_leader, SparkLowLevel.MotorType.kBrushless );
        output_motor_follower = new SparkFlex( Config.launcher_output_follower, SparkLowLevel.MotorType.kBrushless );
        input_motor = new SparkMax( Config.launcher_input, SparkLowLevel.MotorType.kBrushless );
    }

    public void poll( boolean toggle_input, boolean toggle_output ) {
        if( toggle_input ) {
            is_input_active = !is_input_active;
        }
        if( toggle_output ) {
            is_output_active = !is_output_active;
        }

        
        if( is_output_active ) {
            output_motor_leader.set( Config.launcher_output_power );
            output_motor_follower.set( -Config.launcher_output_power );
        }
        else {
            output_motor_leader.set( 0 );
            output_motor_follower.set( 0 );
        }

        if( is_input_active ) {
            input_motor.set( Config.launcher_input );
        }
        else {
            input_motor.set( 0 );
        }
    }
}
