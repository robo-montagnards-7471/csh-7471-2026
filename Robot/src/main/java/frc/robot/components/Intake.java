package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;

import com.revrobotics.RelativeEncoder;
import frc.robot.Config;

public class Intake {
    private boolean is_in;
    private boolean is_out;
    private double target_position;

    private SparkFlex motor;
    private SparkMax remote;
    private RelativeEncoder remote_encoder;

    private DigitalInput in_limit_switch;
    private DigitalInput out_limit_switch;

    public Intake() {
        is_in = false;
        is_out = false;
        target_position = Config.in_position;

        in_limit_switch = new DigitalInput( Config.limit_switch_in );
        out_limit_switch = new DigitalInput( Config.limit_switch_out );

        motor = new SparkFlex( Config.intake, SparkLowLevel.MotorType.kBrushless );
        remote = new SparkMax( Config.remote, SparkLowLevel.MotorType.kBrushless );
        remote_encoder = remote.getEncoder();
        remote_encoder.setPosition( 0 );

        SmartDashboard.putNumber("Intake Speed", motor.get());
        SmartDashboard.putNumber("Remote Speed", remote.get());
        SmartDashboard.putNumber("Remote Position", remote_encoder.getPosition());

        SmartDashboard.putBoolean("Limit Switch Inside", in_limit_switch.get());
        SmartDashboard.putBoolean("Limit Switch Outside", out_limit_switch.get());
    }

    public void poll( boolean in_toggle, boolean out_toggle, boolean toggle_remote ) {
        if( in_toggle ) {
            is_in = !is_in;
            is_out = false;
        }
        if( out_toggle ) {
            is_out = !is_out;
            is_in = false;
        }

        if( is_in ) {
            motor.set( Config.in );
        }
        else if( is_out ) {
            motor.set( Config.out );
        }
        else {
            motor.set( 0 );
        }
        SmartDashboard.putNumber("Intake Speed", motor.get());

        if( toggle_remote ) {
            if( target_position == Config.in_position ) {
                target_position = Config.out_position;
            }
            else if( target_position == Config.out_position ) {
                target_position = Config.in_position;
            }
        }
        
        boolean in_limit_switch_state = in_limit_switch.get();
        boolean out_limit_switch_state = out_limit_switch.get();
        
        if( in_limit_switch_state ) {
            remote_encoder.setPosition( Config.in_position );
        }
        if( out_limit_switch_state ) {
            remote_encoder.setPosition( Config.out_position );
        }
        remote.set( smoothSpeed( remote_encoder.getPosition(), target_position ) );
        SmartDashboard.putNumber("Remote Position", remote_encoder.getPosition());

        SmartDashboard.putBoolean("Limit Switch Inside", in_limit_switch_state);
        SmartDashboard.putBoolean("Limit Switch Outside", out_limit_switch_state);
    }

    private double smoothSpeed( double current_position, double target_position ) {
        double distance_to_go = target_position-current_position;
        return distance_to_go*12/10;
    }
}
