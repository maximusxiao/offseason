package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class main extends LinearOpMode {

    // External class implementation to make code neater
    private system sys = new system();

    // Connect motors
    // ID should match config
    private DcMotorEx motor_fl, motor_bl, motor_fr, motor_br;
    //intake config
    private DcMotorEx extendo;
    private CRServo intake_crservo;

    private DcMotorEx liftL;
    private DcMotorEx liftR;

    @Override
    public void runOpMode() throws InterruptedException {
        motor_fl = hardwareMap.get(DcMotorEx.class, "FL");
        motor_bl = hardwareMap.get(DcMotorEx.class, "BL");
        motor_fr = hardwareMap.get(DcMotorEx.class, "FR");
        motor_br = hardwareMap.get(DcMotorEx.class, "BR");

        intake_crservo = hardwareMap.get(CRServo.class, "intake");
        extendo = hardwareMap.get(DcMotorEx.class, "extendo");

        liftL = hardwareMap.get(DcMotorEx.class, "liftL");
        liftR = hardwareMap.get(DcMotorEx.class, "liftR");

    /*while (opModeInInit()) {
            sys.funny_start_sequence(h_slide_motor);
        }*/

        waitForStart();

        // End when stop is pressed
        if (isStopRequested()) {
            return;
        }

        // Separate method implementation
        while (opModeIsActive()) {
            this.update();
        }
    }
    public void update() {
        sys.drive(gamepad1, motor_fl, motor_bl, motor_fr, motor_br);
        sys.extendo(gamepad2, extendo);
        sys.intake(gamepad2, intake_crservo);
        sys.lift(gamepad2, liftL, liftR);
    }
}
