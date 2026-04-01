package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.spark.SparkLowLevel;

import com.revrobotics.RelativeEncoder;
import frc.robot.Config;

public class Intake {
    private boolean is_in;
    private boolean is_out;
    private double target_position;

    private SparkMax motor;
    private SparkMax remote;
    private RelativeEncoder remote_encoder;

    public Intake() {
        is_in = false;
        is_out = false;
        target_position = Config.in_position;

        motor = new SparkMax( Config.intake, SparkLowLevel.MotorType.kBrushless );
        remote = new SparkMax( Config.remote, SparkLowLevel.MotorType.kBrushless );
        remote_encoder = remote.getEncoder();
        remote_encoder.setPosition( 0 );

        SmartDashboard.putNumber("Intake Speed", motor.get());
        SmartDashboard.putNumber("Remote Speed", remote.get());
        SmartDashboard.putNumber("Remote Position", remote_encoder.getPosition());
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
        
        remote.set( Config.smoothAtEnd( remote_encoder.getPosition(), target_position ) );
    }
}
