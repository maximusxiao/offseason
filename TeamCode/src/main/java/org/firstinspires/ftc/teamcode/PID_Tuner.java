package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.rowanmcalpin.nextftc.core.control.controllers.PIDFController;
import com.rowanmcalpin.nextftc.core.control.controllers.feedforward.StaticFeedforward;

@Config
@TeleOp(name="PID Tuner", group="PID")
public class PID_Tuner extends OpMode {
    private PIDFController controller;

    public static double P = 0;
    public static double I = 0;
    public static double D = 0;
    public static double F = 0;

    public static int target = 200;

    private DcMotorEx test_motor;
    private static String test_motor_name = "motor_extendo";

    @Override
    public void init() {
        controller = new PIDFController(P, I, D, new StaticFeedforward(F));
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