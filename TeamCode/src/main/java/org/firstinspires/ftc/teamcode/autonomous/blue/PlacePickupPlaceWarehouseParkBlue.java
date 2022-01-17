package org.firstinspires.ftc.teamcode.autonomous.blue;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.RobotConfig;
import org.firstinspires.ftc.teamcode.autonomous.AutoBase;
import org.firstinspires.ftc.teamcode.commands.autonav.tasks.ArmTask;
import org.firstinspires.ftc.teamcode.commands.autonav.tasks.CustomTask;
import org.firstinspires.ftc.teamcode.commands.autonav.tasks.MovementTask;
import org.firstinspires.ftc.teamcode.commands.autonav.tasks.ReadBarcodeTask;
import org.firstinspires.ftc.teamcode.commands.autonav.tasks.WaitTask;
import org.firstinspires.ftc.teamcode.utilities.RobotSide;
//DONE
@Autonomous(name="Place, Pickup Another, and Warehouse Park Blue", group="Red")
public class PlacePickupPlaceWarehouseParkBlue extends AutoBase {
    @Override
    public void Run() {
        autoNav.AddTask(new ReadBarcodeTask());
        autoNav.AddTask(new CustomTask() {
            @Override
            public void begin() {
                telemetry.log().add("what did we see?");
                telemetry.log().add(autoNav.computerVision.GetBarcodeStatus().toString());

                switch (autoNav.computerVision.GetBarcodeStatus()) {
                    case One:
                        FirstPosition();
                        Pickup();
                        ThirdPosition();
                        //dropNewFreightAndPark();
                        break;
                    case Two:
                        SecondPosition();
                        Pickup();
                        ThirdPosition();
                        //dropNewFreightAndPark();
                        break;
                    case Three:
                        ThirdPosition();
                        Pickup();
                        ThirdPosition();
                        //dropNewFreightAndPark();
                        break;
                }

                Park();
            }

            @Override
            public boolean execute() { return true; }

            @Override
            public void stop() { }
        });


        autoNav.AddTask(new ArmTask(1000, null));
        autoNav.AddTask(new MovementTask(1200, 90, 0, 0.5f));
        autoNav.AddTask(new MovementTask(1700,-1, -0.5f));
    }

    public void Park() {
        telemetry.log().add("started PARK");
        autoNav.AddTask(new ArmTask(1000, null));
        autoNav.AddTask(new MovementTask(1700,-1, 0.5f));
        autoNav.AddTask(new MovementTask(2000, 0, 0, 0.75f));
        /*autoNav.AddTask(new MovementTask(1300, 90, 0, 0.5f));

        // Park

        autoNav.AddTask(new MovementTask(2000, 10, 0, 0.5f));
        autoNav.AddTask(new MovementTask(1850, -90, 0, 0.5f));
        autoNav.AddTask(new MovementTask(1000, 0, 0, 0.5f));
        autoNav.AddTask(new MovementTask(1250,1, 0.5f));
        autoNav.AddTask(new MovementTask(1250, -90, 0, 0.5f));*/
    }

    public void Pickup() {
        telemetry.log().add("started PICKUP");
        autoNav.AddTask(new ArmTask(1200, null));
        autoNav.AddTask(new MovementTask(1700,-1, 0.5f));
        autoNav.AddTask(new MovementTask(1300, -90, 0, 0.5f));

        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.intake, RobotConfig.intakeSpeed));
        autoNav.AddTask(new MovementTask(1500, -10, 0, 1f));
        autoNav.AddTask(new ArmTask(1200, RobotConfig.intakeSpeed));
        autoNav.AddTask(new MovementTask(1300, -170, 0, 1f));

        //Get ready to place again

        autoNav.AddTask(new ArmTask(1200, null));
        autoNav.AddTask(new MovementTask(1200, 90, 0, 0.5f));
        autoNav.AddTask(new MovementTask(1700,-1, -0.5f));
    }

    public void FirstPosition() {
        telemetry.log().add("started FIRST");
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontThird, RobotConfig.intakeSpeed));
        autoNav.AddTask(new WaitTask(500));
        autoNav.AddTask(new ArmTask(null, 0.0));
        autoNav.AddTask(new MovementTask(625, 0, 0, 0.25f));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontThird, -RobotConfig.outtakeSpeed));
        autoNav.AddTask(new WaitTask(2000));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontThird, 0.0));
        autoNav.AddTask(new MovementTask(625, -180, 0, 0.25f)); //0050
        //autoNav.AddTask(new MovementTask(2850, 90, 0, 0.5f));
    }

    public void SecondPosition() {
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontSecond, RobotConfig.intakeSpeed));
        autoNav.AddTask(new WaitTask(500));
        autoNav.AddTask(new ArmTask(null, 0.0));
        autoNav.AddTask(new MovementTask(850, 0, 0, 0.25f));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontSecond, -RobotConfig.outtakeSpeed));
        autoNav.AddTask(new WaitTask(2000));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontSecond, 0.0));
        autoNav.AddTask(new MovementTask(850, -180, 0, 0.25f));
        //autoNav.AddTask(new MovementTask(2850, 90, 0, 0.5f));
    }

    public void ThirdPosition() {
        telemetry.log().add("started THIRD");
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontFirst, RobotConfig.intakeSpeed));
        autoNav.AddTask(new WaitTask(500));
        autoNav.AddTask(new ArmTask(null, 0.0));
        autoNav.AddTask(new MovementTask(1350, 0, 0, 0.25f));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontFirst, -RobotConfig.outtakeSpeed));
        autoNav.AddTask(new WaitTask(2000));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontFirst, 0.0));
        autoNav.AddTask(new MovementTask(1350, -180, 0, 0.25f));
        //autoNav.AddTask(new MovementTask(2850, 90, 0, 0.5f));
    }
    public void dropNewFreightAndPark() {
        telemetry.log().add("started FINAL");
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontFirst, RobotConfig.intakeSpeed));
        autoNav.AddTask(new WaitTask(500));
        autoNav.AddTask(new ArmTask(null, 0.0));
        autoNav.AddTask(new MovementTask(1350, 0, 0, 0.25f));
        autoNav.AddTask(new ArmTask(RobotConfig.ArmPresets.frontFirst, -RobotConfig.outtakeSpeed));
        autoNav.AddTask(new WaitTask(1500));
        autoNav.AddTask(new MovementTask(1500, 0, 0,-0.25f));
    }


    @Override
    public RobotSide GetSide() {
        return RobotSide.Blue;
    }
}