package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import java.util.concurrent.TimeUnit;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class system {
    public boolean pitch_active = true;
	
	public void drive(Gamepad gamepad1, DcMotor motor_f_l, DcMotor motor_b_l, DcMotor motor_f_r, DcMotor motor_b_r) {

        // y_val reversed
        double y = gamepad1.left_stick_y;


        // Multiplier of 1.17 to counteract strafing range
        double x = -gamepad1.left_stick_x * 1.17;
        double rx = gamepad1.right_stick_x;

        // Range_limiter containes power within range [-1,1]
        double range_limiter = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double power_fl = -1 * ((y + x + rx) / range_limiter);
        double power_bl = -1 * ((y - x + rx) / range_limiter);
        double power_fr = (y - x - rx) / range_limiter;
        double power_br = (y + x - rx) / range_limiter;

        // Set Power
        motor_f_l.setPower(power_fl);
        motor_b_l.setPower(power_bl);
        motor_f_r.setPower(power_fr);
        motor_b_r.setPower(power_br);
    }

    public void extendo(Gamepad gamepad2, DcMotorEx extendo) {
        double slide_power = -gamepad2.left_stick_y;

        extendo.setPower(slide_power);
    }

    public void intake(Gamepad gamepad2, CRServo intake_crservo) {
        double fwd_spin = gamepad2.right_trigger;
        double reverse_spin = gamepad2.left_trigger;
        double spin_power = fwd_spin - reverse_spin;

        intake_crservo.setPower(spin_power);
    }

    public void lift(Gamepad gamepad2, DcMotorEx lift_l, DcMotorEx lift_r) {
        double lift_power = -gamepad2.right_stick_y;

        lift_l.setPower(lift_power);
        lift_r.setPower(lift_power);
    }
    public void pitch(Servo pitch_srvo) {
        double intake_pos = 0.22;
        double reject_pos = 0.65;
        double curr_pos = pitch_srvo.getPosition();
        double next_pos;
        if (curr_pos > intake_pos-0.05 && curr_pos < intake_pos + 0.05) {
            next_pos = reject_pos;
            pitch_srvo.setPosition(next_pos);
        }
        if (curr_pos > reject_pos-0.05 && curr_pos < reject_pos + 0.05) {
            next_pos = intake_pos;
            pitch_srvo.setPosition(next_pos);
        }
    }
    public void intake_pod(Servo intake_pod) {
		double intake_pos = 0.22;
        double transfer_pos = 0.65;
        double curr_pos = intake_arm.getPosition();
        double next_pos;
        if (curr_pos > intake_pos-0.05 && curr_pos < intake_pos + 0.05) {
            next_pos = transfer_pos;
            intake_arm.setPosition(next_pos);
        }
        if (curr_pos > transfer_pos-0.05 && curr_pos < transfer_pos + 0.05) {
            next_pos = intake_pos;
            intake_arm.setPosition(next_pos);
        }
    }
	public void claw(Servo claw_srvo) {
		double open_pos = 0.22;
		double close_pos = 0.65;
		double curr_pos = claw_srvo.getPosition();
		if (curr_pos > open_pos-0.05 && curr_pos < open_pos + 0.05) {
            next_pos = close_pos;
            claw_srvo.setPosition(next_pos);
        }
        if (curr_pos > close_pos-0.05 && curr_pos < close_pos + 0.05) {
            next_pos = open_pos;
            claw_srvo.setPosition(next_pos);
        }
	}
}
