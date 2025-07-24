package org.firstinspires.ftc.teamcode.pid_subsys;

import com.rowanmcalpin.nextftc.core.Subsystem;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.RunToPosition;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class test_subsys extends Subsystem {
    public static final test_subsys INSTANCE = new test_subsys();

    public test_subsys() {

    }

    // Init
    public MotorEx test_motor;

    public String test_motor_config;

    public PIDFController test_controller = new PIDFController(0,0,0,new StaticFeedforward(0));

    public Command tune() {
        return new RunToPosition(
                test_motor,
                0.0,
                test_controller,
                this);
    }

    @Override()
    // Init function
    public void initialize() {
        test_motor = new MotorEx(test_motor_config);
    }
}
