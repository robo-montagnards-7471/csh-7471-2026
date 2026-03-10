package frc.robot.components;

import com.revrobotics.spark.SparkMax;
// import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Launcher {
    private boolean is_output_active;
    boolean is_input_active;

    private SparkFlex output_motor_leader;
    private SparkFlex output_motor_follower;
    private SparkMax input_motor;

    public Launcher() {
        is_output_active = true;
        is_input_active = false;
        output_motor_leader = new SparkFlex( Config.launcher_output_leader, SparkLowLevel.MotorType.kBrushless );
        output_motor_follower = new SparkFlex( Config.launcher_output_follower, SparkLowLevel.MotorType.kBrushless );
        input_motor = new SparkMax( Config.launcher_input, SparkLowLevel.MotorType.kBrushless );
        
        SmartDashboard.putNumber( "Launcher Leader", output_motor_leader.get() );
        SmartDashboard.putNumber("Launcher Follower", output_motor_follower.get());
        SmartDashboard.putNumber( "Launcher Input", input_motor.get() );
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
            // output_motor_follower.set( -Config.launcher_output_power );
        }
        else {
            output_motor_leader.set( 0 );
            // output_motor_follower.set( 0 );
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
