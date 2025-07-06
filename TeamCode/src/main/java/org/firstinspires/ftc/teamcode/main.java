package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class main extends LinearOpMode {

    // External class implementation to make code neater
    private system sys = new system();

    // Connect motors
    // ID should match config
    private DcMotorEx motor_fl, motor_bl, motor_fr, motor_br;
    
	// Intake config
    private DcMotorEx extendo;
    private CRServo intake_crservo;
    private Servo intake_pod_srvo, pitch_servo;
	
	// Lift
    private DcMotorEx lift_l, lift_r;

    @Override
    public void runOpMode() throws InterruptedException {
        motor_fl = hardwareMap.get(DcMotorEx.class, "motor_f_l");
        motor_bl = hardwareMap.get(DcMotorEx.class, "motor_b_l");
        motor_fr = hardwareMap.get(DcMotorEx.class, "motor_f_r");
        motor_br = hardwareMap.get(DcMotorEx.class, "motor_b_r");

        intake_crservo = hardwareMap.get(CRServo.class, "crservo_intake");
        extendo = hardwareMap.get(DcMotorEx.class, "motor_extendo");
	intake_pod_srvo = hardwareMap.get(Servo.class, "servo_intake_pod");
	pitch_servo = hardwareMap.get(Servo.class, "servo_pitch");

        lift_l = hardwareMap.get(DcMotorEx.class, "motor_lift_l");
        lift_r = hardwareMap.get(DcMotorEx.class, "motor_lift_l");

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
        if (gamepad2.right_bumper) {
	    sys.intake_pod(intake_pod_srvo);
	}
	if (gamepad2.left_bumper) {
	    sys.pitch(pitch_servo);
	}
	sys.drive(gamepad1, motor_fl, motor_bl, motor_fr, motor_br);
        sys.extendo(gamepad2, extendo);
        sys.intake(gamepad2, intake_crservo);
        sys.lift(gamepad2, lift_l, lift_r);
    }
}
