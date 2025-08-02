package org.firstinspires.ftc.teamcode.Subsystems;

import org.firstinspires.ftc.teamcode.Resources.SubsystemConstants;
import org.firstinspires.ftc.teamcode.Resources.ConfigConstants;
import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.SetPower;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptAprilTag;

public class ExtendoSubsystem extends Subsystem {
    // Standardized code
    public static final ExtendoSubsystem INSTANCE = new ExtendoSubsystem();
    public ExtendoSubsystem() { }

    // Class Variables
    public static MotorEx extendo_motor;
    public static PIDFController extendo_controller = new PIDFController(0.5,0.0,0.0,new StaticFeedforward(0.0));

    public static final double RETRACT_POS = SubsystemConstants.EXTENDO_RETRACT;

    // Run using gamepad power input method
    public Command extend_power(float power) {
        return new SetPower(extendo_motor,
                            power,
                            this);
    }

    // Extendo retraction method
    public Command retract() {
        return new RunToPosition(extendo_motor,
                                 RETRACT_POS,
                                 extendo_controller,
                                 this);
    }

    @Override
    public void initialize() {
        extendo_motor = new MotorEx(ConfigConstants.EXTENDO_CONFIG);
    }
}