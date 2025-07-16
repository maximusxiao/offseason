package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp()
public class test extends LinearOpMode {

    public DcMotorEx fl, fr, bl, br;

    @Override()
    public void runOpMode() {
        if (opModeInInit()) {
            fl = hardwareMap.get(DcMotorEx.class, "motor_f_l");
            fr = hardwareMap.get(DcMotorEx.class, "motor_f_r");
            bl = hardwareMap.get(DcMotorEx.class, "motor_b_l");
            br = hardwareMap.get(DcMotorEx.class, "motor_b_r");
        }

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.circleWasPressed()){
                fl.setPower(0.5);//change to br
            }
            if(gamepad1.triangleWasPressed()){
                fr.setPower(0.5);//change to bl
            }
            if(gamepad1.squareWasPressed()){
                bl.setPower(0.5);//change to fr
            }
            if(gamepad1.crossWasPressed()){
                br.setPower(0.5);//change to flD
            }
        }
    }

}