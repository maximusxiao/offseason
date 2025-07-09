package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp()
public class tele_tuning extends LinearOpMode {

	// Outtake servos
	private Servo l_arm_l, l_arm_r;
	
	@Override
	public void RunOpMode() throws InterruptedException {
		l_arm_l = hardwareMap.get(Servo.class, "servo_arm_l");
		l_arm_r = hardwareMap.get(Servo.class, "servo_arm_r");
		l_arm_l.setPosition(0.1);
		l_arm_r.setPosition(0.1);
		
		if (opModeInInit()) {
			  ;
		}
		
		// WAIT UNTIL START
		waitForStart();
	
    // STOP WHEN STOP
    if (isStopRequested()) {
        return;
    }
    // Separate method
    while (opModeIsActive()) {
        ;
    }
	}
}
