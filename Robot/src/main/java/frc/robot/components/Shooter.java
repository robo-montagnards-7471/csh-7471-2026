package frc.robot.components;

import com.revrobotics.spark.SparkMax;


import com.revrobotics.spark.SparkLowLevel;

import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.motorcontrol.PWMMotorController;
import com.revrobotics.RelativeEncoder;
import frc.robot.Config;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter {
    private boolean is_output_active;
    boolean is_input_active;

    private SparkMax output_motor_leader;
    private SparkMax output_motor_follower;
    private PWM input_motor;

    private RelativeEncoder output_encoder_leader;
    private RelativeEncoder output_encoder_follower;

    private double output_power = Config.shooter_output_power;
    private double input_power = Config.shooter_input_power;

    private double current_output_speed;

    public Shooter() {
        is_output_active = Config.shooter_output_start_state;
        is_input_active = Config.shooter_input_start_state;

        output_motor_leader = new SparkMax( Config.shooter_output_leader, SparkLowLevel.MotorType.kBrushless );
        output_encoder_leader = output_motor_leader.getEncoder();
        SmartDashboard.putNumber( "Shooter Leader", output_encoder_leader.getVelocity() );

        if( Config.has_follower ) {
            output_motor_follower = new SparkMax( Config.shooter_output_follower, SparkLowLevel.MotorType.kBrushless );
            output_encoder_follower = output_motor_follower.getEncoder();
            SmartDashboard.putNumber( "Shooter Follower", output_encoder_follower.getVelocity() );
        }

        input_motor = new PWM( Config.shooter_input );
        SmartDashboard.putNumber( "Shooter Input", input_motor.getSpeed() );
    }

    private void setOutputMotors( double destination ) {
        destination *= 1;
        double leader_speed = output_encoder_leader.getVelocity();
        output_motor_leader.set( safeAcceleration( leader_speed, destination ) );
        SmartDashboard.putNumber( "Shooter Leader", leader_speed );

        if( Config.has_follower ) {
            double follower_speed = output_encoder_follower.getVelocity();
            output_motor_follower.set( safeAcceleration( follower_speed, destination ) );
            SmartDashboard.putNumber( "Shooter Follower", follower_speed );
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
            current_output_speed = Config.smoothAtEnd(current_output_speed, output_power);
        }
        else {
            current_output_speed = 0;
        }
        setOutputMotors( -current_output_speed );

        if( current_output_speed > output_power*Config.start_shooter_input_when_output_is_at ) {
            input_motor.setSpeed( input_power );
        }
        else {
            input_motor.setSpeed( 0 );
        }

        SmartDashboard.putNumber( "Shooter Input", input_motor.getSpeed() );
        SmartDashboard.putBoolean( "Shooter Input State", is_input_active );
    }

    private double safeAcceleration( double current_speed, double target_speed ) {
        return target_speed;
        // target_speed = target_speed/Config.max_speed;
        // current_speed = current_speed/Config.max_speed;
        // double to_return = Config.smoothAtEnd(current_speed, target_speed);
        // if( current_speed < target_speed*0.05 ) {
        //     to_return = current_speed + target_speed*0.01;
        // }
        // if( to_return > 1 ) {
        //     to_return = 1;
        // }
        // return to_return;
    }
}
