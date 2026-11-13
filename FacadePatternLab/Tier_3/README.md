# Tier 3 — Advanced Facade Tasks (17–21)

Five independent Java console projects that practice rollback, immutable stage results,
priority-based resource allocation, protected energy reserves, and coordinated reservations.

| Folder | Main idea |
| --- | --- |
| `Task_17_Bank_Wire_Transfer` | Cross-currency transfer and exact debit rollback |
| `Task_18_CICD_Deployment_Pipeline` | Ordered deployment stages and previous-build preservation |
| `Task_19_Hospital_Emergency_Admission` | Triage priority, bed allocation, medication, and paging |
| `Task_20_Smart_Microgrid_Balancer` | Solar-first dispatch, battery reserve, grid cap, and load shedding |
| `Task_21_Multi_Campus_Event_Logistics` | Workshop quota and shuttle reservation with coordinated release |

## Run one task

Open the task folder in a terminal and run:

```bash
javac *.java
java Main
```

Task 18 and the structured result examples use Java records, so use Java 16 or newer
(Java 17 is recommended). Each task has its own `Main.java` and default package, so
compile one task folder at a time.

The hospital thresholds, financial services, energy dispatch, and deployment services
are simplified classroom simulations, not production systems. Task 20 assumes each
`balancePower` call represents a one-hour dispatch, allowing the battery's kWh usage
to be compared directly with kW demand for that interval.
