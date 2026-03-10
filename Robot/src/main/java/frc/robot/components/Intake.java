package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.spark.SparkLowLevel;
import frc.robot.Config;

public class Intake {
    private boolean is_in;
    private boolean is_out;

    private SparkMax motor;

    public Intake() {
        is_in = false;
        is_out = false;

        motor = new SparkMax( Config.intake, SparkLowLevel.MotorType.kBrushless );
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
            motor.set( Config.in );
        }
        else if( is_out ) {
            motor.set( Config.out );
        }
        else {
            motor.set( 0 );
        }
    }
}
