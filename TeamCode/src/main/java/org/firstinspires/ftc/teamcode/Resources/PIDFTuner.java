package org.firstinspires.ftc.teamcode.Resources;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.seattlesolvers.solverslib.controller.PIDFController;

@Config
@TeleOp(name="PID Tuner", group="PID")
public class PIDFTuner extends OpMode {
    private PIDFController controller;

    private static double P = 0;
    private static double I = 0;
    private static double D = 0;
    private static double F = 0;

    private static int target = 0;

    private DcMotorEx test_motor;
    private static String test_motor_name = ConfigConstants.LIFT_L_CONFIG;

    @Override
    public void init() {
        controller = new PIDFController(P, I, D, F);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        test_motor = hardwareMap.get(DcMotorEx.class, test_motor_name);
    }

    @Override
    public void loop() {
        double curr_pos = test_motor.getCurrentPosition();
        double power = controller.calculate(curr_pos, target);
        test_motor.setPower(power);

        telemetry.addData("Target: ", target);
        telemetry.addData("Current Position: ", curr_pos);
    }
}