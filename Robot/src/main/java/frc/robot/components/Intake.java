package frc.robot.components;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkLowLevel;
import edu.wpi.first.wpilibj.DigitalInput;

import com.fasterxml.jackson.core.type.TypeReference;
import com.revrobotics.RelativeEncoder;
import frc.robot.Config;

public class Intake {
    private boolean is_moving;

    private boolean is_in;
    private boolean is_out;
    private boolean is_deployed;
    private double target_position;

    private double last_remote_position;
    private boolean in_limit_switch_state;
    private boolean out_limit_switch_state;

    private SparkFlex motor;
    private SparkMax remote;
    private RelativeEncoder remote_encoder;

    private DigitalInput in_limit_switch;
    private DigitalInput out_limit_switch;

    public Intake() {
        is_deployed = Config.intake_deployed_at_start;
        is_moving = false;

        is_in = false;
        is_out = false;
        target_position = Config.in_position;

        in_limit_switch = new DigitalInput( Config.limit_switch_in );
        out_limit_switch = new DigitalInput( Config.limit_switch_out );

        in_limit_switch_state = in_limit_switch.get();
        out_limit_switch_state = out_limit_switch.get();

        motor = new SparkFlex( Config.intake, SparkLowLevel.MotorType.kBrushless );
        remote = new SparkMax( Config.remote, SparkLowLevel.MotorType.kBrushless );
        remote_encoder = remote.getEncoder();
        if( is_deployed ) {
            remote_encoder.setPosition( Config.out_position );
        }
        else if( !is_deployed ) {
            remote_encoder.setPosition( Config.in_position );
        }

        SmartDashboard.putNumber("Intake Speed", motor.get());
        SmartDashboard.putNumber("Remote Speed", remote.get());
        SmartDashboard.putNumber("Remote Position", remote_encoder.getPosition());

        SmartDashboard.putBoolean("Limit Switch Inside", in_limit_switch.get());
        SmartDashboard.putBoolean("Limit Switch Outside", out_limit_switch.get());
        SmartDashboard.putBoolean("Can chain move", is_moving);
        // Dashboard control: a button to immediately stop the intake remote chain
        SmartDashboard.putBoolean("Stop Chain", false);
        SmartDashboard.putBoolean("The intake is deployed", false);
        SmartDashboard.putBoolean("Intake down", false);
    }

    public void poll( boolean in_toggle, boolean out_toggle, boolean toggle_remote ) {
        // If dashboard Stop Chain button was pressed, stop the chain and reset the button
        if ( SmartDashboard.getBoolean("Stop Chain", false) ) {
            stopChain();
            SmartDashboard.putBoolean("Stop Chain", false);
        }
        if( SmartDashboard.getBoolean("The intake is deployed", false) ) {
            is_deployed = true;
            SmartDashboard.putBoolean("The intake is deployed", false);
        }

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
            is_moving = true;
            is_deployed = !is_deployed;
        }

        if( is_deployed ) {
            target_position = Config.out_position;
        }
        else if( !is_deployed ) {
            target_position = Config.in_position;
        }

        in_limit_switch_state = false;
        out_limit_switch_state = false;
        if( is_moving )
        {
            in_limit_switch_state = in_limit_switch.get();
            out_limit_switch_state = out_limit_switch.get();
        }
        
        if( in_limit_switch_state ) {
            remote_encoder.setPosition( Config.in_position );
            if( target_position == Config.in_position ) {
                is_moving = false;
            }
        }
        if( out_limit_switch_state ) {
            remote_encoder.setPosition( Config.out_position );
            if( target_position == Config.out_position ) {
                is_moving = false;
            }
        }

        double current_remote_position = remote_encoder.getPosition();

        // if( Math.abs( last_remote_position - current_remote_position ) < Config.remote_stop_threshold ) {
        //     is_moving = false;
        // }

        if( is_moving )
        {
            if( current_remote_position < target_position && is_deployed ) {
                remote.set( Config.remote_speed );
            }
            else if( current_remote_position > target_position && !is_deployed ) {
                remote.set( -Config.remote_speed );
            }
            else {
                remote.set( 0 );
            }
        }
        else {
            remote.set( 0 );
        }

        // if( Math.abs( target_position-remote_encoder.getPosition() ) < 5 ) {
        //     if( target_position == Config.out_position ) {
        //         remote.set( Config.remote_speed );
        //     }
        //     else if( target_position == Config.in_position ) {
        //         remote.set( -Config.remote_speed );
        //     }
        // }
        // else {
        //     remote.set( 0 );
        // }
        last_remote_position = current_remote_position;
        SmartDashboard.putNumber("Remote Position", current_remote_position);

        SmartDashboard.putBoolean("Limit Switch Inside", in_limit_switch.get());
        SmartDashboard.putBoolean("Limit Switch Outside", out_limit_switch.get());
        SmartDashboard.putBoolean("Can chain move", is_moving);
    }

    public void resetRemoteEncoder() {
        if( in_limit_switch.get() ) {
            remote_encoder.setPosition( Config.in_position );
        }
        else if( out_limit_switch.get() ) {
            remote_encoder.setPosition( Config.out_position );
        }
    }

    private void stopChain() {
        is_moving = false;
    }

    // public boolean isIntakeDown() {
        // if( !is_moving ) {
        //     if( target_position == Config.out_position ) {
        //         SmartDashboard.putBoolean("Intake down", true);
        //         return true;
        //     }
        // }
        // SmartDashboard.putBoolean("Intake down", false);
        // return false;
    // }
}
