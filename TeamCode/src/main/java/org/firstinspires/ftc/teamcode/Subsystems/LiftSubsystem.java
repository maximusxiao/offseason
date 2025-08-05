package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorGroup;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

public class LiftSubsystem extends Subsystem {
    // Standardized code
    public static final LiftSubsystem INSTANCE = new LiftSubsystem();
    public LiftSubsystem() { }

    // Class Variables
    public static MotorEx lift_motor_l;
    public static MotorEx lift_motor_r;
    public static MotorGroup lift_motors;
    public static PIDFController lift_controller = new PIDFController(0.05,0.0,0.0,new StaticFeedforward(0.5));

    public static final double RETRACT_POS = SubsystemConstants.LIFT_RETRACT;
    public static final double SPECIMEN_DEPOSIT_POS = SubsystemConstants.LIFT_SPECIMEN_DEPOSIT;

    // Run using gamepad power input method
    public Command lift_power(float power) {
        return new SetPower(lift_motors,
                            power,
                            this);
    }

    // Lift retraction method
    public Command retract() {
        return new RunToPosition(lift_motors,
                                 RETRACT_POS,
                                 lift_controller,
                                 this);
    }


    // Lift specimen deposit method
    public Command deposit_specimen() {
        return new RunToPosition(lift_motors,
                                 SPECIMEN_DEPOSIT_POS,
                                 lift_controller,
                                 this);
    }

    @Override
    public void initialize() {
        lift_motor_l = new MotorEx(ConfigConstants.LIFT_L_CONFIG).reverse();
        lift_motor_r = new MotorEx(ConfigConstants.LIFT_R_CONFIG);
        lift_motors = new MotorGroup(lift_motor_l, lift_motor_r);
    }
}