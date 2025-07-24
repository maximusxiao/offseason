package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.rowanmcalpin.nextftc.pedro.PedroOpMode;

import org.firstinspires.ftc.teamcode.pid_subsys.test_subsys;

@TeleOp(name = "Command OpMode", group = "Command Opmodes")
public class PID_Tuner extends PedroOpMode {
    public PID_Tuner() {
        super(test_subsys.INSTANCE);
    }

    @Override()
    public void onInit() {

    }

    @Override()
    public void onStartButtonPressed() {
        gamepadManager.getGamepad1().getRightBumper().setPressedCommand(
                () -> test_subsys.INSTANCE.tune()
        );
    }
}