# Tier 2 — Moderate Facade Tasks (9–16)

Eight independent Java console examples focused on state, validation, and clear results.

| Task | Folder | Facade |
|---|---|---|
| 9 | `Task_9_Library_Book_Checkout` | `LibraryCheckoutFacade` |
| 10 | `Task_10_Hotel_Check_In` | `HotelReceptionFacade` |
| 11 | `Task_11_Airport_Check_In` | `AirportCheckInFacade` |
| 12 | `Task_12_Washing_Machine` | `WashingMachineFacade` |
| 13 | `Task_13_ATM_Withdrawal` | `AtmFacade` |
| 14 | `Task_14_Gym_Onboarding` | `GymOnboardingFacade` |
| 15 | `Task_15_Restaurant_Kitchen` | `KitchenFacade` |
| 16 | `Task_16_Game_Level_Loader` | `GameLevelFacade` |

## Run one task

Open one task folder in Terminal and run:

```bash
javac *.java
java Main
```

Each task has its own `Main.java`, uses the default package, and compiles independently.

## Notes

- Task 11 returns `null` and prints a reason when check-in validation fails.
- Task 13 uses sample card numbers and PINs for demonstration only. It masks card numbers in audit output.
- Task 15 reserves the complete ingredient list only after checking every required item.
- Task 16 validates the save slot before unloading an already loaded level.
- All subsystems are in-memory simulations; no external services or hardware are required.
